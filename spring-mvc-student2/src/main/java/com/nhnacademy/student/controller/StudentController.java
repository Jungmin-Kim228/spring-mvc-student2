package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.domain.StudentModifierRequest;
import com.nhnacademy.student.exception.StudentNotFoundException;
import com.nhnacademy.student.exception.ValidationFailedException;
import com.nhnacademy.student.repository.StudentRepository;
import com.nhnacademy.student.validator.StudentModifierRequestValidator;
import java.util.Map;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    private final StudentModifierRequestValidator validator;

    public StudentController(StudentRepository studentRepository, StudentModifierRequestValidator validator) {
        this.studentRepository = studentRepository;
        this.validator = validator;
    }

    // @ModelAttribute가 메서드에 명시되어 있어서
    // 다른 메서드들이 실행되기전에 가장 먼저 실행되는 메서드
    @ModelAttribute("student")
    public Student getStudent(@PathVariable("studentId") long studentId) {
        Student student = studentRepository.getStudent(studentId);
        if (Objects.isNull(student)) {
            throw new StudentNotFoundException();
        }
        return student;
    }
    // 여기서 return 하는 student값이 student라는 ModelAttribute로
    // 저장되고 다른 메서드들이 참조할 수 있음

    @GetMapping("/{studentId}")
    public String viewStudent(@ModelAttribute("student") Student student, ModelMap modelMap) {

        modelMap.put("student", student);
        return "thymeleaf/studentView";
    }

    @GetMapping(value = "/{studentId}", params = "hideScore=yes")
    public String hideScoreConvert(@ModelAttribute Student student, Model model) {
        model.addAttribute("student", Student.constructMaskedStudent(student));
        return "thymeleaf/studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@ModelAttribute("student") Student student,
                                    Model model) {
        model.addAttribute("student", student);
        return "thymeleaf/studentModify";
    }

    // binding = false 해주면 ModelAttribute가 사용자 입력을 받지 않음
    @PostMapping("/{studentId}/modify")
    public String modifyUser(@ModelAttribute(value = "student", binding = false) Student student,
                             @Valid @ModelAttribute StudentModifierRequest studentRequest,
                             BindingResult bindingResult,
                             Map<String, Student> map) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setScore(studentRequest.getScore());
        student.setComment(studentRequest.getComment());

        studentRepository.modify(student);

        map.put("student", student);
        return "thymeleaf/studentView";
    }

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleStudentNotFoundException(StudentNotFoundException ex, Model model) {
        model.addAttribute("exception", ex);
        return "error";
    }

    @InitBinder("studentModifierRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }
}

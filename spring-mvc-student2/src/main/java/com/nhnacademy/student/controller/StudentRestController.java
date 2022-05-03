package com.nhnacademy.student.controller;

import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.domain.StudentRegisterRequest;
import com.nhnacademy.student.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentRestController {
    private final StudentRepository studentRepository;

    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ResponseBody
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") long studentId) {
        return ResponseEntity.ok()
                             .header("X-123", "abc123")
                             .body(studentRepository.getStudent(studentId));
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody StudentRegisterRequest studentRequest) {
        studentRepository.register(studentRequest.getName(),
            studentRequest.getEmail(),
            studentRequest.getScore(),
            studentRequest.getComment());
    }
}

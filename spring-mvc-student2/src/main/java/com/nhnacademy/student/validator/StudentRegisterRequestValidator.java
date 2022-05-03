package com.nhnacademy.student.validator;

import com.nhnacademy.student.domain.StudentRegisterRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentRegisterRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return StudentRegisterRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "name is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "", "comment is empty");

        StudentRegisterRequest request = (StudentRegisterRequest) target;
        String comment = request.getComment();
        if (comment.length() > 200) {
            errors.rejectValue("comment", "", "comment max length is 200");
        }
    }
}

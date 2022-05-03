package com.nhnacademy.student.controller;

import com.nhnacademy.student.exception.ValidationFailedException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler(ValidationFailedException.class)
    public String handleValidationFailedException(ValidationFailedException ex, ModelMap modelMap) {
        modelMap.put("exception", ex);
        return "error";
    }
}

package com.nhnacademy.student.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class StudentModifierRequest {
    @NotBlank
    String name;

    @Email
    String email;

    @Min(-1) // Todo: for mask
    @Max(100)
    int score;

    @NotBlank
    @Length(min = 1, max = 200)
    String comment;
}

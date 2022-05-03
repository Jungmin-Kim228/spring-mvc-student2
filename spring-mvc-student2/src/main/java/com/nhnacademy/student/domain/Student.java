package com.nhnacademy.student.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    private long id;
    private String name;
    private String email;
    private int score;
    private String comment;

    private Student(String name, String email, int score, String comment) {
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    public Student(long id, String name, String email, int score, String comment) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.score = score;
        this.comment = comment;
    }

    public static Student create(String name, String email, int score, String comment) {
        return new Student(name, email, score, comment);
    }

    private static final int SCORE_MASK = -1; // Todo
    private static final String COMMENT_MASK = "***************";

    public static Student constructMaskedStudent(Student student) {
        return new Student(student.getId(), student.getName(), student.getEmail(), SCORE_MASK, COMMENT_MASK);
    }
}

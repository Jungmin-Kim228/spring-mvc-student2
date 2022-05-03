package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.stereotype.Repository;


@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {
    private final Map<Long, Student> students = new HashMap<>();

    @Override
    public boolean exists(long id) {
        return students.containsKey(id);
    }

    @Override
    public Student register(String name, String email, int score, String comment) {
        long id = students.keySet()
            .stream()
            .max(Comparator.comparing(Function.identity()))
            .map(l -> l + 1)
            .orElse(1L);

        Student student = Student.create(name, email, score, comment);
        student.setId(id);

        students.put(id, student);

        return student;
    }

    @Override
    public Student getStudent(long id) {
        return exists(id) ? students.get(id) : null;
    }

    @Override
    public void modify(Student student) {
        Student dbStudent = getStudent(student.getId());

        dbStudent.setName(student.getName());
        dbStudent.setEmail(student.getEmail());
        dbStudent.setScore(student.getScore());
        dbStudent.setComment(student.getComment());
    }
}

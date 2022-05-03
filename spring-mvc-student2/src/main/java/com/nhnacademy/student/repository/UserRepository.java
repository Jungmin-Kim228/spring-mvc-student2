package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.User;

public interface UserRepository {
    boolean exists(String id);
    boolean matches(String id, String password);

    User getUser(String id);

    User addUser(String id, String password);

    User addUser(String id, String password, int age, String name);

    void modify(User user);

}

package org.java.financespring.service;

import org.java.financespring.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User edit(Long id, User user);

    void remove(Long id);

    List<User> findAll();

    User findById(Long id);
}

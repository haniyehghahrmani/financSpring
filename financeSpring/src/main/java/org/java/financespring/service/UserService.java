package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    User edit(Long id, User user);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<User> findUserByIdAndDeletedFalse(Long id) throws NoContentException;

    List<User> findAll();

    User findById(Long id);
}

package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role edit(Long id, Role role);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Role> findAll();

    Role findById(Long id);
}

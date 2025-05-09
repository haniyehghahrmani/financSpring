package org.java.financespring.service;

import org.java.financespring.model.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Role edit(Long id, Role role);

    void remove(Long id);

    List<Role> findAll();

    Role findById(Long id);
}

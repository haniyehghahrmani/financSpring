package org.java.financespring.service;

import org.java.financespring.model.Permission;

import java.util.List;

public interface PermissionService {

    Permission save(Permission permission);

    Permission edit(Long id, Permission permission);

    void remove(Long id);

    List<Permission> findAll();

    Permission findById(Long id);
}

package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Permission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    Permission save(Permission permission);

    Permission edit(Long id, Permission permission);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<Permission> findPermissionByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Permission> findAll();

    Permission findById(Long id);
}

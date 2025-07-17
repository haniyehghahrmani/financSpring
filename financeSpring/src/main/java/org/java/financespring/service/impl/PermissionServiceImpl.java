package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.Permission;
import org.java.financespring.repository.h2repo.PermissionRepository;
import org.java.financespring.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    
    private final PermissionRepository repository;

    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Permission save(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public Permission edit(Long id, Permission permission) throws NoContentException {
        Permission existingPermission = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Permission Was Found with id " + id + " To Update!")
                );
        existingPermission.setName(permission.getName());
        existingPermission.setDescription(permission.getDescription());
        existingPermission.setEditing(true);

        return repository.saveAndFlush(existingPermission);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Permission logicalRemove(Long id) throws NoContentException {
        repository.findPermissionByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Permission Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<Permission> findPermissionByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Permission> optional = repository.findPermissionByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Permission Was Found with id : " + id);
        }
    }

    @Override
    public List<Permission> findAll() {
        return repository.findAll();
    }

    @Override
    public Permission findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No permission found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

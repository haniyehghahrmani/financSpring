package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Role;
import org.java.financespring.repository.RoleRepository;
import org.java.financespring.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role edit(Long id, Role role) throws NoContentException {
        Role existingRole = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Role Was Found with id " + id + " To Update!")
                );
        existingRole.setRoleName(role.getRoleName());
        existingRole.setUsers(role.getUsers());
        existingRole.setPermissions(role.getPermissions());

        return repository.saveAndFlush(existingRole);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findRoleByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Role Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<Role> findRoleByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Role> optional = repository.findRoleByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Role Was Found with id : " + id);
        }
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No role found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

package org.java.financespring.service.impl;

import org.java.financespring.dto.UserDTO;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.User;
import org.java.financespring.repository.UserRepository;
import org.java.financespring.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User edit(Long id, User user) throws NoContentException {
        User existingUser = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active User Was Found with id " + id + " To Update!")
                );
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setStatus(user.isStatus());
        existingUser.setRole(user.getRole());
        existingUser.setPerson(user.getPerson());

        return repository.saveAndFlush(existingUser);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public User logicalRemove(Long id) throws NoContentException {
        repository.findUserByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active User Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<User> findUserByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<User> optional = repository.findUserByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active User Was Found with id : " + id);
        }
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream()
                .map(u -> new UserDTO(
                        u.getId(),
                        u.getUsername(),
                        u.getRole() != null ? u.getRole().getRoleName() : null,
                        u.getPerson() != null ? u.getPerson().getNationalId() : null))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        return repository.findById(id)
                .map(u -> new UserDTO(
                        u.getId(),
                        u.getUsername(),
                        u.getRole() != null ? u.getRole().getRoleName() : null,
                        u.getPerson() != null ? u.getPerson().getNationalId() : null))
                .orElse(null);
    }
}

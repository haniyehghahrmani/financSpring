package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.EmploymentType;
import org.java.financespring.repository.h2repo.EmploymentTypeRepository;
import org.java.financespring.service.EmploymentTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmploymentTypeServiceImpl implements EmploymentTypeService {

    private final EmploymentTypeRepository repository;

    public EmploymentTypeServiceImpl(EmploymentTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmploymentType save(EmploymentType employmentType) {
        return repository.save(employmentType);
    }

    @Override
    public EmploymentType edit(Long id, EmploymentType employmentType) throws NoContentException {
        EmploymentType existingEmploymentType = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active EmploymentType Was Found with id " + id + " To Update!")
                );
        existingEmploymentType.setName(employmentType.getName());

        return repository.saveAndFlush(existingEmploymentType);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public EmploymentType logicalRemove(Long id) throws NoContentException {
        repository.findEmploymentTypeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active EmploymentType Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<EmploymentType> findEmploymentTypeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<EmploymentType> optional = repository.findEmploymentTypeByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active EmploymentType Was Found with id : " + id);
        }
    }

    @Override
    public List<EmploymentType> findAll() {
        return repository.findAll();
    }

    @Override
    public EmploymentType findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No employmentType found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

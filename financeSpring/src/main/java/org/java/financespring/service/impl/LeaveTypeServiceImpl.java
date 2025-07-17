package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.h2model.LeaveType;
import org.java.financespring.repository.h2repo.LeaveTypeRepository;
import org.java.financespring.service.LeaveTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository repository;

    public LeaveTypeServiceImpl(LeaveTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveType save(LeaveType leaveType) {
        return repository.save(leaveType);
    }

    @Override
    public LeaveType edit(Long id, LeaveType leaveType) throws NoContentException {
        LeaveType existingLeaveType = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active LeaveType Was Found with id " + id + " To Update!")
                );
        existingLeaveType.setName(leaveType.getName());

        return repository.saveAndFlush(existingLeaveType);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findLeaveTypeByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active LeaveType Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<LeaveType> findLeaveTypeByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<LeaveType> optional = repository.findLeaveTypeByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active LeaveType Was Found with id : " + id);
        }
    }

    @Override
    public List<LeaveType> findAll() {
        return repository.findAll();
    }

    @Override
    public LeaveType findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No leaveType found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

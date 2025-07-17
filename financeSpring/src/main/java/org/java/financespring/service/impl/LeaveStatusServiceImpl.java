package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.LeaveStatus;
import org.java.financespring.repository.pgrepo.LeaveStatusRepository;
import org.java.financespring.service.LeaveStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveStatusServiceImpl implements LeaveStatusService {
    
    private final LeaveStatusRepository repository;

    public LeaveStatusServiceImpl(LeaveStatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveStatus save(LeaveStatus leaveStatus) {
        return repository.save(leaveStatus);
    }

    @Override
    public LeaveStatus edit(Long id, LeaveStatus leaveStatus) throws NoContentException {
        LeaveStatus existingLeaveStatus = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active LeaveStatus Was Found with id " + id + " To Update!")
                );
        existingLeaveStatus.setName(leaveStatus.getName());

        return repository.saveAndFlush(existingLeaveStatus);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findLeaveStatusByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active LeaveStatus Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<LeaveStatus> findLeaveStatusByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<LeaveStatus> optional = repository.findLeaveStatusByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active LeaveStatus Was Found with id : " + id);
        }
    }

    @Override
    public List<LeaveStatus> findAll() {
        return repository.findAll();
    }

    @Override
    public LeaveStatus findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No leaveStatus found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

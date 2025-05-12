package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.LeaveRequest;
import org.java.financespring.repository.LeaveRequestRepository;
import org.java.financespring.service.LeaveRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    
    private final LeaveRequestRepository repository;

    public LeaveRequestServiceImpl(LeaveRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public LeaveRequest save(LeaveRequest leaveRequest) {
        return repository.save(leaveRequest);
    }

    @Override
    public LeaveRequest edit(Long id, LeaveRequest leaveRequest) throws NoContentException {
        LeaveRequest existingLeaveRequest = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active LeaveRequest Was Found with id " + id + " To Update!")
                );
        existingLeaveRequest.setEmployee(leaveRequest.getEmployee());
        existingLeaveRequest.setStartDate(leaveRequest.getStartDate());
        existingLeaveRequest.setEndDate(leaveRequest.getEndDate());
        existingLeaveRequest.setType(leaveRequest.getType());
        existingLeaveRequest.setReason(leaveRequest.getReason());
        existingLeaveRequest.setApproved(leaveRequest.isApproved());
        existingLeaveRequest.setRequestDate(leaveRequest.getRequestDate());
        existingLeaveRequest.setApprovalDate(leaveRequest.getApprovalDate());
        existingLeaveRequest.setStatus(leaveRequest.getStatus());

        return repository.saveAndFlush(existingLeaveRequest);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findLeaveRequestByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active LeaveRequest Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<LeaveRequest> findLeaveRequestByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<LeaveRequest> optional = repository.findLeaveRequestByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active LeaveRequest Was Found with id : " + id);
        }
    }

    @Override
    public List<LeaveRequest> findAll() {
        return repository.findAll();
    }

    @Override
    public LeaveRequest findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No leaveRequest found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

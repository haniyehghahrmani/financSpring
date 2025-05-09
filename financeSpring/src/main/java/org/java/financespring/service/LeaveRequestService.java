package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.LeaveRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest save(LeaveRequest leaveRequest);

    LeaveRequest edit(Long id, LeaveRequest leaveRequest);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<LeaveRequest> findAll();

    LeaveRequest findById(Long id);
}

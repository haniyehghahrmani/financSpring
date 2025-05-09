package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.LeaveStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeaveStatusService {

    LeaveStatus save(LeaveStatus leaveStatus);

    LeaveStatus edit(Long id, LeaveStatus leaveStatus);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<LeaveStatus> findAll();

    LeaveStatus findById(Long id);
}

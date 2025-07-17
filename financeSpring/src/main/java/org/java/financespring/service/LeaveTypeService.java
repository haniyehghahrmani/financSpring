package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.LeaveType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface LeaveTypeService {

    LeaveType save(LeaveType leaveType);

    LeaveType edit(Long id, LeaveType leaveType) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<LeaveType> findLeaveTypeByIdAndDeletedFalse(Long id) throws NoContentException;

    List<LeaveType> findAll();

    LeaveType findById(Long id);
}

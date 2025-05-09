package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.LeaveType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LeaveTypeService {

    LeaveType save(LeaveType leaveType);

    LeaveType edit(Long id, LeaveType leaveType);

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<LeaveType> findAll();

    LeaveType findById(Long id);
}

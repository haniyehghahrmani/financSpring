package org.java.financespring.service;

import org.java.financespring.model.LeaveType;

import java.util.List;

public interface LeaveTypeService {

    LeaveType save(LeaveType leaveType);

    LeaveType edit(Long id, LeaveType leaveType);

    void remove(Long id);

    List<LeaveType> findAll();

    LeaveType findById(Long id);
}

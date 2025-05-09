package org.java.financespring.service;

import org.java.financespring.model.LeaveStatus;

import java.util.List;

public interface LeaveStatusService {

    LeaveStatus save(LeaveStatus leaveStatus);

    LeaveStatus edit(Long id, LeaveStatus leaveStatus);

    void remove(Long id);

    List<LeaveStatus> findAll();

    LeaveStatus findById(Long id);
}

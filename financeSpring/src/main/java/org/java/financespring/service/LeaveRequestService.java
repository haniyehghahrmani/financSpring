package org.java.financespring.service;

import org.java.financespring.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveRequest save(LeaveRequest leaveRequest);

    LeaveRequest edit(Long id, LeaveRequest leaveRequest);

    void remove(Long id);

    List<LeaveRequest> findAll();

    LeaveRequest findById(Long id);
}

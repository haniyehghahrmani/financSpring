package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Attendance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AttendanceService {

    Attendance save(Attendance attendance);

    Attendance edit(Long id, Attendance attendance) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<Attendance> findAttendanceByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Attendance> findAll();

    Attendance findById(Long id);
}
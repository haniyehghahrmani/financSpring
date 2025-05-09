package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Attendance;

import java.util.List;

public interface AttendanceService {

    Attendance save(Attendance attendance);

    Attendance edit(Long id, Attendance attendance) throws NoContentException;

    void remove(Long id);

    List<Attendance> findAll();

    Attendance findById(Long id);
}
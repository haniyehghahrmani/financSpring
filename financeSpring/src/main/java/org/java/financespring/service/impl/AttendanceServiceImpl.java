package org.java.financespring.service.impl;

import jakarta.transaction.Transactional;
import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Attachment;
import org.java.financespring.model.Attendance;
import org.java.financespring.repository.AttendanceRepository;
import org.java.financespring.service.AttendanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceServiceImpl(AttendanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Attendance save(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public Attendance edit(Long id, Attendance attendance) throws NoContentException {
        Attendance existingAttendance = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Attendance Was Found with id " + id + " To Update!")
                );
        existingAttendance.setEmployee(attendance.getEmployee());
        existingAttendance.setDate(attendance.getDate());
        existingAttendance.setCheckInTime(attendance.getCheckInTime());
        existingAttendance.setCheckOutTime(attendance.getCheckOutTime());
        existingAttendance.setAbsent(attendance.isAbsent());
        existingAttendance.setOnLeave(attendance.isOnLeave());
        existingAttendance.setOvertimeHours(attendance.getOvertimeHours());
        existingAttendance.setDelayMinutes(attendance.getDelayMinutes());
        existingAttendance.setNote(attendance.getNote());

        return repository.saveAndFlush(existingAttendance);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findAttendanceByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Attendance Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<Attendance> findAttendanceByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Attendance> optional = repository.findAttendanceByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Attendance Was Found with id : " + id);
        }
    }

    @Override
    public List<Attendance> findAll() {
        return repository.findAll();
    }

    @Override
    public Attendance findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No attendance found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}

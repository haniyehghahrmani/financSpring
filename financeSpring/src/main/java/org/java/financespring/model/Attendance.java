package org.java.financespring.model;

import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "AttendanceEntity")
@Table(name = "attendances")
public class Attendance {

    @Id
    @SequenceGenerator(name = "attendanceSeq", sequenceName = "attendance_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendanceSeq")
    @Column(name = "a_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "a_employee_id", nullable = false)
    @NotNull(message = "Employee should not be null")
    private Employee employee;

    @Column(name = "a_date", nullable = false)
    @PastOrPresent(message = "Invalid Date")
    @NotNull(message = "date should not be null")
    private LocalDate date; // تاریخ حضور

    @Transient
    private String faDate;

    @Column(name = "a_check_in_time", nullable = false)
    @PastOrPresent(message = "Invalid check in Time")
    @NotNull(message = "Check in time should not be null")
    private LocalTime checkInTime;  // زمان ورود

    @Column(name = "a_check_out_time", nullable = false)
    @PastOrPresent(message = "Invalid check out Time")
    @NotNull(message = "Check out time should not be null")
    private LocalTime checkOutTime; // زمان خروج

    @Column(name = "a_is_absent", nullable = false)
    @NotNull(message = "Absent flag must not be null")
    private boolean isAbsent = false;

    @Column(name = "a_is_on_leave", nullable = false)
    @NotNull(message = "Leave flag must not be null")
    private boolean isOnLeave = false;

    //میزان اضافه‌کاری
    @Column(name = "a_overtime_hours", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Overtime hours must be non-negative")
    @Digits(integer = 4, fraction = 2, message = "Overtime hours format is invalid")
    private double overtimeHours;

    @Column(name = "a_delay_minutes", nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Delay minutes must be non-negative")
    @Digits(integer = 4, fraction = 2, message = "Delay minutes format is invalid")
    private double delayMinutes;

    @Column(name = "a_note", length = 1000)
    @Size(max = 1000, message = "Note can have at most 1000 characters")
    private String note;

    public String getFaDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(date)));
    }

    public void setFaDate(String faDate) {
        this.date = LocalDate.from(PersianDate.parse(faDate).toGregorian().atStartOfDay());
    }
}
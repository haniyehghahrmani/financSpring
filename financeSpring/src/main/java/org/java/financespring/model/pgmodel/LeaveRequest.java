package org.java.financespring.model.pgmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "LeaveRequestEntity")
@Table(name = "leave_requests")
public class LeaveRequest extends Base {

    @Id
    @SequenceGenerator(name = "leaveRequestSeq", sequenceName = "leave_request_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaveRequestSeq")
    @Column(name = "l_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @NotNull(message = "Employee must not be null")
    @JsonIgnore
    private Employee employee;

    @JsonProperty("employeeCode")
    private String getEmployeeCode() {
        return employee != null ? employee.getEmployeeCode() : null;
    }

    @Column(name = "l_start_date", nullable = false)
    @NotNull(message = "Start date must not be null")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;

    @Transient
    private String faStartDate;

    @Column(name = "l_end_date", nullable = false)
    @NotNull(message = "End date must not be null")
    @FutureOrPresent(message = "End date cannot be in the past")
    private LocalDate endDate;

    @Transient
    private String faEndDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull(message = "Leave type must not be null")
    private LeaveType type; // نوع مرخصی

    @Column(name = "l_reason", length = 1000)
    @Size(max = 1000, message = "Reason must be at most 1000 characters")
    private String reason;

    //تأیید شده؟
    @Column(name = "l_is_approved", nullable = false)
    private boolean approved;

    @Column(name = "l_request_date")
    @PastOrPresent(message = "Request date cannot be in the future")
    private LocalDate requestDate;

    @Column(name = "l_approval_date")
    @PastOrPresent(message = "Approval date cannot be in the future")
    private LocalDate approvalDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull(message = "Status must not be null")
    private LeaveStatus status;

    public String getFaStartDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(startDate)));
    }

    public void setFaStartDate(String faStartDate) {
        this.startDate = LocalDate.from(PersianDate.parse(faStartDate).toGregorian().atStartOfDay());
    }

    public String getFaEndDate() {
        return String.valueOf(PersianDate.fromGregorian(LocalDate.from(endDate)));
    }

    public void setFaEndDate(String faEndDate) {
        this.endDate = LocalDate.from(PersianDate.parse(faEndDate).toGregorian().atStartOfDay());
    }
}
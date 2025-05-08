package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "LeaveTypeEntity")
@Table(name = "LeaveTypeTbl")
@Cacheable
public class LeaveType {

    @Id
    @SequenceGenerator(name = "leaveTypeSeq", sequenceName = "leaveType_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "leaveTypeSeq")
    @Column(name = "leaveType_id")
    private Long id;

    @Column(name = "leaveType_name", length = 200, nullable = false)
    @NotBlank(message = "type name should not be blank")
    @Size(min = 3, max = 200, message = "type name must be between 3 and 200 characters")
    private String name;
}
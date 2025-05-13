package org.java.financespring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "PermissionEntity")
@Table(name = "permissions")
public class Permission extends Base {

    @Id
    @SequenceGenerator(name = "permissionSeq", sequenceName = "permission_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissionSeq")
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name", columnDefinition = "NVARCHAR2(50)", unique = true)
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid permission name")
    @Size(min = 3, max = 50, message = "Permission name must be between 3 and 50 characters")
    @NotBlank(message = "Permission name should not be null or empty")
    private String name;

    @Column(name = "p_description", columnDefinition = "NVARCHAR2(255)")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @Column(name = "p_created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "p_updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
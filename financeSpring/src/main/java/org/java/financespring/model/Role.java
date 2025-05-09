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

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "RoleEntity")
@Table(name = "roles")
public class Role extends Base {

    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "role_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    @Column(name = "r_id")
    private Long id;

    @Column(name = "r_name", columnDefinition = "NVARCHAR2(50)", unique = true)
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid role name")
    @Size(min = 3, max = 50, message = "Role name must be between 3 and 50 characters")
    @NotBlank(message = "Role name should not be null or empty")
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<User> users;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;

    @Column(name = "r_created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "r_updated_at")
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

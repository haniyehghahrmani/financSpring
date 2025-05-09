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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "UserEntity")
@Table(name = "users")
public class User extends Base {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @Column(name = "u_id")
    private Long id;

    @Column(name = "u_userName", columnDefinition = "NVARCHAR2(50)", unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]{3,30}$", message = "Invalid username")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    @NotBlank(message = "Username should not be null or empty")
    private String username;

    @Column(name = "u_password", columnDefinition = "NVARCHAR2(100)")
    @Pattern(regexp = "^[a-zA-Z0-9!@#\\$%^&*()_+=-]{8,100}$", message = "Invalid password format")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @NotBlank(message = "Password should not be null or empty")
    private String password;

    @Column(name = "u_status")
    private boolean status = true;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "u_role_id")
    private Role role;

    @Column(name = "u_created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "u_updated_at")
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
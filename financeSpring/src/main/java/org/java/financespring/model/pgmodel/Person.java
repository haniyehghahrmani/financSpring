package org.java.financespring.model.pgmodel;

import com.github.mfathi91.time.PersianDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.java.financespring.model.h2model.Base;
import org.java.financespring.model.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity(name = "PersonEntity")
@Table(name = "Persons")
public class Person extends Base {

    @Id
    @SequenceGenerator(name = "personSeq", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    @Column(name = "p_id")
    private Long id;

    @Column(name = "p_name", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String name;

    @Column(name = "p_lastname", columnDefinition = "NVARCHAR2(50)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,50}$", message = "Invalid Family")
    @Size(min = 3, max = 50, message = "Last Name must be between 3 and 50 characters")
    @NotBlank(message = "Should Not Be Null")
    private String lastname;

    @Column(name = "p_national_id", length = 10, unique = true)
    @Pattern(regexp = "^[0-9]{1,10}$", message = "Invalid National ID")
    @Size(min = 1, max = 10, message = " National ID must be between 1 and 10 characters")
    @NotBlank(message = "Should Not Be Null")
    private String nationalId;

    @Column(name = "p_birthdate")
    @Past(message = "Invalid Birth Date")
    @NotNull(message = "Should Not Be Null")
    private LocalDate birthdate;

    @Transient
    private String faBirthdate;

    @Column(name = "p_phoneNumber", length = 11, unique = true)
    @Pattern(regexp = "^[0-9]{1,11}$", message = "Invalid phoneNumber")
    @Size(min = 1, max = 11, message = " phoneNumber must be between 1 and 11 characters")
    @NotBlank(message = "Should Not Be Null")
    private String phoneNumber;

    @Column(name = "p_email", length = 100, unique = true)
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than or equal to 100 characters")
    private String email;

    @Column(name = "p_gender")
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Should Not Be Null")
    private Gender gender;

    public String getFaBirthdate() {
        if (birthdate != null) {
            return PersianDate.fromGregorian(birthdate).toString();
        }
        return null;
    }

    public void setFaBirthdate(String faBirthdate) {
        if (faBirthdate != null && !faBirthdate.isEmpty()) {
            this.birthdate = PersianDate.parse(faBirthdate).toGregorian();
        }
    }
}

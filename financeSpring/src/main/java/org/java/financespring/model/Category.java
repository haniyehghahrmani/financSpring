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

@Entity(name = "CategoryEntity")
@Table(name = "CategoryTbl")
@Cacheable
public class Category {

    @Id
    @SequenceGenerator(name = "categorySeq", sequenceName = "category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySeq")
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Name")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String categoryName;

    @Column(name = "category_description", columnDefinition = "NVARCHAR2(200)")
    @Pattern(regexp = "^[a-zA-Zآ-ی\\s]{3,200}$", message = "Invalid Description")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    @NotBlank(message = "Should Not Be Null")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_budget", nullable = false)
    private Budget budget;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "category_transaction", nullable = false)
    private Transaction transaction;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> category;
}
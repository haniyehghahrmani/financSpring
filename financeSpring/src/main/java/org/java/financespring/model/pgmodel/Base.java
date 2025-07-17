package org.java.financespring.model.pgmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@MappedSuperclass
public class Base {

    @Version
    @JsonIgnore
    private Long version;

    @JsonIgnore
    private boolean deleted = false;

    @JsonIgnore
    private boolean editing = false;
}
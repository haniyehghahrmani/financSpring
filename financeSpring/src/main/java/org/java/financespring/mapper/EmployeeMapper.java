package org.java.financespring.mapper;

import org.java.financespring.dto.EmployeeDTO;
import org.java.financespring.model.pgmodel.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "employmentType.name", target = "employmentTypeName")
    EmployeeDTO toDTO(Employee employee);
}

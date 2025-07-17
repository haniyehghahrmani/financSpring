package org.java.financespring.mapper;

import javax.annotation.processing.Generated;
import org.java.financespring.dto.EmployeeDTO;
import org.java.financespring.model.pgmodel.Employee;
import org.java.financespring.model.h2model.EmploymentType;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-06T23:06:24+0330",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO toDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO.EmployeeDTOBuilder<?, ?> employeeDTO = EmployeeDTO.builder();

        employeeDTO.employmentTypeName( employeeEmploymentTypeName( employee ) );
        employeeDTO.id( employee.getId() );
        employeeDTO.employeeCode( employee.getEmployeeCode() );
        employeeDTO.jobTitle( employee.getJobTitle() );
        employeeDTO.department( employee.getDepartment() );
        employeeDTO.hireDate( employee.getHireDate() );
        employeeDTO.baseSalary( employee.getBaseSalary() );

        return employeeDTO.build();
    }

    private String employeeEmploymentTypeName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        EmploymentType employmentType = employee.getEmploymentType();
        if ( employmentType == null ) {
            return null;
        }
        String name = employmentType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}

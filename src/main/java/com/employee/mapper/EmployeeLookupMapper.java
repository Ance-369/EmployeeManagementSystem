package com.employee.mapper;


import com.employee.dto.EmployeeLookupDTO;
import com.employee.entity.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeLookupMapper {
    EmployeeLookupDTO toLookupDTO(Employee employee);

    List<EmployeeLookupDTO> toLookupDTOs(List<Employee> employees);
}


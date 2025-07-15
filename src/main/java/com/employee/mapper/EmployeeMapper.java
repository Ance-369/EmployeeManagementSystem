package com.employee.mapper;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "department.id", target = "departmentId")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "manager.id", source = "managerId")
    @Mapping(target = "department.id", source = "departmentId")
    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDTOs(List<Employee> employees);

}

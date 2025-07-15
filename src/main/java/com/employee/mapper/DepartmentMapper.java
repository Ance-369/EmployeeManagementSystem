package com.employee.mapper;

import com.employee.dto.DepartmentDTO;
import com.employee.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface DepartmentMapper {

    @Mapping(target = "departmentHeadId", source = "departmentHead.id")
    DepartmentDTO toDTO(Department department);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "departmentHead.id", source = "departmentHeadId")
    Department toEntity(DepartmentDTO dto);
}





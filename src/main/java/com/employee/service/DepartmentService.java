package com.employee.service;

import com.employee.dto.DepartmentDTO;
import org.springframework.data.domain.Page;

public interface DepartmentService {

    DepartmentDTO createDepartment(DepartmentDTO dto);

    DepartmentDTO updateDepartment(Long id, DepartmentDTO dto);

    void deleteDepartment(Long id);

    Page<DepartmentDTO> getAllDepartments(int page, int size);

    DepartmentDTO getDepartmentById(Long id, boolean includeEmployees);
}


package com.employee.service.impl;


import com.employee.dto.DepartmentDTO;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.exception.BadRequestException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.DepartmentMapper;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import com.employee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final EmployeeRepository employeeRepo;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department dept = departmentMapper.toEntity(dto);
        if (dto.getDepartmentHeadId() != null) {
            Employee head = employeeRepo.findById(dto.getDepartmentHeadId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department head not found with ID: " + dto.getDepartmentHeadId()));
            dept.setDepartmentHead(head);
        }
        dept = departmentRepo.save(dept);
        return departmentMapper.toDTO(dept);
    }

    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department dept = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        dept.setName(dto.getName());
        dept.setCreationDate(dto.getCreationDate());
        if (dto.getDepartmentHeadId() != null) {
            Employee head = employeeRepo.findById(dto.getDepartmentHeadId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department head not found with ID: " + dto.getDepartmentHeadId()));
            dept.setDepartmentHead(head);
        } else {
            dept.setDepartmentHead(null);
        }
        dept = departmentRepo.save(dept);
        return departmentMapper.toDTO(dept);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department dept = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        List<Employee> employees = employeeRepo.findByDepartmentId(id);
        if (!employees.isEmpty()) {
            throw new BadRequestException("Cannot delete department with assigned employees.");
        }
        departmentRepo.delete(dept);
    }

    @Override
    public Page<DepartmentDTO> getAllDepartments(int page, int size) {
        return departmentRepo.findAll(PageRequest.of(page, size))
                .map(departmentMapper::toDTO);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id, boolean includeEmployees) {
        Department dept = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));
        DepartmentDTO dto = departmentMapper.toDTO(dept);
        if (includeEmployees && dept.getEmployees() != null) {
            dto.setEmployees(employeeMapper.toDTOs(dept.getEmployees()));
        } else {
            dto.setEmployees(null);
        }
        return dto;
    }
}

package com.employee.service.impl;


import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeLookupDTO;
import com.employee.dto.ReportingChainDTO;
import com.employee.entity.Department;
import com.employee.entity.Employee;
import com.employee.exception.BadRequestException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.mapper.EmployeeLookupMapper;
import com.employee.mapper.EmployeeMapper;
import com.employee.repository.DepartmentRepository;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepo;
    private final DepartmentRepository departmentRepo;
    private final EmployeeMapper employeeMapper;
    private final EmployeeLookupMapper lookupMapper;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee emp = employeeMapper.toEntity(dto);
        setReferences(emp, dto);
        emp = employeeRepo.save(emp);
        return employeeMapper.toDTO(emp);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
        updateEntityFields(emp, dto);
        setReferences(emp, dto);
        return employeeMapper.toDTO(employeeRepo.save(emp));
    }

    @Override
    public EmployeeDTO updateEmployeeDepartment(Long employeeId, Long departmentId) {
        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));
        Department dept = departmentRepo.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + departmentId));
        emp.setDepartment(dept);
        return employeeMapper.toDTO(employeeRepo.save(emp));
    }

    @Override
    public Page<EmployeeDTO> getAllEmployees(int page, int size) {
        return employeeRepo.findAll(PageRequest.of(page, size))
                .map(employeeMapper::toDTO);
    }

    @Override
    public List<EmployeeLookupDTO> getEmployeeLookups() {
        return lookupMapper.toLookupDTOs(employeeRepo.findAll());
    }

    @Override
    public ReportingChainDTO getReportingChain(Long employeeId) {
        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        List<EmployeeLookupDTO> chain = new ArrayList<>();
        Employee manager = emp.getManager();
        while (manager != null) {
            chain.add(lookupMapper.toLookupDTO(manager));
            manager = manager.getManager();
        }

        return new ReportingChainDTO(
                emp.getId(),
                emp.getName(),
                emp.getManager() != null ? emp.getManager().getId() : null,
                emp.getManager() != null ? emp.getManager().getName() : null,
                chain
        );
    }


    private void setReferences(Employee emp, EmployeeDTO dto) {
        if (dto.getManagerId() != null) {
            Employee manager = employeeRepo.findById(dto.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found with ID: " + dto.getManagerId()));

            if (emp.getId() != null && emp.getId().equals(manager.getId())) {
                throw new BadRequestException("An employee cannot be their own manager.");
            }

            if (manager.getManager() != null && emp.getId() != null &&
                    manager.getManager().getId().equals(emp.getId())) {
                throw new BadRequestException("Circular management is not allowed: mutual reporting detected.");
            }

            emp.setManager(manager);
        } else {
            emp.setManager(null);
        }

        emp.setDepartment(dto.getDepartmentId() != null
                ? departmentRepo.findById(dto.getDepartmentId()).orElseThrow(() ->
                new ResourceNotFoundException("Department not found with ID: " + dto.getDepartmentId()))
                : null);
    }

    private void updateEntityFields(Employee emp, EmployeeDTO dto) {
        emp.setName(dto.getName());
        emp.setDateOfBirth(dto.getDateOfBirth());
        emp.setSalary(dto.getSalary());
        emp.setAddress(dto.getAddress());
        emp.setRole(dto.getRole());
        emp.setJoiningDate(dto.getJoiningDate());
        emp.setYearlyBonusPercentage(dto.getYearlyBonusPercentage());
    }
}

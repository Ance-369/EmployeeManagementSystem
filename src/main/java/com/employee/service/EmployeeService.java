package com.employee.service;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeLookupDTO;
import com.employee.dto.ReportingChainDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO dto);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);

    EmployeeDTO updateEmployeeDepartment(Long employeeId, Long departmentId);

    Page<EmployeeDTO> getAllEmployees(int page, int size);

    List<EmployeeLookupDTO> getEmployeeLookups();

    ReportingChainDTO getReportingChain(Long employeeId);
}


package com.employee.controller;

import com.employee.dto.EmployeeDTO;
import com.employee.dto.EmployeeLookupDTO;
import com.employee.dto.ReportingChainDTO;
import com.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @PutMapping("/{id}/department/{deptId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeDepartment(@PathVariable Long id, @PathVariable Long deptId) {
        return ResponseEntity.ok(employeeService.updateEmployeeDepartment(id, deptId));
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(employeeService.getAllEmployees(page, size));
    }

    @GetMapping(params = "lookup")
    public ResponseEntity<List<EmployeeLookupDTO>> getEmployeeNamesAndIds(@RequestParam boolean lookup) {
        if (lookup) {
            return ResponseEntity.ok(employeeService.getEmployeeLookups());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}/reporting")
    public ResponseEntity<ReportingChainDTO> getReportingChain(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getReportingChain(id));
    }
}

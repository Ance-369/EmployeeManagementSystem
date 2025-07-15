package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate creationDate;

    private Long departmentHeadId;     // reference to an Employee

    private List<EmployeeDTO> employees;   // optional, only populated when expand=employee
}

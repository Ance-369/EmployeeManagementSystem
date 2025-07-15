package com.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private BigDecimal salary;

    @NotBlank
    private String address;

    @NotBlank
    private String role;

    @NotNull
    private LocalDate joiningDate;

    @NotNull
    private Double yearlyBonusPercentage;

    private Long managerId;         // another Employee
    private Long departmentId;      // reference to Department
}

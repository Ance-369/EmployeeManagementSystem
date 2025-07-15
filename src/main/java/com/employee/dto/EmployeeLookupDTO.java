package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeLookupDTO {
    private Long id;
    private String name;
}

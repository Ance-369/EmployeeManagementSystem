package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportingChainDTO {

    private Long employeeId;
    private String employeeName;
    private Long managerId;
    private String managerName;

    private List<EmployeeLookupDTO> reportingChain;
}

package com.lala.varun.employee_ms_prac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIResponseDto {
    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;
}

package com.lala.varun.employee_ms_prac.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String departmentCode;

}

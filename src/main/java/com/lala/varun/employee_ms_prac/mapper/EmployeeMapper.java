package com.lala.varun.employee_ms_prac.mapper;

import com.lala.varun.employee_ms_prac.dto.EmployeeDto;
import com.lala.varun.employee_ms_prac.entity.Employee;

public class EmployeeMapper {
    // This class will contain methods to map between EmployeeDto and Employee entities
    // For example, you can use MapStruct or manually implement the mapping methods here

    // Example method to convert EmployeeDto to Employee entity
     public static  Employee toEntity(EmployeeDto employeeDto) {
         if (employeeDto == null) {
             return null;
         }
         Employee employee = new Employee();
         employee.setId(employeeDto.getId());
         employee.setFirstName(employeeDto.getFirstName());
         employee.setLastName(employeeDto.getLastName());
         employee.setEmail(employeeDto.getEmail());
         employee.setPhoneNumber(employeeDto.getPhoneNumber());
         employee.setDepartmentCode(employeeDto.getDepartmentCode());
         return employee;
     }
    // Example method to convert Employee entity to EmployeeDto

     public static  EmployeeDto toDto(Employee employee) {
         if (employee == null) {
             return null;
         }
         EmployeeDto employeeDto = new EmployeeDto();
         employeeDto.setId(employee.getId());
         employeeDto.setDepartmentCode(employee.getDepartmentCode());

            employeeDto.setFirstName(employee.getFirstName());
            employeeDto.setLastName(employee.getLastName());
            employeeDto.setEmail(employee.getEmail());
            employeeDto.setPhoneNumber(employee.getPhoneNumber());
            return employeeDto;
     }

}

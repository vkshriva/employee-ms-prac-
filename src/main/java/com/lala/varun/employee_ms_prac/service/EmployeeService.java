package com.lala.varun.employee_ms_prac.service;

import com.lala.varun.employee_ms_prac.dto.EmployeeDto;
import com.lala.varun.employee_ms_prac.entity.Employee;
import com.lala.varun.employee_ms_prac.mapper.EmployeeMapper;
import com.lala.varun.employee_ms_prac.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.toDto(savedEmployee);
        return savedEmployeeDto;
    }

    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            return EmployeeMapper.toDto(employee);
        } else {
            return null; // or throw an exception if preferred
        }
    }
}

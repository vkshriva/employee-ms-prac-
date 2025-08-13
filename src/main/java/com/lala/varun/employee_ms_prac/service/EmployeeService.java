package com.lala.varun.employee_ms_prac.service;

import com.lala.varun.employee_ms_prac.dto.APIResponseDto;
import com.lala.varun.employee_ms_prac.dto.DepartmentDto;
import com.lala.varun.employee_ms_prac.dto.EmployeeDto;
import com.lala.varun.employee_ms_prac.entity.Employee;
import com.lala.varun.employee_ms_prac.mapper.EmployeeMapper;
import com.lala.varun.employee_ms_prac.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.toDto(savedEmployee);
        return savedEmployeeDto;
    }

    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        ResponseEntity<DepartmentDto> responseEntity =
                restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(), DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        if (employee != null && departmentDto != null) {
            EmployeeDto employeeDto = EmployeeMapper.toDto(employee);
            return APIResponseDto.builder()
                    .employeeDto(employeeDto)
                    .departmentDto(departmentDto)
                    .build();
        } else {
            return null; // or throw an exception
        }

    }
}

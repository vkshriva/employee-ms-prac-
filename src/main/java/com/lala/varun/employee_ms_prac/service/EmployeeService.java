package com.lala.varun.employee_ms_prac.service;

import com.lala.varun.employee_ms_prac.client.APIClient;
import com.lala.varun.employee_ms_prac.dto.APIResponseDto;
import com.lala.varun.employee_ms_prac.dto.DepartmentDto;
import com.lala.varun.employee_ms_prac.dto.EmployeeDto;
import com.lala.varun.employee_ms_prac.entity.Employee;
import com.lala.varun.employee_ms_prac.mapper.EmployeeMapper;
import com.lala.varun.employee_ms_prac.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private APIClient apiClient;

    @Value("${emp.message}")
    private String welcomeMessage;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.toEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = EmployeeMapper.toDto(savedEmployee);
        return savedEmployeeDto;
    }

    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
        if (employee != null && departmentDto != null) {
            EmployeeDto employeeDto = EmployeeMapper.toDto(employee);
            employeeDto.setFirstName(employeeDto.getFirstName()+" " + welcomeMessage);
            APIResponseDto apiResponseDto = new APIResponseDto();
            apiResponseDto.setDepartmentDto(departmentDto);
            apiResponseDto.setEmployeeDto(employeeDto);
            return apiResponseDto;


        } else {
            return null; // or throw an exception
        }

    }
}

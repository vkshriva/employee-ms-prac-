package com.lala.varun.employee_ms_prac.service;

import com.lala.varun.employee_ms_prac.client.APIClient;
import com.lala.varun.employee_ms_prac.dto.APIResponseDto;
import com.lala.varun.employee_ms_prac.dto.DepartmentDto;
import com.lala.varun.employee_ms_prac.dto.EmployeeDto;
import com.lala.varun.employee_ms_prac.entity.Employee;
import com.lala.varun.employee_ms_prac.mapper.EmployeeMapper;
import com.lala.varun.employee_ms_prac.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@RefreshScope
@Service
public class EmployeeService {
  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private APIClient apiClient;

  @Value("${emp.message}")
  private String welcomeMessage;

  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

    Employee employee = EmployeeMapper.toEntity(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);
    EmployeeDto savedEmployeeDto = EmployeeMapper.toDto(savedEmployee);
    return savedEmployeeDto;
  }

  /*
  Yeh Method jo Finally External Service Use karega ot Humme Yahn Circuit Breaker Lagana hai
  Use CircuitBreaker which minimum with name jo aapko jo details batayega .naam kuch bhi ho sakta hai
  @CircuitBreaker(name="DeptBreaker")
  */
  @CircuitBreaker(name = "deptBreaker", fallbackMethod = "getDefaultDepartment")
  public APIResponseDto getEmployeeById(Long id) {
    Employee employee = employeeRepository.findById(id).orElse(null);
    DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());
    if (employee != null && departmentDto != null) {
      EmployeeDto employeeDto = EmployeeMapper.toDto(employee);
      employeeDto.setFirstName(employeeDto.getFirstName() + " " + welcomeMessage);
      APIResponseDto apiResponseDto = new APIResponseDto();
      apiResponseDto.setDepartmentDto(departmentDto);
      apiResponseDto.setEmployeeDto(employeeDto);
      return apiResponseDto;

    } else {
      return null; // or throw an exception
    }
  }

  public APIResponseDto getDefaultDepartment(Long id, Throwable throwable) {
    Employee employee = employeeRepository.findById(id).orElse(null);
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentName("Default Department");
    departmentDto.setDepartmentCode("0000");
    departmentDto.setDepartmentDescription("This is a default department description.");
    if (employee != null) {
      EmployeeDto employeeDto = EmployeeMapper.toDto(employee);
      employeeDto.setFirstName(employeeDto.getFirstName() + " " + welcomeMessage);
      APIResponseDto apiResponseDto = new APIResponseDto();
      apiResponseDto.setDepartmentDto(departmentDto);
      apiResponseDto.setEmployeeDto(employeeDto);
      return apiResponseDto;

    } else {
      return null; // or throw an exception
    }
  }
}

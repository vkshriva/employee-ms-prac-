package com.lala.varun.employee_ms_prac.client;

import com.lala.varun.employee_ms_prac.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dept-service", url = "http://localhost:8080/api/departments/")
public interface APIClient {
    @GetMapping ("{department-code}")
    public DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
}

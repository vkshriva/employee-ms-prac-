package com.lala.varun.employee_ms_prac.client;

import com.lala.varun.employee_ms_prac.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//name should be whatever u write application.properties
@FeignClient(name = "department-ms-prac")
public interface APIClient {
    @GetMapping ("api/departments/{department-code}")
    public DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);
}

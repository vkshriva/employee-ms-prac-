package com.lala.varun.employee_ms_prac.repository;

import com.lala.varun.employee_ms_prac.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByEmail(String email);
    Employee findByPhoneNumber(String phoneNumber);
}

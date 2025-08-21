package com.example.department_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.department_service.entity.Employee;

@FeignClient(name = "employee-service", url = "http://localhost:8081/employees")
public interface APIClient {

	@GetMapping("/{id}")
	Employee getEmployeeDetails(@PathVariable long id);
}

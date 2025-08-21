package com.example.employee_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repo;
	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeDetails(@PathVariable long id) {
		return repo.findById(id);
	}
	
	@PostMapping
	public Employee createEmployee(Employee emp) {
		return repo.save(emp);
	}
}

package com.example.department_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.department_service.APIClient;
import com.example.department_service.entity.Department;
import com.example.department_service.entity.Employee;
import com.example.department_service.repository.DepartmentRepository;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository repo;
	
	@Autowired
	private APIClient apiClient;
	
	@GetMapping
	public List<Department> listAll(){
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Department> getDepartmentDetails(@PathVariable long id) {
		return repo.findById(id);
	}
	
	@GetMapping("/emp/{id}")
	public String getEmployeeInfo(@PathVariable long id) {
		Employee employee = apiClient.getEmployeeDetails(id);
		return "Employee with id: " + id + " works in " + employee.getDepartment();
	}
	
	@PostMapping
	public Department createDepartment(@RequestBody Department dept) {
		return repo.save(dept);
	}
}

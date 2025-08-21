package com.example.department_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Employee {
	private long id;
	private String name;
	private String department;
	private String email;
}

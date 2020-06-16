package com.wedding.service;

import java.util.List;

import com.wedding.models.Employee;
import com.wedding.repository.EmployeeRepository;

public class EmployeeService {
	EmployeeRepository employeeRepository;

	public EmployeeService() {
		employeeRepository = new EmployeeRepository();
	}
	
	public List<Employee> getAllEmployee() {
		return employeeRepository.getAll();
	}
	
}

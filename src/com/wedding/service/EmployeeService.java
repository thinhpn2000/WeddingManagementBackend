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
	public List<String> getAllUsername() {
		return employeeRepository.getAllUsername();
	}
	public void deleteUser(int id) {
		employeeRepository.delete(id);
	}
	public void resetpasswordUser(int id) {
		employeeRepository.resetpassword(id);
	}
	public void addEmployee(Employee employee) {
		employeeRepository.add(employee);
	}
	public Employee convertJSONToEmployee(String JSON) {
		return employeeRepository.convertJSONToEmployee(JSON);
	}
	public void updateEmployee(Employee employee) {
		employeeRepository.update(employee);
	}
	
}

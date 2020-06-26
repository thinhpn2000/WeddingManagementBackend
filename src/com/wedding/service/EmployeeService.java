package com.wedding.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

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
		String password = "1";
		String encodePassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
		employeeRepository.resetpassword(id, encodePassword);
	}
	public void addEmployee(Employee employee) {
	
		String password = "1";
		String encodePassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
		employee.setPassword(encodePassword);
		employeeRepository.add(employee);
	}
	public Employee convertJSONToEmployee(String JSON) {
		return employeeRepository.convertJSONToEmployee(JSON);
	}
	public void updateEmployee(Employee employee) {
		employeeRepository.update(employee);
	}
	public Employee getByUsername(String username) {
		return employeeRepository.getUserByUsername(username);
	}
	public void updatePassword(String password, int userID) {
		String encodePassword = BCrypt.hashpw(password, BCrypt.gensalt(13));
		employeeRepository.updatePassword(encodePassword, userID);
	}
}

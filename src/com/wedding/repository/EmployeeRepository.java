package com.wedding.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Employee;

public class EmployeeRepository {
	private Employee employee;

	public EmployeeRepository() {
		employee = new Employee();
	}
	
	public List<Employee> getAll() {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call getAllEmployee()}";
		List<Employee> listEmployee = new ArrayList<Employee>();
		
		try {
			CallableStatement statement = connection.prepareCall(query);
			ResultSet res = statement.executeQuery();
			while(res.next()) {
				Employee employee = new Employee();
				employee.setUserID(res.getInt("userID"));
				employee.setDOB(res.getString("DOB"));
				employee.setFullname(res.getString("fullname"));
				employee.setGender(res.getString("gender"));
				employee.setJoiningDate(res.getString("joiningDate"));
				employee.setSalary(res.getInt("salary"));
				employee.setUsername(res.getString("username"));
				listEmployee.add(employee);
			}
			connection.close();
			return listEmployee;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

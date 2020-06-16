package com.wedding.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Food;
import com.wedding.models.Service;

public class ServiceRepository {
	private Gson gson = new Gson();
	public List<Service> getAll() {

		String queryinService = "{call getAllService()}";
		String queryinUpdatedService = "{call getAllUpdatedService()}";
		
		Connection connection = MySqlConnection.getInstance().getConnection();
		List<Service> serviceList = new ArrayList<Service>();
		try {
			CallableStatement statement = connection.prepareCall(queryinUpdatedService);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Service service = new Service();
				service.setServiceID(res.getInt("serviceID"));
				service.setServiceName(res.getString("serviceName"));
				service.setServicePrice(res.getInt("servicePrice"));
				serviceList.add(service);
			}
			statement = connection.prepareCall(queryinService);
			res = statement.executeQuery();
			while (res.next()) {
				Service service = new Service();
				service.setServiceID(res.getInt("serviceID"));
				service.setServiceName(res.getString("serviceName"));
				service.setServicePrice(res.getInt("servicePrice"));
				serviceList.add(service);
			}
			connection.close();
			return serviceList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void add(Service service) {
		String query = "{call addService(?,?,?,?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setString(1, service.getServiceName());
			statement.setInt(2, service.getServicePrice());
			statement.setString(3, service.getStartingDate());
			statement.setString(4, service.getEndingDate());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void delele(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call deleteService(?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Service getByIdInService(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call getByIdInService(?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			Service service = new Service();
			if (res.next()) {
				service.setServiceName(res.getString("serviceName"));
				service.setServicePrice(res.getInt("servicePrice"));
				service.setEndingDate(res.getString("endingDate"));
			}
			connection.close();
			return service;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Service getByIdInUpdatedService(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call getByIdInUpdatedService(?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			Service service = new Service();
			if (res.next()) {
				service.setServicePrice(res.getInt("servicePrice"));
			}
			connection.close();
			return service;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void updateEndingService(Service service) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call updateEndingService(?,?,?,?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, service.getServiceID());
			statement.setInt(2, service.getServicePrice());
			statement.setString(3, service.getStartingDate());
			statement.setNull(4, Types.VARCHAR);
			statement.executeUpdate();
			
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateEndingUpdatedService(Service service) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call updateEndingUpdatedService(?,?,?,?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, service.getServiceID());
			statement.setInt(2, service.getServicePrice());
			statement.setString(3, service.getStartingDate());
			statement.setNull(4, Types.VARCHAR);
			
			statement.executeUpdate();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateName(Service service) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call updateNameService(?,?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, service.getServiceID());
			statement.setString(2, service.getServiceName());
			
			statement.executeUpdate();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Service convertJSONtoServiceUpdate(String json) {
		Service service = gson.fromJson(json, Service.class);
		return service;
	}
}

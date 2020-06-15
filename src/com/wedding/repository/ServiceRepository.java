package com.wedding.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Service;

public class ServiceRepository {

	public List<Service> getAll() {

		String queryinService = "SELECT serviceID, serviceName, servicePrice FROM SERVICE WHERE NOT isDeleted AND endingDate IS NULL";
		String queryinUpdatedService = "SELECT SERVICE.serviceID, SERVICE.serviceName, UPDATEDSERVICE.servicePrice FROM SERVICE, UPDATEDSERVICE WHERE NOT UPDATEDSERVICE.isDeleted AND  SERVICE.serviceID = UPDATEDSERVICE.serviceID AND UPDATEDSERVICE.endingDate IS NULL";

		Connection connection = MySqlConnection.getInstance().getConnection();
		List<Service> serviceList = new ArrayList<Service>();
		try {
			PreparedStatement statement = connection.prepareStatement(queryinUpdatedService);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Service service = new Service();
				service.setServiceID(res.getInt("serviceID"));
				service.setServiceName(res.getString("serviceName"));
				service.setServicePrice(res.getInt("servicePrice"));
				serviceList.add(service);
			}
			statement = connection.prepareStatement(queryinService);
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
		String query = "INSERT INTO SERVICE(serviceName,servicePrice,startingDate,endingDate) VALUES (?,?,?,?)";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
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
		String queryService = "UPDATE SERVICE SET isDeleted = ? WHERE serviceID = ?";
		String queryUpdatedService = "UPDATE UPDATEDSERVICE SET isDeleted = ? WHERE serviceID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(queryService);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			statement = connection.prepareStatement(queryUpdatedService);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Service getByIdInService(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "SELECT serviceName, servicePrice, endingDate FROM SERVICE WHERE serviceID = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(query);
			prep.setInt(1, id);
			ResultSet res = prep.executeQuery();
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
		String query = "SELECT servicePrice FROM UPDATEDSERVICE WHERE serviceID = ? AND endingDate is null";
		try {
			PreparedStatement prep = connection.prepareStatement(query);
			prep.setInt(1, id);
			ResultSet res = prep.executeQuery();
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
		String queryUpdate = "UPDATE SERVICE SET endingDate = ? WHERE serviceID = ?";
		String queryInsert = "INSERT INTO UPDATEDSERVICE(serviceID, servicePrice, startingDate, endingDate) VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(queryUpdate);
			statement.setString(1, service.getStartingDate());
			statement.setInt(2, service.getServiceID());
			statement.executeUpdate();
			
			statement = connection.prepareStatement(queryInsert);
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
		String queryUpdate = "UPDATE UPDATEDSERVICE SET endingDate = ? WHERE serviceID = ? AND endingDate IS NULL";
		String queryInsert = "INSERT INTO UPDATEDSERVICE(serviceID, servicePrice, startingDate, endingDate) VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = connection.prepareStatement(queryUpdate);
			statement.setString(1, service.getStartingDate());
			statement.setInt(2, service.getServiceID());
			statement.executeUpdate();
			
			statement = connection.prepareStatement(queryInsert);
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
//	public void insertUpdatedService(Service service) {
//		Connection connection = MySqlConnection.getInstance().getConnection();
//		String query = "INSERT INTO UPDATEDSERVICE(serviceID, servicePrice, startingDate, endingDate) VALUES (?,?,?,?)";
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setInt(1, service.getServiceID());
//			statement.setInt(2, service.getServicePrice());
//			statement.setString(3, service.getStartingDate());
//			statement.setNull(4, Types.VARCHAR);
//			statement.executeUpdate();
//			connection.close();
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public void updateName(Service service) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "UPDATE SERVICE SET serviceName = ? WHERE serviceID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, service.getServiceName());
			statement.setInt(2, service.getServiceID());
			statement.executeUpdate();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}

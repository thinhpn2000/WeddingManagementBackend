package com.wedding.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}

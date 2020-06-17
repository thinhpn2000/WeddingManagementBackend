package com.wedding.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Reservation;
import com.wedding.models.ServiceReservation;

public class ReservationRepository {

	private Gson gson = new Gson();
	
	public Reservation convertJSONtoReservation(String JSON) {
		return gson.fromJson(JSON, Reservation.class);
	}
	
	
	public void add(Reservation reservation) {
		String query = "INSERT INTO WEDDING(bride, groom, weddingDate, shift, phone, tableQuantity, reservedTable, weddingStatus, lobbyID, userID, tablePrice, balance, totalServicePrice, totalTablePrice, totalWeddingPrice, deposit, reservationDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, reservation.getBride());
			statement.setString(2, reservation.getGroom());
			statement.setString(3, reservation.getWeddingDate());
			statement.setInt(4, reservation.getShift());
			statement.setString(5, reservation.getPhone());
			statement.setInt(6, reservation.getTableQuantity());
			statement.setInt(7, reservation.getReservedTable());
			statement.setInt(8, 0);
			statement.setInt(9, reservation.getLobbyID());
			statement.setInt(10,reservation.getUserID());
			statement.setInt(11, reservation.getTablePrice());
			statement.setInt(12, reservation.getBalance());
			statement.setInt(13, reservation.getTotalServicePrice());
			statement.setInt(14, reservation.getTotalTablePrice());
			statement.setInt(15, reservation.getTotalWeddingPrice());
			statement.setInt(16, reservation.getDeposit());
			statement.setString(17, reservation.getReservationDate());
			statement.executeUpdate();
			
			
			query = "SELECT weddingID FROM WEDDING WHERE bride = ? AND groom = ?  AND weddingDate = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, reservation.getBride());
			statement.setString(2, reservation.getGroom());
			statement.setString(3, reservation.getWeddingDate());
			ResultSet res = statement.executeQuery();
			int weddingID = 0;
			while(res.next()) {
				weddingID = res.getInt("weddingID");
			}
			
			query = "{call price_in_food_invoice(?, ?, ?)}";
			CallableStatement callStatement = connection.prepareCall(query);
			List<Integer> listFoodID = reservation.getListFoodID();
			for(int i : listFoodID) {
				callStatement.setInt(1, weddingID);
				callStatement.setInt(2, i);
				callStatement.setString(3, reservation.getReservationDate());
				callStatement.executeUpdate();
			}
			query = "{call price_in_service_invoice(?, ?, ?, ?)}";
			List<ServiceReservation> listServiceReservation = reservation.getListServiceReservation();
			callStatement = connection.prepareCall(query);
			for(ServiceReservation service : listServiceReservation) {
				callStatement.setInt(1, weddingID);
				callStatement.setInt(2, service.getServiceID());
				callStatement.setInt(3, service.getServiceQuantity());
				callStatement.setString(4, reservation.getReservationDate());
				callStatement.executeUpdate();
			}
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

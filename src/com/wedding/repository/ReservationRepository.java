package com.wedding.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.dto.FoodPrice;
import com.wedding.dto.ServicePrice;
import com.wedding.models.Lobby;
import com.wedding.models.Reservation;
import com.wedding.models.ReservationUpdate;
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
			statement.setInt(10, reservation.getUserID());
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
			while (res.next()) {
				weddingID = res.getInt("weddingID");
			}

			query = "{call price_in_food_invoice(?, ?, ?)}";
			CallableStatement callStatement = connection.prepareCall(query);
			List<Integer> listFoodID = reservation.getListFoodID();
			for (int i : listFoodID) {
				callStatement.setInt(1, weddingID);
				callStatement.setInt(2, i);
				callStatement.setString(3, reservation.getReservationDate());
				callStatement.executeUpdate();
			}
			query = "{call price_in_service_invoice(?, ?, ?, ?)}";
			List<ServiceReservation> listServiceReservation = reservation.getListServiceReservation();
			callStatement = connection.prepareCall(query);
			for (ServiceReservation service : listServiceReservation) {
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

	public List<ReservationUpdate> getAll() {
		List<ReservationUpdate> reservationUpdate = new ArrayList<ReservationUpdate>();
		String query = "SELECT WEDDING.*, TYPE_SHIFT.shiftTypeName, LOBBY.lobbyName  FROM TYPE_SHIFT,WEDDING,LOBBY WHERE shift = shiftTypeID AND WEDDING.lobbyID = LOBBY.lobbyID AND NOT WEDDING.isDeleted";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				ReservationUpdate reservation = new ReservationUpdate();
				reservation.setBride(result.getString("bride"));
				reservation.setGroom(result.getString("groom"));
				reservation.setWeddingDate(result.getString("weddingDate"));
				reservation.setTableQuantity(result.getInt("tableQuantity"));
				reservation.setReservedTable(result.getInt("reservedTable"));
				reservation.setWeddingStatus(result.getInt("weddingStatus"));

				reservation.setWeddingID(result.getInt("weddingID"));
				reservation.setLobbyName(result.getString("lobbyName"));
				reservation.setShiftTypeName(result.getString("shiftTypeName"));
				reservationUpdate.add(reservation);

			}
			return reservationUpdate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ReservationUpdate getReservationById(int id) {
		ReservationUpdate reservation = new ReservationUpdate();
		String query = "SELECT WEDDING.*, TYPE_SHIFT.shiftTypeName, LOBBY.lobbyName, LOBBY.maxTable, minPrice  FROM TYPE_SHIFT,WEDDING,LOBBY,TYPE_LOBBY WHERE shift = shiftTypeID AND WEDDING.lobbyID = LOBBY.lobbyID AND LOBBY.lobbyType = TYPE_LOBBY.lobbyTypeID AND NOT WEDDING.isDeleted AND weddingID = ?";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				reservation.setBride(result.getString("bride"));
				reservation.setGroom(result.getString("groom"));
				reservation.setWeddingDate(result.getString("weddingDate"));
				reservation.setShift(result.getInt("shift"));
				reservation.setPhone(result.getString("phone"));
				reservation.setTableQuantity(result.getInt("tableQuantity"));
				reservation.setReservedTable(result.getInt("reservedTable"));
				reservation.setWeddingStatus(result.getInt("weddingStatus"));
				reservation.setLobbyID(result.getInt("lobbyID"));
				reservation.setUserID(result.getInt("userID"));
				reservation.setTablePrice(result.getInt("tablePrice"));
				reservation.setBalance(result.getInt("balance"));
				reservation.setTotalServicePrice(result.getInt("totalServicePrice"));
				reservation.setTotalTablePrice(result.getInt("totalTablePrice"));
				reservation.setTotalWeddingPrice(result.getInt("totalWeddingPrice"));
				reservation.setDeposit(result.getInt("deposit"));
				reservation.setReservationDate(result.getString("reservationDate"));

				reservation.setWeddingID(result.getInt("weddingID"));
				reservation.setLobbyName(result.getString("lobbyName"));
				reservation.setShiftTypeName(result.getString("shiftTypeName"));
				reservation.setMaxTable(result.getInt("maxTable"));
				reservation.setMinPrice(result.getInt("minPrice"));
			}
			query = "SELECT FOOD_INVOICE.foodID, price, foodName FROM FOOD_INVOICE, FOOD WHERE FOOD.foodID = FOOD_INVOICE.foodID AND weddingID = ? AND NOT FOOD_INVOICE.isDeleted";
			List<FoodPrice> foodAndprices = new ArrayList<FoodPrice>();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				FoodPrice foodAndprice = new FoodPrice();
				foodAndprice.setFoodID(result.getInt("foodID"));
				foodAndprice.setFoodPrice(result.getInt("price"));
				foodAndprice.setFoodName(result.getString("foodName"));
				foodAndprices.add(foodAndprice);
			}
			reservation.setListFood(foodAndprices);

			query = "SELECT SERVICE_INVOICE.serviceID, price, serviceQuantity, serviceName FROM SERVICE_INVOICE, SERVICE WHERE SERVICE.serviceID = SERVICE_INVOICE.serviceID AND weddingID = ? AND NOT SERVICE_INVOICE.isDeleted";
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			List<ServicePrice> serviceAndprices = new ArrayList<ServicePrice>();
			while (result.next()) {
				ServicePrice serviceAndprice = new ServicePrice();
				serviceAndprice.setServiceID(result.getInt("serviceID"));
				serviceAndprice.setServiceQuantity(result.getInt("serviceQuantity"));
				serviceAndprice.setServicePrice(result.getInt("price"));
				serviceAndprice.setServiceName(result.getString("serviceName"));
				serviceAndprice
						.setService1Price(serviceAndprice.getServicePrice() / serviceAndprice.getServiceQuantity());
				serviceAndprices.add(serviceAndprice);
			}
			reservation.setListServicePrice(serviceAndprices);
			return reservation;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void delete(int id) {
		String query = "UPDATE WEDDING SET isDeleted = ? WHERE weddingID = ?";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

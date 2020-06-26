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
import com.wedding.dto.MonthRevenueDTO;
import com.wedding.dto.ReservationUpdateDTO;
import com.wedding.dto.ServicePrice;
import com.wedding.dto.TotalRevenueDTO;
import com.wedding.models.Reservation;
import com.wedding.models.ReservationUpdate;
import com.wedding.models.ServiceReservation;

public class ReservationRepository {

	private Gson gson = new Gson();

	public Reservation convertJSONtoReservation(String JSON) {
		return gson.fromJson(JSON, Reservation.class);
	}
	
	public ReservationUpdateDTO convertJSONtoReservationDTO(String json) {
		return gson.fromJson(json, ReservationUpdateDTO.class);
	}

	public void add(Reservation reservation) {
		String query = "{CALL insertReservation(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement callStatement = connection.prepareCall(query);
			callStatement.setString(1, reservation.getBride());
			callStatement.setString(2, reservation.getGroom());
			callStatement.setString(3, reservation.getWeddingDate());
			callStatement.setInt(4, reservation.getShift());
			callStatement.setString(5, reservation.getPhone());
			callStatement.setInt(6, reservation.getTableQuantity());
			callStatement.setInt(7, reservation.getReservedTable());
			callStatement.setInt(8, 0);
			callStatement.setInt(9, reservation.getLobbyID());
			callStatement.setInt(10, reservation.getUserID());
			callStatement.setInt(11, reservation.getTablePrice());
			callStatement.setInt(12, reservation.getBalance());
			callStatement.setInt(13, reservation.getTotalServicePrice());
			callStatement.setInt(14, reservation.getTotalTablePrice());
			callStatement.setInt(15, reservation.getTotalWeddingPrice());
			callStatement.setInt(16, reservation.getDeposit());
			callStatement.setString(17, reservation.getReservationDate());
			callStatement.executeUpdate();

			query = "{CALL getWeddingID(?, ?, ?)}";
			callStatement = connection.prepareCall(query);
			callStatement.setString(1, reservation.getBride());
			callStatement.setString(2, reservation.getGroom());
			callStatement.setString(3, reservation.getWeddingDate());
			ResultSet res = callStatement.executeQuery();
			int weddingID = 0;
			while (res.next()) {
				weddingID = res.getInt("weddingID");
			}
			query = "{CALL price_in_food_invoice(?, ?, ?)}";
			callStatement = connection.prepareCall(query);
			List<Integer> listFoodID = reservation.getListFoodID();
			for (int i : listFoodID) {
				callStatement.setInt(1, weddingID);
				callStatement.setInt(2, i);
				callStatement.setString(3, reservation.getReservationDate());
				callStatement.executeUpdate();
			}
			query = "{CALL price_in_service_invoice(?, ?, ?, ?)}";
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
		String query = "{CALL getAllReservation()}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
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
		String query = "{CALL getReservationByID(?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
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
			query = "{CALL getFoodInvoiceByID(?)}";
			List<FoodPrice> foodAndprices = new ArrayList<FoodPrice>();
			statement = connection.prepareCall(query);
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

			query = "{CALL getServiceInvoiceByID(?)}";
			statement = connection.prepareCall(query);
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
		String query = "{CALL deleteReservation(?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void updateInvoice(String listServicePrice, String listFood, int weddingID, String updateDate) {
		String queryFood = "{CALL createListFood (?, ?, ?)}";
		String queryService = "{CALL createListService (?, ?, ?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(queryFood);
			statement.setString(1, listFood);
			statement.setInt(2, weddingID);
			statement.setString(3, updateDate);
			statement.executeUpdate();
			
			statement = connection.prepareCall(queryService);
			statement.setString(1, listServicePrice);
			statement.setInt(2, weddingID);
			statement.setString(3, updateDate);
			statement.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void update(ReservationUpdateDTO reservationUpdate) {
		String query = "{CALL updateWedding (?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, reservationUpdate.getWeddingID());
			statement.setString(2,reservationUpdate.getPhone());
			statement.setInt(3,reservationUpdate.getBalance());
			statement.setInt(4,reservationUpdate.getReservedTable());
			statement.setInt(5,reservationUpdate.getTableQuantity());
			statement.setInt(6,reservationUpdate.getTablePrice());
			statement.setInt(7,reservationUpdate.getTotalTablePrice());
			statement.setInt(8,reservationUpdate.getTotalServicePrice());
			statement.setInt(9,reservationUpdate.getTotalWeddingPrice());
			
			statement.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void pay(int id, int userID) {
		String query = "{CALL pay (?, ?, ?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			statement.setInt(2, userID);
			statement.setString(3,java.time.LocalDate.now().toString());
			
			statement.executeUpdate();
			
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<TotalRevenueDTO> getTotalRevenue(int year) {
		String query = "{CALL getTotalRevenue (?)}";
		List<TotalRevenueDTO> listTotalRevenue = new ArrayList<TotalRevenueDTO>();
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, year);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				TotalRevenueDTO totalRevenue = new TotalRevenueDTO();
				totalRevenue.setMonth(result.getString("month"));
				totalRevenue.setTotalRevenue(result.getInt("total"));
				listTotalRevenue.add(totalRevenue);
			}
			connection.close();
			return listTotalRevenue;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<MonthRevenueDTO> getMonthRevenue(int year, int month) {
		String query = "{CALL getMonthRevenue (?, ?)}";
		List<MonthRevenueDTO> monthRevenue = new ArrayList<MonthRevenueDTO>();
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, year);
			statement.setInt(2, month);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				MonthRevenueDTO dayRevenue = new MonthRevenueDTO();
				dayRevenue.setDate(result.getString("Date"));
				dayRevenue.setRevenue(result.getInt("REVENUE"));
				dayRevenue.setAmountWedding(result.getInt("NumberOfWeddings"));
				monthRevenue.add(dayRevenue);
			}
			connection.close();
			return monthRevenue;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.wedding.models;

import java.util.List;

import com.wedding.dto.FoodPrice;
import com.wedding.dto.ServicePrice;

public class ReservationUpdate {

	private String groom;
	private String bride;
	private String phone;

	private int lobbyID;
	private int userID;

	private int tableQuantity;
	private int reservedTable;

	private int tablePrice;
	private int totalServicePrice;
	private int totalTablePrice;
	private int totalWeddingPrice;

	private int deposit;
	private int balance;

	private int shift;
	private String reservationDate;
	private String weddingDate;

	private List<FoodPrice> listFood;
	private List<ServicePrice> listServicePrice;

	private String lobbyName;
	private int weddingID;
	private String shiftTypeName;
	private int weddingStatus;
	private int maxTable;
	private int minPrice;

	public ReservationUpdate() {
	}
	
	

	public ReservationUpdate(String groom, String bride, String phone, int lobbyID, int userID, int tableQuantity,
			int reservedTable, int tablePrice, int totalServicePrice, int totalTablePrice, int totalWeddingPrice,
			int deposit, int balance, int shift, String reservationDate, String weddingDate, List<FoodPrice> listFood,
			List<ServicePrice> listServicePrice, String lobbyName, int weddingID, String shiftTypeName,
			int weddingStatus, int maxTable, int minPrice) {
		super();
		this.groom = groom;
		this.bride = bride;
		this.phone = phone;
		this.lobbyID = lobbyID;
		this.userID = userID;
		this.tableQuantity = tableQuantity;
		this.reservedTable = reservedTable;
		this.tablePrice = tablePrice;
		this.totalServicePrice = totalServicePrice;
		this.totalTablePrice = totalTablePrice;
		this.totalWeddingPrice = totalWeddingPrice;
		this.deposit = deposit;
		this.balance = balance;
		this.shift = shift;
		this.reservationDate = reservationDate;
		this.weddingDate = weddingDate;
		this.listFood = listFood;
		this.listServicePrice = listServicePrice;
		this.lobbyName = lobbyName;
		this.weddingID = weddingID;
		this.shiftTypeName = shiftTypeName;
		this.weddingStatus = weddingStatus;
		this.maxTable = maxTable;
		this.minPrice = minPrice;
	}



	public List<FoodPrice> getListFood() {
		return listFood;
	}



	public void setListFood(List<FoodPrice> listFood) {
		this.listFood = listFood;
	}





	public List<ServicePrice> getListServicePrice() {
		return listServicePrice;
	}



	public void setListServicePrice(List<ServicePrice> listServicePrice) {
		this.listServicePrice = listServicePrice;
	}
	
	



	public int getMaxTable() {
		return maxTable;
	}



	public void setMaxTable(int maxTable) {
		this.maxTable = maxTable;
	}


	



	public int getMinPrice() {
		return minPrice;
	}



	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}



	public int getWeddingStatus() {
		return weddingStatus;
	}


	public void setWeddingStatus(int weddingStatus) {
		this.weddingStatus = weddingStatus;
	}


	public String getShiftTypeName() {
		return shiftTypeName;
	}

	public void setShiftTypeName(String shiftTypeName) {
		this.shiftTypeName = shiftTypeName;
	}

	public int getWeddingID() {
		return weddingID;
	}

	public void setWeddingID(int weddingID) {
		this.weddingID = weddingID;
	}

	public String getLobbyName() {
		return lobbyName;
	}

	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	public String getGroom() {
		return groom;
	}

	public void setGroom(String groom) {
		this.groom = groom;
	}

	public String getBride() {
		return bride;
	}

	public void setBride(String bride) {
		this.bride = bride;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getLobbyID() {
		return lobbyID;
	}

	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getTableQuantity() {
		return tableQuantity;
	}

	public void setTableQuantity(int tableQuantity) {
		this.tableQuantity = tableQuantity;
	}

	public int getReservedTable() {
		return reservedTable;
	}

	public void setReservedTable(int reservedTable) {
		this.reservedTable = reservedTable;
	}

	public int getTablePrice() {
		return tablePrice;
	}

	public void setTablePrice(int tablePrice) {
		this.tablePrice = tablePrice;
	}

	public int getTotalServicePrice() {
		return totalServicePrice;
	}

	public void setTotalServicePrice(int totalServicePrice) {
		this.totalServicePrice = totalServicePrice;
	}

	public int getTotalTablePrice() {
		return totalTablePrice;
	}

	public void setTotalTablePrice(int totalTablePrice) {
		this.totalTablePrice = totalTablePrice;
	}

	public int getTotalWeddingPrice() {
		return totalWeddingPrice;
	}

	public void setTotalWeddingPrice(int totalWeddingPrice) {
		this.totalWeddingPrice = totalWeddingPrice;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getShift() {
		return shift;
	}

	public void setShift(int shift) {
		this.shift = shift;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getWeddingDate() {
		return weddingDate;
	}

	public void setWeddingDate(String weddingDate) {
		this.weddingDate = weddingDate;
	}
}

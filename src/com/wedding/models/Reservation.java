package com.wedding.models;

import java.util.List;

public class Reservation {

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
	
	private List<Integer> listFoodID;
	private List<ServiceReservation> listServiceReservation;
	
	public Reservation() {}

	public Reservation(String groom, String bride, String phone, int lobbyID, int userID, int tableQuantity,
			int reservedTable, int tablePrice, int totalServicePrice, int totalTablePrice, int totalWeddingPrice,
			int deposit, int balance, int shift, String reservationDate, String weddingDate, List<Integer> listFoodID,
			List<ServiceReservation> listServiceReservation) {
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
		this.listFoodID = listFoodID;
		this.listServiceReservation = listServiceReservation;
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

	public List<Integer> getListFoodID() {
		return listFoodID;
	}

	public void setListFoodID(List<Integer> listFoodID) {
		this.listFoodID = listFoodID;
	}

	public List<ServiceReservation> getListServiceReservation() {
		return listServiceReservation;
	}

	public void setListServiceReservation(List<ServiceReservation> listServiceReservation) {
		this.listServiceReservation = listServiceReservation;
	}
	
	
	
	
	
}

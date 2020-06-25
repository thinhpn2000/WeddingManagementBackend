package com.wedding.dto;

import java.util.List;

import com.wedding.models.ServiceReservation;

public class ReservationUpdateDTO {
	private String phone;
	private int weddingID;
	
	private int tableQuantity;
	private int reservedTable;
	
	private int tablePrice;
	private int totalServicePrice;
	private int totalTablePrice;
	private int totalWeddingPrice;
	
	private int deposit;
	private int balance;
	
	private String reservationDate;
	
	private List<Integer> listFoodID;
	private List<ServiceReservation> listServiceReservation;
	public ReservationUpdateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getWeddingID() {
		return weddingID;
	}

	public void setWeddingID(int weddingID) {
		this.weddingID = weddingID;
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

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
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

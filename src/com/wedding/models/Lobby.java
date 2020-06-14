package com.wedding.models;

public class Lobby {

	private int lobbyID;
	private int lobbyTypeID;
	private String lobbyName;
	private String lobbyType;
	private int maxTable;
	private int minPrice;
	private boolean isDeleted;
	
	public Lobby() {};
	public Lobby(int lobbyID, String lobbyName, String lobbyType, int maxTable, int minPrice, boolean isDeleted) {
		super();
		this.lobbyID = lobbyID;
		this.lobbyName = lobbyName;
		this.lobbyType = lobbyType;
		this.maxTable = maxTable;
		this.minPrice = minPrice;
		this.isDeleted = isDeleted;
	}
	public int getLobbyID() {
		return lobbyID;
	}
	public void setLobbyID(int lobbyID) {
		this.lobbyID = lobbyID;
	}
	public String getLobbyName() {
		return lobbyName;
	}
	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}
	public String getLobbyType() {
		return lobbyType;
	}
	public void setLobbyType(String lobbyType) {
		this.lobbyType = lobbyType;
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getLobbyTypeID() {
		return lobbyTypeID;
	}
	public void setLobbyTypeID(int lobbyTypeID) {
		this.lobbyTypeID = lobbyTypeID;
	}
}

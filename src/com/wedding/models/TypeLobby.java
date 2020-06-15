package com.wedding.models;

public class TypeLobby {

	private int lobbyTypeID;
	private String lobbyTypeName;
	private int minPrice;
	private boolean isDeleted;
	
	public TypeLobby(int lobbyTypeID, String lobbyTypeName, int minPrice, boolean isDeleted) {
		super();
		this.lobbyTypeID = lobbyTypeID;
		this.lobbyTypeName = lobbyTypeName;
		this.minPrice = minPrice;
		this.isDeleted = isDeleted;
	}
	public TypeLobby() {}
	public int getLobbyTypeID() {
		return lobbyTypeID;
	}
	public void setLobbyTypeID(int lobbyTypeID) {
		this.lobbyTypeID = lobbyTypeID;
	}
	public String getLobbyTypeName() {
		return lobbyTypeName;
	}
	public void setLobbyTypeName(String lobbyTypeName) {
		this.lobbyTypeName = lobbyTypeName;
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
}

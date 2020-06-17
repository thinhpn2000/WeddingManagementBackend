package com.wedding.models;

public class ServiceReservation {
	private int serviceID;
	private int serviceQuantity;
	
	public ServiceReservation() {};
	public ServiceReservation(int serviceID, int serviceQuantity) {
		super();
		this.serviceID = serviceID;
		this.serviceQuantity = serviceQuantity;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public int getServiceQuantity() {
		return serviceQuantity;
	}
	public void setServiceQuantity(int serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
	}
	
	
}

package com.wedding.dto;

public class ServicePrice {
	private int serviceID;
	private int servicePrice;
	
	public ServicePrice() {}
	
	public ServicePrice(int serviceID, int servicePrice) {
		super();
		this.serviceID = serviceID;
		this.servicePrice = servicePrice;
	}

	public int getServiceID() {
		return serviceID;
	}
	
	

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public int getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(int servicePrice) {
		this.servicePrice = servicePrice;
	};
	
	

}

package com.wedding.models;

public class Service {

	private int serviceID;
	private String serviceName;
	private int servicePrice;
	private String startingDate;
	private String endingDate;
	private boolean isDeleted;
	
	public Service() {};
	public Service(int serviceID, String serviceName, int servicePrice, String startingDate, String endingDate,
			boolean isDeleted) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.isDeleted = isDeleted;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(int servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}
	public String getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

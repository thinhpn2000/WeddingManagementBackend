package com.wedding.dto;

public class ServicePrice {
	private int serviceID;
	private int serviceQuantity;
	private int servicePrice;
	private int service1Price;
	private String serviceName;
	
	public ServicePrice() {}
	
	



	public ServicePrice(int serviceID, int serviceQuantity, int servicePrice, int service1Price, String serviceName) {
		super();
		this.serviceID = serviceID;
		this.serviceQuantity = serviceQuantity;
		this.servicePrice = servicePrice;
		this.service1Price = service1Price;
		this.serviceName = serviceName;
	}





	public String getServiceName() {
		return serviceName;
	}





	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}





	public int getService1Price() {
		return service1Price;
	}




	public void setService1Price(int service1Price) {
		this.service1Price = service1Price;
	}




	public int getServiceQuantity() {
		return serviceQuantity;
	}



	public void setServiceQuantity(int serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
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

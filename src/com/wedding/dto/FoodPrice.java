package com.wedding.dto;

public class FoodPrice {
	private int foodID;
	private int foodPrice;
	
	public FoodPrice() {}
	
	

	public FoodPrice(int foodID, int foodPrice) {
		super();
		this.foodID = foodID;
		this.foodPrice = foodPrice;
	}



	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	};
	
	
}

package com.wedding.dto;

public class FoodPrice {
	private int foodID;
	private int foodPrice;
	private String foodName;
	
	public FoodPrice() {}
	
	



	public FoodPrice(int foodID, int foodPrice, String foodName) {
		super();
		this.foodID = foodID;
		this.foodPrice = foodPrice;
		this.foodName = foodName;
	}





	public String getFoodName() {
		return foodName;
	}





	public void setFoodName(String foodName) {
		this.foodName = foodName;
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

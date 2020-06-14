package com.wedding.models;

public class Food {

	private int foodID;
	private String foodName;
	private int foodPrice;
	private String foodNote;

	private boolean fromUpdatedFood;

	public Food() {
	}

	public Food(int foodID, String foodName, int foodPrice, String foodNote, boolean fromUpdatedFood,
			String startingDate, String endingDate, boolean isDeleted) {
		super();
		this.foodID = foodID;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
		this.foodNote = foodNote;
		this.fromUpdatedFood = fromUpdatedFood;
		this.startingDate = startingDate;
		this.endingDate = endingDate;
		this.isDeleted = isDeleted;
	}

	public boolean isFromUpdatedFood() {
		return fromUpdatedFood;
	}

	public void setFromUpdatedFood(boolean fromUpdatedFood) {
		this.fromUpdatedFood = fromUpdatedFood;
	}

	private String startingDate;
	private String endingDate;
	private boolean isDeleted;

	public String getFoodNote() {
		return foodNote;
	}

	public void setFoodNote(String foodNote) {
		this.foodNote = foodNote;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
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
	};

}

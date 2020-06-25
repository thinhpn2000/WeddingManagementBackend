package com.wedding.dto;

public class MonthRevenueDTO {
	private String date;
	private int revenue;
	private int amountWedding;
	private float proportion;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getAmountWedding() {
		return amountWedding;
	}

	public void setAmountWedding(int amountWedding) {
		this.amountWedding = amountWedding;
	}

	public float getProportion() {
		return proportion;
	}

	public void setProportion(float proportion) {
		this.proportion = proportion;
	}
}

package com.wedding.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Food;

public class FoodRepository {
	private Gson gson = new Gson();

	public List<Food> getAll() {

		String queryinFood = "{call getAllFood()}";
		String queryinUpdatedFood = "{call getAllUpdatedFood()}";

		Connection connection = MySqlConnection.getInstance().getConnection();
		List<Food> foodList = new ArrayList<Food>();
		try {
			CallableStatement statement = connection.prepareCall(queryinUpdatedFood);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Food food = new Food();
				food.setFoodID(res.getInt("foodID"));
				food.setFoodName(res.getString("foodName"));
				food.setFoodPrice(res.getInt("foodPrice"));
				food.setFoodNote(res.getString("foodNote"));
				food.setFromUpdatedFood(true);
				foodList.add(food);
			}
			statement = connection.prepareCall(queryinFood);
			res = statement.executeQuery();
			while (res.next()) {
				Food food = new Food();
				food.setFoodID(res.getInt("foodID"));
				food.setFoodName(res.getString("foodName"));
				food.setFoodPrice(res.getInt("foodPrice"));
				food.setFoodNote(res.getString("foodNote"));
				foodList.add(food);
			}
			connection.close();
			return foodList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void add(Food food) {
		String query = "call insertFood(?,?,?,?,?)";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setString(1, food.getFoodName());
			statement.setInt(2, food.getFoodPrice());
			statement.setString(3, food.getFoodNote());
			statement.setString(4, food.getStartingDate());
			statement.setString(5, food.getEndingDate());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delele(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call deleteFood(?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Food getByIDInFood(int id) {
		String query = "{call getByIDInFood(?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			Food food = new Food();
			if (res.next()) {
				food.setFoodID(res.getInt("foodID"));
				food.setFoodName(res.getString("foodName"));
				food.setFoodPrice(res.getInt("foodPrice"));
				food.setFoodNote(res.getString("foodNote"));
				food.setEndingDate(res.getString("endingDate"));
			}
			connection.close();
			return food;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Food getByIDInUpdatedFood(int id) {
		String query = "{call getByIDInUpdatedFood(?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			Food food = new Food();
			if (res.next()) {
				food.setFoodID(res.getInt("foodID"));
				food.setFoodName(res.getString("foodName"));
				food.setFoodPrice(res.getInt("foodPrice"));
				food.setFoodNote(res.getString("foodNote"));
				food.setEndingDate(res.getString("endingDate"));
			}
			connection.close();
			return food;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateOthersInFood(Food food) {
		String query = "{call updateOthersInFood(?,?,?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, food.getFoodID());
			statement.setString(2, food.getFoodName());
			statement.setString(3, food.getFoodNote());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateHasPriceInFood(Food food) {
		String query = "{call updateHasPriceInFood(?,?,?,?,?,?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, food.getFoodID());
			statement.setString(2, food.getFoodName());
			statement.setInt(3, food.getFoodPrice());
			statement.setString(4, food.getFoodNote());
			statement.setString(5, food.getStartingDate());
			statement.setString(6, food.getEndingDate());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateHasPriceInUpdatedFood(Food food) {
		updateOthersInFood(food);
		String query = "{call updateHasPriceInUpdatedFood(?,?,?,?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, food.getFoodID());
			statement.setInt(2, food.getFoodPrice());
			statement.setString(3, food.getStartingDate());
			statement.setString(4, food.getEndingDate());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Food convertJSONtoFoodUpdate(String json) {
		Food food = gson.fromJson(json, Food.class);
		return food;
	}
}

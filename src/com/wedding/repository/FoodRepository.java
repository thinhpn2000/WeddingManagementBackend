package com.wedding.repository;

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

		String queryinFood = "SELECT foodID, foodName, foodPrice, foodNote FROM FOOD WHERE NOT isDeleted AND endingDate IS NULL";
		String queryinUpdatedFood = "SELECT FOOD.foodID, FOOD.foodName, UPDATEDFOOD.foodPrice, FOOD.foodNote FROM FOOD, UPDATEDFOOD WHERE NOT UPDATEDFOOD.isDeleted AND  FOOD.foodID = UPDATEDFOOD.foodID AND UPDATEDFOOD.endingDate IS NULL";

		Connection connection = MySqlConnection.getInstance().getConnection();
		List<Food> foodList = new ArrayList<Food>();
		try {
			PreparedStatement statement = connection.prepareStatement(queryinUpdatedFood);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Food food = new Food();
				food.setFoodID(res.getInt("foodID"));
				food.setFoodName(res.getString("foodName"));
				food.setFoodPrice(res.getInt("foodPrice"));
				food.setFoodNote(res.getString("foodNote"));
		
				foodList.add(food);
			}
			statement = connection.prepareStatement(queryinFood);
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
		String query = "INSERT INTO FOOD(foodName,foodPrice,foodNote,startingDate,endingDate) VALUES (?,?,?,?,?)";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
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
		String queryFood = "UPDATE FOOD SET isDeleted = ? WHERE foodID = ?";
		String queryUpdatedFood = "UPDATE UPDATEDFOOD SET isDeleted = ? WHERE foodID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(queryFood);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			statement = connection.prepareStatement(queryUpdatedFood);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Food getByIDInFood(int id) {
		String query = "SELECT * FROM FOOD WHERE FOOD.foodID = ? AND NOT isDeleted";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
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
		String query = "SELECT FOOD.foodID,FOOD.foodName,UPDATEDFOOD.foodPrice, FOOD.foodNote, UPDATEDFOOD.endingDate FROM FOOD,UPDATEDFOOD WHERE UPDATEDFOOD.foodID=FOOD.foodID AND UPDATEDFOOD.ENDINGDATE IS NULL AND FOOD.foodID = ? AND NOT FOOD.isDeleted";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
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
		String query = "UPDATE FOOD SET foodName = ?, foodNote = ? WHERE foodID = ?";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, food.getFoodName());
			statement.setString(2, food.getFoodNote());
			statement.setInt(3, food.getFoodID());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateHasPriceInFood(Food food) {
		String query = "UPDATE FOOD SET foodName = ?, foodNote = ?, endingDate = ? WHERE foodID = ?";
		String queryInsertInUpdated = "INSERT INTO UPDATEDFOOD(foodID,foodPrice,startingDate,endingDate) VALUES (?,?,?,?)";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, food.getFoodName());
			statement.setString(2, food.getFoodNote());
			statement.setString(3, food.getStartingDate());
			statement.setInt(4, food.getFoodID());
			statement.executeUpdate();
			statement = connection.prepareStatement(queryInsertInUpdated);
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

	public void updateHasPriceInUpdatedFood(Food food) {
		updateOthersInFood(food);
		String query = "UPDATE UPDATEDFOOD SET endingDate = ? WHERE foodID = ? AND endingDate IS NULL";
		String queryInsertInUpdated = "INSERT INTO UPDATEDFOOD(foodID,foodPrice,startingDate,endingDate) VALUES (?,?,?,?)";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, food.getStartingDate());
			statement.setInt(2, food.getFoodID());
			statement.executeUpdate();
			statement = connection.prepareStatement(queryInsertInUpdated);
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

package com.wedding.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Food;

public class FoodRepository {

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
}

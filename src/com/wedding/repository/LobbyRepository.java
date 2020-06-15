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
import com.wedding.models.Lobby;

public class LobbyRepository {
	private Gson gson = new Gson();
	public List<Lobby> getAll() {

		String query = "SELECT lobbyID, lobbyName, lobbyTypeName, maxTable, LOBBY.isDeleted, minPrice, lobbyTypeID FROM TYPE_LOBBY, LOBBY WHERE LOBBY.lobbyType = TYPE_LOBBY.lobbyTypeID AND NOT LOBBY.isDeleted ORDER BY lobbyID ASC;";

		Connection connection = MySqlConnection.getInstance().getConnection();
		List<Lobby> lobbyList = new ArrayList<Lobby>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while (res.next()) {
				Lobby lobby = new Lobby();
				lobby.setLobbyID(res.getInt("lobbyID"));
				lobby.setLobbyName(res.getString("lobbyName"));
				lobby.setLobbyType(res.getString("lobbyTypeName"));
				lobby.setMaxTable(res.getInt("maxTable"));
				lobby.setMinPrice(res.getInt("minPrice"));
				lobby.setDeleted(res.getBoolean("isDeleted"));
				lobby.setLobbyTypeID(res.getInt("lobbyTypeID"));
				lobbyList.add(lobby);
			}
			connection.close();
			return lobbyList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	public void addLobby(Lobby sanh) {
		String query = "INSERT INTO LOBBY(lobbyName, lobbyType, maxTable) VALUES (?, ?, ?);";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
				PreparedStatement prep = connection.prepareStatement(query);
				prep.setString(1, sanh.getLobbyName());
				prep.setInt(2, sanh.getLobbyTypeID());
				prep.setInt(3, sanh.getMaxTable());
				prep.executeUpdate();
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delele(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "UPDATE LOBBY SET isDeleted = ? WHERE lobbyID = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, true);
			statement.setInt(2, id);
			statement.executeUpdate();
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Lobby sanh) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "UPDATE LOBBY SET lobbyName = ?, maxTable = ?, lobbyType = ? WHERE lobbyID = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(query);
			prep.setString(1, sanh.getLobbyName());
			prep.setInt(2, sanh.getMaxTable());
			prep.setInt(3, sanh.getLobbyTypeID());
			prep.setInt(4, sanh.getLobbyID());
			prep.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Lobby convertJSONtoLobbyUpdate(String json) {
		Lobby lobby = gson.fromJson(json, Lobby.class);
		return lobby;
	}
}

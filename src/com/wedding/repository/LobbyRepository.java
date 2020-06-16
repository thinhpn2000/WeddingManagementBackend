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
import com.wedding.models.Lobby;

public class LobbyRepository {
	private Gson gson = new Gson();

	public List<Lobby> getAll() {
		String query = "{call getAllLobby()}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		List<Lobby> lobbyList = new ArrayList<Lobby>();
		try {
			CallableStatement statement = connection.prepareCall(query);
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
		String query = "{call addLobby(?, ?, ?)}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setString(1, sanh.getLobbyName());
			statement.setInt(2, sanh.getLobbyTypeID());
			statement.setInt(3, sanh.getMaxTable());
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delele(int id) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call deleteLobby(?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, id);
			statement.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Lobby sanh) {
		Connection connection = MySqlConnection.getInstance().getConnection();
		String query = "{call updateLobby(?,?,?,?)}";
		try {
			CallableStatement statement = connection.prepareCall(query);
			statement.setInt(1, sanh.getLobbyID());
			statement.setString(2, sanh.getLobbyName());
			statement.setInt(3, sanh.getLobbyTypeID());
			statement.setInt(4, sanh.getMaxTable());
			statement.executeUpdate();
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

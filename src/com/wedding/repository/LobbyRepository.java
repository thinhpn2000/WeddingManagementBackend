package com.wedding.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.Lobby;

public class LobbyRepository {

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
}

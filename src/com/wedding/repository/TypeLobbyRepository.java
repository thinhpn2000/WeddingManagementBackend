package com.wedding.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wedding.databaseconnection.MySqlConnection;
import com.wedding.models.TypeLobby;

public class TypeLobbyRepository {

	public List<TypeLobby> getAll() {
		String query = "{CALL getAllTypeLobby()}";
		Connection connection = MySqlConnection.getInstance().getConnection();
		List<TypeLobby> typeLobbyList = new ArrayList<TypeLobby>();
		try {
			CallableStatement prep = connection.prepareCall(query);
			ResultSet res = prep.executeQuery();
			while(res.next()) {
				TypeLobby typeLobby = new TypeLobby();
				typeLobby.setLobbyTypeID(res.getInt("lobbyTypeID"));
				typeLobby.setLobbyTypeName(res.getString("lobbyTypeName"));
				typeLobby.setMinPrice(res.getInt("minPrice"));
				typeLobby.setDeleted(res.getBoolean("isDeleted"));
				typeLobbyList.add(typeLobby);
			}
			connection.close();
			return typeLobbyList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

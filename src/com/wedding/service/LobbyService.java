package com.wedding.service;

import java.util.List;

import com.wedding.models.Lobby;
import com.wedding.repository.LobbyRepository;

public class LobbyService {
	private LobbyRepository lobbyRepository;

	public LobbyService() {
		lobbyRepository = new LobbyRepository();
	}

	public Lobby convertJSONtoLobbyUpdate(String json) {
		return lobbyRepository.convertJSONtoLobbyUpdate(json);
	}

	public void addLobby(Lobby sanh) {
		lobbyRepository.addLobby(sanh);
	}

	public void deleteLobby(int id) {
		lobbyRepository.delele(id);

	}

	public Lobby getLobbyById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Lobby> getAllLobby() {
		return lobbyRepository.getAll();
	}

	public void updateLobby(Lobby sanh) {
		lobbyRepository.update(sanh);
	}

	public List<Lobby> checkLobby(String weddingDate, int shift) {
		return lobbyRepository.checkLobby(weddingDate, shift);
	}
}

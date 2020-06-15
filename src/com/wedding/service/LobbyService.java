package com.wedding.service;

import com.wedding.models.Lobby;
import com.wedding.repository.LobbyRepository;

public class LobbyService {
	private LobbyRepository lobbyRepository;
	
	public LobbyService(LobbyRepository lobbyRepository) {
		lobbyRepository = new LobbyRepository();
	}

	public Lobby convertJSONtoLobbyUpdate(String json) {
		return lobbyRepository.convertJSONtoLobbyUpdate(json);
	}
}

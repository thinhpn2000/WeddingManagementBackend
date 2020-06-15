package com.wedding.service;

import java.util.List;

import com.wedding.models.TypeLobby;
import com.wedding.repository.TypeLobbyRepository;

public class TypeLobbyService {

	private TypeLobbyRepository typeLobbyRepository;
	public TypeLobbyService() {
		typeLobbyRepository = new TypeLobbyRepository();
	}

	public void addTypeLobby(TypeLobby type) {
		// TODO Auto-generated method stub
		
	}

	public List<TypeLobby> getAllTypeLobby() {
		// TODO Auto-generated method stub
		return typeLobbyRepository.getAll();
	}
}

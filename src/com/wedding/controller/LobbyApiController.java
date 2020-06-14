package com.wedding.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wedding.models.Lobby;
import com.wedding.repository.LobbyRepository;
import com.wedding.utils.UrlConstant;

@WebServlet({UrlConstant.URL_LOBBY, UrlConstant.URL_LOBBY_ADD, UrlConstant.URL_LOBBY_DELETE, UrlConstant.URL_LOBBY_UPDATE})
public class LobbyApiController extends HttpServlet {

	private LobbyRepository lobbyRepository;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		lobbyRepository = new LobbyRepository();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		List<Lobby> lobbies = lobbyRepository.getAll();
		Gson gson = new Gson();
		String data = gson.toJson(lobbies);
		PrintWriter writer = resp.getWriter();
		writer.write(data);
		writer.flush();
	}
}

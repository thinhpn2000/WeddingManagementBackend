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
import com.wedding.models.TypeLobby;
import com.wedding.service.TypeLobbyService;
import com.wedding.utils.UrlConstant;

@WebServlet({UrlConstant.URL_TYPELOBBY})
public class TypeLobbyApiController extends HttpServlet{
	private TypeLobbyService typeLobbyService;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		typeLobbyService = new TypeLobbyService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		switch(servletPath) {
		case UrlConstant.URL_TYPELOBBY:
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			List<TypeLobby> listTypeLobby = typeLobbyService.getAllTypeLobby();
			Gson gson = new Gson();
			String typeLobbyJson = gson.toJson(listTypeLobby);
			PrintWriter writer = resp.getWriter();
			writer.write(typeLobbyJson);
			writer.flush();
			break;
		default:
			break;
		}
	}


}

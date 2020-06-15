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
import com.wedding.models.Service;
import com.wedding.service.ServiceService;
import com.wedding.utils.UrlConstant;

@WebServlet({UrlConstant.URL_SERVICE, UrlConstant.URL_SERVICE_ADD, UrlConstant.URL_SERVICE_DELETE, UrlConstant.URL_SERVICE_UPDATE})
public class ServiceApiController extends HttpServlet {

	private ServiceService serviceService;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		serviceService = new ServiceService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		List<Service> services = serviceService.getAllService();
		Gson gson = new Gson();
		String data = gson.toJson(services);
		PrintWriter writer = resp.getWriter();
		writer.write(data);
		writer.flush();
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	
}

package com.wedding.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wedding.models.Reservation;
import com.wedding.models.ReservationUpdate;
import com.wedding.service.ReservationService;
import com.wedding.utils.UrlConstant;

@WebServlet({UrlConstant.URL_RESERVATION, UrlConstant.URL_RESERVATION_ADD, UrlConstant.URL_RESERVATION_UPDATE, UrlConstant.URL_RESERVATION_DELETE})
public class ReservationApiController extends HttpServlet {

	private ReservationService reservationService;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		reservationService = new ReservationService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		switch(servletPath) {
			case UrlConstant.URL_RESERVATION_ADD:
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("text/html");
				String JSON = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
				Reservation reservation = reservationService.convertJSONtoReservation(JSON);
				reservationService.addReservation(reservation);
				break;
			default:
				break;
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String id = req.getParameter("id");
		switch(servletPath) {
			case UrlConstant.URL_RESERVATION:
				if(id == null) {
					req.setCharacterEncoding("UTF-8");
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					List<ReservationUpdate> reservationUpdate = reservationService.getAllWedding();
					Gson gson = new Gson();
					String data = gson.toJson(reservationUpdate);
					PrintWriter writer = resp.getWriter();
					writer.write(data);
					writer.flush();
				}
				else {
					req.setCharacterEncoding("UTF-8");
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					int weddingID = Integer.parseInt(id);
					ReservationUpdate reservation = reservationService.getReservationById(weddingID);
					Gson gson = new Gson();
					String data = gson.toJson(reservation);
					PrintWriter writer = resp.getWriter();
					writer.write(data);
					writer.flush();
				}
				break;
			default:
				break;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String id = req.getParameter("id");
		switch(servletPath) {
			case UrlConstant.URL_RESERVATION_DELETE:
				if(id != null) {
					req.setCharacterEncoding("UTF-8");
					resp.setCharacterEncoding("UTF-8");
					resp.setContentType("application/json");
					int weddingID = Integer.parseInt(id);
					reservationService.deleteReservation(weddingID);
					break;
				}
				break;
			default:
				break;
		}
	}
	
	
	
	

	
	
}

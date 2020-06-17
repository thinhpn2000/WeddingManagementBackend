package com.wedding.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wedding.models.Reservation;
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

	
	
}

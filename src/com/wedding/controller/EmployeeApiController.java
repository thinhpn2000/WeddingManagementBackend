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
import com.wedding.models.Employee;
import com.wedding.service.EmployeeService;
import com.wedding.utils.UrlConstant;

@WebServlet({UrlConstant.URL_EMPLOYEE})
public class EmployeeApiController extends HttpServlet{
	private EmployeeService employeeService;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		// init only once
		employeeService = new EmployeeService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		switch (servletPath) {
			case UrlConstant.URL_EMPLOYEE:
				// set UTF-8 for req and resp
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				// set json type for resp
				resp.setContentType("application/json");
				// get list employee
				List<Employee> listEmployee = employeeService.getAllEmployee();
				// convert list employee to json
				Gson gson = new Gson();
				String employeeJson = gson.toJson(listEmployee);
				// return for FE this json
				PrintWriter writer = resp.getWriter();
				writer.write(employeeJson);
				writer.flush();
				break;
			default:
				break;
		}
	}


}

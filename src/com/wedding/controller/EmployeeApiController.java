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
import com.wedding.models.Employee;
import com.wedding.service.EmployeeService;
import com.wedding.utils.UrlConstant;

@WebServlet({ UrlConstant.URL_EMPLOYEE, UrlConstant.URL_EMPLOYEE_ADD, UrlConstant.URL_EMPLOYEE_DELETE,
		UrlConstant.URL_EMPLOYEE_UPDATE, UrlConstant.URL_EMPLOYEE_USERNAME, UrlConstant.URL_EMPLOYEE_RESETPASSWORD,
		UrlConstant.URL_UPDATEPASSWORD })
public class EmployeeApiController extends HttpServlet {
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
		case UrlConstant.URL_EMPLOYEE_USERNAME:
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");

			List<String> listUsername = employeeService.getAllUsername();
			Gson json = new Gson();
			String usernameJson = json.toJson(listUsername);
			// return for FE this json
			PrintWriter writerPrint = resp.getWriter();
			writerPrint.write(usernameJson);
			writerPrint.flush();
			break;
		default:
			break;
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String id = req.getParameter("id");
		switch (servletPath) {
		case UrlConstant.URL_EMPLOYEE_DELETE:
			if (id != null) {
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("UTF-8");
				resp.setContentType("application/json");
				int userID = Integer.parseInt(id);
				employeeService.deleteUser(userID);
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();

		switch (servletPath) {
		case UrlConstant.URL_EMPLOYEE_ADD:
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			String JSON = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Employee employee = employeeService.convertJSONToEmployee(JSON);
			employeeService.addEmployee(employee);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		String id = req.getParameter("id");
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		switch (servletPath) {
		case UrlConstant.URL_EMPLOYEE_RESETPASSWORD:
			if (id != null) {
				int userID = Integer.parseInt(id);
				employeeService.resetpasswordUser(userID);
			}
			break;
		case UrlConstant.URL_EMPLOYEE_UPDATE:
			String JSON = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			Employee employee = employeeService.convertJSONToEmployee(JSON);
			employeeService.updateEmployee(employee);
			break;
		case UrlConstant.URL_UPDATEPASSWORD:
			int userID = Integer.parseInt(id);
			String password = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			employeeService.updatePassword(password, userID);
			break;
		default:
			break;
		}
	}

}

package com.wedding.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.wedding.dto.UserDto;
import com.wedding.models.Employee;
import com.wedding.service.EmployeeService;
import com.wedding.utils.UrlConstant;

@WebServlet(UrlConstant.URL_LOGIN)
public class AuthenAccountApiController extends HttpServlet {
	private EmployeeService employeeService;
	

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		employeeService = new EmployeeService();
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Employee employee  = employeeService.getByUsername(username);
		Gson json = new Gson();
		if(BCrypt.checkpw(password, employee.getPassword())) {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			UserDto userDto = new UserDto();
			userDto.setUsername(employee.getUsername());
			userDto.setFullname(employee.getFullname());
			userDto.setAccess(BCrypt.hashpw(employee.getRoleName(), BCrypt.gensalt(13)));
			String JSON = json.toJson(userDto);
			PrintWriter writer = resp.getWriter();
			writer.write(JSON);
			writer.flush();
		}
		else {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json");
			resp.setStatus(400, "Bad Request");
			String JSON = json.toJson("Login Failed");
			PrintWriter writer = resp.getWriter();
			writer.write(JSON);
			writer.flush();
		}
	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
	
	
	
	

	
}

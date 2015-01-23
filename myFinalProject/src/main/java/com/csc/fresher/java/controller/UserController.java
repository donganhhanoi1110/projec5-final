package com.csc.fresher.java.controller;

import java.util.List;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fresher.java.DAO.UserDAO;

@Controller
public class UserController {
	/**
	 * check login by loginId, and password
	 * 
	 * @param request
	 * @param model
	 * @return
	 * 
	 * @author vuhuynh
	 */

	@RequestMapping(value = "/login123",method = RequestMethod.POST)
	public String doLoginCustomer(HttpServletRequest request, Model model) {
		// Read account info from request and save into Account object
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		return "myHome";

	}

}

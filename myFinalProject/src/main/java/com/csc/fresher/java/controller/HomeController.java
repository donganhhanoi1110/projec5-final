package com.csc.fresher.java.controller;

import java.util.List;






import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.service.UserService;



/**
 * This is a controller for home page when user first accesses the application
 * 
 * @author nvu3
 * 
 */

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/home")
	public ModelAndView getAccountList(HttpServletRequest request, Model model) {
		// Create a new AccountDAO

		ModelAndView model1 = new ModelAndView("myHome");

		// Get the list of all accounts from DB
		try {

			return model1;
		} catch (Exception e) {
			model1.addObject("ERROR_CODE", "You get error");
			return model1;

		}

	}
	@RequestMapping(value = "/homePost",method=RequestMethod.GET)
	public ModelAndView getAccountList2(HttpServletRequest request, Model model) {
		// Create a new AccountDAO

		ModelAndView model1 = new ModelAndView("myHome");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		model1.addObject("minh",userService.checkLogin(loginId, password));
		// Get the list of all accounts from DB
		try {

			return model1;
		} catch (Exception e) {
			model1.addObject("ERROR_CODE", "You get error");
			return model1;

		}

	}

}

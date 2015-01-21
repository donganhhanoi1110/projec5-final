package com.csc.fresher.java.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



/**
 * This is a controller for home page when user first accesses the application
 * 
 * @author nvu3
 * 
 */

@Controller
public class HomeController {
	/**
	 * Returns a list of accounts
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home")
	public ModelAndView getAccountList(HttpServletRequest request, Model model) {
		// Create a new AccountDAO

		ModelAndView model1 = new ModelAndView("home");

		// Get the list of all accounts from DB
		try {

			return model1;
		} catch (Exception e) {
			model1.addObject("ERROR_CODE", "You get error");
			return model1;

		}
		// Add the list of accounts to request object which will be then used in
		// jsp to show to user

	}

}

package com.csc.fresher.java.controller;

import java.security.Principal;
import java.util.List;









import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.service.UserService;



/**
 * This is a controller for home page when user first accesses the application
 * 
 * @author nvu3
 * 
 */

@Controller
@SessionAttributes("loginSession")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/home")
	public ModelAndView getAccountList(HttpServletRequest request, Model model,Principal principal) {
		// Create a new AccountDAO

		ModelAndView modelview = new ModelAndView("myHome");
		modelview.addObject("loginSession", principal.getName());
		// Get the list of all accounts from DB
		try {
			
			modelview.addObject("listUser",userService.getAllUser());
			return modelview;
		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}

	}
	
	


}

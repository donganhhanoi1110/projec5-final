package com.csc.fresher.java.controller;

import java.util.List;







import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class HomeController {
	
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/home")
	public ModelAndView getAccountList(HttpServletRequest request, Model model) {
		// Create a new AccountDAO

		ModelAndView model1 = new ModelAndView("myHome");

		// Get the list of all accounts from DB
		try {
			
			model1.addObject("listUser",userService.getAllUser());
			return model1;
		} catch (Exception e) {
			model1.addObject("ERROR_CODE", "You get error");
			return model1;

		}

	}
	
	@RequestMapping(value = "/createUser")
	public ModelAndView createUser(HttpServletRequest request, Model model) {
		// Create a new AccountDAO
String message="";
ModelAndView modelview = new ModelAndView("forward:/home");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String enable=request.getParameter("enable");

		User user=new User(0,username,password,enable);
		// Get the list of all accounts from DB
		System.out.println(user.toString());
		modelview.addObject("ERROR_CODE", user.toString());
		try {
		
			boolean check=userService.createUser(user);
			if(check)
			{
				message="You have created User successfully!!!";
				
				modelview.addObject("message",message);
				
			}
			else{
				message="You have created User FAILED!!!";
				modelview.addObject("message",message);
			}
			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}

	}


}

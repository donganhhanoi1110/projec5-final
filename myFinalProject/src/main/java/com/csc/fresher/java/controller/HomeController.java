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
	/*	boolean check=false;
		System.out.println("\nGet user id=5");
		User user=new User();
		user=userService.getUser(5);
		System.out.println("\nUpdate to minh123");
		user.setUserName("minh");
		
	
		System.out.println("\nDelete user ngan");
		if(userService.deleteUserById(8))
		{
			System.out.println("Delete ngan successfully!!!");
		}
	
		user.setPassword("1234");
		userService.updateUser(user);*/
		
		
		//userService.deleteUserById(9);
		
	
		
		// Get the list of all accounts from DB
		try {
			
			model1.addObject("listUser",userService.getAllUser());
			return model1;
		} catch (Exception e) {
			model1.addObject("ERROR_CODE", "You get error");
			return model1;

		}

	}
	
	


}

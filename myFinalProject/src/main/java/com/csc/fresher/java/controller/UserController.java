package com.csc.fresher.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.dao.UserDAO;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.service.UserService;


@Controller
public class UserController {
	/**
	 * CRUD methods for User using UserService
	 * 
	 * @param request
	 * @param model
	 * @return
	 * 
	 * @author Nguyen ANh Minh
	 */

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login123", method = RequestMethod.POST)
	public String doLoginCustomer(HttpServletRequest request, Model model) {
		// Read account info from request and save into Account object
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		return "myHome";

	}

	@RequestMapping(value = "/createUser")
	public ModelAndView createUser(HttpServletRequest request, Model model) {
		// Create a new AccountDAO
		String message = "";
		ModelAndView modelview = new ModelAndView("forward:/home");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String enable = request.getParameter("enable");

		User user = new User(0, username, password, enable);
		// Get the list of all accounts from DB
		System.out.println(user.toString());

		try {

			boolean check = userService.createUser(user);
			if (check) {
				message = "You have created User successfully!!!";

				modelview.addObject("message", message);

			} else {
				message = "You have created User FAILED!!!";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
			}
			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "0");
			return modelview;

		}

	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request, Model model) {
		
		String message = "";
		ModelAndView modelview = new ModelAndView("forward:/home");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			// Check error when Delete to Database
			if (userService.deleteUserById(userId)) {
				message = "Delete User" + userId + " Successfully";

				model.addAttribute("message", message);
			} else {
				message = "Delete User" + userId + " FAIL";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
			}
		} catch (Exception e) {
			System.out.println("Delete User Controller has Error");
			message = "Delete User Controller has Error";
			modelview.addObject("ERROR_CODE", "0");
			modelview.addObject("message", message);
			return modelview;
		}
		return modelview;

	}

	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(HttpServletRequest request, Model model) {
		// Read account info from request and save into Account object
		String message = "";
		ModelAndView modelview = new ModelAndView("editMyUser");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user = userService.getUser(userId);
			System.out.println(user.toString()+"-Edit User");
			List<User> list= new ArrayList<User>();
			list.add(user);
			model.addAttribute("userProfile", list);

		} catch (Exception e) {
			System.out.println("Edit User Controller has Error");
			message = "Edit User Controller has Error";
			modelview.addObject("ERROR_CODE", "0");
			modelview.addObject("message", message);
			return modelview;
		}
		return modelview;

	}

	@RequestMapping(value = "/editUserProfile")
	public ModelAndView editUserProfile(HttpServletRequest request, Model model) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String enable = request.getParameter("enable");
	
		String message = "";
		ModelAndView modelview = new ModelAndView("forward:/home");
		try {
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user=new User(userId, userName, password, enable);
			// Check error when Update to Database
			if (userService.updateUser(user)) {
				message = "Create User" + userName + " Successfully";

				model.addAttribute("message", message);
			} else {
				message = "Create User" + userName + " FAIL";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
			}
		} catch (Exception e) {
			System.out.println("Create User Controller has Error");
			message = "Create User Controller has Error";
			modelview.addObject("ERROR_CODE", "0");
			modelview.addObject("message", message);
			return modelview;
		}
		return modelview;
	
	}

}

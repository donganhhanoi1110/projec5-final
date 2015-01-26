package com.csc.fresher.java.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.dao.UserDAO;
import com.csc.fresher.java.domain.AjaxResponse;
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
	public String doLoginCustomer(HttpServletRequest request, Model model,
			HttpSession session) {
		// Read account info from request and save into Account object
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		return "myHome";

	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ModelAndView createUser(HttpServletRequest request, Model model,
			HttpSession session) {
		// Create a new AccountDAO

		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("forward:/home");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int enable = Integer.parseInt(request.getParameter("enable"));

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
		} else {
			return new ModelAndView("redirect:/login");
		}

	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request, Model model,
			HttpSession session) {
		if (session.getAttribute("loginSession") != null) {
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
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/deleteUserJson")
	public @ResponseBody AjaxResponse deleteUser(HttpServletRequest request,
			HttpSession session) {
		// Read account info from request and save into Account object
		int userId = Integer.parseInt(request.getParameter("userId"));
		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;
		if (session.getAttribute("loginSession") != null) {
			try {

				// Check error when Delete to Database
				if (userService.deleteUserById(userId)) {
					message = "Delete User" + userId + " Successfully";
					error_code = "1";
					check = true;

				} else {
					message = "Delete User" + userId + " FAIL";
					error_code = "0";
					check = false;

				}
			} catch (Exception e) {
				System.out.println("Delete User Controller has Error");
				message = "Delete User Controller has Error";
				error_code = "0";
				check = false;

			}

			response.setSuccess(check);
			response.setMessage(message);
			response.setError_code(error_code);
			response.setLogin(true);

		} else {
			response.setLogin(false);
		}
		return response;
	}

	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(HttpServletRequest request, Model model,
			HttpSession session) {
		// Read account info from request and save into Account object
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("editMyUser");
			try {
				int userId = Integer.parseInt(request.getParameter("userId"));
				User user = userService.getUser(userId);
				System.out.println(user.toString() + "-Edit User");
				List<User> list = new ArrayList<User>();
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
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/editUserProfile", method = RequestMethod.POST)
	public ModelAndView editUserProfile(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/home");

		String message = "";
	
		if (session.getAttribute("loginSession") != null) {
			try {
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				int enable = Integer.parseInt(request.getParameter("enable"));


				int userId = Integer.parseInt(request.getParameter("userId"));
				User user = new User(userId, userName, password, enable);
				// Check error when Update to Database
				if (userService.updateUser(user)) {
					message = "Edit User *_" + userName + "_* Successfully";

					model.addAttribute("message", message);
				} else {
					message = "Edit User" + userName + " FAIL";
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Create User Controller has Error");
				message = "Edit User Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

}

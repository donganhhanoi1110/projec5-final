package com.csc.fresher.java.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.domain.UserRole;
import com.csc.fresher.java.service.SavingAccountService;
import com.csc.fresher.java.service.TransactionService;
import com.csc.fresher.java.service.UserRoleService;
import com.csc.fresher.java.service.UserService;

/**
 * This is a controller for home page when user first accesses the application
 * 
 * @author Nguyen ANh Minh
 * 
 */

@Controller
@SessionAttributes("loginSession")
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private SavingAccountService savingaccountService;

	@RequestMapping(value = "/homeUser")
	public ModelAndView getAccountList(HttpServletRequest request, Model model) {
		// Create a new AccountDAO
		String error_code = request.getParameter("ERROR_CODE");
		ModelAndView modelview = new ModelAndView("homeUser");

		modelview.addObject("ERROR_CODE", error_code);
		// Get the list of all accounts from DB
		try {

			modelview.addObject("listUser", userService.getAllUser());

			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}

	}

	@RequestMapping(value = "/home")
	public ModelAndView Home(HttpServletRequest request, Model model,
			Principal principal) {
		// Create a new AccountDAO
		String error_code = request.getParameter("ERROR_CODE");
		ModelAndView modelview = new ModelAndView("home");
		try {
			modelview.addObject("loginSession", principal.getName());

		} catch (Exception e) {
			return new ModelAndView("redirect:/login");
		}
		modelview.addObject("ERROR_CODE", error_code);
		// Get the list of all accounts from DB
		try {
			List<SavingAccount> save1 = savingaccountService
					.getSavingAccountByCustomerIDNumber("024939082");
			List<SavingAccount> save2 = savingaccountService
					.getSavingAccountByNumber(123456);

			for (SavingAccount s : save1) {
				System.out.println("Save1: " + s.getSavingAccountNumber());
			}
			for (SavingAccount s : save2) {
				System.out.println("Save2: " + s.getSavingAccountNumber());
			}

			modelview.addObject("listUser", userService.getAllUser());

			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}

	}

}

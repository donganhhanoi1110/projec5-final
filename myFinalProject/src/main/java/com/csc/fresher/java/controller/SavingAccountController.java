package com.csc.fresher.java.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.service.CustomerService;
import com.csc.fresher.java.service.SavingAccountService;
import com.csc.fresher.java.service.UserService;

@Controller
public class SavingAccountController {

	@Autowired
	private SavingAccountService savingAccountService;
	@Autowired
	private CustomerService customerService;

	// @RequestMapping(value = "/homeSavingAccount")
	// public ModelAndView getAccountList(HttpServletRequest request, Model
	// model, HttpSession session) {
	// // Create a new AccountDAO
	// if (session.getAttribute("loginSession") != null) {
	// User user=null;
	// String username = request.getSession()
	// .getAttribute("loginSession").toString();
	// String error_code = request.getParameter("ERROR_CODE");
	// String message = request.getParameter("message");
	// ModelAndView modelview = new ModelAndView("homeSavingAccount");
	//
	// model.addAttribute("ERROR_CODE", error_code);
	// model.addAttribute("message", message);
	// // Get the list of all accounts from DB
	//
	// try {
	// List<SavingAccount> savingAccountList =
	// savingAccountService.getSavingAccountList();
	// model.addAttribute("listSavingAccount",savingAccountList);
	// // user=UserService.getUserbyUserName(username);
	// //
	// // model.addAttribute("listSavingAccount",
	// // SavingAccountService.getAllSavingAccount());
	//
	// } catch (Exception e) {
	// model.addAttribute("ERROR_CODE", "0");
	// model.addAttribute("message",
	// "You get Error *Home SavingAccount*");
	//
	// }
	// return modelview;
	// } else {
	// return new ModelAndView("redirect:/homeSavingAccount");
	// }
	// }

	@RequestMapping(value = { "/homeSavingAccount" })
	public ModelAndView getHome(HttpServletRequest request) {

		List<SavingAccount> savingAccountList = savingAccountService
				.getSavingAccountList();
		// homeSavingAccount la o trong file tile.xml
		ModelAndView modelAndView = new ModelAndView("homeSavingAccount");
		modelAndView.addObject("listSavingAccount", savingAccountList);
		// add preregisteredAccountList above to the object
		// preregisteredAccountList on view preregisteredAccountList
		// // modelAndView.addObject("listCustomer", customerList);
		return modelAndView;
	}

	@RequestMapping(value = "/createSavingAccount", method = RequestMethod.POST)
	public ModelAndView createSavingAccount(HttpServletRequest request,
			Model model, HttpSession session) {
		// Create a new AccountDAO
		User user = null;
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView(
					"forward:/homeSavingAccount");
			String username = request.getSession().getAttribute("loginSession")
					.toString();

			// float transactionAmount = Float.parseFloat(request
			// .getParameter("transactionAmount"));
			// String dateStart = request.getParameter("transactionDateStart");
			// String dateEnd = request.getParameter("transactionDateEnd");
			// int savingAccountId = Integer.parseInt(request
			// .getParameter("transactionSavingAccountId"));
			// String state = request.getParameter("transactionState");
			//
			// SavingAccount SavingAccount = new SavingAccount(0,
			// transactionAmount,
			// dateStart, dateEnd, savingAccountId, state);

			float balanceAmount = Float.parseFloat(request
					.getParameter("balanceAmount"));
			int repeatable = Integer.parseInt(request
					.getParameter("repeatable"));

			String state = request.getParameter("state");

			try {

				return modelview;

			} catch (Exception e) {
				model.addAttribute("ERROR_CODE", "0");
				return modelview;

			}
		} else {
			return new ModelAndView("redirect:/login");
		}

	}

}

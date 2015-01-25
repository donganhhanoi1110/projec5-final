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

import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.service.TransactionService;
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
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/home")
	public ModelAndView getAccountList(HttpServletRequest request, Model model,
			Principal principal) {
		// Create a new AccountDAO
		String error_code= request.getParameter("ERROR_CODE");
		ModelAndView modelview = new ModelAndView("myHome");
		modelview.addObject("loginSession", principal.getName());
		modelview.addObject("ERROR_CODE",error_code);
		// Get the list of all accounts from DB
		try {

			modelview.addObject("listUser", userService.getAllUser());

//			/**
//			 * For Test @Many to many
//			 */
//			Transaction tran1 = new Transaction(0, 20000, "20/1/2015",
//					"20/1/2015", "new");
//			Transaction tran2 = new Transaction(0, 10000, "20/1/2015",
//					"20/1/2015", "active");
//		
//			//Add  transaction to database
//			transactionService.createTransaction(tran1);
//			transactionService.createTransaction(tran2);
//			Collection<Transaction> tranSet1 = new HashSet<Transaction>();
//			tranSet1.add(tran1);
//			tranSet1.add(tran2);
//			Collection<Transaction> tranSet2 = new HashSet<Transaction>();
//			tranSet2.add(tran2);
//			tranSet2.add(tran1);
//			User user1 = new User(0, "user1", "123", "1",tranSet1);
//			User user2 = new User(0, "user2", "1234", "1",tranSet2);
//			//Add user to database
//			userService.createUser(user1);
//			userService.createUser(user2);
			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}

	}

}

package com.csc.fresher.java.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.AjaxResponse;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.domain.UserRole;
import com.csc.fresher.java.service.TransactionService;
import com.csc.fresher.java.service.UserRoleService;
import com.csc.fresher.java.service.UserService;

@Controller
public class TransactionController {
	/**
	 * CRUD methods for Transaction using transactionService
	 * 
	 * @param request
	 * @param model
	 * @return
	 * 
	 * @author Nguyen ANh Minh
	 */

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserRoleService userRoleService;

	@RequestMapping(value = "/homeTransaction")
	public ModelAndView getAccountList(HttpServletRequest request, Model model,
			HttpSession session) {
		// Create a new AccountDAO
		if (session.getAttribute("loginSession") != null) {
			User user = null;
			String username = request.getSession().getAttribute("loginSession")
					.toString();
			String error_code = request.getParameter("ERROR_CODE");
			String message = request.getParameter("message");
			ModelAndView modelview = new ModelAndView();

			model.addAttribute("ERROR_CODE", error_code);
			model.addAttribute("message", message);
			// Get the list of all accounts from DB

			try {
				List<Transaction> listAllTransaction=transactionService.getAllTransaction();
				model.addAttribute("listTransaction",listAllTransaction);
				String myrole = userRoleService
						.getUserRolebyUserRoleName(username);
				System.out.println("My role"+myrole);
				if ("admin".equals(myrole)) {
					List<Transaction> listHoldTransaction = transactionService
							.getTransactionByState("hold");
					model.addAttribute("listHoldTransaction",listHoldTransaction);
					modelview.setViewName("adminTransaction");
				} else {
					if ("support".equals(myrole)) {
						List<Transaction> listActiveTransaction = transactionService
								.getTransactionByState("active");
						List<Transaction> listNewTransaction = transactionService
								.getTransactionByState("new");
						model.addAttribute("listActiveTransaction",listActiveTransaction);
						model.addAttribute("listNewTransaction",listNewTransaction);
						modelview.setViewName("supportTransaction");
					}
				}
				

			} catch (Exception e) {
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message",
						"You get Error *Home Transaction*");

			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public ModelAndView createTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		// Create a new AccountDAO
		User user = null;
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView(
					"forward:/homeTransaction");
			String username = request.getSession().getAttribute("loginSession")
					.toString();
			float transactionAmount = Float.parseFloat(request
					.getParameter("transactionAmount"));
			String dateStart = request.getParameter("transactionDateStart");
			String dateEnd = request.getParameter("transactionDateEnd");
			int savingAccountId = Integer.parseInt(request
					.getParameter("transactionSavingAccountId"));
			String state = request.getParameter("transactionState");

			Transaction Transaction = new Transaction(0, transactionAmount,
					dateStart, dateEnd, savingAccountId, state);

			try {

				boolean check = transactionService
						.createTransaction(Transaction);
				if (check) {

					// Add to table transactionuser in database
					user = userService.getUserbyUserName(username);
					Collection<User> userSets = Transaction.getTransactions();
					userSets.add(user);
					Transaction.setTransactions(userSets);
					transactionService.updateTransaction(Transaction);
					message = "You have created Transaction successfully!!!";

					model.addAttribute("message", message);

				} else {
					message = "You have created Transaction FAILED!!!";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}

				return modelview;

			} catch (Exception e) {
				model.addAttribute("ERROR_CODE", "0");
				return modelview;

			}
		} else {
			return new ModelAndView("redirect:/login");
		}

	}

	@RequestMapping(value = "/deleteTransaction")
	public ModelAndView deleteTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView(
					"forward:/homeTransaction");

			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));
				// Check error when Delete to Database
				if (transactionService.deleteTransactionById(TransactionId)) {
					message = "Delete Transaction" + TransactionId
							+ " Successfully";

					model.addAttribute("message", message);
				} else {
					message = "Delete Transaction" + TransactionId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Delete Transaction Controller has Error");
				message = "Delete Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/deleteTransactionJson")
	public @ResponseBody AjaxResponse deleteTransaction(
			HttpServletRequest request, HttpSession session) {
		// Read account info from request and save into Account object
		int TransactionId = Integer.parseInt(request
				.getParameter("TransactionId"));
		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;
		if (session.getAttribute("loginSession") != null) {
			try {

				// Check error when Delete to Database
				if (transactionService.deleteTransactionById(TransactionId)) {
					message = "Delete Transaction" + TransactionId
							+ " Successfully";
					error_code = "1";
					check = true;

				} else {
					message = "Delete Transaction" + TransactionId + " FAIL";
					error_code = "0";
					check = false;

				}
			} catch (Exception e) {
				System.out.println("Delete Transaction Controller has Error");
				message = "Delete Transaction Controller has Error";
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

	@RequestMapping(value = "/editTransaction")
	public ModelAndView editTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		// Read account info from request and save into Account object
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("editMyTransaction");
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));
				Transaction Transaction = transactionService
						.getTransaction(TransactionId);
				System.out
						.println(Transaction.toString() + "-Edit Transaction");
				List<Transaction> list = new ArrayList<Transaction>();
				list.add(Transaction);

				model.addAttribute("TransactionProfile", list);

			} catch (Exception e) {
				System.out.println("Edit Transaction Controller has Error");
				message = "Edit Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}

			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/editTransactionProfile", method = RequestMethod.POST)
	public ModelAndView editTransactionProfile(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeTransaction");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("transactionId"));
				float transactionAmount = Float.parseFloat(request
						.getParameter("transactionAmount"));
				String dateStart = request.getParameter("transactionDateStart");
				String dateEnd = request.getParameter("transactionDateEnd");
				int savingAccountId = Integer.parseInt(request
						.getParameter("transactionSavingAccountId"));
				String state = request.getParameter("transactionState");

				Transaction Transaction = new Transaction(TransactionId,
						transactionAmount, dateStart, dateEnd, savingAccountId,
						state);
				// Check error when Update to Database
				if (transactionService.updateTransaction(Transaction)) {
					message = "Edit Transaction *_" + TransactionId
							+ "_* Successfully";

					model.addAttribute("message", message);
				} else {
					message = "Edit Transaction" + TransactionId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Edit Transaction Controller has Error");
				message = "Edit Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/approveTransaction")
	public ModelAndView approveTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeTransaction");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));

				Transaction tran = transactionService
						.getTransaction(TransactionId);
				tran.setState("active");
				// Check error when Update to Database
				if (!transactionService.updateTransaction(tran)) {
					message = "Approve Transaction" + TransactionId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Approve Transaction Controller has Error");
				message = "Approve Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/denyTransaction")
	public ModelAndView denyTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeTransaction");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));

				Transaction tran = transactionService
						.getTransaction(TransactionId);
				tran.setState("deny");
				// Check error when Update to Database
				if (!transactionService.updateTransaction(tran)) {
					message = "Deny Transaction" + TransactionId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Deny Transaction Controller has Error");
				message = "Deny Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/submitTransaction")
	public ModelAndView submitTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeTransaction");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));

				Transaction tran = transactionService
						.getTransaction(TransactionId);
				tran.setState("hold");
				// Check error when Update to Database
				if (!transactionService.updateTransaction(tran)) {
					message = "Submit Transaction" + TransactionId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Submit Transaction Controller has Error");
				message = "Submit Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping(value = "/sendTransaction")
	public ModelAndView sendTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeTransaction");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));

				Transaction tran = transactionService
						.getTransaction(TransactionId);
				tran.setState("done");
				// Check error when Update to Database
				if (!transactionService.updateTransaction(tran)) {
					message = "Send Transaction" + TransactionId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Send Transaction Controller has Error");
				message = "Send Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	@RequestMapping(value = "/viewTransaction")
	public ModelAndView viewTransaction(HttpServletRequest request, Model model,
			HttpSession session) {
		// Create a new AccountDAO
		if (session.getAttribute("loginSession") != null) {
			ModelAndView modelview=new ModelAndView("viewTransaction");

			try {
				List<Transaction> listAllTransaction=transactionService.getAllTransaction();
				model.addAttribute("listTransaction",listAllTransaction);
			

			} catch (Exception e) {
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message",
						"You get Error *View Transaction*");

			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	@RequestMapping(value = "/viewOneTransaction")
	public ModelAndView viewOneTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		// Read account info from request and save into Account object
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("viewOneTransaction");
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));
				Transaction Transaction = transactionService
						.getTransaction(TransactionId);
				System.out
						.println(Transaction.toString() + "-Edit Transaction");
				List<Transaction> list = new ArrayList<Transaction>();
				list.add(Transaction);

				model.addAttribute("TransactionProfile", list);

			} catch (Exception e) {
				System.out.println("Edit Transaction Controller has Error");
				message = "Edit Transaction Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}

			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
}

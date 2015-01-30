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
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.AjaxResponse;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.domain.UserRole;
import com.csc.fresher.java.service.SavingAccountService;
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

	@Autowired
	private SavingAccountService savingAccountService;

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

			modelview.addObject("ERROR_CODE", error_code);
			modelview.addObject("message", message);
			// Get the list of all accounts from DB

			try {

				modelview.addObject("transaction", new Transaction());

				String myrole = userRoleService
						.getUserRolebyUserRoleName(username);
				System.out.println("My role" + myrole);
				if ("admin".equals(myrole)) {
					List<Transaction> listHoldTransaction = transactionService
							.getTransactionByState("hold");
					modelview.addObject("listHoldTransaction",
							listHoldTransaction);
					modelview.setViewName("adminTransaction");
				} else {
					if ("support".equals(myrole)) {
						List<SavingAccount> savingAccountlist = savingAccountService
								.getSavingAccountList();

						List<Transaction> listActiveTransaction = transactionService
								.getTransactionByState("active");
						List<Transaction> listNewTransaction = transactionService
								.getTransactionByState("new");
						modelview.addObject("listActiveTransaction",
								listActiveTransaction);
						modelview.addObject("listNewTransaction",
								listNewTransaction);
						modelview.addObject("savingaccountlist",
								savingAccountlist);
						modelview.setViewName("supportTransaction");
					}
				}

			} catch (Exception e) {
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message",
						"You get Error *Home Transaction*");

			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/createTransactionAttribute", method = RequestMethod.POST)
	public ModelAndView createTransactionAttribute(
			@ModelAttribute Transaction transaction,
			HttpServletRequest request, Model model, HttpSession session) {
		// Create a new AccountDAO
		User user = null;
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView(
					"forward:/homeTransaction");
			try {
				boolean check = transactionService
						.createTransaction(transaction);
				if (check) {
					String username = request.getSession()
							.getAttribute("loginSession").toString();
					// Add to table transactionuser in database bcz @ManyToMany
					user = userService.getUserbyUserName(username);
					Collection<User> userSets = transaction.getTransactions();
					userSets.add(user);
					transaction.setTransactions(userSets);
					transactionService.updateTransaction(transaction);
					message = "You have created Transaction successfully!!!";

					modelview.addObject("message", message);

				} else {
					message = "You have created Transaction FAILED!!!";
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

					modelview.addObject("message", message);
				} else {
					message = "Delete Transaction" + TransactionId + " FAIL";
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Delete Transaction Controller has Error");
				message = "Delete Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
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
			Model model, HttpSession session,
			@ModelAttribute Transaction transaction) {
		// Read account info from request and save into Account object
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("editMyTransaction");
			try {
				int TransactionId = Integer.parseInt(request
						.getParameter("TransactionId"));
				Transaction Transaction = transactionService
						.getTransaction(TransactionId);
				List<SavingAccount> savingAccountlist = savingAccountService
						.getSavingAccountList();

				String[] states = { "new", "hold", "active", "done" };
				String[] transactiontype = { "deposit", "withdraw" };

				modelview.addObject("savingaccountlist", savingAccountlist);
				modelview.addObject("states", states);
				modelview.addObject("transactiontype", transactiontype);

				modelview.addObject("transaction", Transaction);

			} catch (Exception e) {
				System.out.println("Edit Transaction Controller has Error");
				message = "Edit Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
				return modelview;
			}

			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/editTransactionProfile", method = RequestMethod.POST)
	public ModelAndView editTransactionProfile(HttpServletRequest request,
			Model model, HttpSession session,
			@ModelAttribute Transaction transaction) {
		ModelAndView modelview = new ModelAndView("forward:/homeTransaction");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {

				String username = request.getSession()
						.getAttribute("loginSession").toString();
				// Add to table transactionuser in database bcz @ManyToMany
				User user = userService.getUserbyUserName(username);
				Collection<User> userSets = transaction.getTransactions();
				userSets.add(user);
				transaction.setTransactions(userSets);

				// Check error when Update to Database
				if (transactionService.updateTransaction(transaction)) {
					System.out.println("Update Successfully");
					message = "Edit Transaction *_" + transaction.getId()
							+ "_* Successfully";

					modelview.addObject("message", message);
				} else {
					System.out.println("Update Failed");
					message = "Edit Transaction" + transaction.getId()
							+ " FAIL";
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Edit Transaction Controller has Error");
				message = "Edit Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
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
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Approve Transaction Controller has Error");
				message = "Approve Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
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
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Deny Transaction Controller has Error");
				message = "Deny Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
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
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Submit Transaction Controller has Error");
				message = "Submit Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
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
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
			} catch (Exception e) {
				System.out.println("Send Transaction Controller has Error");
				message = "Send Transaction Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/viewTransaction")
	public ModelAndView viewTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		// Create a new AccountDAO
		if (session.getAttribute("loginSession") != null) {
			ModelAndView modelview = new ModelAndView("viewTransaction");

			try {
				List<Transaction> listAllTransaction = transactionService
						.getAllTransaction();
				modelview.addObject("listTransaction", listAllTransaction);

			} catch (Exception e) {
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message",
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

				modelview.addObject("TransactionProfile", list);

			} catch (Exception e) {
				System.out.println("Edit Transaction Controller has Error");
				message = "Edit Transaction Controller has Error";
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

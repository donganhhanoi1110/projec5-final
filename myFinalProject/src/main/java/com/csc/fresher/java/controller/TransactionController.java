package com.csc.fresher.java.controller;

import java.security.Principal;
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

import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.AjaxResponse;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.service.TransactionService;

@Controller
public class TransactionController {
	/**
	 * CRUD methods for Transaction using TransactionService
	 * 
	 * @param request
	 * @param model
	 * @return
	 * 
	 * @author Nguyen ANh Minh
	 */

	@Autowired
	private TransactionService TransactionService;

	@RequestMapping(value = "/homeTransaction")
	public ModelAndView getAccountList(HttpServletRequest request, Model model,
			Principal principal) {
		// Create a new AccountDAO
		String error_code = request.getParameter("ERROR_CODE");
		ModelAndView modelview = new ModelAndView("homeTransaction");
		modelview.addObject("loginSession", principal.getName());
		modelview.addObject("ERROR_CODE", error_code);
		// Get the list of all accounts from DB
		try {

			modelview.addObject("listTransaction",
					TransactionService.getAllTransaction());
			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}

	}

	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public ModelAndView createTransaction(HttpServletRequest request,
			Model model, HttpSession session) {
		// Create a new AccountDAO

		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("forward:/home");
			int TransactionId = Integer.parseInt(request
					.getParameter("TransactionId"));
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

			try {

				boolean check = TransactionService
						.createTransaction(Transaction);
				if (check) {
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
				if (TransactionService.deleteTransactionById(TransactionId)) {
					message = "Delete Transaction" + TransactionId
							+ " Successfully";

					model.addAttribute("message", message);
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
				if (TransactionService.deleteTransactionById(TransactionId)) {
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
				Transaction Transaction = TransactionService
						.getTransaction(TransactionId);
				System.out
						.println(Transaction.toString() + "-Edit Transaction");
				List<Transaction> list = new ArrayList<Transaction>();
				list.add(Transaction);
				model.addAttribute("TransactionProfile", list);

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
				if (TransactionService.updateTransaction(Transaction)) {
					message = "Edit Transaction *_" + TransactionId
							+ "_* Successfully";

					model.addAttribute("message", message);
				} else {
					message = "Edit Transaction" + TransactionId + " FAIL";
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
				
				Transaction tran=TransactionService.getTransaction(TransactionId);
				tran.setState("done");
				// Check error when Update to Database
				if (!TransactionService.updateTransaction(tran)) {
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
				
				Transaction tran=TransactionService.getTransaction(TransactionId);
				tran.setState("deny");
				// Check error when Update to Database
				if (!TransactionService.updateTransaction(tran)) {
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

}

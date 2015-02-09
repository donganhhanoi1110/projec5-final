package com.csc.fresher.java.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
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
import com.csc.fresher.java.domain.InterestRate;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.domain.UserRole;
import com.csc.fresher.java.service.InterestRateService;
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

	@Autowired
	private InterestRateService interestRateService;

	@RequestMapping(value = "/homeTransaction")
	public ModelAndView getAccountList(HttpServletRequest request, Model model,
			HttpSession session) {
		// Create a new AccountDAO
		if (session.getAttribute("loginSession") != null) {

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
				System.out.println("My role " + myrole);
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

						List<Transaction> listNewTransaction = transactionService
								.getTransactionByState("done");

						modelview.addObject("listDoneTransaction",
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
			Date dateStart = new Date();
			SavingAccount savingAccount = transactionService
					.getAccountbyTranID(transaction);
			float currentBalance = savingAccount.getBalanceAmount();

			transaction.setCurrentBalance(currentBalance);
			transaction.setDateStart(savingAccountService
					.convertDateToString(dateStart));
			transaction.setState("hold");

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

	@RequestMapping(value = "/viewListTransactionJson", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse viewListTransactionJson(
			HttpServletRequest request, Model model, HttpSession session) {
		// Create a new AccountDAO
		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;

		if (session.getAttribute("loginSession") != null) {
			int savingAccountNumber = Integer.parseInt(request
					.getParameter("savingAccountNumber"));
			try {
				SavingAccount savingAccount = savingAccountService
						.getSavingAccountByNumber(savingAccountNumber);
				List<Transaction> transactions = transactionService
						.getTransactionBySavingAccountNumber(savingAccountNumber);
				List<Transaction> newTrans = new ArrayList<Transaction>();
				// Business With Saving Account
				for (Transaction tran : transactions) {
					tran.setTransactions(null);
					tran.setSavingAccountId(null);
					newTrans.add(tran);
				}

				// Check error when Delete to Database
				if (transactions.size() > 0) {
					response.setListTransactions(newTrans);
					// Create transaction of this saving account to admin
					savingAccount.setCustomerId(null);
					savingAccount.setInterestRateId(null);
					savingAccount.setTransactions(null);
					response.setSavingAccount(savingAccount);
					message = "Get Transactions" + " Successfully";
					error_code = "1";

					check = true;

				} else {
					message = "Getting Transactions has Failed, No Transactions Found!!!";
					error_code = "0";
					check = false;

				}
			} catch (Exception e) {
				System.out.println("Get TransactionsJson Controller has Error");
				message = "Getting Transactions has Failed, No Transactions Found!!!";
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

	@RequestMapping(value = "/getSavingAccountInfoJson", method = RequestMethod.POST)
	public @ResponseBody SavingAccount getSavingAccountInfoJson(
			HttpServletRequest request, Model model, HttpSession session) {

		if (session.getAttribute("loginSession") != null) {
			SavingAccount saving = null;

			int number = Integer.parseInt(request.getParameter

			("savingAccountNumber"));

			try {
				saving = savingAccountService.getSavingAccountByNumber

				(number);

				if (saving == null) {

					// Create transaction of this saving account to admin

					return null;

				} else {
					saving.setTransactions(null);
					saving.getCustomerId().setSavingaccounts(null);
					saving.getInterestRateId().setSavingaccounts(null);
					return saving;
				}
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/createTransactionJson", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse createTransactionJson(
			HttpServletRequest request, Model model, HttpSession session,
			@ModelAttribute Transaction transaction) {
		// Create a new AccountDAO

		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;
		request.getParameter("chooseAmmount");
		if (session.getAttribute("loginSession") != null) {
			Date dateStart = new Date();
			SavingAccount savingAccount = savingAccountService
					.getSavingAccountByNumber(transaction.getSavingAccountId()
							.getSavingAccountNumber());
			// checking have transaction or not
			List<Transaction> transactions = transactionService
					.getTransactionBySavingAccountNumber(transaction
							.getSavingAccountId().getSavingAccountNumber());
			boolean checkHold = false;
			for (Transaction a : transactions) {
				if (a.getState().equalsIgnoreCase("hold")) {
					checkHold = true;
					break;
				}
			}
			if (checkHold == false) {
				float currentBalance = savingAccount.getBalanceAmount();
				if (transaction.getTransactionType().equals("withdraw")) {
					Date startWithdraw = new Date();
					float totalAmount = 0;
					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd/MM/yyyy hh:mm");
					try {
						Date dateStar = formatter.parse(savingAccount
								.getDateStart());
						Date dateEnd = formatter.parse(savingAccount
								.getDateEnd());

						float interestPerDay = 0;
						float interest = 0;
						if (startWithdraw.compareTo(dateEnd) >= 0) {
							interestPerDay = ((savingAccount
									.getInterestRateId().getInterestRate()) / 360) / 100;

						} else {
							List<InterestRate> interestRate = interestRateService
									.getInterestRateList();
							for (InterestRate a : interestRate) {
								if (a.getMonth() == 0) {
									interest = a.getInterestRate();
									break;
								}
							}
							interestPerDay = (interest / 360) / 100;
						}

						int days = Days.daysBetween(new DateTime(dateStar),
								new DateTime(startWithdraw)).getDays();
						totalAmount = savingAccount.getBalanceAmount()
								+ savingAccount.getBalanceAmount()
								* interestPerDay * days;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					transaction.setCurrentBalance(totalAmount);
					if (transaction.getAmount() <= totalAmount) {
						if (request.getParameter("chooseAmmount").equals("all")) {
							transaction.setTransactionType("withdrawAll");
							transaction.setAmount(totalAmount);
						}
						if (request.getParameter("chooseAmmount").equals(
								"apart")) {
							transaction.setTransactionType("withdraw");
						}
					} else {
						message = "The amount withdraw is greater the amount of Saving Account";
						error_code = "0";
						check = false;

						response.setSuccess(check);
						response.setMessage(message);
						response.setError_code(error_code);
						response.setLogin(true);
						return response;
					}
				}
				if (transaction.getTransactionType()
						.equalsIgnoreCase("deposit")) {
					transaction.setCurrentBalance(currentBalance);
				}
				transaction.setDateStart(savingAccountService
						.convertDateToString(dateStart));
				transaction.setState("hold");

				try {
					System.out.println("Json Saving Account: "
							+ transaction.toString());

					// Business With Saving Account

					if (transaction.getAmount() > 10000000) {
						transaction.setState("hold");
					}
					transaction.setSavingAccountId(savingAccount);
					// Check error when Delete to Database
					if (transactionService.createTransaction(transaction)) {

						String username = request.getSession()
								.getAttribute("loginSession").toString();
						// Add to table transactionuser in database bcz
						// @ManyToMany
						User user = userService.getUserbyUserName(username);
						Collection<User> userSets = transaction
								.getTransactions();
						userSets.add(user);
						transaction.setTransactions(userSets);
						transactionService.updateTransaction(transaction);
						message = "Create Transaction" + " Successfully";
						error_code = "1";
						check = true;
					} else {
						message = "Create Transaction" + " FAIL";
						error_code = "0";
						check = false;
					}
				} catch (Exception e) {
					System.out
							.println("Create TransactionJson Controller has Error");
					message = "Create TransactionJson Controller has Error";
					error_code = "0";
					check = false;
				}
				response.setSuccess(check);
				response.setMessage(message);
				response.setError_code(error_code);
				response.setLogin(true);
			} else {
				response.setSuccess(false);
				message = " Hold Transaction is being available!!!";
				response.setMessage(message);
				response.setError_code("0");
				response.setLogin(true);
			}

		} else {
			response.setLogin(false);
		}
		return response;
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
				if (tran.getTransactionType().startsWith("withdraw")) {

					if (!transactionService.ApproveWithdraw(tran)) {
						message = "Approve Transaction" + TransactionId
								+ " FAIL";
						modelview.addObject("ERROR_CODE", "0");
						modelview.addObject("message", message);
					}
				}
				if (tran.getTransactionType().equals("deposit")) {
					if (!transactionService
							.approveTransacsionAdmin(TransactionId)) {
						message = "Approve Transaction" + TransactionId
								+ " FAIL";
						modelview.addObject("ERROR_CODE", "0");
						modelview.addObject("message", message);
					}
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
				tran.setDateEnd(savingAccountService.convertDateToString(new Date()));
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

	@RequestMapping(value = "/searchTransaction", method = RequestMethod.POST)
	public ModelAndView createTransactionAttribute(HttpServletRequest request,
			Model model, HttpSession session) {
		// Create a new AccountDAO
		User user = null;
		String dateStart = request.getParameter("dateStart") + " 00:00:00";
		String dateEnd = request.getParameter("dateEnd") + " 00:00:00";
		System.out.println("Date Format" + dateStart);
		ModelAndView modelAndView = new ModelAndView("searchTran");
		if (session.getAttribute("loginSession") != null) {

			List<Transaction> listTrans = new ArrayList<Transaction>();
			List<Transaction> listTransaction = transactionService
					.getAllTransaction();
			for (Transaction trans : listTransaction) {
				if (transactionService.checkDate(dateStart, dateEnd,
						trans.getDateStart()) == true) {
					listTrans.add(trans);
				}
			}

			modelAndView.addObject("listTrans", listTrans);
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

}

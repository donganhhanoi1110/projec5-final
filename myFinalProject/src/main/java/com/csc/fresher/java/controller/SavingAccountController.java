package com.csc.fresher.java.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.domain.AjaxResponse;
import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.domain.InterestRate;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.User;
import com.csc.fresher.java.service.CustomerService;
import com.csc.fresher.java.service.InterestRateService;
import com.csc.fresher.java.service.SavingAccountService;
import com.csc.fresher.java.service.TransactionService;
import com.csc.fresher.java.service.UserRoleService;
import com.csc.fresher.java.service.UserService;

@Controller
public class SavingAccountController {

	@Autowired
	private SavingAccountService savingAccountService;
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private InterestRateService interestRateService;

	@RequestMapping(value = "/homeSavingAccount")
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
				List<SavingAccount> savingAccountList = savingAccountService
						.getSavingAccountList();
				List<Customer> cus = customerService.getAllCustomer();
				List<InterestRate> interestRate = interestRateService
						.getInterestRateList();

				// setDefault Saving Account Number
				int savingAccountNumber = myRandom(100, 400);
				String[] states = { "new", "hold", "active" };
				modelview.addObject("interestrateList", interestRate);
				modelview.addObject("customerList", cus);
				modelview.addObject("states", states);
				modelview.addObject("listSavingAccount", savingAccountList);
				modelview.addObject("savingAccountNumber", "123"
						+ savingAccountNumber);
				List<SavingAccount> listAllSavingAccount = savingAccountService
						.getSavingAccountList();
				model.addAttribute("listSavingAccount", listAllSavingAccount);
				String myrole = userRoleService
						.getUserRolebyUserRoleName(username);
				System.out.println("My role" + myrole);
				if ("admin".equals(myrole)) {
					List<SavingAccount> listHoldSavingAccount = savingAccountService
							.getSavingAccountByState("hold");
					model.addAttribute("listHoldSavingAccount",
							listHoldSavingAccount);
					modelview.setViewName("adminSavingAccount");
					modelview.addObject("savingaccount", new SavingAccount());
				} else {
					if ("support".equals(myrole)) {
						List<SavingAccount> listActiveSavingAccount = savingAccountService
								.getSavingAccountByState("active");
						List<SavingAccount> listNewSavingAccount = savingAccountService
								.getSavingAccountByState("new");
						model.addAttribute("listActiveSavingAccount",
								listActiveSavingAccount);
						model.addAttribute("listNewSavingAccount",
								listNewSavingAccount);
						modelview.setViewName("supportSavingAccount");
						modelview.addObject("savingaccount",
								new SavingAccount());
					}
				}

			} catch (Exception e) {
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message",
						"You get Error *Home SavingAccount*");

			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	public int myRandom(int start, int end) {
		try {
			Random random = new Random();
			int range = end - start + 1;
			int randomNum = end + random.nextInt(range);
			return randomNum;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@RequestMapping(value = { "/viewAllSavingAccount" })
	public ModelAndView getHome(HttpServletRequest request,
			HttpSession session, @ModelAttribute SavingAccount savingaccount) {
		if (session.getAttribute("loginSession") != null) {
			List<SavingAccount> savingAccountList = savingAccountService
					.getSavingAccountList();
			// homeSavingAccount la o trong file tile.xml
			ModelAndView modelAndView = new ModelAndView("viewAllSavingAccount");
			modelAndView.addObject("savingaccount", new SavingAccount());

			List<Customer> cus = customerService.getAllCustomer();
			List<InterestRate> interestRate = interestRateService
					.getInterestRateList();

			// setDefault Saving Account Number
			int savingAccountNumber = myRandom(100, 1000);
			String[] states = { "new", "hold", "active" };
			modelAndView.addObject("interestrateList", interestRate);
			modelAndView.addObject("customerList", cus);
			modelAndView.addObject("states", states);
			modelAndView.addObject("listSavingAccount", savingAccountList);
			modelAndView.addObject("savingAccountNumber", "123"
					+ savingAccountNumber);
			return modelAndView;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = { "/getSavingAccountNumber" })
	public @ResponseBody String getSavingAccountNumber(
			HttpServletRequest request) {
		int savingAccountNumber = myRandom(200, 500);
		String number = "123" + savingAccountNumber;
		List<SavingAccount> savingList = savingAccountService
				.getSavingAccountList();
		for (SavingAccount a : savingList) {
			if (number.equals(a.getSavingAccountNumber())) {
				int savingAccountNumber2 = myRandom(500, 1000);
				number = "234" + savingAccountNumber2;
				break;

			}
		}
		return number;
	}

	@RequestMapping(value = "/createSavingAccountJson", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse createSavingAccountJson(
			HttpServletRequest request, Model model, HttpSession session,
			@Valid @ModelAttribute("savingacount") SavingAccount savingaccount,
			BindingResult result) {
		// Create a new AccountDAO
		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;
		if (session.getAttribute("loginSession") != null) {
			try {

				// Business With Saving Account

				if (!result.hasErrors()) {
					InterestRate interestRate = interestRateService
							.getInterestRate(savingaccount.getInterestRateId()
									.getId());
					// Change dateStart and dateEnd to dd/MM/yyyy hh/mm/ss
					String sysTime = savingAccountService
							.convertTimeToString(new Date());
					String dateStart = savingaccount.getDateStart().concat(
							" " + sysTime);
					Date date = savingAccountService
							.convertStringToDate(dateStart);
					Date dateEnd = DateUtils.addMonths(date,
							interestRate.getMonth());
					String dateEndtemp = savingAccountService
							.convertDateToString(dateEnd);
					// Set new date to database
					savingaccount.setDateStart(dateStart);
					savingaccount.setDateEnd(dateEndtemp);

					// Check error when Delete to Database

					response.setErrorValidattionCheck(false);
					boolean myCheck = savingAccountService
							.createSavingAccount(savingaccount);
					if (myCheck) {

						SavingAccount saving = savingAccountService
								.getSavingAccountByNumber(savingaccount
										.getSavingAccountNumber());

						Transaction transaction = new Transaction(0,
								saving.getBalanceAmount(),
								savingAccountService
										.convertDateToString(new Date()), "",
								"deposit", "new", (float) 0,
								saving.getBalanceAmount(), saving);

						if (transactionService.createTransaction(transaction)) {
							System.out
									.println("Sucessfully Create Tran from new savingAcount");
						} else {
							System.out
									.println("Failed Create Tran from new savingAcount");
						}
						// Create transaction of this saving account to admin

						message = "Create SavingAccount"
								+ savingaccount.getSavingAccountNumber()
								+ " Successfully";
						error_code = "1";
						saving.getCustomerId().setSavingaccounts(null);
						saving.getInterestRateId().setSavingaccounts(null);
						saving.setTransactions(null);
						response.setSavingAccount(saving);
						check = true;

					} else {
						message = "Create SavingAccount"
								+ savingaccount.getSavingAccountNumber()
								+ " FAIL";
						error_code = "0";
						check = false;

					}
					// Else of HasError
				} else {
					message = "Getting Error with Validation"
							+ savingaccount.getSavingAccountNumber() + " FAIL";
					error_code = "0";
					response.setErrorValidattionCheck(true);
					response.setErrorValidation(result.getAllErrors());

				}
			} catch (Exception e) {
				System.out.println("Create SavingAccount Controller has Error");
				message = "Create SavingAccount Controller has Error";
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

	@RequestMapping(value = "/createSavingAccount", method = RequestMethod.POST)
	public ModelAndView createSavingAccount(HttpServletRequest request,
			Model model, HttpSession session,
			@ModelAttribute SavingAccount savingaccount) {
		// Create a new AccountDAO
		User user = null;
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView(
					"forward:/homeSavingAccount");
			String username = request.getSession().getAttribute("loginSession")
					.toString();

			try {

				boolean check = savingAccountService
						.createSavingAccount(savingaccount);
				if (check) {

					message = "You have created SavingAccount successfully!!!";

					model.addAttribute("message", message);

				} else {
					message = "You have created SavingAccount FAILED!!!";
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

	@RequestMapping(value = "/deleteSavingAccountJson")
	public @ResponseBody AjaxResponse deleteSavingAccount(
			HttpServletRequest request, HttpSession session) {
		// Read account info from request and save into Account object
		int savingAccountId = Integer.parseInt(request
				.getParameter("savingAccountId"));

		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;
		if (session.getAttribute("loginSession") != null) {
			try {

				// Check error when Delete to Database
				if (savingAccountService
						.deleteSavingAccountById(savingAccountId)) {
					message = "Delete SavingAccount" + savingAccountId
							+ " Successfully";
					error_code = "1";
					check = true;

				} else {
					message = "Delete SavingAccount" + savingAccountId
							+ " FAIL";
					error_code = "0";
					check = false;

				}
			} catch (Exception e) {
				System.out.println("Delete SavingAccount Controller has Error");
				message = "Delete SavingAccount Controller has Error";
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

	@RequestMapping(value = "/editSavingAccount")
	public ModelAndView editSavingAccount(HttpServletRequest request,
			Model model, HttpSession session) {
		// Read account info from request and save into Account object
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelview = new ModelAndView("editSavingAccount");
			try {
				int savingAccountId = Integer.parseInt(request
						.getParameter("SavingAccountId"));
				SavingAccount savingAccount = savingAccountService
						.getSavingAccount(savingAccountId);
				System.out.println(savingAccount.toString()
						+ "-Edit SavingAccount");
				modelview.addObject("savingaccount", savingAccount);
				List<Customer> cus = new ArrayList<Customer>();
				Customer myCus = customerService
						.getCustomerBySavingAccountId(savingAccount);
				cus.add(myCus);

				List<InterestRate> interestRate = interestRateService
						.getInterestRateList();
				String[] states = { "new", "hold", "done" };
				modelview.addObject("interestrateList", interestRate);
				modelview.addObject("customerList", cus);
				modelview.addObject("states", states);
			} catch (Exception e) {
				System.out.println("Edit SavingAccount Controller has Error");
				message = "Edit SavingAccount Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}

			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/editSavingAccountProfile", method = RequestMethod.POST)
	public ModelAndView editSavingAccountProfile(HttpServletRequest request,
			Model model, HttpSession session,
			@ModelAttribute("savingaccount") SavingAccount savingaccount) {
		ModelAndView modelview = new ModelAndView(
				"forward:/viewAllSavingAccount");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {

				// Check error when Update to Database
				if (savingAccountService.updateSavingAccount(savingaccount)) {
					message = "Edit SavingAccount *_"
							+ savingaccount.getSavingAccountNumber()
							+ "_* Successfully";

					model.addAttribute("message", message);
				} else {
					message = "Edit SavingAccount"
							+ savingaccount.getSavingAccountNumber() + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Edit SavingAccount Controller has Error");
				message = "Edit SavingAccount Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/approveSavingAccount")
	public ModelAndView approveSavingAccount(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeSavingAccount");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				
				int savingAccountId = Integer.parseInt(request
						.getParameter("SavingAccountId"));
				

				SavingAccount savingAccount = savingAccountService
						.getSavingAccount(savingAccountId);
				savingAccount.setState("active");
				List<Transaction> transactions = transactionService
						.getTransactionBySavingAccountNumber(savingAccount
								.getSavingAccountNumber());
				List<Transaction> newTrans = new ArrayList<Transaction>();

				for (Transaction tran : transactions) {
					if (tran.getState().equals("new")) {
						tran.setState("done");
						tran.setDateEnd(savingAccountService.convertDateToString(new Date()));

					}
					newTrans.add(tran);
					transactionService.updateTransaction(tran);
				}
				savingAccount.setTransactions(newTrans);
				// Check error when Update to Database
				if (!savingAccountService.updateSavingAccount(savingAccount)) {
					message = "Approve Saving Account" + savingAccountId
							+ " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out
						.println("Approve Saving Account Controller has Error");
				message = "Approve Saving Account Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/denySavingAccount")
	public ModelAndView denySavingAccount(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView(
				"forward:/homeSavingAccount");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int SavingAccountId = Integer.parseInt(request
						.getParameter("SavingAccountId"));

				SavingAccount savingAccount = savingAccountService
						.getSavingAccount(SavingAccountId);
				List<Transaction> listTran=transactionService.getTransactionBySavingAccountNumber(savingAccount.getSavingAccountNumber());
				for(Transaction tran:listTran)
				{
					if("new".equals(tran.getState()))
					{
						tran.setState("deny");
						tran.setDateEnd(savingAccountService.convertDateToString(new Date()));
						transactionService.updateTransaction(tran);
					}
				}
			
				savingAccount.setState("deactive");
				// Check error when Update to Database
				if (!savingAccountService.updateSavingAccount(savingAccount)) {
					message = "Deny SavingAccount" + SavingAccountId + " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Deny SavingAccount Controller has Error");
				message = "Deny SavingAccount Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/submitSavingAccount")
	public ModelAndView submitSavingAccount(HttpServletRequest request,
			Model model, HttpSession session) {
		ModelAndView modelview = new ModelAndView("forward:/homeSavingAccount");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
				int savingAccountId = Integer.parseInt(request
						.getParameter("SavingAccountId"));

				SavingAccount savingAccount = savingAccountService
						.getSavingAccount(savingAccountId);
				savingAccount.setState("hold");
				// Check error when Update to Database
				if (!savingAccountService.updateSavingAccount(savingAccount)) {
					message = "Submit SavingAccount" + savingAccountId
							+ " FAIL";
					model.addAttribute("ERROR_CODE", "0");
					model.addAttribute("message", message);
				}
			} catch (Exception e) {
				System.out.println("Submit SavingAccount Controller has Error");
				message = "Submit SavingAccount Controller has Error";
				model.addAttribute("ERROR_CODE", "0");
				model.addAttribute("message", message);
				return modelview;
			}
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/searchSavingAccount", method = RequestMethod.POST)
	public ModelAndView searchSavingAccount(HttpServletRequest request,
			Model model, HttpSession session,
			@ModelAttribute Customer customer,
			@ModelAttribute SavingAccount savingaccount,
			@ModelAttribute Transaction transaction) {
		String message = "";
		if (session.getAttribute("loginSession") != null) {
			boolean check = false;
			ModelAndView modelview = new ModelAndView("searchSavingAccount");
			SavingAccount saving = new SavingAccount();
			// FOr Add Transaction
			modelview.addObject("transaction", new Transaction());
			modelview.addObject("transactiondeposit", new Transaction());

			// For Create Saving Account
			modelview.addObject("savingaccount", new SavingAccount());

			// For Search
			String searchValue = request.getParameter("searchSavingAcount");
			String searchType = request.getParameter("searchType");
			Customer customerfromSaving = null;
			// Search by Saving Account Number

			// List Interest Rate For Creating SavingAccount
			List<InterestRate> interestRate = interestRateService
					.getInterestRateList();
			modelview.addObject("interestrateList", interestRate);
			// List states for Creatiing new SavingAccount
			String[] states = { "new", "hold", "active" };
			modelview.addObject("states", states);

			/** Check for Type of Searching */
			if (searchType.equals("accountNumber")) {
				try {
					List<SavingAccount> listSaving = new ArrayList<SavingAccount>();
					saving = savingAccountService
							.getSavingAccountByNumber(Integer
									.parseInt(searchValue));

					if (saving == null) {
						message = "Can Not Find Saving Account Number "
								+ searchValue + "!!! ";
						check = false;
					} else {
						if (saving.getState() != null
								&& saving.getState().equals("active")) {
							listSaving.add(saving);
							// Show Customer Data to Search Page
							customerfromSaving = customerService
									.getCustomerBySavingAccountId(saving);
							check = true;
						}
					}
					// List Customer For Add Saving Account
					List<Customer> listCustomer = new ArrayList<Customer>();
					listCustomer.add(customerfromSaving);
					modelview.addObject("customerList", listCustomer);

					// Check for Searching Result
					modelview.addObject("myCustomer", customerfromSaving);
					modelview.addObject("message", message);
					modelview.addObject("listSavingAccount", listSaving);
				} catch (Exception e) {
					check = false;
					e.printStackTrace();
					message = "Find Saving Account Number Error" + searchValue
							+ "!!! ";
					modelview.addObject("message", message);
				}

			} else {
				// Search by ID Number of Customer
				if (searchType.equals("idNumber")) {
					try {
						List<SavingAccount> listSaving = new ArrayList<SavingAccount>();
						List<SavingAccount> myListSaving = savingAccountService
								.getSavingAccountByCustomerIDNumber(searchValue);
						// Check for Searching Result
						for (SavingAccount s : myListSaving) {
							if (s.getState() != null
									&& s.getState().equals("active")) {
								listSaving.add(s);
							}
						}

						int checkNumber = listSaving.size();
						if (checkNumber == 0) {
							message = "Can Not Find List of Saving Account of Customer "
									+ searchValue + "!!!";
							check = false;
						} else {
							check = true;
						}
						// Get Customer
						customerfromSaving = customerService
								.getCustomerByIDNumber(searchValue);

						// List Customer For Add Saving Account
						List<Customer> listCustomer = new ArrayList<Customer>();
						listCustomer.add(customerfromSaving);
						modelview.addObject("customerList", listCustomer);

						modelview.addObject("myCustomer", customerfromSaving);
						modelview.addObject("message", message);
						modelview.addObject("listSavingAccount", listSaving);

					} catch (Exception e) {
						check = false;
						e.printStackTrace();
						message = "Find Saving Account Number Error"
								+ searchValue + "!!! ";
						modelview.addObject("message", message);
					}
				}
			}

			modelview.addObject("check", check);
			return modelview;
		} else {
			return new ModelAndView("redirect:/login");
		}

	}
}

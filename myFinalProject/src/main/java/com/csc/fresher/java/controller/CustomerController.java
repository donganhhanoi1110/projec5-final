package com.csc.fresher.java.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csc.fresher.java.domain.AjaxResponse;
import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.service.CustomerService;
import com.csc.fresher.java.service.UserService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/homeCustomer", method=RequestMethod.GET)
	public ModelAndView getCustomerList(HttpServletRequest request,
			Model model, Principal principal,@ModelAttribute Customer customer) {

		String error_code = request.getParameter("ERROR_CODE");
		ModelAndView modelview = new ModelAndView("customer");
		modelview.setViewName("customer");
		modelview.addObject("loginSession", principal.getName());
		modelview.addObject("ERROR_CODE", error_code);
		// Get the list of all accounts from DB
		try {
			modelview.addObject("customer",new Customer());
			modelview.addObject("listCustomer",customerService.getAllCustomer());

			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;
		}

	}

	@RequestMapping(value = "/editCustomer")
	public ModelAndView editCustomer(HttpServletRequest request, Model model,
			HttpSession session, @ModelAttribute Customer customer) {
		if (session.getAttribute("loginSession") != null) {
			String message = "";
			ModelAndView modelView = new ModelAndView("editCustomer");
			try {
				int custId = Integer.parseInt(request.getParameter("custID"));
				Customer Customer = customerService.getCustomer(custId);
				System.out.println(customer.toString() + "edit customer");
				List<Customer> list = new ArrayList<Customer>();
				list.add(customer);
				model.addAttribute("customer", Customer);
			} catch (Exception e) {
				System.out.println("edit customer controller has error");
				message = "Edit customer controller has error";
				modelView.addObject("ERROR_CODE", "0");
				modelView.addObject("message", message);
			}
			return modelView;
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(value = "/editCustProfile")
	public ModelAndView editCusProfile(HttpServletRequest request, Model model,
			HttpSession session, @ModelAttribute Customer customer) {
		ModelAndView modelview = new ModelAndView("forward:/home");

		String message = "";

		if (session.getAttribute("loginSession") != null) {
			try {
		

				if (customerService.updateCustomer(customer)) {
					message = "Edit Customer *_" + customer.getFirstName()
							+ "_* Successfully";
					model.addAttribute("message", message);
				} else {
					message = "Edit Customer " + customer.getFirstName() + " FAIL";
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

	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public ModelAndView createeCustomer(HttpServletRequest request,Model model, HttpSession session,@ModelAttribute("customer") @Valid Customer customer,
			BindingResult result) throws Exception{

		if (session.getAttribute("loginSession") != null) {
			if(result.hasErrors()){
				return new ModelAndView("customer");
			}else{
			String message = "";
			ModelAndView modelview = new ModelAndView("forward:/homeCustomer");
			try {
				boolean check = customerService.createCustomer(customer);
				if (check) {
					message = "You have created Customer successfully!!!";
					modelview.addObject("message", message);
				} else {
					message = "You have created Customer FAILED!!!";
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
				}
				return modelview;
			} catch (Exception e) {
				modelview.addObject("ERROR_CODE", "0");
				return modelview;
			}
		} }else {
			return new ModelAndView("redirect:/login");
		}

	}
	
	@RequestMapping(value = "/createCustomerJson", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse CreateCustomerJson(
			HttpServletRequest request, Model model, HttpSession session,
			@Valid @ModelAttribute("customer") Customer customer, BindingResult result){
		AjaxResponse response = new AjaxResponse();
		String message="";
		String error_code="";
		boolean check =false;
		if(session.getAttribute("loginSession")!=null){
			try{
				
			
			if(!result.hasErrors()){
				response.setErrorValidattionCheck(false);
				boolean myCheck = customerService.createCustomer(customer);
				if(myCheck){
					message = "You have created Customer successfully!!!";
					response.setCustomer(customer);
					check =true;
				}else{
					message = "You have created Customer FAILED!!!";
					error_code = "0";
					check = false;
				}
			}else{
				message="Getting Error with Validation";
				error_code = "0";
				response.setErrorValidattionCheck(true);
				response.setErrorValidation(result.getAllErrors());
			}
		} catch(Exception e){
			System.out.println("Create Customer Controller has Error");
			message = "Create SavingAccount Controller has Error";
			error_code = "0";
			check = false;
		}
			response.setSuccess(check);
			response.setMessage(message);
			response.setError_code(error_code);
			response.setLogin(true);
	}else {
		response.setLogin(false);
	}
		return response;
	}
	@RequestMapping(value = "/deleteCustomerJson", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse deteleUser(HttpServletRequest request,
			HttpSession session) {

		int custId = Integer.parseInt(request.getParameter("customerId"));
		AjaxResponse response = new AjaxResponse();
		String message = "";
		String error_code = "";
		boolean check = false;
		if (session.getAttribute("loginSession") != null) {
			try {
				if (customerService.deleteCustomerById(custId)) {
					message = "Delete Customer" + custId + " Successfully";
					error_code = "1";
					check = true;
				} else {
					message = "Delete Customer" + custId + " FAIL";
					error_code = "0";
					check = false;
				}
			} catch (Exception e) {
				System.out.println("Delete Customer Controller has Error");
				message = "Delete Customer Controller has Error";
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
}

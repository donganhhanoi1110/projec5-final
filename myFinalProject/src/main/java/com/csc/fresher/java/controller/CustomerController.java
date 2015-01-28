package com.csc.fresher.java.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value="/homeCustomer")
	public ModelAndView getCustomerList(HttpServletRequest request, Model model, Principal principal){
		
		String error_code= request.getParameter("ERROR_CODE");
		ModelAndView modelview = new ModelAndView("customer");
		modelview.addObject("loginSession", principal.getName());
		modelview.addObject("ERROR_CODE",error_code);
		// Get the list of all accounts from DB
		try {
			modelview.addObject("listCustomer",customerService.getAllCustomer());
			
			return modelview;

		} catch (Exception e) {
			modelview.addObject("ERROR_CODE", "You get error");
			return modelview;

		}
		
	}
	
	@RequestMapping(value="/editCustomer")
	public ModelAndView editCustomer(HttpServletRequest request, Model model, HttpSession session){
		if(session.getAttribute("loginSession")!=null){
			String message="";
			ModelAndView modelView= new ModelAndView("editCustomer");
			try{
				int custId=Integer.parseInt(request.getParameter("custID"));
				Customer customer=customerService.getCustomer(custId);
				System.out.println(customer.toString()+ "edit customer");
				List<Customer> list= new ArrayList<Customer>();
				list.add(customer);
				model.addAttribute("customerProfile", list);
			} catch(Exception e){
				System.out.println("edit customer controller has error");
				message="Edit customer controller has error";
				modelView.addObject("ERROR_CODE", "0");
				modelView.addObject("message", message);
			}
			return modelView;
		}else{
			return new ModelAndView("redirect:/login");
		}
	}
	@RequestMapping(value="/editCustProfile")
	public ModelAndView editCusProfile(HttpServletRequest request, Model model, 
			HttpSession session){
		ModelAndView modelview= new ModelAndView("forward:/home");
		
		String message="";
		
		if(session.getAttribute("loginSession")!=null){
		try{
			String accountType=request.getParameter("accountType");
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String midName=request.getParameter("midName");
			String idNumber=request.getParameter("idNumber");
			int phone1=Integer.parseInt(request.getParameter("phone1"));
			int phone2=Integer.parseInt(request.getParameter("phone2"));
			String add1=request.getParameter("add1");
			String add2=request.getParameter("add2");
			String email=request.getParameter("email");
			String state=request.getParameter("state");
			int id=Integer.parseInt(request.getParameter("custID"));
			int accountNumber=Integer.parseInt(request.getParameter("accountNumber"));
			
			Customer cust= new Customer(id, accountNumber, accountType, firstName,
					lastName, midName, idNumber, phone1, phone2, add1, add2, email, state);
			
			if(customerService.updateCustomer(cust)){
				message="Edit Customer *_"+ firstName+ "_* Successfully";
				model.addAttribute("message", message);
			}else{
				message = "Edit Customer " + firstName + " FAIL";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
			}}catch (Exception e){
				System.out.println("Create User Controller has Error");
				message = "Edit User Controller has Error";
				modelview.addObject("ERROR_CODE", "0");
				modelview.addObject("message", message);
				return modelview;
			}
		return modelview;
		}else{
			return new ModelAndView("redirect:/login");
		}
		
	}
	@RequestMapping(value="/createCustomer", method=RequestMethod.POST)
	public ModelAndView createeCustomer(HttpServletRequest request, Model model,
			HttpSession session){
		
		if(session.getAttribute("loginSession")!=null){
			String message="";
			ModelAndView modelview=new ModelAndView("forward:/home");
			String accountType=request.getParameter("accountType");
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String midName=request.getParameter("midName");
			String idNumber=request.getParameter("idNumber");
			int phone1=Integer.parseInt(request.getParameter("phone1"));
			int phone2=Integer.parseInt(request.getParameter("phone2"));
			String add1=request.getParameter("add1");
			String add2=request.getParameter("add2");
			String email=request.getParameter("email");
			String state=request.getParameter("state");
			
			int accountNumber=Integer.parseInt(request.getParameter("accountNumber"));
			
			Customer cust=new Customer(0, accountNumber, accountType, firstName, lastName, midName, idNumber,
					phone1, phone2, add1, add2, email, state);
			try{
				boolean check=customerService.createCustomer(cust);
				if(check){
					message = "You have created Customer successfully!!!";
					modelview.addObject("message", message);	
				}else {
					message = "You have created Customer FAILED!!!";
					modelview.addObject("ERROR_CODE", "0");
					modelview.addObject("message", message);
			}
				return modelview;
		}catch(Exception e){
			modelview.addObject("ERROR_CODE", "0");
			return modelview;
		}
		}else{
			return new ModelAndView("redirect:/login");
		}
		
	}
	
	public @ResponseBody AjaxResponse deteleUser(HttpServletRequest request,
			HttpSession session){
		
	int custId= Integer.parseInt(request.getParameter("customerId"));
	AjaxResponse response = new AjaxResponse();
	String message="";
	String error_code="";
	boolean check=false;
	if(session.getAttribute("loginSession")!=null){
		try{
			if(customerService.deleteCustomerById(custId)){
				message = "Delete Customer" + custId + " Successfully";
				error_code = "1";
				check = true;
			} else{
				message = "Delete Customer" + custId + " FAIL";
				error_code = "0";
				check = false;
			}
		}catch (Exception e){
			System.out.println("Delete Customer Controller has Error");
			message = "Delete Customer Controller has Error";
			error_code = "0";
			check = false;
		}
		
		response.setSuccess(check);
		response.setMessage(message);
		response.setError_code(error_code);
		response.setLogin(true);
		
	} else{
		response.setLogin(false);
	}
	return response;	
	}
}

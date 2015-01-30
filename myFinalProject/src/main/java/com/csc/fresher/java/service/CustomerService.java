package com.csc.fresher.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.CustomerDAO;
import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.domain.SavingAccount;

@Service("customerService")
public class CustomerService {

	CustomerDAO customerDAO;

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	@Autowired
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public List<Customer> getAllCustomer() {
		return this.getCustomerDAO().getAllCustomer();
	}

	public Customer getCustomer(int id) {
		return this.getCustomerDAO().getCustomerId(id);
	}

	public boolean updateCustomer(Customer cust) {
		return this.getCustomerDAO().updateCustomer(cust);
	}

	public boolean createCustomer(Customer cust) {
		return this.getCustomerDAO().createCust(cust);
	}

	public boolean deleteCustomerById(int CustId) {
		return this.getCustomerDAO().deleteCustomerById(CustId);
	}

	public Customer getCustomerBySavingAccountId(SavingAccount saving) {
		return this.getCustomerDAO().getCustomerBySavingAccountId(saving);
	}

	public Customer getCustomerByIDNumber(String idNumber) {
		return this.getCustomerDAO().getCustomerByIDNumber(idNumber);
	}

}

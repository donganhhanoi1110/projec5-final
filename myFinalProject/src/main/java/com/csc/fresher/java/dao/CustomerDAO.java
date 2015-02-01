package com.csc.fresher.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csc.fresher.java.domain.AjaxResponse;
import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.User;

@Repository("customerDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public List<Customer> getAllCustomer() {
		List<Customer> list = null;
		try {
			TypedQuery<Customer> query = entityManager.createQuery(
					"SELECT C FROM " + Customer.class.getName() + " C",
					Customer.class);

			list = query.getResultList();
			System.out.println("Get All Customer");
		} catch (Exception e) {
			System.out.println("\n Get Error" + "*_" + e.getMessage() + "*_");
		}
		return list;
	}

	@Transactional
	public Customer getCustomerByIDNumber(String idNumber) {
		Customer list = null;
		try {
			TypedQuery<Customer> query = entityManager.createQuery(
					"SELECT C FROM " + Customer.class.getName()
							+ " C where C.idNumber=:idNumber", Customer.class);
			query.setParameter("idNumber", idNumber + "");

			list = query.getSingleResult();
			System.out.println("Get All getCustomerByIDNumber");
		} catch (Exception e) {
			System.out.println("\n Get Error" + "*_" + e.getMessage() + "*_");
		}
		return list;
	}

	@Transactional
	public Customer getCustomerId(int id) {
		Customer customer = null;
		try {
			customer = entityManager.find(Customer.class, id);
			if (customer == null) {
				throw new EntityNotFoundException(" Can't find user for ID "
						+ id);
			}
			System.out.println(customer.toString() + "get-Customer-Dao");
		} catch (Exception e) {
			System.out.println("\nGetting customer has error" + "*_"
					+ e.getMessage());
		}

		return customer;
	}

	@Transactional
	public Customer getCustomerBySavingAccountId(SavingAccount saving) {
		Customer customer = null;
		try {
			customer = entityManager.find(Customer.class, saving
					.getCustomerId().getId());
			if (customer == null) {
				throw new EntityNotFoundException(" Can't find user for ID "
						+ saving.getCustomerId().getId());
			}
			System.out.println(customer.toString()
					+ "getCustomerBySavingAccountId-Customer-Dao");
		} catch (Exception e) {
			System.out.println("\nGetting customer has error" + "*_"
					+ e.getMessage());
		}

		return customer;
	}

	@Transactional
	public boolean updateCustomer(Customer cust) {
		boolean check = false;
		try {
			entityManager.merge(cust);
			check = true;
			System.out.println("Customer " + cust.getFirstName() + " updated");
		} catch (Exception e) {
			System.out.println("\nUpdate customer get Error " + "*_"
					+ e.getMessage() + "*_");
		}
		return check;
	}

	@Transactional
	public boolean createCust(Customer cust) {
		boolean check = false;

		try {
			entityManager.persist(cust);
			check = true;
		} catch (Exception e) {
			System.out.println("\n Get error with create customer:"
					+ e.getMessage());
		}
		return check;
	}

	@Transactional
	public boolean deleteCustomerById(int CustId) {
		boolean check = false;
		try {
			Customer cust = entityManager.find(Customer.class, CustId);
			if (cust == null) {
				check = false;
				throw new EntityNotFoundException("Can't find Customer for ID "
						+ CustId);
			}
			if (cust != null) {
				entityManager.remove(cust);
				check = true;
				System.out.println("delete Customer by Customer");
			}
		} catch (Exception e) {
			System.out.println("\nDelete Customer by ID get Error " + "*_"
					+ e.getMessage() + "*_");
		}
		return check;
	}
}

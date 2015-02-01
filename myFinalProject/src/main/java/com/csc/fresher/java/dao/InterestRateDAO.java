// saving AccountDAO

package com.csc.fresher.java.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.fresher.java.domain.Customer;
import com.csc.fresher.java.domain.InterestRate;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.InterestRate;

@Repository("interestRateDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class InterestRateDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	public InterestRate getInterestRateBySavingAccountId(SavingAccount saving) {
		InterestRate interestRate = null;
		try {
			interestRate = entityManager.find(InterestRate.class, saving
					.getCustomerId().getId());
			if (interestRate == null) {
				throw new EntityNotFoundException(" Can't find user for ID "
						+ saving.getCustomerId().getId());
			}
			System.out.println(interestRate.toString()
					+ "getCustomerBySavingAccountId-Customer-Dao");
		} catch (Exception e) {
			System.out.println("\nGetting customer has error" + "*_"
					+ e.getMessage());
		}

		return interestRate;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public InterestRate getInterestRate(int id) {
		InterestRate InterestRate = null;
		try {
			InterestRate = entityManager.find(InterestRate.class, id);
			if (InterestRate == null) {
				throw new EntityNotFoundException(
						"Can't find InterestRate for ID " + id);
			}
			System.out.println(InterestRate.toString() + "getInterestRate-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting InterestRate had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return InterestRate;

	}

	@Transactional
	public InterestRate getInterestRatebyType(String type) {
		InterestRate inter = null;
		try {
			TypedQuery<InterestRate> query = entityManager.createQuery(
					"SELECT C FROM " + InterestRate.class.getName()
							+ " C where C.savingAccountType=:type",
					InterestRate.class);
			query.setParameter("type", type);
			inter = query.getSingleResult();
			System.out.println("Get getInterestRatebyType");
		} catch (Exception e) {
			System.out.println("\n Get Error" + "*_" + e.getMessage() + "*_");
		}
		return inter;
	}

	public List<InterestRate> getInterestRateList() {

		List<InterestRate> list = new ArrayList<InterestRate>();

		try {
			TypedQuery<InterestRate> query = entityManager.createQuery(
					"SELECT s FROM " + InterestRate.class.getName() + " s",
					InterestRate.class);

			list = query.getResultList();
			System.out.println("Get Saving Account List");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List");
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public boolean createInterestRate(InterestRate InterestRate) {
		boolean check = false;

		try {

			// Insert a row to InterestRate table
			entityManager.persist(InterestRate);
			check = true;

		} catch (Exception e) {
			System.out.println("\nGet Error with Create InterestRate " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public boolean updateInterestRate(InterestRate InterestRate) {

		boolean check = false;
		try {
			entityManager.merge(InterestRate);
			check = true;
			System.out.println("InterestRate " + InterestRate.getId()
					+ "updated");
		} catch (Exception e) {
			System.out.println("\nUpdate InterestRate get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

}

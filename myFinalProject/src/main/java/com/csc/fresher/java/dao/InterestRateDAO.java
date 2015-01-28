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
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.InterestRate;

@Repository("interestRateDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class InterestRateDAO {

	@PersistenceContext
	public EntityManager entityManager;

	@Transactional
	@SuppressWarnings("unchecked")
	public InterestRate getInterestRate(int id) {
		InterestRate InterestRate = new InterestRate();
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

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
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.domain.SavingAccount;

@Repository("savingAccountDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class SavingAccountDAO {

	@PersistenceContext
	public EntityManager entityManager;
	@Transactional
	@SuppressWarnings("unchecked")
	public SavingAccount getSavingAccount(int id) {
		SavingAccount SavingAccount = new SavingAccount();
		try {
			SavingAccount = entityManager.find(SavingAccount.class, id);
			if (SavingAccount == null) {
				throw new EntityNotFoundException("Can't find SavingAccount for ID "
						+ id);
			}
			System.out.println(SavingAccount.toString() + "getSavingAccount-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting SavingAccount had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return SavingAccount;

	}
public List<SavingAccount> getSavingAccountListbyCustomerId(int id) {
		
		List<SavingAccount> list = new ArrayList<SavingAccount>();
		
		try {
			TypedQuery<Customer> query = entityManager.createQuery("SELECT s FROM "
					+ Customer.class.getName() + " s where s.id=:id", Customer.class);
				query.setParameter("id", id);
				Customer cus= query.getSingleResult();	
			list =cus.getSavingaccounts();
			System.out.println("Get Saving Account List");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List" );
			e.printStackTrace();
		}
		return list;
	}
	public List<SavingAccount> getSavingAccountList() {
		
		List<SavingAccount> list = new ArrayList<SavingAccount>();
		
		try {
			TypedQuery<SavingAccount> query = entityManager.createQuery("SELECT s FROM "
					+ SavingAccount.class.getName() + " s", SavingAccount.class);

			list = query.getResultList();
			System.out.println("Get Saving Account List");
		} catch (Exception e) {
			System.out.println("Error get Saving Account List" );
			e.printStackTrace();
		}
		return list;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean createSavingAccount(SavingAccount savingAccount) {
		boolean check = false;

		try {

			// Insert a row to SavingAccount table
			entityManager.persist(savingAccount);
			check = true;

		} catch (Exception e) {
			System.out.println("\nGet Error with Create SavingAccount "+ "*_"+e.getMessage()+"*_");

		}
		return check;
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean updateSavingAccount(SavingAccount savingAccount) {

		boolean check = false;
		try {
			entityManager.merge(savingAccount);
			check = true;
			System.out.println("SavingAccount " + savingAccount.getId() + "updated");
		} catch (Exception e) {
			System.out.println("\nUpdate SavingAccount get Error "+ "*_"+e.getMessage()+"*_");

		}
		return check;
	}

}

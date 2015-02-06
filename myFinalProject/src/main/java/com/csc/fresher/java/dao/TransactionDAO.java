package com.csc.fresher.java.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csc.fresher.java.domain.InterestRate;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;
import com.csc.fresher.java.service.SavingAccountService;

/**
 * DAO class for Account entity. This class contains all methods that
 * inserts/updates/deletes Transaction info
 * 
 * @author Anh Minh Nguyen
 *
 */
@Repository("transactionDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class TransactionDAO {
	@PersistenceContext
	public EntityManager entityManager;

	SavingAccountService savingService = new SavingAccountService();
	InterestRateDAO interestDAO = new InterestRateDAO();

	@Transactional
	@SuppressWarnings("unchecked")
	public Transaction getTransaction(int id) {
		Transaction Transaction = null;
		try {
			System.out.println("getTransaction-DAO");
			Transaction = entityManager.find(Transaction.class, id);
			if (Transaction == null) {
				throw new EntityNotFoundException(
						"Can't find Transaction for ID " + id);
			}
			System.out.println(Transaction.getId() + "getTransaction-DAO");
		} catch (Exception e) {
			System.out.println("\nGetting Transaction had Errors" + "*_"
					+ e.getMessage() + "*_");

		}
		return Transaction;

	}

	public List<Transaction> getTransactionByState(String state) {
		List<Transaction> list = null;
		try {
			TypedQuery<Transaction> query = entityManager.createQuery(
					"SELECT c FROM " + Transaction.class.getName()
							+ " c where c.state=:state", Transaction.class);
			query.setParameter("state", state);
			list = query.getResultList();
			System.out.println("Get getTransactionByState");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	public List<Transaction> getAllTransaction() {
		List<Transaction> list = null;
		try {
			TypedQuery<Transaction> query = entityManager.createQuery(
					"SELECT c FROM " + Transaction.class.getName() + " c",
					Transaction.class);

			list = query.getResultList();
			System.out.println("Get All Transactions");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public boolean createTransaction(Transaction Transaction) {
		boolean check = false;

		try {

			// Insert a row to Transaction table
			entityManager.persist(Transaction);
			check = true;

		} catch (Exception e) {
			System.out.println("\nGet Error with Create Transaction " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Update Transaction information.
	 *
	 * @param Transaction
	 *            an Transaction to be updated.
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean updateTransaction(Transaction transaction) {

		boolean check = false;
		try {
			entityManager.merge(transaction);
			check = true;
			System.out
					.println("Transaction " + transaction.getId() + "updated");
		} catch (Exception e) {
			System.out.println("\nUpdate Transaction get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete Transaction by their Id.
	 *
	 * @param TransactionId
	 *            the Transaction Id.
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public boolean deleteTransactionById(int TransactionId) {
		boolean check = false;
		try {
			Transaction Transaction = entityManager.find(Transaction.class,
					TransactionId);
			if (Transaction == null) {
				check = false;
				throw new EntityNotFoundException(
						"Can't find Transaction for ID " + TransactionId);
			}
			System.out.println(Transaction.toString());
			if (Transaction != null) {

				entityManager.remove(Transaction);
				check = true;
				System.out.println("delete Transaction by ID");
			}
		} catch (Exception e) {

			System.out.println("\nDelete Transaction by ID get Error " + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	/**
	 * Delete Transaction entity.
	 *
	 * @param Transaction
	 *            the object to be deleted.
	 */
	public boolean deleteTransaction(Transaction Transaction) {
		boolean check = false;
		try {
			entityManager.remove(Transaction);
			check = true;
			System.out.println("delete Transaction by Transaction");
		} catch (Exception e) {
			System.out.println("\nDelete Transaction get Error" + "*_"
					+ e.getMessage() + "*_");

		}
		return check;
	}

	public List<Transaction> getTransactionBySavingAccountNumber(
			int savingAccountNumber) {
		List<Transaction> list = null;
		try {
			TypedQuery<Transaction> query = entityManager
					.createQuery(
							"SELECT c FROM "
									+ Transaction.class.getName()
									+ " c where c.savingAccountId.savingAccountNumber=:number",
							Transaction.class);
			query.setParameter("number", savingAccountNumber);
			list = query.getResultList();
			System.out.println("Get getTransactionBySavingAccountNumber");
		} catch (Exception e) {
			System.out.println("\nGet Error " + "*_" + e.getMessage() + "*_");

		}
		return list;

	}

	public SavingAccount getAccountbyTranID(Transaction tran) {
		SavingAccount acc = null;
		try {
			acc = entityManager.find(SavingAccount.class, tran
					.getSavingAccountId().getId());

			
		} catch (Exception e) {
			System.out.println("\nFind  Transaction get Error" + "*_"
					+ e.getMessage() + "*_");

		}
		return acc;
	}

	public boolean approveTransacsionAdmin(int tranId) {
		System.out.println("/nApprove Transaction Admin");
		Date date = new Date();
		try {
			Transaction tran = getTransaction(tranId);
			SavingAccount acc = entityManager.find(SavingAccount.class, tran
					.getSavingAccountId().getId());
			InterestRate interestRateofSavingAcc = acc.getInterestRateId();

			InterestRate interestNoPeriod = interestDAO.getInterestRate(3);
			int days = Days.daysBetween(
					new DateTime(savingService.convertStringToDate(acc
							.getDateStart())), new DateTime(date)).getDays();
			float newBalance = (float) ((acc.getBalanceAmount() * days * (0.01/360)) + acc.getBalanceAmount()+tran.getAmount());
			
			// Update Saving Account with new Balance
	
			Date nextDate = DateUtils.addMonths(date, interestRateofSavingAcc.getMonth());

			acc.setBalanceAmount(newBalance);
			acc.setDateStart(savingService.convertDateToString(date));
			acc.setDateEnd(savingService.convertDateToString(nextDate));

			if (savingService.checkTransaction(acc, tran)) {
				float afterBalance = newBalance;

				tran.setState("done");
				tran.setDateEnd(savingService.convertDateToString(date));
				tran.setAfterBalance(afterBalance);

				tran.setSavingAccountId(acc);
				updateTransaction(tran);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}

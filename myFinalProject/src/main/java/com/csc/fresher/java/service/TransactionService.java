package com.csc.fresher.java.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;

/**
 * Transaction Service class call method from DAO for controller to do get Data
 * or business logic
 * 
 * @author Anh Minh Nguyen
 *
 */
@Service("transactionService")
public class TransactionService {

	TransactionDAO TransactionDAO;

	public TransactionDAO getTransactionDAO() {
		return TransactionDAO;
	}

	@Autowired
	public void setTransactionDAO(TransactionDAO TransactionDAO) {
		this.TransactionDAO = TransactionDAO;
	}

	public List<Transaction> getTransactionByState(String state) {
		return this.getTransactionDAO().getTransactionByState(state);
	}

	public Transaction getTransaction(int id) {
		return this.getTransactionDAO().getTransaction(id);
	}

	public List<Transaction> getAllTransaction() {
		return this.getTransactionDAO().getAllTransaction();
	}

	public boolean createTransaction(Transaction Transaction) {
		return this.getTransactionDAO().createTransaction(Transaction);
	}

	public boolean updateTransaction(Transaction Transaction) {
		return this.getTransactionDAO().updateTransaction(Transaction);
	}

	/**
	 * Delete Transaction by their Id.
	 *
	 * @param TransactionId
	 *            the Transaction Id.
	 */
	public boolean deleteTransactionById(int TransactionId) {
		return this.getTransactionDAO().deleteTransactionById(TransactionId);
	}

	/**
	 * Delete Transaction entity.
	 *
	 * @param Transaction
	 *            the object to be deleted.
	 */
	public boolean deleteTransaction(Transaction Transaction) {
		return this.getTransactionDAO().deleteTransaction(Transaction);
	}

	public List<Transaction> getTransactionBySavingAccountNumber(
			int savingAccountNumber) {
		return this.getTransactionDAO().getTransactionBySavingAccountNumber(
				savingAccountNumber);
	}

	public boolean approveTransacsionAdmin(int tranId) {
		return this.getTransactionDAO().approveTransacsionAdmin(tranId);
	}

	public SavingAccount getAccountbyTranID(Transaction tran) {
		return this.getTransactionDAO().getAccountbyTranID(tran);

	}

	public Date convertStringToDate(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (Date) formatter.parse(mydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public Date convertStringToDateDB(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;
			// Fri Jan 30 10:54:23 ICT 2015
			formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			date = (Date) formatter.parse(mydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public boolean checkDate(String dateStart, String dateEnd, String transStart) {

		Date myDateStart = convertStringToDate(dateStart);
		Date myDateEnd = convertStringToDate(dateEnd);
		Date myTransStart = convertStringToDateDB(transStart);
		
		boolean res = false;
		try{
			if ((myTransStart.compareTo(myDateStart) >= 0) && (myTransStart.compareTo(myDateEnd) <= 0)) {
				res = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			res = false;
		}
/*		 if ((myDateStart.getTime() <= myTransStart.getTime()) && (
		 myTransStart.getTime() <= myDateEnd.getTime()) ) {
		 return true;
		 } else*/
		return res;
	}

}

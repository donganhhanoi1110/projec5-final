package com.csc.fresher.java.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.Transaction;

/**
 * Transaction Service class call method from DAO for controller to do get Data or business logic
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
	     * @param TransactionId the Transaction Id.
	     */
	    public boolean deleteTransactionById(int TransactionId) {
	       return this.getTransactionDAO().deleteTransactionById(TransactionId);
	    }
	 
	    /**
	     * Delete Transaction entity.
	     *
	     * @param Transaction the object to be deleted.
	     */
	    public boolean deleteTransaction(Transaction Transaction) {
	    	return this.getTransactionDAO().deleteTransaction(Transaction);
	    }
}

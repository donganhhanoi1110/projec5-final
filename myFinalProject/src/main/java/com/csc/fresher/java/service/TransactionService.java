package com.csc.fresher.java.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.TransactionDAO;
import com.csc.fresher.java.domain.InterestRate;
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
	@Autowired
	private SavingAccountService savingAccountService;
	@Autowired
	private InterestRateService interestRateService;

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

	public boolean  ApproveWithdraw( Transaction tran){
		try{
		SavingAccount savingAccount = getAccountbyTranID(tran);
		// find interest
		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd/MM/yyyy hh:mm");
		Date startWithdraw =new Date();
		Date dateStart = formatter.parse(savingAccount
				.getDateStart());
		Date dateEnd = formatter.parse(savingAccount.getDateEnd());
		float totalAmount = 0;
		float interestPerDay=0;
		float interest=0;
		Date date = new Date();
		if(tran.getTransactionType().equals("withdrawAll")){
			
			totalAmount = tran.getAmount();
			
			// update Saving Account
			savingAccount.setBalanceAmount(0f);
			savingAccount.setState("deactive");
			// update trasaction
			tran.setState("done");
			tran.setDateEnd(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(date));
			tran.setAfterBalance(0);
			tran.setSavingAccountId(savingAccount);
			updateTransaction(tran);
			savingAccountService.updateSavingAccount(savingAccount);
		}
		if(tran.getTransactionType().equals("withdraw")){
			if (startWithdraw.compareTo(dateEnd) >= 0) {
				interestPerDay = ((savingAccount.getInterestRateId()
						.getInterestRate())/360)/100;
				
			}else{
				List<InterestRate> interestRate = interestRateService.getInterestRateList();
				for(InterestRate a:interestRate){
					if(a.getMonth()==0){
						interest=a.getInterestRate();
						break;
					}
				}
				interestPerDay=(interest/360)/100;
			}
			int days=Days.daysBetween(new DateTime(dateStart),new DateTime(startWithdraw)).getDays();
			totalAmount = savingAccount.getBalanceAmount()
					+ savingAccount.getBalanceAmount()
					*days*interestPerDay;
			// update Saving Account
			savingAccount.setBalanceAmount(totalAmount-tran.getAmount());
			savingAccount.setDateStart(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(date));
					//next day
			InterestRate interestRate =savingAccount.getInterestRateId();
			Date nextMonths =DateUtils.addMonths(date, interestRate.getMonth());
			savingAccount.setDateEnd(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(nextMonths));
			savingAccount.setState("active");						
			savingAccountService.updateSavingAccount(savingAccount);
			//update transaction
			tran.setState("done");
			tran.setDateEnd(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(date));
			tran.setAfterBalance(totalAmount-tran.getAmount());
			tran.setCurrentBalance(totalAmount);
			tran.setSavingAccountId(savingAccount);
			
			updateTransaction(tran);	
		}
		}catch(Exception e){
			System.out.println("Approve Transaction Controller has Error");
			return false;
		}
		return true;
	}
	public Date convertStringToDate(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
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
		Date myTransStart = convertStringToDate(transStart);
		
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

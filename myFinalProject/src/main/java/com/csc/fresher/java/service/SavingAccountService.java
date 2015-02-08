// Saving Account Service 
package com.csc.fresher.java.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.SavingAccountDAO;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;

@Service(value = "savingAccountService")
public class SavingAccountService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SavingAccountDAO savingAccountDAO;

	public SavingAccountDAO getSavingAccountDAO() {
		return savingAccountDAO;
	}

	public List<SavingAccount> getSavingAccountListbyCustomerId(int id) {
		return this.savingAccountDAO.getSavingAccountListbyCustomerId(id);
	}

	public void setSavingAccountDAO(SavingAccountDAO savingAccountDAO) {
		this.savingAccountDAO = savingAccountDAO;
	}

	public List<SavingAccount> getSavingAccountList() {
		return this.savingAccountDAO.getSavingAccountList();
	}
	public List<SavingAccount> getListSavingAccountByRepeatable(
			String repeat) {
		return this.savingAccountDAO.getListSavingAccountByRepeatable(repeat);
	}
	public boolean createSavingAccount(SavingAccount SavingAccount) {
		return this.getSavingAccountDAO().createSavingAccount(SavingAccount);
	}

	public SavingAccount getSavingAccountByNumber(int savingaccount_number) {
		return this.savingAccountDAO
				.getSavingAccountByNumber(savingaccount_number);

	}

	public List<SavingAccount> getSavingAccountByCustomerIDNumber(
			String IDNumber) {

		return this.savingAccountDAO
				.getSavingAccountByCustomerIDNumber(IDNumber);
	}

	public List<SavingAccount> getSavingAccountByState(String state) {
		return this.getSavingAccountDAO().getSavingAccountByState(state);
	}

	public boolean updateSavingAccount(SavingAccount savingAccount) {
		return this.getSavingAccountDAO().updateSavingAccount(savingAccount);
	}

	/**
	 * Delete Transaction by their Id.
	 *
	 * @param TransactionId
	 *            the Transaction Id.
	 */
	public boolean deleteSavingAccountById(int savingAccountId) {
		return this.savingAccountDAO.deleteSavingAccountById(savingAccountId);
	}

	public SavingAccount getSavingAccount(int id) {
		return this.savingAccountDAO.getSavingAccount(id);
	}

	public boolean checkTransaction(SavingAccount savingAccount,
			Transaction tran) {
		boolean check = true;
		if (savingAccount.getState().equals("deactive")) {
			check = false;
		} else {
			if (tran.getTransactionType().equals("withdraw")) {
				if (savingAccount.getBalanceAmount() < tran.getAmount()) {
					check = false;
				}
			} else {
				if (tran.getState().equals("deny")) {
					check = false;
				}

			}

		}

		return check;
	}

	public boolean checkDate(String dateStart, String dateEnd) {

		Date myDateStart = convertStringToDate(dateStart);
		Date myDateEnd = convertStringToDate(dateEnd);
		if (myDateEnd.getTime() <= (myDateStart.getTime())) {
			return false;
		} else
			return true;
	}

	public String convertStringDateToStringDate(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			date = (Date) formatter.parse(mydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date.toString();
	}

	public Date convertStringToDate(String mydate) {
		Date date = null;
		try {
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			date = (Date) formatter.parse(mydate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public String convertDateToStringDDmmYYYY(Date date) {
		String myDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
		return myDate;
	}
	public Date convertStringToDateDDmmYYYY(String mydate) {
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
	public String convertDateToString(Date date) {
		String myDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(date);
		return myDate;
	}
	public String convertTimeToString(Date date) {
		String myDate = new SimpleDateFormat("hh:mm:ss").format(date);
		return myDate;
	}
	
	public String myAfterBalanceAmount() {
		return this.savingAccountDAO.myAfterBalanceAmount();
	}
	public static void main(String[] args) {
		 String d1 = "20/11/2015 00:00:00";
		 String d2 = "14/11/2015";
		 SavingAccountService a = new SavingAccountService();
		
		Date date= a.convertStringToDate(d1);
		
		String b=a.convertDateToString(date);
		
		System.out.println(date.toString());
		System.out.println(date.getDate());
		System.out.println(b);
		String d3=d2.concat(" "+a.convertTimeToString(new Date()));
		System.out.println(d3);
		// Date date1 = new Date();
		// Date date2 = new Date();
		//
		// date2 = DateUtils.addMonths(date1, 5);
		// System.out.println(a.convertStringToDate(date1.toString()));
		// System.out.println(a.convertDateToString(date2));

	
	}

}

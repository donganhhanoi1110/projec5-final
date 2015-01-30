// Saving Account Service 
package com.csc.fresher.java.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import com.csc.fresher.java.dao.SavingAccountDAO;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.SavingAccount;
import com.csc.fresher.java.domain.Transaction;

@Service(value = "savingAccountService")
public class SavingAccountService {
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

		Date myDateStart = convertDateTime(dateStart);
		Date myDateEnd = convertDateTime(dateEnd);
		if (myDateEnd.getTime() <= (myDateStart.getTime())) {
			return false;
		} else
			return true;
	}

	public Date convertDateTime(String mydate) {
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

	public static void main(String[] args) {
		String d1 = "20/11/2015";
		String d2 = "14/11/2015";
		SavingAccountService a = new SavingAccountService();

		System.out.println(a.checkDate(d1, d2));
	}

}

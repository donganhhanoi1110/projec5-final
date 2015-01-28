// Saving Account Service 
package com.csc.fresher.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	
	public void setSavingAccountDAO(SavingAccountDAO savingAccountDAO) {
		this.savingAccountDAO = savingAccountDAO;
	}

	public List<SavingAccount> getSavingAccountList() {
		return this.savingAccountDAO.getSavingAccountList();
	}
	
	public boolean createSavingAccount(SavingAccount SavingAccount) {
		return this.getSavingAccountDAO().createSavingAccount(SavingAccount);
	}

	 public boolean updateSavingAccount(SavingAccount savingAccount) {
	    	return this.getSavingAccountDAO().updateSavingAccount(savingAccount);
	    }
	 
}

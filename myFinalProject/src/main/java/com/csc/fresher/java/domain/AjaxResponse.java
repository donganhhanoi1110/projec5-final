package com.csc.fresher.java.domain;

import java.util.List;

public class AjaxResponse {
	private boolean success;
	private String message;
	private String error_code;
	private boolean login;
	private SavingAccount savingAccount;
	private List<Transaction> listTransactions;
	private Object errorValidation;
	private boolean errorValidattionCheck;
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public boolean isErrorValidattionCheck() {
		return errorValidattionCheck;
	}
	public void setErrorValidattionCheck(boolean errorValidattionCheck) {
		this.errorValidattionCheck = errorValidattionCheck;
	}
	public Object getErrorValidation() {
		return errorValidation;
	}
	public void setErrorValidation(Object errorValidation) {
		this.errorValidation = errorValidation;
	}
	public List<Transaction> getListTransactions() {
		return listTransactions;
	}
	public void setListTransactions(List<Transaction> listTransactions) {
		this.listTransactions = listTransactions;
	}
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}
	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

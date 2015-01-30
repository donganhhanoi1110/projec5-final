package com.csc.fresher.java.domain;

public class AjaxResponse {
	private boolean success;
	private String message;
	private String error_code;
	private boolean login;
	private SavingAccount savingAccount;
	
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

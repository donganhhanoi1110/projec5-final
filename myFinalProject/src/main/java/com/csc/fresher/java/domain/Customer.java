package com.csc.fresher.java.domain;

public class Customer {

	public Long c_ID;
	
	public Long c_AccountNumber;
	
	public String c_AccountType;

	public String c_FirstName;

	public String c_LastName;
	
	public String c_MiddleName;
	
	public long c_IDNumber;
	
	public long c_Phone1;
	
	public long c_Phone2;
	
	public String c_Address1;
	
	public String c_Address2;
	
	public String c_Email;
	
	public String c_State;

	public Long getC_ID() {
		return c_ID;
	}

	public void setC_ID(Long c_ID) {
		this.c_ID = c_ID;
	}

	public Long getC_AccountNumber() {
		return c_AccountNumber;
	}

	public void setC_AccountNumber(Long c_AccountNumber) {
		this.c_AccountNumber = c_AccountNumber;
	}

	public String getC_AccountType() {
		return c_AccountType;
	}

	public void setC_AccountType(String c_AccountType) {
		this.c_AccountType = c_AccountType;
	}

	public String getC_FirstName() {
		return c_FirstName;
	}

	public void setC_FirstName(String c_FirstName) {
		this.c_FirstName = c_FirstName;
	}

	public String getC_LastName() {
		return c_LastName;
	}

	public void setC_LastName(String c_LastName) {
		this.c_LastName = c_LastName;
	}

	public String getC_MiddleName() {
		return c_MiddleName;
	}

	public void setC_MiddleName(String c_MiddleName) {
		this.c_MiddleName = c_MiddleName;
	}

	public long getC_IDNumber() {
		return c_IDNumber;
	}

	public void setC_IDNumber(long c_IDNumber) {
		this.c_IDNumber = c_IDNumber;
	}

	public long getC_Phone1() {
		return c_Phone1;
	}

	public void setC_Phone1(long c_Phone1) {
		this.c_Phone1 = c_Phone1;
	}

	public long getC_Phone2() {
		return c_Phone2;
	}

	public void setC_Phone2(long c_Phone2) {
		this.c_Phone2 = c_Phone2;
	}

	public String getC_Address1() {
		return c_Address1;
	}

	public void setC_Address1(String c_Address1) {
		this.c_Address1 = c_Address1;
	}

	public String getC_Address2() {
		return c_Address2;
	}

	public void setC_Address2(String c_Address2) {
		this.c_Address2 = c_Address2;
	}

	public String getC_Email() {
		return c_Email;
	}

	public void setC_Email(String c_Email) {
		this.c_Email = c_Email;
	}

	public String getC_State() {
		return c_State;
	}

	public void setC_State(String c_State) {
		this.c_State = c_State;
	}

	public Customer(Long c_ID, Long c_AccountNumber, String c_AccountType,
			String c_FirstName, String c_LastName, String c_MiddleName,
			long c_IDNumber, long c_Phone1, long c_Phone2, String c_Address1,
			String c_Address2, String c_Email, String c_State) {
		super();
		this.c_ID = c_ID;
		this.c_AccountNumber = c_AccountNumber;
		this.c_AccountType = c_AccountType;
		this.c_FirstName = c_FirstName;
		this.c_LastName = c_LastName;
		this.c_MiddleName = c_MiddleName;
		this.c_IDNumber = c_IDNumber;
		this.c_Phone1 = c_Phone1;
		this.c_Phone2 = c_Phone2;
		this.c_Address1 = c_Address1;
		this.c_Address2 = c_Address2;
		this.c_Email = c_Email;
		this.c_State = c_State;
	}
	
	
	public Customer() {
	}
	
	
}

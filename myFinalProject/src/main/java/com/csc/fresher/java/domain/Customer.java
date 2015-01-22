package com.csc.fresher.java.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@Column(name="account_Number")
	private Long accountNumber;

	@Column(name="account_Type")
	private String accountType;

	@Column(name="first_Name")
	private String firstName;

	@Column(name="last_Name")
	private String lastName;

	@Column(name="mid_Name")
	private String  middleName;

	@Column(name="id_Number")
	private long idNumber;

	@Column(name="phone1")
	private long phone1;

	@Column(name="phone2")
	private long phone2;

	@Column(name="address1")
	private String address1;

	@Column(name="address2")
	private String address2;

	@Column(name="email")
	private String email;

	@Column(name="state")
	private String state;
	
	@OneToMany
	@JoinColumn(name="customer1")
	private List<SavingAccount> transactions;
	




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}



	public String getAccountType() {
		return accountType;
	}



	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getMiddleName() {
		return middleName;
	}



	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}



	public long getIdNumber() {
		return idNumber;
	}



	public void setIdNumber(long idNumber) {
		this.idNumber = idNumber;
	}



	public long getPhone1() {
		return phone1;
	}



	public void setPhone1(long phone1) {
		this.phone1 = phone1;
	}



	public long getPhone2() {
		return phone2;
	}



	public void setPhone2(long phone2) {
		this.phone2 = phone2;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public Customer() {
	}

}

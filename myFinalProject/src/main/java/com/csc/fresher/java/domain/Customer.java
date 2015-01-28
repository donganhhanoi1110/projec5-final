package com.csc.fresher.java.domain;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable{
	
	@Column(name="account_type")
	private String accountType;
		
	@Column (name="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="mid_name")
	String midName;
	
	@Column (name="id_number")
	String idNumber;
	
	public int getPhone1() {
		return phone1;
	}

	public void setPhone1(int phone1) {
		this.phone1 = phone1;
	}

	public int getPhone2() {
		return phone2;
	}

	public void setPhone2(int phone2) {
		this.phone2 = phone2;
	}

	@Column (name="phone1")
	int phone1;
	
	@Column (name="phone2")
	int	phone2;
	
	@Column (name="address1")
	String add1;
	
	@Column (name="address2")
	String add2;
	
	@Column (name="email")
	String email;
	
	@Column (name="state")
	String state;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="id")
	int id;	

	@Column(name= "account_number")
	private int accountNumber;
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
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

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer(){
		
	}
	
	public Customer(int id, int accountNumber,String accountType,String firstName,
			String lastName,String midName,String idNumber,int phone1,int phone2,
			String add1,String add2,String email,String state){
		super();
		this.id=id;
		this.accountNumber=accountNumber;
		this.accountType=accountType;
		this.firstName=firstName;
		this.lastName=lastName;
		this.midName=midName;
		this.idNumber=idNumber;
		this.phone1=phone1;
		this.phone2=phone2;
		this.add1=add1;
		this.add2=add2;
		this.email=email;
		this.state=state;
	}
	
}

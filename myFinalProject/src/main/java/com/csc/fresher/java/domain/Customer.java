package com.csc.fresher.java.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="customer",uniqueConstraints = {
		@UniqueConstraint(columnNames = "id_number")})
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
		@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	Integer id;
	
	
	@Column(name = "account_number")
	@NotNull(message="The Account Number can not empty")
	private Integer accountNumber;
	

	@Column(name = "account_type")
	@NotEmpty(message="The Account Type an not empty")
	private String accountType;


	@Column(name = "first_name")
	@NotEmpty(message="Please input First Name")
	String firstName;

	
	@Column(name = "last_name")
	@NotEmpty(message=" Please input last name")
	String lastName;

	@Column(name = "mid_name")
	String midName;
	
	
	@Column(name = "id_number")
	@NotEmpty(message=" Please input ID card")
	String idNumber;
	
	
	@Column(name = "phone1")
	@NotNull(message="Please input number phone")
	Integer phone1;

	@Column(name = "phone2")
	Integer phone2;

	
	@Column(name = "address1")
	@NotEmpty(message=" Please input Address")
	String add1;

	@Column(name = "address2")
	String add2;

	
	@Column(name = "email")
	@Email(message=" Please input email")
	String email;

	
	@Column(name = "state")
	@NotEmpty(message=" Please input State")
	String state;

	@OneToMany(mappedBy = "customerId")
	private List<SavingAccount> savingaccounts;

	public Customer(Integer id, String accountType, String firstName,
			String lastName, String midName, String idNumber, Integer phone1,
			Integer phone2, String add1, String add2, String email, String state,
			List<SavingAccount> savingaccounts, Integer accountNumber) {
		super();
		this.id = id;
		this.accountType = accountType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midName = midName;
		this.idNumber = idNumber;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.add1 = add1;
		this.add2 = add2;
		this.email = email;
		this.state = state;
		this.savingaccounts = savingaccounts;
		this.accountNumber = accountNumber;
	}

	public List<SavingAccount> getSavingaccounts() {
		return savingaccounts;
	}

	public void setSavingaccounts(List<SavingAccount> savingaccounts) {
		this.savingaccounts = savingaccounts;
	}

	public Integer getPhone1() {
		return phone1;
	}

	public void setPhone1(Integer phone1) {
		this.phone1 = phone1;
	}

	public Integer getPhone2() {
		return phone2;
	}

	public void setPhone2(Integer phone2) {
		this.phone2 = phone2;
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer() {

	}

	public Customer(Integer id, Integer accountNumber, String accountType,
			String firstName, String lastName, String midName, String idNumber,
			Integer phone1, Integer phone2, String add1, String add2, String email,
			String state) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.firstName = firstName;
		this.lastName = lastName;
		this.midName = midName;
		this.idNumber = idNumber;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.add1 = add1;
		this.add2 = add2;
		this.email = email;
		this.state = state;
	}

}

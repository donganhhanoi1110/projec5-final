package com.csc.fresher.java.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="SavingAccount")
public class SavingAccount {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	


	@Column(name="balance_Amount")
	private Long balanceAmount;
	
	@Column(name="repeatable")
	private Integer repeatable;
	

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer1;
	
	// alert: Relationship between InterestRate and SavingAccount
	@OneToOne
	@JoinColumn(name="interest_rate_id")
	private  InterestRate interestRate;
	
	
	
	@OneToMany(mappedBy="savingAccount")
	private List<Transaction> transactions;
	
	
	
	public InterestRate getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(InterestRate interestRate) {
		this.interestRate = interestRate;
	}



	public List<Transaction> getTransactions() {
		return transactions;
	}



	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Customer getCustomer() {
		return customer1;
	}



	public void setCustomer(Customer customerID) {
		this.customer1 = customerID;
	}



	public Long getBalanceAmount() {
		return balanceAmount;
	}



	public void setBalanceAmount(Long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}



	public Integer getRepeatable() {
		return repeatable;
	}



	public void setRepeatable(Integer repeatable) {
		this.repeatable = repeatable;
	}



	public InterestRate getInterest() {
		return interestRate;
	}



	public void setInterest(InterestRate interestRate) {
		this.interestRate = interestRate;
	}



	public SavingAccount() {

	}
	
	
	
	
		
	
}

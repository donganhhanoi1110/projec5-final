package com.csc.fresher.java.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "savingaccount")
public class SavingAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "balance_amount")
	private float balanceAmount;

	@Column(name = "repeatable")
	private int repeatable;

	@Column(name = "state")
	private String state;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	@ManyToOne
	@JoinColumn(name = "interest_rate_id")
	private InterestRate interestRateId;

	@OneToMany(mappedBy="savingAccountId")
	private List<Transaction> transactions;
	
	public SavingAccount(int id, float balanceAmount, int repeatable,
			String state, Customer customerId, InterestRate interestRateId,
			List<Transaction> transactions) {
		super();
		this.id = id;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.state = state;
		this.customerId = customerId;
		this.interestRateId = interestRateId;
		this.transactions = transactions;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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

	public float getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public int getRepeatable() {
		return repeatable;
	}

	public void setRepeatable(int repeatable) {
		this.repeatable = repeatable;
	}

	public SavingAccount(int id, float balanceAmount, int repeatable,
			String state) {
		super();

		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;

		this.state = state;
		this.id = id;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public SavingAccount() {

	}

	public SavingAccount(int id, float balanceAmount, int repeatable,
			String state, Customer customerId) {
		super();
		this.id = id;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.state = state;
		this.customerId = customerId;
	}

	public InterestRate getInterestRateId() {
		return interestRateId;
	}

	public void setInterestRateId(InterestRate interestRateId) {
		this.interestRateId = interestRateId;
	}

	public SavingAccount(int id, float balanceAmount, int repeatable,
			String state, Customer customerId, InterestRate interestRateId) {
		super();
		this.id = id;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.state = state;
		this.customerId = customerId;
		this.interestRateId = interestRateId;
	}

}

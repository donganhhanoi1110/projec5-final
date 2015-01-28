package com.csc.fresher.java.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="savingaccount")
public class SavingAccount{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="customer_id")
	private int customerId;
	
	@Column(name="balance_amount")
	private float balanceAmount;
	
	@Column(name="repeatable")
	private int repeatable;
	
	@Column(name="interest_rate_id")
	private int interestRateId;
	
	@Column(name="state")
	private String state;
	
	

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

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getInterestRateId() {
		return interestRateId;
	}

	public void setInterestRateId(int interestRateId) {
		this.interestRateId = interestRateId;
	}

	public SavingAccount(int customerId, float balanceAmount,
			int repeatable, int interestRateId,String state) {
		super();
		this.customerId = customerId;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.interestRateId = interestRateId;
		this.state = state;
	}
	
	public SavingAccount(){
		
	}
	
	
}

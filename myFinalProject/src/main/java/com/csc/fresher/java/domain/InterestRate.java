package com.csc.fresher.java.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class InterestRate {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="saving_Account_Type")
	private String savingAccountType;
	
	@Column(name="interestRate")
	private Float interestRate;
	
	@Column(name="saving_Account_Type")
	private String currency;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getSavingAccountType() {
		return savingAccountType;
	}



	public void setSavingAccountType(String savingAccountType) {
		this.savingAccountType = savingAccountType;
	}



	public Float getInterestRate() {
		return interestRate;
	}



	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}



	public String getCurrency() {
		return currency;
	}



	public void setCurrency(String currency) {
		this.currency = currency;
	}



	public InterestRate() {

	}
	
	

}

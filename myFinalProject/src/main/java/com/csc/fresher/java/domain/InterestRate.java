package com.csc.fresher.java.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "interestrate")
public class InterestRate implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "savingaccount_type")
	private String savingAccountType;

	@Column(name = "interest_rate")
	private float interestRate;

	@Column(name = "currency")
	private String currency;
	
	@Column(name="month")
	private int month;
	


	@OneToMany(mappedBy = "interestRateId")
	private List<SavingAccount> savingaccounts;

	public InterestRate(int id, String savingAccountType, float interestRate,
			String currency, List<SavingAccount> savingaccounts) {
		super();
		this.id = id;
		this.savingAccountType = savingAccountType;
		this.interestRate = interestRate;
		this.currency = currency;
		this.savingaccounts = savingaccounts;
	}

	public List<SavingAccount> getSavingaccounts() {
		return savingaccounts;
	}

	public void setSavingaccounts(List<SavingAccount> savingaccounts) {
		this.savingaccounts = savingaccounts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
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

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	public InterestRate() {

	}

	public InterestRate(int id, String savingAccountType, float interestRate,
			String currency) {
		super();
		this.id = id;
		this.savingAccountType = savingAccountType;
		this.interestRate = interestRate;
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "InterestRate [id=" + id + ", savingAccountType="
				+ savingAccountType + ", interestRate=" + interestRate
				+ ", currency=" + currency + "]";
	}

}

package com.csc.fresher.java.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="date_Start")
	private String dateStart;
	
	@Column(name="date_End")
	private String dateEnd;
	
	@Column(name="state")
	private Long state;

	@ManyToOne
	@JoinColumn(name="saving_Account_Id")
	private SavingAccount savingAccount;
	
	 
    @ManyToMany
    @JoinTable(name="Transaction_has_User", joinColumns={@JoinColumn(name="transaction_id")}, inverseJoinColumns={@JoinColumn(name="user_id")})
    private List<User> user;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTransactionAmount() {
		return amount;
	}

	public void setTransactionAmount(Long transactionAmount) {
		this.amount = transactionAmount;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public SavingAccount getSavingAccountId() {
		return savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccountId) {
		this.savingAccount = savingAccountId;
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Transaction() {
	}



	
}

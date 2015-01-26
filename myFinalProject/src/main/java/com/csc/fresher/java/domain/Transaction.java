package com.csc.fresher.java.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction  {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "amount")
	private float amount;

	@Column(name = "date_start")
	private String dateStart;

	@Column(name = "date_end")
	private String dateEnd;
	
	@Column(name="saving_account_id")
	private int savingAccountId;
	
	@Column(name = "state")
	private String state;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "transactionuser", joinColumns = { 
			@JoinColumn(name = "transaction_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<User> transactions;
	public Set<User> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<User> transactions) {
		this.transactions = transactions;
	}

	public int getSavingAccountId() {
		return savingAccountId;
	}

	public void setSavingAccountId(int savingAccountId) {
		this.savingAccountId = savingAccountId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTransactionAmount() {
		return amount;
	}

	public void setTransactionAmount(float transactionAmount) {
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Transaction() {
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Transaction(int id, float amount, String dateStart, String dateEnd,
			int savingAccountId, String state) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.savingAccountId = savingAccountId;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + ", savingAccountId="
				+ savingAccountId + ", state=" + state + "]";
	}






}

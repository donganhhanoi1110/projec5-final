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
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
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
	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "state")
	private String state;

	@Column(name = "current_balance")
	private float currentBalance;

	@Column(name = "after_balance")
	private float afterBalance;

	@ManyToOne
	@JoinColumn(name = "saving_account_id")
	private SavingAccount savingAccountId;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "transactionuser", joinColumns = { @JoinColumn(name = "transaction_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Collection<User> transactions = new HashSet<User>();

	public Transaction(int id, float amount, String dateStart, String dateEnd,
			String transactionType, String state, float currentBalance,
			float afterBalance, SavingAccount savingAccountId,
			Collection<User> transactions) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.transactionType = transactionType;
		this.state = state;
		this.currentBalance = currentBalance;
		this.afterBalance = afterBalance;
		this.savingAccountId = savingAccountId;
		this.transactions = transactions;
	}

	public Transaction(int id, float amount, String dateStart, String dateEnd,
			String transactionType, String state, float currentBalance,
			float afterBalance, SavingAccount savingAccountId) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.transactionType = transactionType;
		this.state = state;
		this.currentBalance = currentBalance;
		this.afterBalance = afterBalance;
		this.savingAccountId = savingAccountId;
	}

	public float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public float getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(float afterBalance) {
		this.afterBalance = afterBalance;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Collection<User> getTransactions() {
		return transactions;
	}

	public void setTransactions(Collection<User> transactions) {
		this.transactions = transactions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
			String transactionType, String state, SavingAccount savingAccountId) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.transactionType = transactionType;
		this.state = state;
		this.savingAccountId = savingAccountId;
	}

	public Transaction(int id, float amount, String dateStart, String dateEnd,
			String transactionType, String state,
			SavingAccount savingAccountId, Collection<User> transactions) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.transactionType = transactionType;
		this.state = state;
		this.savingAccountId = savingAccountId;
		this.transactions = transactions;
	}

	public Transaction(int id, float amount, String dateStart, String dateEnd,
			String transactionType, String state) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.transactionType = transactionType;
		this.state = state;
	}

	public SavingAccount getSavingAccountId() {
		return savingAccountId;
	}

	public void setSavingAccountId(SavingAccount savingAccountId) {
		this.savingAccountId = savingAccountId;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", dateStart="
				+ dateStart + ", dateEnd=" + dateEnd + ", savingAccountId="
				+ savingAccountId + ", state=" + state + "]";
	}

}

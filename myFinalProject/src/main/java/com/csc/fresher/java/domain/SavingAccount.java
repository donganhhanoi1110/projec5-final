package com.csc.fresher.java.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "savingaccount", uniqueConstraints = { @UniqueConstraint(columnNames = "savingaccount_number") })
public class SavingAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "savingaccount_number")
	@NotNull(message = "Saving Account Number Cant Be Null")
	private Integer savingAccountNumber;

	@Column(name = "balance_amount")
	@NotNull(message = "Balance Amount Cant Be Null")
	private Float balanceAmount;

	@Column(name = "repeatable")
	@NotNull(message = "Saving Account Number Cant Be Null")
	private String repeatable;

	@Column(name = "state")
	@NotEmpty(message = "Please Input Select State.")
	private String state;

	@Column(name = "date_start")
	@NotEmpty(message = "Please Input Date Start.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dateStart;

	@Column(name = "date_end")
	private String dateEnd;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	@ManyToOne
	@JoinColumn(name = "interest_rate_id")
	private InterestRate interestRateId;

	@OneToMany(mappedBy = "savingAccountId")
	private List<Transaction> transactions;

	public SavingAccount(Integer id, Integer savingAccountNumber,
			Float balanceAmount, String repeatable, String state,
			String dateStart, String dateEnd, Customer customerId,
			InterestRate interestRateId, List<Transaction> transactions) {
		super();
		this.id = id;
		this.savingAccountNumber = savingAccountNumber;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.state = state;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.customerId = customerId;
		this.interestRateId = interestRateId;
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "SavingAccount [id=" + id + ", savingAccountNumber="
				+ savingAccountNumber + ", balanceAmount=" + balanceAmount
				+ ", repeatable=" + repeatable + ", state=" + state
				+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd
				+ ", customerId=" + customerId + ", interestRateId="
				+ interestRateId + "]";
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

	public SavingAccount(Integer id, Float balanceAmount, String repeatable,
			String state, Customer customerId, InterestRate interestRateId,
			List<Transaction> transactions, Integer savingaccountnumber) {
		super();
		this.id = id;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.state = state;
		this.customerId = customerId;
		this.interestRateId = interestRateId;
		this.transactions = transactions;
		this.savingAccountNumber = savingaccountnumber;
	}

	public Integer getSavingAccountNumber() {
		return savingAccountNumber;
	}

	public void setSavingAccountNumber(Integer savingAccountNumber) {
		this.savingAccountNumber = savingAccountNumber;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Float i) {
		this.balanceAmount = i;
	}

	public String getRepeatable() {
		return repeatable;
	}

	public void setRepeatable(String repeatable) {
		this.repeatable = repeatable;
	}

	public SavingAccount(Integer id, Float balanceAmount, String repeatable,
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

	public SavingAccount(Integer id, Float balanceAmount, String repeatable,
			String state, Customer customerId, Integer savingaccountnumber) {
		super();
		this.id = id;
		this.balanceAmount = balanceAmount;
		this.repeatable = repeatable;
		this.state = state;
		this.customerId = customerId;
		this.savingAccountNumber = savingaccountnumber;
	}

	public InterestRate getInterestRateId() {
		return interestRateId;
	}

	public void setInterestRateId(InterestRate interestRateId) {
		this.interestRateId = interestRateId;
	}

	public SavingAccount(Integer id, Float balanceAmount, String repeatable,
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

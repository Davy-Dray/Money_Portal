package com.ragnar.moneyportal.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private String transactType;
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private float amount;
	private float balance;

	@ManyToOne(cascade = CascadeType.ALL)
	private Account fromAccount;

	public Transaction() {

	}

	public Transaction(String transactType, float amount, float balance, Account fromAccount) {
		this.transactType = transactType;
		this.amount = amount;
		this.balance = balance;
		this.fromAccount = fromAccount;
		this.transactionDate = LocalDate.now();
		this.transactionTime = LocalTime.now();
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactType() {
		return transactType;
	}

	public void setTransactType(String transactType) {
		this.transactType = transactType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public LocalTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}

}
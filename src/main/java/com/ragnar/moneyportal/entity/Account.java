package com.ragnar.moneyportal.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private float balance;
	
	@NotBlank(message = "email is mandatory")
	private String accountNo;
	
	@NotBlank(message = "email is mandatory")
	private String accountName;
	
	@NotBlank(message = "email is mandatory")
	private String accountType;
	
	@NotBlank(message = "email is mandatory")
	private String bankName;

	@ManyToOne
	private User accountOwner;

	public Account() {
	}

	public Account(float balance, String accountNo, String accountName, String accountType, String bankName,
			User accountOwner) {
		this.balance = balance;
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.accountType = accountType;
		this.bankName = bankName;
		this.accountOwner = accountOwner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public User getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(User accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}

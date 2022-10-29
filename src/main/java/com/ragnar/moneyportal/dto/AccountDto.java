package com.ragnar.moneyportal.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountDto {

	private float balance;

	@NotBlank(message = "email is mandatory")
	private String accountNo;

	@NotBlank(message = "email is mandatory")
	private String accountName;

	@NotBlank(message = "email is mandatory")
	private String accountType;

	@NotBlank(message = "email is mandatory")
	private String bankName;

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

package com.ragnar.moneyportal.service;

import java.util.List;

import com.ragnar.moneyportal.entity.Account;
import com.ragnar.moneyportal.entity.Transaction;

public interface TransactionService {

	public List<Transaction> findTransactionByAccountId(Account account);

	public float getToatalIncome(Account account, String type);
}

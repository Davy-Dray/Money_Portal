package com.ragnar.moneyportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragnar.moneyportal.dao.TransactionRepository;
import com.ragnar.moneyportal.entity.Account;
import com.ragnar.moneyportal.entity.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findTransactionByAccountId(Account account) {
		return transactionRepository.findTransactionByfromAccount(account);
	}

	@Override
	public float getToatalIncome(Account account, String type) {
		return transactionRepository.
				findTransactionByTransactTypeAndFromAccount(type, account)
				.stream()
				.map(Transaction::getAmount).reduce((float) 0.0, Float::sum);
	}
	/*
	 * sort accounts transaction by date
	 * 
	 */
     
	
}

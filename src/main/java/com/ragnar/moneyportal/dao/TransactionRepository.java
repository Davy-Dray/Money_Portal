package com.ragnar.moneyportal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragnar.moneyportal.entity.Account;
import com.ragnar.moneyportal.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findTransactionByfromAccount(Account account);

	List<Transaction> findTransactionByTransactTypeAndFromAccount(String type,Account account);
}

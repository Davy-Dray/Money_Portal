package com.ragnar.moneyportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ragnar.moneyportal.dao.AccountRepository;
import com.ragnar.moneyportal.dao.TransactionRepository;
import com.ragnar.moneyportal.dao.UserRepository;
import com.ragnar.moneyportal.dto.AccountDto;
import com.ragnar.moneyportal.entity.Account;
import com.ragnar.moneyportal.entity.Transaction;
import com.ragnar.moneyportal.entity.User;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public void addAccount(AccountDto account, String username) {
		User owner = userRepository.findByUserName(username);
		float balance = account.getBalance();
		String accountNumber = account.getAccountNo();
		String accountType = account.getAccountType();
		String accountName = account.getAccountName();
        String bankName = account.getBankName();
		Account newAccount = new Account(balance, accountNumber, accountName, accountType, bankName, owner);
		accountRepository.save(newAccount);
	}
	@Override
	public List<Account> getCustomerAccounts(String username) {
		return accountRepository.findAccountByAccountOwnerUserName(username);
	}

	@Override
	public double getAccountTotal(String username) {
		return accountRepository.findAccountByAccountOwnerUserName(username)
				.stream()
				.map(Account::getBalance)
				.reduce((float) 0, Float::sum);
	}
	
	@Override
	public Page<Account> findPaginated(int pageNo, int pageSize, String username) {
		User user = userRepository.findByUserName(username);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		return accountRepository.list(user.getId(), pageable);
	}
	
	@Override
	public Account checkAccount(String accountNumber) {
		return accountRepository.findByAccountNo(accountNumber);
	}
	
	public Account withdraw(String accountNumber, float amount) {
		Account account = checkAccount(accountNumber);
		if (account.getBalance() < amount)
			throw new IllegalArgumentException("Insufficient funds");
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
		return account;
	}
	
	private void createTransactionHistory(String typeOfTransaction, float amount, float postBalance,Account associatedAccount) {
		Transaction transaction = new Transaction(typeOfTransaction, amount, postBalance, associatedAccount);
		transactionRepository.save(transaction);
	}
	
	public Account deposit(String accountNumber, float amount) {
		Account account = checkAccount(accountNumber);
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);
		return account;
	}
	
	@Transactional
	public void transfer(String sender, String receiver, float theAmount) {
		Account from = withdraw(sender, theAmount);
		createTransactionHistory("TRANSFER-DEBIT", theAmount, from.getBalance(), from);
		Account to = deposit(receiver, theAmount);
		createTransactionHistory("TRANSFER-CREDIT", theAmount, to.getBalance(), to);
	}

	@Override
	public Account findAccountbyId(long id) {
		Optional<Account> account = accountRepository.findById(id); 
		Account theAccount = null;
		if (account.isPresent()) {
			theAccount = account.get();
		} else {
			throw new RuntimeException("not found " + id);
		}
		return theAccount;
	}
	@Override
	public void deleteAccountById(long id) {
		
		accountRepository.deleteById(id);
	}
}

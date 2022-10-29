package com.ragnar.moneyportal.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ragnar.moneyportal.dto.AccountDto;
import com.ragnar.moneyportal.entity.Account;

public interface AccountService {

	public void addAccount(AccountDto account,String username);
	
	public List<Account>getCustomerAccounts(String username);
	
	Page<Account> findPaginated(int pageNo,int pageSize,String username);
	
	public double getAccountTotal(String username);
	
	public Account deposit(String username,float amount);
	
	public void transfer(String sender, String receiver, float theAmount);
	
	public Account checkAccount(String accountNo);
	
	public Account findAccountbyId(long id);
	
	public void deleteAccountById(long id);
	
	
}

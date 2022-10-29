package com.ragnar.moneyportal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ragnar.moneyportal.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
	List<Account> findAccountByAccountOwnerUserName(String username);
	@Query("SELECT o FROM Account o WHERE AccountOwnerId=:x order by o.bankName DESC")
	public Page<Account> list(@Param("x") Long id, Pageable pageable);
	Account findByAccountNo(String accountNumber);
	public void deleteAccountOwnerById(Long id);
	
}

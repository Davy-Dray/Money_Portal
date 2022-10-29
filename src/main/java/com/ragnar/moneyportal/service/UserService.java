package com.ragnar.moneyportal.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ragnar.moneyportal.entity.User;
import com.ragnar.moneyportal.user.CrmUser;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

	public void save(CrmUser crmUser);
}

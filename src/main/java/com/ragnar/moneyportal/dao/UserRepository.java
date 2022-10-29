package com.ragnar.moneyportal.dao;

import org.springframework.data.repository.CrudRepository;

import com.ragnar.moneyportal.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUserName(String username);
}

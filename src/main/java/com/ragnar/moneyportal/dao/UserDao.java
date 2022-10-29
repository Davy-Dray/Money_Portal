package com.ragnar.moneyportal.dao;

import com.ragnar.moneyportal.entity.User;

public interface UserDao {

    public User findByUserName(String userName);
    
    public void save(User user);
    
}

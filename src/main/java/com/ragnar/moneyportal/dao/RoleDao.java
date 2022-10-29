package com.ragnar.moneyportal.dao;

import com.ragnar.moneyportal.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}

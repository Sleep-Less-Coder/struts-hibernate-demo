package com.hemant.struts.hibernate.demo.dao;

import com.hemant.struts.hibernate.demo.model.User;

public interface UserDAO {
	
	User getUserByCredentials(String userId, String password);
	
}

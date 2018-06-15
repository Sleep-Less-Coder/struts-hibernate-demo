package com.hemant.struts.hibernate.demo.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hemant.struts.hibernate.demo.model.User;

public class UserDAOImpl implements UserDAO {

	SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUserByCredentials(String id, String password) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from User where id=:id and password=:password");
		query.setString("id", id);
		query.setString("password", password);
		User user = (User) query.uniqueResult();
		
		if(user != null) {
			System.out.println(user + " retrieved from the database.");
		}
		
		transaction.commit();
		session.close();
		
		return user;
	}

}

package com.hemant.struts.hibernate.demo.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.hemant.struts.hibernate.demo.dao.UserDAOImpl;
import com.hemant.struts.hibernate.demo.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction implements Action, ModelDriven<User>, ServletContextAware {

	private User user = new User();
	private ServletContext servletContext;

	@Override
	public String execute() throws Exception {
		
		SessionFactory sessionFactory = (SessionFactory) servletContext.getAttribute("SessionFactory");
		UserDAOImpl userDAOImpl = new UserDAOImpl(sessionFactory);
		User userDB = userDAOImpl.getUserByCredentials(user.getId(), user.getPassword());
		
		if(userDB == null) return ERROR;
		else {
			user.setEmail(userDB.getEmail());
			user.setName(userDB.getName());
			return SUCCESS;
		}
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	@Override
	public User getModel() {
		return user;
	}

}

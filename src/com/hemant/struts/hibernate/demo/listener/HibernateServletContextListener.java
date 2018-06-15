package com.hemant.struts.hibernate.demo.listener;

import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SessionFactory sessionFactory = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
		sessionFactory.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.xml");
		Configuration configuration = new Configuration();
		configuration.configure(url);
		ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
	}

}

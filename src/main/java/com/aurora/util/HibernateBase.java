package com.aurora.util;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateBase {
	 public static Session getSession() throws IOException{
		 // Create configuration instance
		 Configuration configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
		 applySettings(configuration.getProperties()).build(); 

		 // Create session factory instance
		 SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

		 // Get current session
		 Session session = factory.getCurrentSession();
		 return session;
		 }
}

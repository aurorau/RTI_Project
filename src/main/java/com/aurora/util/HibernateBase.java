package com.aurora.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateBase<T, PK extends Serializable> {
	
	private Class<T> persistentClass;
	
    private static SessionFactory factory;
    static {
    	
		 // Create configuration instance
		 Configuration configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
		 applySettings(configuration.getProperties()).build(); 

		 // Create session factory instance
		 factory = configuration.buildSessionFactory(serviceRegistry);
    	
        //factory = new Configuration().configure().buildSessionFactory();
    }

    public Session getSession() {
        return factory.openSession();
    }

    // Call this during shutdown
    public static void factoryClose() {
        factory.close();
    }
	
	
/*	 public static Session getSession() throws IOException{

		 
		 // Create configuration instance
		 Configuration configuration = new Configuration();
		 configuration.configure("hibernate.cfg.xml");
		 ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
		 applySettings(configuration.getProperties()).build(); 

		 // Create session factory instance
		 SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

		 // Get current session
		 //Session session = factory.getCurrentSession();
		 Session session = factory.openSession();
		 return session;
		}*/
	 
/*	 public T getById(PK id) throws IOException {
	        Session sess = getSession();
	        IdentifierLoadAccess byId = sess.byId(persistentClass);
	        T entity = (T) byId.load(id);

	        if (entity == null) {
	            //log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
	           // throw new ObjectRetrievalFailureException(this.persistentClass, id);
	        }
	        return entity;
	 }*/
}

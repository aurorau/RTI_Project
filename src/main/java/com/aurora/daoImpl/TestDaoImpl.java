package com.aurora.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aurora.dao.TestDao;
import com.aurora.model.Person;
import com.aurora.util.HibernateBase;

@Repository("testDaoImpl")
public class TestDaoImpl extends HibernateBase  implements TestDao{
	
	@Transactional
	public void personSave(Person person) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(person);
		session.getTransaction().commit();
	}
}

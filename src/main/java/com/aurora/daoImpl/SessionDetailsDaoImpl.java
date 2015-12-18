package com.aurora.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.SessionDetails;
import com.aurora.util.HibernateBase;

@Repository("sessionDetailsDao")
public class SessionDetailsDaoImpl extends HibernateBase implements SessionDetailsDao {

	@Transactional
	public void saveSessionDetails(SessionDetails sessionDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(sessionDetails);
		session.getTransaction().commit();
		
	}

}

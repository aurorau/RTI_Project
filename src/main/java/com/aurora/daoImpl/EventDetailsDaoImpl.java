package com.aurora.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aurora.dao.EventDetailsDao;
import com.aurora.model.EventDetails;
import com.aurora.util.HibernateBase;

@Repository("eventDetailsDao")
public class EventDetailsDaoImpl extends HibernateBase implements EventDetailsDao {

	@Transactional
	public void saveEventDetails(EventDetails eventDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(eventDetails);
		session.getTransaction().commit();
	}

}

package com.aurora.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aurora.dao.BrowserDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.util.HibernateBase;

@Repository("browserDetailsDao")
public class BrowserDetailsDaoImpl extends HibernateBase implements BrowserDetailsDao {

	@Transactional
	public void saveBrowserDetails(BrowserDetails browserDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(browserDetails);
		session.getTransaction().commit();
		
	}

}

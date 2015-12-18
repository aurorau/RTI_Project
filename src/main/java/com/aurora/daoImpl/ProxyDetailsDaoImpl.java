package com.aurora.daoImpl;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.aurora.dao.ProxyDetailsDao;
import com.aurora.model.ProxyDetails;
import com.aurora.util.HibernateBase;

@Repository("proxyDetailsDao")
public class ProxyDetailsDaoImpl extends HibernateBase implements ProxyDetailsDao {

	@Transactional
	public void saveProxyDetailsService(ProxyDetails proxyDetails) throws Exception {
		
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(proxyDetails);
		session.getTransaction().commit();
	}

}

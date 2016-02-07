package com.aurora.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.aurora.dao.DeviceDetailsDao;
import com.aurora.model.DeviceDetails;
import com.aurora.model.SessionDetails;
import com.aurora.util.HibernateBase;

@Repository("deviceDetailsDao")
public class DeviceDetailsDaoImpl extends HibernateBase implements DeviceDetailsDao {

	@Transactional
	public void saveDeviceDetails(DeviceDetails deviceDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.saveOrUpdate(deviceDetails);
		session.getTransaction().commit();
		session.close();
	}

	@Transactional
	public List<DeviceDetails> getDeviceCount() throws Exception {
		
		List<DeviceDetails> deviceDetailsList = null;
		
		Session session = getSession();
		
		String hql= "FROM DeviceDetails";
		session.getTransaction().begin();
			Query q = session.createQuery(hql);
			deviceDetailsList = q.list();
		session.getTransaction().commit();
		session.close();
		return deviceDetailsList;
	}

	public DeviceDetails getDeviceDetailsById(Long did) throws Exception {
		DeviceDetails deviceDetails = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(DeviceDetails.class,"deviceDetails")
				.add(Restrictions.eq("deviceDetails.DID", did));
		
		deviceDetails = (DeviceDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return deviceDetails;
	}

}

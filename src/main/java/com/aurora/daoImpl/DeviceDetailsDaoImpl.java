package com.aurora.daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.aurora.dao.DeviceDetailsDao;
import com.aurora.model.DeviceDetails;
import com.aurora.util.HibernateBase;

@Repository("deviceDetailsDao")
public class DeviceDetailsDaoImpl extends HibernateBase implements DeviceDetailsDao {

	@Transactional
	public void saveDeviceDetails(DeviceDetails deviceDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(deviceDetails);
		session.getTransaction().commit();
	}

}

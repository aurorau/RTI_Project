package com.aurora.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.aurora.dao.ProxyDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.ProxyDetails;
import com.aurora.util.BrowserAndDeviceDetailsDTO;
import com.aurora.util.HibernateBase;
import com.aurora.util.ProxyDetailsDTO;

@Repository("proxyDetailsDao")
public class ProxyDetailsDaoImpl extends HibernateBase implements ProxyDetailsDao {

	@Transactional
	public void saveProxyDetailsService(ProxyDetails proxyDetails) throws Exception {
		
		Session session = getSession();
		
		session.getTransaction().begin();
		session.save(proxyDetails);
		session.getTransaction().commit();
		session.close();
	}

	@Transactional
	public List<ProxyDetailsDTO> getProxyDetails(Long bid) {
		
		List<ProxyDetailsDTO> list = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(ProxyDetails.class,"proxyDetails")
				.createAlias("proxyDetails.browserDetails", "browserDetails")
				.add(Restrictions.eq("browserDetails.BID", bid));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("ipAddress").as("ip"))
				.add(Projections.property("countryName").as("countryName")));
		
		list = criteria.setResultTransformer(Transformers.aliasToBean(ProxyDetailsDTO.class)).list();
		
		session.getTransaction().commit();
		session.close();
		
		return list;
	}

}

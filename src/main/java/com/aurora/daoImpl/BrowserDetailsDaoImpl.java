package com.aurora.daoImpl;


import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import com.aurora.dao.BrowserDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.SessionDetails;
import com.aurora.util.BrowserAndDeviceDetailsDTO;
import com.aurora.util.HibernateBase;

@Repository("browserDetailsDao")
public class BrowserDetailsDaoImpl extends HibernateBase implements BrowserDetailsDao {

	@Transactional
	public void saveBrowserDetails(BrowserDetails browserDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.saveOrUpdate(browserDetails);
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Transactional
	public List<BrowserAndDeviceDetailsDTO> getBrowserDetails() throws Exception {
		
		List<BrowserAndDeviceDetailsDTO> list = null;
		
		
		Session session = getSession();
		//SELECT DISTINCT c FROM Classroom c, ClassroomFacility fac INNER JOIN fetch c.admStudyCentre l WHERE fac.id.classroomCode=c.code 
/*		String hql = "SELECT DISTINCT browserDetails.userAgetntId AS userAgetntId,"
				+ " browserDetails.BID AS bid,"
				+ " browserDetails.deviceDetails.DID AS did,"
				+ " dd.deviceName AS deviceName"
				+ " FROM BrowserDetails browserDetails INNER JOIN FETCH browserDetails.deviceDetails dd";*/
		
		//String hql = "SELECT browserDetails FROM BrowserDetails browserDetails INNER JOIN FETCH browserDetails.deviceDetails dd GROUP BY browserDetails.BID,dd.DID,browserDetails.userAgetntId";
		
		//String hql = " FROM BrowserDetails GROUP BY userAgetntId";
		
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails")
				.createAlias("browserDetails.deviceDetails", "deviceDetails")
						.setProjection(Projections.projectionList()
							.add(Projections.property("userAgetntId").as("userAgetntId"))
							.add(Projections.property("BID").as("bid"))
							.add(Projections.property("deviceDetails.DID").as("did"))
							.add(Projections.property("deviceDetails.deviceName").as("deviceName")));
		
		//Query q = session.createQuery(hql);
		
		//Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails")
				
		
		
		
		list = criteria.setResultTransformer(Transformers.aliasToBean(BrowserAndDeviceDetailsDTO.class)).list();
		//list = q.list();
		session.getTransaction().commit();
		session.close();
		
		
		return list;
	}

	public BrowserDetails getBrowserById(Long bid) throws Exception {
		
		BrowserDetails browserDetails = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails")
				.add(Restrictions.eq("browserDetails.BID", bid));
		
		browserDetails = (BrowserDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return browserDetails;
	}

}

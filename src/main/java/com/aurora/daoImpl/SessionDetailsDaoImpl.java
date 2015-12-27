package com.aurora.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.EventDetails;
import com.aurora.model.SessionDetails;
import com.aurora.util.BrowserAndDeviceDetailsDTO;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.HibernateBase;
import com.aurora.util.SessionBrowserDetailsDTO;
import com.aurora.util.UserDetailsDTO;

@Repository("sessionDetailsDao")
public class SessionDetailsDaoImpl extends HibernateBase implements SessionDetailsDao {

	@Transactional
	public void saveSessionDetails(SessionDetails sessionDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.saveOrUpdate(sessionDetails);
		session.getTransaction().commit();
		session.close();
		
	}

	@Transactional
	public SessionDetails getSessionDetailsByCreationTimeById(Long creationTime, String sessionId) throws Exception {
		SessionDetails sessionDetails = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.eq("sessionDetails.sessionId", sessionId));
				//.add(Restrictions.eq("sessionDetails.sessionCreatedTime", creationTime));
		
		sessionDetails = (SessionDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return sessionDetails;
	}

	@Transactional
	public SessionDetails getById(Long sid) throws Exception {
		
		SessionDetails sessionDetails = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.eq("sessionDetails.SID", sid));
		
		sessionDetails = (SessionDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return sessionDetails;
	}

	@Transactional
	public List<CurrentUsersDTO> getCurrentUserCount() throws Exception {
		List<CurrentUsersDTO> dtoList = null;
		
		String currentTime = getTime();
		String beforeTime = getBeforeTime();
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.between("sessionDetails.lastAccessTime",beforeTime, currentTime));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("SID").as("sid"))
				.add(Projections.property("lastAccessTime").as("lastAccessTime")));
		
		dtoList = criteria.setResultTransformer(Transformers.aliasToBean(CurrentUsersDTO.class)).list();
		session.getTransaction().commit();
		session.close();
		return dtoList;
	}
	
	public String getTime(){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(date);
		 return result;
	 }
	public String getBeforeTime(){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(DateUtils.addMinutes(date, -3));
		 return result;
	 }

	@Override
	public void getUserDetailsBySessionId(Long sid) throws Exception {
		
		List<UserDetailsDTO> list = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails");
			criteria.createAlias("browserDetails.sessionDetails", "sessionDetails");
			criteria.createAlias("browserDetails.deviceDetails", "deviceDetails");
			criteria.createAlias("browserDetails.eventDetails", "eventDetails");
			criteria.add(Restrictions.eq("sessionDetails.SID", sid));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("sessionDetails.SID").as("sid"))
					.add(Projections.property("eventDetails.EID").as("eid"))
					.add(Projections.property("BID").as("bid"))
					.add(Projections.property("deviceDetails.DID").as("did")));
		
			list = criteria.setResultTransformer(Transformers.aliasToBean(UserDetailsDTO.class)).list();
			
		session.getTransaction().commit();
		session.close();
		
		System.out.println(list.size());
		//list = getEID(list);
		
	}
	
	public List<UserDetailsDTO> getEID(List<UserDetailsDTO> list) {
		
		Session session = getSession();
		session.getTransaction().begin();
		
		for(UserDetailsDTO userDetailsDTO : list) {
			Criteria criteriaEvent = session.createCriteria(EventDetails.class,"eventDetails");
				criteriaEvent.add(Restrictions.eq("eventDetails.BID", userDetailsDTO.getBid()));
			
				EventDetails eventDetails = (EventDetails) criteriaEvent.uniqueResult();
				userDetailsDTO.setEid(eventDetails.getEID());
		}
		
		session.getTransaction().commit();
		session.close();
		
		return list;
	}
}

package com.aurora.daoImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.ProxyDetails;
import com.aurora.model.SessionDetails;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.HibernateBase;
import com.aurora.util.SessionTimeOutDTO;
import com.aurora.util.UserDetailsDTO;

@Repository("sessionDetailsDao")
@EnableScheduling
public class SessionDetailsDaoImpl extends HibernateBase implements SessionDetailsDao {

	public void saveSessionDetails(SessionDetails sessionDetails) throws Exception {
		Session session = getSession();
		
		session.getTransaction().begin();
		session.saveOrUpdate(sessionDetails);
		session.getTransaction().commit();
		session.close();
		
	}

	public SessionDetails getSessionDetailsByCreationTimeById(Long creationTime, String sessionId) throws Exception {
		SessionDetails sessionDetails = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.eq("sessionDetails.status", "ACTIVE"))
				.add(Restrictions.eq("sessionDetails.sessionId", sessionId));
				//.add(Restrictions.eq("sessionDetails.sessionCreatedTime", creationTime));
		
		sessionDetails = (SessionDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return sessionDetails;
	}

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

	public List<CurrentUsersDTO> getCurrentUserCount() throws Exception {
		List<CurrentUsersDTO> dtoList = null;
		
/*		String currentTime = getTime();
		String beforeTime = getBeforeTime();
		String beforeHeartBeatTime = getBeforeHeartBeatTime();*/
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.eq("status", "ACTIVE"));
/*				.add(Restrictions.between("sessionDetails.heartBeatTime",beforeHeartBeatTime, currentTime))
				.add(Restrictions.between("sessionDetails.lastAccessTime",beforeTime, currentTime));*/
		criteria.addOrder(Order.asc("SID"));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("SID").as("sid"))
				.add(Projections.property("lastAccessTime").as("lastAccessTime")));
		
		dtoList = criteria.setResultTransformer(Transformers.aliasToBean(CurrentUsersDTO.class)).list();
		session.getTransaction().commit();
		session.close();
		return dtoList;
	}
	
	public String getTime(){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(date);
		 return result;
	 }
	public String getBeforeTime(){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(DateUtils.addMinutes(date, -3));
		 return result;
	 }
	public String getBeforeHeartBeatTime(){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(DateUtils.addSeconds(date, -31));
		 return result;
	 }

	public List<UserDetailsDTO> getUserDetailsBySessionId(String sortField, int order, int start, int gridTableSize,String searchq, Long sid) throws Exception {
		
		List<UserDetailsDTO> list = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails");
			criteria.createAlias("browserDetails.sessionDetails", "sessionDetails");
			criteria.createAlias("browserDetails.deviceDetails", "deviceDetails");
			criteria.createAlias("browserDetails.eventDetails", "eventDetails");
			criteria.add(Restrictions.eq("sessionDetails.SID", sid));
			criteria.addOrder(Order.desc("eventDetails.EID"));
			criteria.setFirstResult(start)
			        .setMaxResults(gridTableSize);
			
			if(!searchq.isEmpty() && searchq != null) {
				criteria.add(Restrictions.disjunction()
				        .add(Restrictions.ilike("deviceDetails.deviceName", searchq, MatchMode.ANYWHERE))
				        .add(Restrictions.ilike("browserName", searchq, MatchMode.ANYWHERE))
				        .add(Restrictions.ilike("eventDetails.eventName", searchq, MatchMode.ANYWHERE))
				        .add(Restrictions.ilike("eventDetails.orientation", searchq, MatchMode.ANYWHERE)));
			}
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("sessionDetails.SID").as("sid"))
					.add(Projections.property("sessionDetails.lastAccessTime").as("lastAccessTime"))
					.add(Projections.property("sessionDetails.sessionCreatedTime").as("firstAccessTime"))
					.add(Projections.property("eventDetails.EID").as("eid"))
					.add(Projections.property("eventDetails.triggeredTime").as("eventTriggeredTime"))
					.add(Projections.property("eventDetails.eventTypes").as("eventName"))
					.add(Projections.property("eventDetails.coordinateX").as("coordinateX"))
					.add(Projections.property("eventDetails.coordinateY").as("coordinateY"))
					.add(Projections.property("eventDetails.screenWidth").as("screenWidth"))
					.add(Projections.property("eventDetails.screenHeight").as("screenHeight"))
					.add(Projections.property("eventDetails.orientation").as("orientation"))
					.add(Projections.property("eventDetails.viewportHeight").as("viewportHeight"))
					.add(Projections.property("eventDetails.viewportWidth").as("viewportWidth"))
					.add(Projections.property("eventDetails.numOfTaps").as("numOfTaps"))
					.add(Projections.property("eventDetails.tagName").as("tagName"))
					.add(Projections.property("eventDetails.scrollTop").as("scrollTop"))
					.add(Projections.property("eventDetails.timeZone").as("timeZone"))
					.add(Projections.property("eventDetails.zoneDateTime").as("zoneDateTime"))
					.add(Projections.property("eventDetails.imageName").as("imageName"))
					.add(Projections.property("BID").as("bid"))
					.add(Projections.property("browserName").as("browserName"))
					.add(Projections.property("browserVersion").as("browserVersion"))
					.add(Projections.property("userAgetntId").as("userAgentId"))
					.add(Projections.property("deviceDetails.DID").as("did"))
					.add(Projections.property("deviceDetails.osName").as("osName"))
					.add(Projections.property("deviceDetails.deviceName").as("deviceName")));
			list = criteria.setResultTransformer(Transformers.aliasToBean(UserDetailsDTO.class)).list();
			
		session.getTransaction().commit();
		session.close();
		
		list = getPID(list);
		return list;
	}
	
	public int getUserDetailsCountBySessionId(String searchq, Long sessionPK) {
		Session session = getSession();
		session.getTransaction().begin();
		
		int totalRowCount =0;
		Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails");
				criteria.createAlias("browserDetails.sessionDetails", "sessionDetails");
				criteria.createAlias("browserDetails.deviceDetails", "deviceDetails");
				criteria.createAlias("browserDetails.eventDetails", "eventDetails");
				criteria.add(Restrictions.eq("sessionDetails.SID",sessionPK));
				criteria.setProjection(Projections.count("eventDetails.EID"));
		
		if(!searchq.isEmpty()) {
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.ilike("deviceDetails.deviceName", searchq, MatchMode.ANYWHERE))
			        .add(Restrictions.ilike("browserName", searchq, MatchMode.ANYWHERE))
			        .add(Restrictions.ilike("eventDetails.eventName", searchq, MatchMode.ANYWHERE))
			        .add(Restrictions.ilike("eventDetails.orientation", searchq, MatchMode.ANYWHERE)));
		}
		
		Long value = (Long) criteria.uniqueResult();
		totalRowCount = Integer.valueOf(value.intValue());
		
		session.getTransaction().commit();
		session.close();
		
		return totalRowCount;
	}
	
	public List<UserDetailsDTO> getPID(List<UserDetailsDTO> list) {
		
		Session session = getSession();
		session.getTransaction().begin();
		
		for(UserDetailsDTO userDetailsDTO : list) {
			Criteria criteriaProxy = session.createCriteria(ProxyDetails.class,"proxyDetails");
			criteriaProxy.add(Restrictions.eq("proxyDetails.browserDetails.BID", userDetailsDTO.getBid()));
			criteriaProxy.setProjection(Projections.projectionList()
						.add(Projections.property("PID").as("pid")));
			
				List<Long> pidList = criteriaProxy.list();
				
				userDetailsDTO.setPid(pidList);
		}
		
		session.getTransaction().commit();
		session.close();
		
		return list;
	}
	
	public void displayData(List<UserDetailsDTO> list){
		for(UserDetailsDTO userDetailsDTO : list) {
			System.out.println("SID"+userDetailsDTO.getSid());
			System.out.println("BID"+userDetailsDTO.getBid());
			System.out.println("DID"+userDetailsDTO.getDid());
			System.out.println("EID"+userDetailsDTO.getEid());
			
			for(Long id : userDetailsDTO.getPid()) {
				System.out.println("PID"+id);
			}
		}
	}

	public List<UserDetailsDTO> analyseUserBySessionId(Long sid) {
		List<UserDetailsDTO> list = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(BrowserDetails.class,"browserDetails");
		criteria.createAlias("browserDetails.sessionDetails", "sessionDetails");
		criteria.createAlias("browserDetails.deviceDetails", "deviceDetails");
		criteria.createAlias("browserDetails.eventDetails", "eventDetails");
		criteria.add(Restrictions.eq("sessionDetails.SID", sid));
		criteria.addOrder(Order.desc("eventDetails.EID"));
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("sessionDetails.SID").as("sid"))
				.add(Projections.property("sessionDetails.lastAccessTime").as("lastAccessTime"))
				.add(Projections.property("sessionDetails.sessionCreatedTime").as("firstAccessTime"))
				.add(Projections.property("eventDetails.EID").as("eid"))
				.add(Projections.property("eventDetails.triggeredTime").as("eventTriggeredTime"))
				.add(Projections.property("eventDetails.eventTypes").as("eventName"))
				.add(Projections.property("eventDetails.coordinateX").as("coordinateX"))
				.add(Projections.property("eventDetails.coordinateY").as("coordinateY"))
				.add(Projections.property("eventDetails.screenWidth").as("screenWidth"))
				.add(Projections.property("eventDetails.screenHeight").as("screenHeight"))
				.add(Projections.property("eventDetails.orientation").as("orientation"))
				.add(Projections.property("eventDetails.viewportHeight").as("viewportHeight"))
				.add(Projections.property("eventDetails.viewportWidth").as("viewportWidth"))
				.add(Projections.property("eventDetails.numOfTaps").as("numOfTaps"))
				.add(Projections.property("eventDetails.tagName").as("tagName"))
				.add(Projections.property("eventDetails.scrollTop").as("scrollTop"))
				.add(Projections.property("eventDetails.timeZone").as("timeZone"))
				.add(Projections.property("eventDetails.zoneDateTime").as("zoneDateTime"))
				.add(Projections.property("eventDetails.imageName").as("imageName"))
				.add(Projections.property("BID").as("bid"))
				.add(Projections.property("browserName").as("browserName"))
				.add(Projections.property("browserVersion").as("browserVersion"))
				.add(Projections.property("userAgetntId").as("userAgentId"))
				.add(Projections.property("deviceDetails.DID").as("did"))
				.add(Projections.property("deviceDetails.osName").as("osName"))
				.add(Projections.property("deviceDetails.deviceName").as("deviceName")));
		list = criteria.setResultTransformer(Transformers.aliasToBean(UserDetailsDTO.class)).list();
		
		session.getTransaction().commit();
		session.close();
		
		list = getPID(list);
		return list;
	}
	
	@Scheduled(fixedDelay =60000)
	public void changeSessionStatus(){

		List<CurrentUsersDTO> allList = null;
		List<CurrentUsersDTO> activeList = null;
		
		String currentTime = getTime();
		String beforeTime = getBeforeTime();
		String beforeHeartBeatTime = getBeforeHeartBeatTime();
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.between("sessionDetails.heartBeatTime",beforeHeartBeatTime, currentTime))
				.add(Restrictions.between("sessionDetails.lastAccessTime",beforeTime, currentTime));
		criteria.addOrder(Order.asc("SID"));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("SID").as("sid"))
				.add(Projections.property("lastAccessTime").as("lastAccessTime")));	
		activeList = criteria.setResultTransformer(Transformers.aliasToBean(CurrentUsersDTO.class)).list();
		
		Criteria criteria1 = session.createCriteria(SessionDetails.class,"sessionDetails");
		criteria1.addOrder(Order.asc("SID"));
		criteria1.setProjection(Projections.projectionList()
				.add(Projections.property("SID").as("sid"))
				.add(Projections.property("lastAccessTime").as("lastAccessTime")));
		allList= criteria1.setResultTransformer(Transformers.aliasToBean(CurrentUsersDTO.class)).list();
		
		for(CurrentUsersDTO dto : allList){
			try{
				SessionDetails sd = getById(dto.getSid());
				sd.setStatus("INACTIVE");
				saveSessionDetails(sd);
			} catch(Exception e){
				System.out.println("E :"+e);
			}
		}
		
		for(CurrentUsersDTO dto : activeList){
			try{
				SessionDetails sd = getById(dto.getSid());
				sd.setStatus("ACTIVE");
				saveSessionDetails(sd);
			} catch(Exception e){
				System.out.println("E :"+e);
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	public List<SessionTimeOutDTO> getSessionIDListBySID(Long sid) {
		List<SessionTimeOutDTO> dtoList = null;
		SessionTimeOutDTO dto = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.eq("SID", sid));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("SID").as("sid"))
				.add(Projections.property("sessionId").as("sessionId")));
		dto = (SessionTimeOutDTO) criteria.setResultTransformer(Transformers.aliasToBean(SessionTimeOutDTO.class)).uniqueResult();
		
		session.getTransaction().commit();
		session.close();
		
		dtoList = getList(dto);
		return dtoList;
	}
	
	public List<SessionTimeOutDTO> getList(SessionTimeOutDTO dto) {
		List<SessionTimeOutDTO> dtoList = null;
		
		Session session = getSession();
		session.getTransaction().begin();
		
		Criteria criteria1 = session.createCriteria(SessionDetails.class,"sessionDetails")
				.add(Restrictions.eq("status", "INACTIVE"))
				.add(Restrictions.eq("sessionId", dto.getSessionId()));
		criteria1.setProjection(Projections.projectionList()
				.add(Projections.property("SID").as("sid"))
				.add(Projections.property("sessionId").as("sessionId")));
		dtoList = criteria1.setResultTransformer(Transformers.aliasToBean(SessionTimeOutDTO.class)).list();
		
		session.getTransaction().commit();
		session.close();
		return dtoList;
	}

}

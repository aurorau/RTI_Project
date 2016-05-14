package com.aurora.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.SessionDetails;
import com.aurora.service.AnalyseAgentService;
import com.aurora.service.AnalyseUserService;
import com.aurora.service.DeviceDetailsService;
import com.aurora.service.SessionDetailsService;
import com.aurora.util.AnalyseUserDTO;
import com.aurora.util.Constants;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.SessionTimeOutDTO;
import com.aurora.util.UserCountDTO;
import com.aurora.util.UserDetailsDTO;

@Service("sessionDetailsService")
public class SessionDetailsImpl implements SessionDetailsService{
	
	private SessionDetailsDao sessionDetailsDao = null;
	private AnalyseAgentService analyseAgentService = null;
	private DeviceDetailsService deviceDetailsService = null;
	private AnalyseUserService analyseUserService = null;
	
	@Autowired
	public void setSessionDetailsDao(SessionDetailsDao sessionDetailsDao) {
		this.sessionDetailsDao = sessionDetailsDao;
	}
	
	@Autowired
	public void setAnalyseAgentService(AnalyseAgentService analyseAgentService) {
		this.analyseAgentService = analyseAgentService;
	}
	
	@Autowired
	public void setDeviceDetailsService(DeviceDetailsService deviceDetailsService) {
		this.deviceDetailsService = deviceDetailsService;
	}
	
	@Autowired
	public void setAnalyseUserService(AnalyseUserService analyseUserService) {
		this.analyseUserService = analyseUserService;
	}
	
	@Transactional
	public String heartBeat(HttpServletRequest request) {
		String res = Constants.FAIL;
		SessionDetails sessionDetails = null;
		String sessionId = null;
		try {
			sessionId = request.getParameter("sessionID");
			if(!sessionId.equalsIgnoreCase("-1")){
				sessionDetails = sessionDetailsDao.getSessionDetailsByCreationTimeById(1L, sessionId);
				if(sessionDetails != null) {
					res = Constants.ACTIVE;
					sessionDetails.setHeartBeatTime(getServerTime());
					sessionDetailsDao.saveSessionDetails(sessionDetails);
				} else {
					res = Constants.INACTIVE;
				}
			}
		} catch(Exception e){
			res = Constants.ERROR;
		}
		return res;
	}
	
	@Transactional
	public String saveSessionDetails(HttpServletRequest request) {
		String res = Constants.FAIL;
		String sessionId = null;
		String currentTime = getServerTime();
	    String currentLocationBasedTime = getLocationBasedCurrentTime(request.getParameter("timeZoneOffset"));
		sessionId = request.getParameter("sessionID");
		 
		SessionDetails sessionDetails = null;

			if(sessionId == null || sessionId.equalsIgnoreCase("")) {
				sessionId = request.getSession().getId();
			} 
			
			try {
				sessionDetails = sessionDetailsDao.getSessionDetailsByCreationTimeById(1L, sessionId);

				if(sessionDetails == null) {
					sessionDetails = new SessionDetails();
					sessionDetails.setSessionId(sessionId);
					sessionDetails.setSessionAccessCount(1L);
					sessionDetails.setSessionCreatedTime(currentTime);
					sessionDetails.setLastAccessTime(currentTime);
					sessionDetails.setHeartBeatTime(getServerTime());
					sessionDetails.setStatus("ACTIVE");
				} else {
					sessionDetails = sessionDetailsDao.getById(sessionDetails.getSID());
					sessionDetails.setSessionAccessCount(sessionDetails.getSessionAccessCount()+1);
					sessionDetails.setLastAccessTime(currentTime);
					sessionDetails.setStatus("ACTIVE");
				}
				sessionDetailsDao.saveSessionDetails(sessionDetails);
				String status = deviceDetailsService.saveDeviceDetails(request, sessionDetails);
			
				if(status.equalsIgnoreCase(Constants.SUCCESS)) {
					res = Constants.SUCCESS;
				}
			} catch(Exception e) {
				System.out.println(e);
				res = Constants.ERROR;
			}
			return res;
	}

	@Transactional
	public SessionDetails getSessionDetailsByCreationTimeById(Long creationTime, String sessionId) {
		SessionDetails session = null;
		
		try {
			session = sessionDetailsDao.getSessionDetailsByCreationTimeById(creationTime, sessionId);
		} catch(Exception e) {
			System.out.println(e);
		}
		return session;
	}

	@Transactional
	public SessionDetails getById(Long sid) {
		SessionDetails session = null;
		
		try {
			session = sessionDetailsDao.getById(sid);
		} catch(Exception e) {
		}
		return session;
	}

	@Transactional
	public List<UserCountDTO> getCurrentUserCountList(String sortField, int order, int start, int gridTableSize,String searchq) {
		List<UserCountDTO> dtoList = null;

		try {
			dtoList = sessionDetailsDao.getCurrentUserCountList(sortField, order, start, gridTableSize,searchq);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return dtoList;
	}
	@Transactional
	public int getCurrentUserCount(String searchq) {
		int count = 0;
		
		try {
			count = sessionDetailsDao.getCurrentUserCount(searchq);
		}catch (Exception e){
			System.out.println("Error userCount:"+e);
		}
		
		return count;
	}
	@Transactional
	public List<UserDetailsDTO> getUserDetailsBySessionId(String sortField, int order, int start, int gridTableSize,String searchq, Long sid) {
		
		List<UserDetailsDTO> list = null;
		
		try {
			list = sessionDetailsDao.getUserDetailsBySessionId(sortField,order,start,gridTableSize,searchq, sid);
		} catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	@Transactional
	public int getUserDetailsCountBySessionId(String searchq, Long sessionPK) {
		int count = 0;
		
		try {
			count = sessionDetailsDao.getUserDetailsCountBySessionId(searchq, sessionPK);
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		
		return count;
	}
	@Transactional
	public AnalyseUserDTO analyseUserBySessionId(String sortField,int order, int start, int gridTableSize, String searchq,Long sessionPK) {
		List<UserDetailsDTO> list = null;
		AnalyseUserDTO dto = new AnalyseUserDTO();
		
		try {
			list = sessionDetailsDao.analyseUserBySessionId(sessionPK);
			
			Map<String, Object> map = analyseAgentService.getEventCount(list);
			Map<String, Object> deviceMap = analyseAgentService.deviceIdenticication(list);
			Map<String, Map<String, Integer>> userAnalyseMap = analyseUserService.analyseUser(list);
			
			dto.setEventCount(map);
			dto.setDeviceType(deviceMap);
			dto.setUserStatus(userAnalyseMap);
			dto.setUserDetailsList(list);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return dto;
	}
	@Transactional
	public List<SessionTimeOutDTO> getSessionIDListBySID(Long sid) {
		List<SessionTimeOutDTO> list = null;
		
		try {
			list = sessionDetailsDao.getSessionIDListBySID(sid);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	 public String getLocationBasedCurrentTime(String timeOffset){
		 String dateTime = null;
		 if(!timeOffset.equalsIgnoreCase("-1")) {
			 DateTime utc = new DateTime(DateTimeZone.UTC);
			 DateTimeZone tz = DateTimeZone.forOffsetMillis((Integer.parseInt(timeOffset)* 60000 * -1));
			 DateTime currentTime = utc.toDateTime(tz);
			 
			 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			 String str = currentTime.toString(fmt);
			 dateTime = str;
		 }
		 return dateTime;
	 }
	 public long getTimeDifference(String time1, String time2){
		 DateTime lstAccessTime = getDate(time1);
		 DateTime currentTime = getDate(time2);
			
		 long timeDiffer  = (currentTime.getMillis() - lstAccessTime.getMillis());
		 return timeDiffer;
	 }
	 public DateTime getDate(String date1){
		 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		 DateTime date = null;
		 date = fmt.parseDateTime(date1);
	     return date;
	 }
	 
	 public String getServerTime(){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(date);
		 return result;
	 }
}

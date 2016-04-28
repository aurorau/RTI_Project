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
import com.aurora.service.DeviceDetailsService;
import com.aurora.service.SessionDetailsService;
import com.aurora.util.AnalyseUserDTO;
import com.aurora.util.Constants;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.UserDetailsDTO;

@Service("sessionDetailsService")
public class SessionDetailsImpl implements SessionDetailsService{
	
	SessionDetailsDao sessionDetailsDao = null;
	AnalyseAgentService analyseAgentService = null;
	DeviceDetailsService deviceDetailsService = null;
	
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
	
	@Transactional
	public String heartBeat(HttpServletRequest request) {
		String res = Constants.FAIL;
		SessionDetails sessionDetails = null;
		String currentLocationBasedTime = null;
		String sessionId = null;
		try {
			sessionId = request.getParameter("sessionID");
			if(!sessionId.equalsIgnoreCase("-1")){
				sessionDetails = sessionDetailsDao.getSessionDetailsByCreationTimeById(1L, sessionId);
				//currentLocationBasedTime = getLocationBasedCurrentTime(request.getParameter("timeZoneOffset"));
				
				//long timeDiffer = getTimeDifference(sessionDetails.getLastAccessTime(),currentLocationBasedTime);
				//int timeDiffer1 = (int)(timeDiffer/60000);
				
				//if(timeDiffer1 < 2) {
					res = Constants.SUCCESS;
					sessionDetails.setHeartBeatTime(getServerTime());
					sessionDetailsDao.saveSessionDetails(sessionDetails);
				//} 
			} 
/*			else {
				sessionDetails = sessionDetailsDao.getSessionDetailsByCreationTimeById(1L, sessionId);
				sessionDetails.setStatus("INACTIVE");
				sessionDetailsDao.saveSessionDetails(sessionDetails);
			}*/

		} catch(Exception e){
			res = Constants.ERROR;
		}
		return res;
	}
	
	@Transactional
	public String saveSessionDetails(HttpServletRequest request) {
		String res = Constants.FAIL;
		String sessionId = null;
		String sessionCreationTime = null;
	    String currentLocationBasedTime = getLocationBasedCurrentTime(request.getParameter("timeZoneOffset"));
		sessionId = request.getParameter("sessionID");
		 
		SessionDetails sessionDetails = null;
		
		if(sessionId == null || sessionId.equalsIgnoreCase("")) {
			sessionId = request.getSession().getId();
			//sessionCreationTime = currentLocationBasedTime;
			sessionCreationTime = getServerTime();
		}
		try {
			sessionDetails = sessionDetailsDao.getSessionDetailsByCreationTimeById(1L, sessionId);	
		    if(sessionDetails == null) {
			   sessionDetails = new SessionDetails();
			   sessionDetails.setSessionId(sessionId);
			   sessionDetails.setSessionAccessCount(1L);
			   sessionDetails.setSessionCreatedTime(sessionCreationTime);
			   sessionDetails.setLastAccessTime(sessionCreationTime);
			   //sessionDetails.setStatus("INACTIVE");
		    } else {
			   sessionDetails = sessionDetailsDao.getById(sessionDetails.getSID());
			   sessionDetails.setSessionAccessCount(sessionDetails.getSessionAccessCount()+1);
			   sessionDetails.setLastAccessTime(getServerTime());
			   //sessionDetails.setStatus("INACTIVE");
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
	public List<CurrentUsersDTO> getCurrentUserCount() {
		List<CurrentUsersDTO> dtoList = null;
		
		try {
			dtoList = sessionDetailsDao.getCurrentUserCount();
		} catch(Exception e) {
			System.out.println(e);
		}
		return dtoList;
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
			String deviceType = analyseAgentService.deviceIdenticication(list);
			
			dto.setEventCount(map);
			dto.setDeviceType(deviceType);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return dto;
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

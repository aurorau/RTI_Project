package com.aurora.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.SessionDetails;
import com.aurora.service.AnalyseAgentService;
import com.aurora.service.SessionDetailsService;
import com.aurora.util.AnalyseUserDTO;
import com.aurora.util.Constants;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.SessionBrowserDetailsDTO;
import com.aurora.util.UserDetailsDTO;

@Service("sessionDetailsService")
public class SessionDetailsImpl implements SessionDetailsService{
	
	SessionDetailsDao sessionDetailsDao = null;
	AnalyseAgentService analyseAgentService = null;
	
	@Autowired
	public void setSessionDetailsDao(SessionDetailsDao sessionDetailsDao) {
		this.sessionDetailsDao = sessionDetailsDao;
	}
	
	@Autowired
	public void setAnalyseAgentService(AnalyseAgentService analyseAgentService) {
		this.analyseAgentService = analyseAgentService;
	}
	
	@Transactional
	public String saveSessionDetails(SessionDetails sessionDetails) {
		String res = Constants.FAIL;
		
		try {
			sessionDetailsDao.saveSessionDetails(sessionDetails);
			res = Constants.SUCCESS;
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
			
			Map<String, Integer> map = analyseAgentService.getEventCount(list);
			dto.setEventCount(map);
			
		} catch(Exception e) {
			System.out.println(e);
		}
		return dto;
	}
/*	@Transactional
	public int analyseUserCountBySessionId(String searchq, Long sessionPK) {
		int count = 0;
		
		try {
			count = sessionDetailsDao.analyseUserCountBySessionId(searchq, sessionPK);
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		
		return count;
	}*/

}

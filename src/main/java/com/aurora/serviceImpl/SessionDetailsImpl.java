package com.aurora.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.SessionDetails;
import com.aurora.service.SessionDetailsService;
import com.aurora.util.Constants;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.SessionBrowserDetailsDTO;
import com.aurora.util.UserDetailsDTO;

@Service("sessionDetailsService")
public class SessionDetailsImpl implements SessionDetailsService{
	
	SessionDetailsDao sessionDetailsDao = null;
	
	 @Autowired
	 public void setSessionDetailsDao(SessionDetailsDao sessionDetailsDao) {
		 this.sessionDetailsDao = sessionDetailsDao;
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
	public List<UserDetailsDTO> getUserDetailsBySessionId(Long sid) {
		
		List<UserDetailsDTO> list = null;
		
		try {
			list = sessionDetailsDao.getUserDetailsBySessionId(sid);
		} catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}

}

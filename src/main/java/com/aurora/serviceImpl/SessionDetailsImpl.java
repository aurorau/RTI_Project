package com.aurora.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.SessionDetailsDao;
import com.aurora.dao.TestDao;
import com.aurora.model.SessionDetails;
import com.aurora.service.SessionDetailsService;
import com.aurora.util.Constants;

@Service("sessionDetailsService")
public class SessionDetailsImpl implements SessionDetailsService{
	
	SessionDetailsDao sessionDetailsDao = null;
	
	 @Autowired
	 public void setSessionDetailsDao(SessionDetailsDao sessionDetailsDao) {
		 this.sessionDetailsDao = sessionDetailsDao;
	 }
	
	public String saveSessionDetails(SessionDetails sessionDetails) {
		String res = Constants.FAIL;
		
		try {
			sessionDetailsDao.saveSessionDetails(sessionDetails);
			res = Constants.SUCCESS;
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

}

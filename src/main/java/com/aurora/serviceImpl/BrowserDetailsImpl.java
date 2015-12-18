package com.aurora.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.BrowserDetailsDao;
import com.aurora.dao.SessionDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.service.BrowserDetailsService;
import com.aurora.util.Constants;

@Service("browserDetailsService")
public class BrowserDetailsImpl implements BrowserDetailsService {

	private BrowserDetailsDao browserDetailsDao = null;
	
	 @Autowired
	 public void setBrowserDetailsDao(BrowserDetailsDao browserDetailsDao) {
		 this.browserDetailsDao = browserDetailsDao;
	 }
	
	public String saveBrowserDetails(BrowserDetails browserDetails) {
		String res = Constants.FAIL;
		
		try {
			browserDetailsDao.saveBrowserDetails(browserDetails);
			res = Constants.SUCCESS;
		} catch(Exception e) {
			System.out.println("Error :"+e);
			res = Constants.ERROR;
		}
		return res;
	}

}

package com.aurora.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurora.dao.BrowserDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.SessionDetails;
import com.aurora.service.BrowserDetailsService;
import com.aurora.util.BrowserAndDeviceDetailsDTO;
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

	public List<BrowserAndDeviceDetailsDTO> getBrowserDetails() {
		List<BrowserAndDeviceDetailsDTO>  list =null;
		try {
			list = browserDetailsDao.getBrowserDetails();
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}

	@Override
	public List<BrowserDetails> getBrowserDetailsByUserAgentId(long userAgentId) {
		List<BrowserDetails>  list =null;
		try {
			//list = browserDetailsDao.getBrowserDetailsByUserAgentId();
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}

	@Override
	public BrowserDetails getBrowserById(Long bid) {
		BrowserDetails browser = null;
		
		try {
			browser = browserDetailsDao.getBrowserById(bid);
		} catch(Exception e) {
		}
		return browser;
	}

}

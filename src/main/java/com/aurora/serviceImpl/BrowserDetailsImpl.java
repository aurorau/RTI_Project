package com.aurora.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.BrowserType;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.BrowserDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.DeviceDetails;
import com.aurora.model.EventDetails;
import com.aurora.model.SessionDetails;
import com.aurora.service.BrowserDetailsService;
import com.aurora.service.ProxyDetailsService;
import com.aurora.util.BrowserAndDeviceDetailsDTO;
import com.aurora.util.Constants;

@Service("browserDetailsService")
public class BrowserDetailsImpl implements BrowserDetailsService {

	private BrowserDetailsDao browserDetailsDao = null;
	private ProxyDetailsService proxyDetailsService = null;
	
	@Autowired
	public void setBrowserDetailsDao(BrowserDetailsDao browserDetailsDao) {
		this.browserDetailsDao = browserDetailsDao;
	}
	 
	@Autowired
	public void setProxyDetailsService(ProxyDetailsService proxyDetailsService) {
		this.proxyDetailsService = proxyDetailsService;
	}
	
	@Transactional
	public String saveBrowserDetails(HttpServletRequest request, SessionDetails sessionDetails, DeviceDetails deviceDetails, EventDetails eventDetails) {
		String res = Constants.FAIL;
		
		try {
			String refererURL = request.getHeader("referer");
			UserAgent userAgent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	        long getId = userAgent1.getId();
	        Browser browser = userAgent1.getBrowser();
	        String browserName = browser.getName();
	        BrowserType browserType = browser.getBrowserType();
	        Version browserVersion = userAgent1.getBrowserVersion();
			
			BrowserDetails browserDetails =  new BrowserDetails();
			
			browserDetails.setBrowserName(browserName);
			browserDetails.setBrowserType(browserType.toString());
			browserDetails.setBrowserVersion(browserVersion.toString());
			browserDetails.setUserAgetntId(getId);
			browserDetails.setRefererURL(refererURL);
			browserDetails.setDeviceDetails(deviceDetails);
			browserDetails.setSessionDetails(sessionDetails);
			browserDetails.setEventDetails(eventDetails);
		
			browserDetailsDao.saveBrowserDetails(browserDetails);
			String status = proxyDetailsService.saveProxyDetailsService(request, browserDetails);
			
			if(status.equalsIgnoreCase(Constants.SUCCESS)){
				res = Constants.SUCCESS;
			}
		} catch(Exception e) {
			System.out.println("Error :"+e);
			res = Constants.ERROR;
		}
		return res;
	}
	
	@Transactional
	public List<BrowserAndDeviceDetailsDTO> getBrowserDetails() {
		List<BrowserAndDeviceDetailsDTO>  list =null;
		try {
			list = browserDetailsDao.getBrowserDetails();
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}
	
	@Transactional
	public List<BrowserDetails> getBrowserDetailsByUserAgentId(long userAgentId) {
		List<BrowserDetails>  list =null;
		try {
			//list = browserDetailsDao.getBrowserDetailsByUserAgentId();
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}
	
	@Transactional
	public BrowserDetails getBrowserById(Long bid) {
		BrowserDetails browser = null;
		
		try {
			browser = browserDetailsDao.getBrowserById(bid);
		} catch(Exception e) {
		}
		return browser;
	}

}

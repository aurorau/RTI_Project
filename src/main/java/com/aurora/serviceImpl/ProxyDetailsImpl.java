package com.aurora.serviceImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.controller.HomePageController;
import com.aurora.dao.ProxyDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.ProxyDetails;
import com.aurora.service.ProxyDetailsService;
import com.aurora.util.Constants;
import com.aurora.util.GeoLocation;
import com.aurora.util.ProxyDetailsDTO;
import com.maxmind.geoip.LookupService;

@Service("proxyDetailsService")
public class ProxyDetailsImpl implements ProxyDetailsService {

	private ProxyDetailsDao proxyDetailsDao = null;
	private static LookupService lookUp;
	
	@Autowired
	public void setProxyDetailsDao(ProxyDetailsDao proxyDetailsDao) {
		this.proxyDetailsDao = proxyDetailsDao;
	}
	
	@Transactional
	public String saveProxyDetailsService(HttpServletRequest request,BrowserDetails browserDetails) {
		String res = Constants.FAIL;
		List<String> proxyList = null;
		try {
			 proxyList = new ArrayList<String>();
			 String ipAddress = request.getHeader("X-FORWARDED-FOR");
					 //"112.135.1.252,199.189.80.13,177.207.196.50";
					 //request.getHeader("X-FORWARDED-FOR");
					
			 
			 if(ipAddress != null) {
	        	String[] proxyList1 = ipAddress.split(",");
	        	for(String prxy : proxyList1) {
	        		String ip = prxy;
	        		proxyList.add(ip);
	        	}
			 } else {
				 ipAddress = request.getRemoteAddr();  
			 }
			 
			 for(String proxyIP : proxyList) {
				 ProxyDetails proxyDetails=  new ProxyDetails();
				 GeoLocation geoLocation = getLocation(proxyIP);
				 
				 proxyDetails.setBrowserDetails(browserDetails);
				 proxyDetails.setCity(geoLocation.getCity());
				 proxyDetails.setCountryCode(geoLocation.getCountryCode());
				 proxyDetails.setCountryName(geoLocation.getCountryName());
				 proxyDetails.setIpAddress(proxyIP);
				 proxyDetails.setLatitude(geoLocation.getLatitude());
				 proxyDetails.setLongitude(geoLocation.getLongitude());
				 proxyDetails.setPostalCode(geoLocation.getPostalCode());
				 proxyDetails.setRegion(geoLocation.getRegion());
				 
				 proxyDetailsDao.saveProxyDetailsService(proxyDetails);
			 }
			res = Constants.SUCCESS;
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

	@Transactional
	public List<ProxyDetailsDTO> getProxyDetails(Long bid) {
		List<ProxyDetailsDTO>  list =null;
		try {
			list = proxyDetailsDao.getProxyDetails(bid);
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}
    static {
        try {
            lookUp = new LookupService(
            		HomePageController.class.getResource("/GeoLiteCity.dat").getFile(),
                    LookupService.GEOIP_MEMORY_CACHE);

        } catch (IOException e) {
           // System.out.println("Could not load geo ip database: " + e.getMessage());
        }
    }
	 public static GeoLocation getLocation(long ipAddress) {
	        return GeoLocation.map(lookUp.getLocation(ipAddress));
	 }
	 
	 public static GeoLocation getLocation(String ipAddress) {
	        return GeoLocation.map(lookUp.getLocation(ipAddress));
	 }

	 public static GeoLocation getLocation(InetAddress ipAddress){
	        return GeoLocation.map(lookUp.getLocation(ipAddress));
	 }
}

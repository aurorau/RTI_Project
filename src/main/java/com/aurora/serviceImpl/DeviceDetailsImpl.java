package com.aurora.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import nl.bitwalker.useragentutils.Manufacturer;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.DeviceDetailsDao;
import com.aurora.model.DeviceDetails;
import com.aurora.model.SessionDetails;
import com.aurora.service.DeviceDetailsService;
import com.aurora.service.EventDetailsService;
import com.aurora.util.Constants;

@Service("deviceDetailsService")
public class DeviceDetailsImpl implements DeviceDetailsService {

	private DeviceDetailsDao deviceDetailsDao = null;
	EventDetailsService eventDetailsService = null;
	
	@Autowired
	public void setDeviceDetailsDao(DeviceDetailsDao deviceDetailsDao) {
		this.deviceDetailsDao = deviceDetailsDao;
	}
	
	@Autowired
	public void setEventDetailsService(EventDetailsService eventDetailsService) {
		this.eventDetailsService = eventDetailsService;
	}
	
	@Transactional
	public String saveDeviceDetails(HttpServletRequest request, SessionDetails sessionDetails) {
		String res = Constants.FAIL;
		
		try {
			UserAgent userAgent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			OperatingSystem agent = userAgent1.getOperatingSystem(); 
	        String deviceName = agent.getDeviceType().getName();
	        String osName = agent.getName();
	        Manufacturer osManufacture = agent.getManufacturer();
	        String orientation = request.getParameter("orientation");
	        
			DeviceDetails deviceDetails = new DeviceDetails();
			deviceDetails.setDeviceName(deviceName);
			deviceDetails.setOrientation(orientation);
			deviceDetails.setOsManufacture(osManufacture.toString());
			deviceDetails.setOsName(osName);
			
			deviceDetailsDao.saveDeviceDetails(deviceDetails);
			String status = eventDetailsService.saveEventDetails(request, sessionDetails, deviceDetails);
			
			if(status.equalsIgnoreCase(Constants.SUCCESS)){
				res = Constants.SUCCESS;
			}
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

	@Transactional
	public List<DeviceDetails> getDeviceCount() {
		List<DeviceDetails>  list =null;
		try {
			list = deviceDetailsDao.getDeviceCount();
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}

	@Transactional
	public DeviceDetails getDeviceDetailsById(Long did) {
		DeviceDetails device = null;
		
		try {
			device = deviceDetailsDao.getDeviceDetailsById(did);
		} catch(Exception e) {
		}
		return device;
	}

}

package com.aurora.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurora.dao.DeviceDetailsDao;
import com.aurora.model.DeviceDetails;
import com.aurora.model.SessionDetails;
import com.aurora.service.DeviceDetailsService;
import com.aurora.util.Constants;

@Service("deviceDetailsService")
public class DeviceDetailsImpl implements DeviceDetailsService {

	private DeviceDetailsDao deviceDetailsDao = null;
	
	@Autowired
	public void setDeviceDetailsDao(DeviceDetailsDao deviceDetailsDao) {
		this.deviceDetailsDao = deviceDetailsDao;
	}
	
	public String saveDeviceDetails(DeviceDetails deviceDetails) {
		String res = Constants.FAIL;
		
		try {
			deviceDetailsDao.saveDeviceDetails(deviceDetails);
			res = Constants.SUCCESS;
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

	public List<DeviceDetails> getDeviceCount() {
		List<DeviceDetails>  list =null;
		try {
			list = deviceDetailsDao.getDeviceCount();
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}

	@Override
	public DeviceDetails getDeviceDetailsById(Long did) {
		DeviceDetails device = null;
		
		try {
			device = deviceDetailsDao.getDeviceDetailsById(did);
		} catch(Exception e) {
		}
		return device;
	}

}

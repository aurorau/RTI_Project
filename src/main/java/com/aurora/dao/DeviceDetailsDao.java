package com.aurora.dao;

import java.util.List;

import com.aurora.model.DeviceDetails;

public interface DeviceDetailsDao {
	public void saveDeviceDetails(DeviceDetails deviceDetails) throws Exception;
	public List<DeviceDetails> getDeviceCount()throws Exception;
	public DeviceDetails getDeviceDetailsById(Long did)throws Exception;
}

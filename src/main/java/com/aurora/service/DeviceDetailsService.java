package com.aurora.service;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.aurora.model.DeviceDetails;
import com.aurora.model.SessionDetails;


public interface DeviceDetailsService {
	public String saveDeviceDetails(DeviceDetails deviceDetails);
	/**
	 *  Get getDeviceDetailsById
	 *  
	 *  @param Long did
	 * @return SessionDetails
	 */
	@GET
	@Path("getById/{did}")
	public DeviceDetails getDeviceDetailsById (@PathParam("did") Long did);
	
	/**
	 *  Get getDeviceCount
	 *  
	 * @return DeviceDetails
	 */
	@GET
	@Path("/getDeviceCount")
	public List<DeviceDetails> getDeviceCount();
}

package com.aurora.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.aurora.model.BrowserDetails;
import com.aurora.model.DeviceDetails;
import com.aurora.model.SessionDetails;
import com.aurora.util.BrowserAndDeviceDetailsDTO;

public interface BrowserDetailsService {
	public String saveBrowserDetails(BrowserDetails browserDetails);
	/**
	 *  Get getBrowserById
	 *  
	 *  @param Long bid
	 * @return BrowserDetails
	 */
	@GET
	@Path("getBrowserById/{bid}")
	public BrowserDetails getBrowserById (@PathParam("bid") Long bid);
	/**
	 *  Get getBrowserDetails
	 *  
	 * @return getBrowserDetails
	 */
	@GET
	@Path("getBrowserDetails")
	public List<BrowserAndDeviceDetailsDTO> getBrowserDetails();
	
	/**
	 *  Get getBrowserDetailsByUserAgentId
	 *  
	 *  @param Long userAgentId
	 * @return BrowserDetails
	 */
	@GET
	@Path("getBrowserDetailsByUserAgentId/{userAgentId}")
	public List<BrowserDetails> getBrowserDetailsByUserAgentId(@PathParam("userAgentId") long userAgentId);
}

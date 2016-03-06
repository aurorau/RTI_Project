package com.aurora.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.aurora.model.BrowserDetails;
import com.aurora.model.ProxyDetails;
import com.aurora.util.ProxyDetailsDTO;

public interface ProxyDetailsService {
	public String saveProxyDetailsService(HttpServletRequest request,BrowserDetails browserDetails);
	/**
	 *  Get getProxyDetails
	 *  
	 *  @param Long sid
	 * @return List<ProxyDetails>
	 */
	@GET
	@Path("getProxyDetails/{bid}")
	public List<ProxyDetailsDTO> getProxyDetails(@PathParam("bid") Long bid); 
}

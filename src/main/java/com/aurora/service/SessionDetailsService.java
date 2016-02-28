package com.aurora.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.aurora.model.SessionDetails;
import com.aurora.util.AnalyseUserDTO;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.SessionBrowserDetailsDTO;
import com.aurora.util.UserDetailsDTO;

public interface SessionDetailsService {
	public String saveSessionDetails(SessionDetails sessionDetails);
	/**
	 *  Get getById
	 *  
	 *  @param Long sid
	 * @return SessionDetails
	 */
	@GET
	@Path("getById/{sid}")
	public SessionDetails getById (@PathParam("sid") Long sid);
	
	/**
	 *  Get getCurrentUserCount
	 *  
	 * 
	 * @return CurrentUsersDTO
	 */
	@GET
	@Path("getCurrentUserCount")
	public List<CurrentUsersDTO> getCurrentUserCount(); 
	/**
	 *  Get getUserDetailsBySessionId
	 *  
	 *  @param Long sid
	 * @return List<UserDetailsDTO>
	 */
	@GET
	@Path("getUserDetailsBySessionId/{sid}")
	public List<UserDetailsDTO> getUserDetailsBySessionId(String sortField, int order, int start, int gridTableSize, String searchq, Long sid);
	
	/**
	 *  Get getSessionDetailsByCreationTimeById
	 *  
	 *  @param Long creationTime
	 *  @param Long sessionId
	 * @return SessionDetails
	 */
	@GET
	@Path("getSessionDetailsByCreationTimeById/{creationTime}/{sessionId}")
	public SessionDetails getSessionDetailsByCreationTimeById(@PathParam("creationTime") Long creationTime, @PathParam("sessionId") String sessionId);
	public int getUserDetailsCountBySessionId(String searchq, Long sessionPK);
	public AnalyseUserDTO analyseUserBySessionId(String sortField,int order, int start, int gridTableSize, String searchq,Long sessionPK);
	//public int analyseUserCountBySessionId(String searchq, Long sessionPK);
}

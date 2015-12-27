package com.aurora.dao;

import java.util.List;

import javax.ws.rs.PathParam;

import com.aurora.model.SessionDetails;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.SessionBrowserDetailsDTO;

public interface SessionDetailsDao {
	public void saveSessionDetails(SessionDetails sessionDetails) throws Exception;
	public SessionDetails getById(Long sid) throws Exception;
	public List<CurrentUsersDTO> getCurrentUserCount()throws Exception;
	public void getUserDetailsBySessionId(Long sid)throws Exception;
	public SessionDetails getSessionDetailsByCreationTimeById(Long creationTime,String sessionId)throws Exception;
}

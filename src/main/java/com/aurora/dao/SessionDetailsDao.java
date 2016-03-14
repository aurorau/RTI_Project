package com.aurora.dao;

import java.util.List;

import javax.ws.rs.PathParam;

import com.aurora.model.SessionDetails;
import com.aurora.util.AnalyseUserDTO;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.SessionBrowserDetailsDTO;
import com.aurora.util.SessionTimeOutDTO;
import com.aurora.util.UserDetailsDTO;

public interface SessionDetailsDao {
	public void saveSessionDetails(SessionDetails sessionDetails) throws Exception;
	public SessionDetails getById(Long sid) throws Exception;
	public List<CurrentUsersDTO> getCurrentUserCount()throws Exception;
	public List<UserDetailsDTO> getUserDetailsBySessionId(String sortField, int order, int start, int gridTableSize,String searchq, Long sid)throws Exception;
	public SessionDetails getSessionDetailsByCreationTimeById(Long creationTime,String sessionId)throws Exception;
	public int getUserDetailsCountBySessionId(String searchq, Long sessionPK);
	public List<UserDetailsDTO> analyseUserBySessionId(Long sessionPK);
	//public int analyseUserCountBySessionId(String searchq, Long sessionPK);
	public List<SessionTimeOutDTO> getSessionIDListBySID(Long sid);
}

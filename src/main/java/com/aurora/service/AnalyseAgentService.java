package com.aurora.service;

import java.util.List;
import java.util.Map;

import com.aurora.util.UserDetailsDTO;

public interface AnalyseAgentService {

	public Map<String, Object> getEventCount(List<UserDetailsDTO> dto);
	public Map<String, Object> deviceIdenticication(List<UserDetailsDTO> dto);
}

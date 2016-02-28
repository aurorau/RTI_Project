package com.aurora.service;

import java.util.List;
import java.util.Map;
import com.aurora.util.UserDetailsDTO;

public interface AnalyseAgentService {

	public Map<String, Integer> getEventCount(List<UserDetailsDTO> dto);
}

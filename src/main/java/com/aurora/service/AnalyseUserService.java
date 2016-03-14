package com.aurora.service;

import java.util.List;
import java.util.Map;

import com.aurora.util.UserDetailsDTO;

public interface AnalyseUserService {
	public Map<String, Map<String, Integer>> analyseUser(List<UserDetailsDTO> dto);
}

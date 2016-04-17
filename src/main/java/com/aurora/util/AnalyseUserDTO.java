package com.aurora.util;

import java.util.List;
import java.util.Map;

public class AnalyseUserDTO {
	Map<String, Object> eventCount;
	String deviceType;
	Map<String, Map<String, Integer>> userStatus;
	List<UserDetailsDTO> userDetailsList;

	public List<UserDetailsDTO> getUserDetailsList() {
		return userDetailsList;
	}

	public void setUserDetailsList(List<UserDetailsDTO> userDetailsList) {
		this.userDetailsList = userDetailsList;
	}

	public Map<String, Object> getEventCount() {
		return eventCount;
	}

	public void setEventCount(Map<String, Object> eventCount) {
		this.eventCount = eventCount;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Map<String, Map<String, Integer>> getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Map<String, Map<String, Integer>> userStatus) {
		this.userStatus = userStatus;
	}
	
}

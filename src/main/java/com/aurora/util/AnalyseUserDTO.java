package com.aurora.util;

import java.util.Map;

public class AnalyseUserDTO {
	Map<String, Object> eventCount;
	String deviceType;

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
	
}

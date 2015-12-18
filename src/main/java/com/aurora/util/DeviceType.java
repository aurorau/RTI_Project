package com.aurora.util;

public enum DeviceType {
	
	COMPUTER("COM"),
	TABLET("TAB"),
	MOBILE("MOB");

	private String deviceCode;

	private DeviceType(String s) {
		deviceCode = s;
	}

	public String getEventTypes() {
		return deviceCode;
	}
}

package com.aurora.util;

public enum EventTypes {
	
	LEFT_CLICK("LC"),
	RIGHT_CLICK("RC"),
	DB_CLICK("DC"),
	KEY_PRESS("KP"),
	SCROLL_EVENT("SE"),
	TOUCH_EVENT("TE"),
	DESKTOP_ZOOM_EVENT("DZE"),
	TOUCH_ZOOM_EVENT("TZE");

	private String eventCode;

	private EventTypes(String s) {
		eventCode = s;
	}

	public String getEventTypes() {
		return eventCode;
	}
	
}

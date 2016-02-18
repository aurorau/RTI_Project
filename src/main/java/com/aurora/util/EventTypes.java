package com.aurora.util;

public enum EventTypes {
	
	LEFT_CLICK("LC"),
	RIGHT_CLICK("RC"),
	DB_CLICK("DC"),
	KEY_PRESS("KP"),
	SCROLL_EVENT("SE"),
	TOUCH_EVENT("TE"),
	DESKTOP_ZOOM_EVENT("DZE"),
	DESKTOP_SCROLL_EVENT("DE_SE"),
	TOUCH_ZOOM_EVENT("TZE"),
	TOUCH_SCROLL_EVENT("TE_SE"),
	TOUCH_MOVE_EVENT("TM"),
	TOUCH_ZOOM_EVENT_SCROLL_EVENT("TZE_SE");

	private String eventCode;

	private EventTypes(String s) {
		eventCode = s;
	}

	public String getEventTypes() {
		return eventCode;
	}
	
}

package com.aurora.util;

public enum EventTypes {
	
	LEFT_CLICK("LC"),
	RIGHT_CLICK("RC"),
	DB_CLICK("DC"),
	KEY_PRESS("KP"),
	SCROLL_EVENT("SE"),
	TOUCH_EVENT("TE"),
	TOUCH_START("TS"),
	DESKTOP_ZOOM("DZE"),
	DESKTOP_SCROLL("DSE"),
	TOUCH_ZOOM("TZE"),
	TOUCH_SCROLL("TE_SE"),
	TOUCH_MOVE("TM"),
	REFRESH_EVENT("RF"),
	SWAP_TOUCH_ZOOM("STZE"),
	TOUCH_SCROLL_EVENT("TSE"),
	TOUCH_ZOOM_EVENT_SCROLL("TZE_SE");

	private String eventCode;

	private EventTypes(String s) {
		eventCode = s;
	}

	public String getEventTypes() {
		return eventCode;
	}
	
}

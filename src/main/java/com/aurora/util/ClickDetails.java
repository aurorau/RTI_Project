package com.aurora.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickDetails {
	private String xForwardedFor;
	private String deviceName;
	private String os;
	private String browser;
	private long leftClickCount;
	private long rightClickCount;
	private long dbClickCount;
	private long keyPressCount;
	private long scrollCount;
	private long touchCount;
	private String orientation;
	private String eventType;
	private long zoomOutCount;
	private long zoomInCount;
	private long zoomCount;
	private long screenWidth;
	private long screenHeight;
	private long touchX;
	private long touchY;
	private long clickX;
	private long clickY;
	private String languages;
	private String location;
	private Long creationTime;
	private String sessionId;
	private Long lastAccessTime;
	private String refererURI;
	private String dataSubmitTime;
	private List<String> proxyList;
	
	public String getxForwardedFor() {
		return xForwardedFor;
	}
	public void setxForwardedFor(String xForwardedFor) {
		this.xForwardedFor = xForwardedFor;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public long getLeftClickCount() {
		return leftClickCount;
	}
	public void setLeftClickCount(long leftClickCount) {
		this.leftClickCount = leftClickCount;
	}
	public long getRightClickCount() {
		return rightClickCount;
	}
	public void setRightClickCount(long rightClickCount) {
		this.rightClickCount = rightClickCount;
	}
	public long getDbClickCount() {
		return dbClickCount;
	}
	public void setDbClickCount(long dbClickCount) {
		this.dbClickCount = dbClickCount;
	}
	public long getKeyPressCount() {
		return keyPressCount;
	}
	public void setKeyPressCount(long keyPressCount) {
		this.keyPressCount = keyPressCount;
	}
	public long getScrollCount() {
		return scrollCount;
	}
	public void setScrollCount(long scrollCount) {
		this.scrollCount = scrollCount;
	}
	public long getTouchCount() {
		return touchCount;
	}
	public void setTouchCount(long touchCount) {
		this.touchCount = touchCount;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public long getZoomOutCount() {
		return zoomOutCount;
	}
	public void setZoomOutCount(long zoomOutCount) {
		this.zoomOutCount = zoomOutCount;
	}
	public long getZoomInCount() {
		return zoomInCount;
	}
	public void setZoomInCount(long zoomInCount) {
		this.zoomInCount = zoomInCount;
	}
	public long getZoomCount() {
		return zoomCount;
	}
	public void setZoomCount(long zoomCount) {
		this.zoomCount = zoomCount;
	}
	public long getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(long screenWidth) {
		this.screenWidth = screenWidth;
	}
	public long getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(long screenHeight) {
		this.screenHeight = screenHeight;
	}
	public long getTouchX() {
		return touchX;
	}
	public void setTouchX(long touchX) {
		this.touchX = touchX;
	}
	public long getTouchY() {
		return touchY;
	}
	public void setTouchY(long touchY) {
		this.touchY = touchY;
	}
	public long getClickX() {
		return clickX;
	}
	public void setClickX(long clickX) {
		this.clickX = clickX;
	}
	public long getClickY() {
		return clickY;
	}
	public void setClickY(long clickY) {
		this.clickY = clickY;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Long getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(Long lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public String getRefererURI() {
		return refererURI;
	}
	public void setRefererURI(String refererURI) {
		this.refererURI = refererURI;
	}
	public String getDataSubmitTime() {
		return dataSubmitTime;
	}
	public void setDataSubmitTime(String dataSubmitTime) {
		this.dataSubmitTime = dataSubmitTime;
	}
	public List<String> getProxyList() {
		return proxyList;
	}
	public void setProxyList(List<String> proxyList) {
		this.proxyList = proxyList;
	}
}

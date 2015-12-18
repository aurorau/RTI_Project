package com.aurora.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClickDetails {
	private String xForwardedFor;
	private String deviceName;
	private int userAgentId;
	private String os;
	private String browser;
	private String browserName;
	private String browserType;
	private String browserVersion;
	private String osName;
	private String osManufacture;
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
	private String screenWidth;
	private String screenHeight;
	private String touchX;
	private String touchY;
	private String clickX;
	private String clickY;
	private String languages;
	private String location;
	private Long creationTime;
	private String sessionId;
	private Long lastAccessTime;
	private String refererURI;
	private String dataSubmitTime;
	private List<String> proxyList;
	private String latitude;
	private String longitude;
	
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
	public String getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}
	public String getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}
	public String getTouchX() {
		return touchX;
	}
	public void setTouchX(String touchX) {
		this.touchX = touchX;
	}
	public String getTouchY() {
		return touchY;
	}
	public void setTouchY(String touchY) {
		this.touchY = touchY;
	}
	public String getClickX() {
		return clickX;
	}
	public void setClickX(String clickX) {
		this.clickX = clickX;
	}
	public String getClickY() {
		return clickY;
	}
	public void setClickY(String clickY) {
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getUserAgentId() {
		return userAgentId;
	}
	public void setUserAgentId(int userAgentId) {
		this.userAgentId = userAgentId;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getOsManufacture() {
		return osManufacture;
	}
	public void setOsManufacture(String osManufacture) {
		this.osManufacture = osManufacture;
	}
}

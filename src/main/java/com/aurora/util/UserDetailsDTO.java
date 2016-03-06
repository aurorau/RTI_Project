package com.aurora.util;

import java.util.List;

public class UserDetailsDTO {
	private Long sid;
	private Long bid;
	private String browserName;
	private Long userAgentId;
	private String browserVersion;
	private Long eid;
	private String eventTriggeredTime;
	private String eventName;
	private String coordinateX;
	private String coordinateY;
	private Long did;
	private String deviceName;
	private String screenHeight;
	private String screenWidth;
	private String orientation;
	private List<Long> pid;
	private String osName;
	private String viewportHeight;
	private String viewportWidth;
	private String numOfTaps;
	private String tagName;
	private String scrollTop;
	private String timeZone;
	private String zoneDateTime;
	private String imageName;
	private String lastAccessTime;
	private String firstAccessTime;

	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public Long getUserAgentId() {
		return userAgentId;
	}
	public void setUserAgentId(Long userAgentId) {
		this.userAgentId = userAgentId;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getEventTriggeredTime() {
		return eventTriggeredTime;
	}
	public void setEventTriggeredTime(String eventTriggeredTime) {
		this.eventTriggeredTime = eventTriggeredTime;
	}
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public List<Long> getPid() {
		return pid;
	}
	public void setPid(List<Long> pid) {
		this.pid = pid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	public String getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}
	public String getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getViewportHeight() {
		return viewportHeight;
	}
	public void setViewportHeight(String viewportHeight) {
		this.viewportHeight = viewportHeight;
	}
	public String getViewportWidth() {
		return viewportWidth;
	}
	public void setViewportWidth(String viewportWidth) {
		this.viewportWidth = viewportWidth;
	}
	public String getNumOfTaps() {
		return numOfTaps;
	}
	public void setNumOfTaps(String numOfTaps) {
		this.numOfTaps = numOfTaps;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getScrollTop() {
		return scrollTop;
	}
	public void setScrollTop(String scrollTop) {
		this.scrollTop = scrollTop;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getZoneDateTime() {
		return zoneDateTime;
	}
	public void setZoneDateTime(String zoneDateTime) {
		this.zoneDateTime = zoneDateTime;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	public String getFirstAccessTime() {
		return firstAccessTime;
	}
	public void setFirstAccessTime(String firstAccessTime) {
		this.firstAccessTime = firstAccessTime;
	}
	
}

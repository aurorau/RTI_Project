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
	private List<Long> pid;
	
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
	
}

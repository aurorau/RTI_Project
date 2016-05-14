package com.aurora.util;

public class UserCountDTO {
	private Long sid;
	private int countId;
	private String deviceType;
	
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public int getCountId() {
		return countId;
	}
	public void setCountId(int countId) {
		this.countId = countId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
}

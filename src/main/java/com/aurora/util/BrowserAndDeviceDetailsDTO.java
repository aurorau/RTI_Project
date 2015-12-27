package com.aurora.util;

public class BrowserAndDeviceDetailsDTO {
	
	private Long bid;
	private Long userAgetntId;
	private Long did;
	private String deviceName;
	
	public Long getBid() {
		return bid;
	}
	public void setBid(Long bid) {
		this.bid = bid;
	}
	public Long getUserAgetntId() {
		return userAgetntId;
	}
	public void setUserAgetntId(Long userAgetntId) {
		this.userAgetntId = userAgetntId;
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
}

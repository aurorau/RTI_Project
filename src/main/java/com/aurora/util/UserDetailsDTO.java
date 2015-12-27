package com.aurora.util;

public class UserDetailsDTO {
	private Long sid;
	private Long bid;
	private Long eid;
	//private Long pid;
	private Long did;
	
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
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
/*	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}*/
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	
	
}

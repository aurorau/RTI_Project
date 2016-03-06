package com.aurora.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="session_details")
public class SessionDetails implements Serializable{

	private Long SID;
	private String sessionId;
	private String sessionCreatedTime;
	private Long sessionAccessCount;
	private String lastAccessTime;
	private String status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SID")
	public Long getSID() {
		return SID;
	}
	public void setSID(Long sID) {
		SID = sID;
	}
	
	@Column(name="session_id", nullable=true, length=100)
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Column(name="session_created_time", nullable=true, length=100)
	public String getSessionCreatedTime() {
		return sessionCreatedTime;
	}
	public void setSessionCreatedTime(String sessionCreatedTime) {
		this.sessionCreatedTime = sessionCreatedTime;
	}
	
	@Column(name="session_accessed_count", nullable=true, length=100)
	public Long getSessionAccessCount() {
		return sessionAccessCount;
	}
	public void setSessionAccessCount(Long sessionAccessCount) {
		this.sessionAccessCount = sessionAccessCount;
	}
	
	@Column(name="last_access_time", nullable=true, length=100)
	public String getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(String lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}
	
	@Column(name="status", nullable=true, length=20)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

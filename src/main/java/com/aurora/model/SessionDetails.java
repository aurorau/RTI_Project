package com.aurora.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="session_details")
public class SessionDetails implements Serializable{

	private Long SID;
	private String sessionId;
	private Long sessionCreatedTime;
	private Long sessionLastAccessedTime;
	//private BrowserDetails browserDetails;
	private Long sessionAccessCount;
	private String lastAccessTime;
	
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
	public Long getSessionCreatedTime() {
		return sessionCreatedTime;
	}
	public void setSessionCreatedTime(Long sessionCreatedTime) {
		this.sessionCreatedTime = sessionCreatedTime;
	}
	
	@Column(name="session_last_accessed_time", nullable=true, length=100)
	public Long getSessionLastAccessedTime() {
		return sessionLastAccessedTime;
	}
	public void setSessionLastAccessedTime(Long sessionLastAccessedTime) {
		this.sessionLastAccessedTime = sessionLastAccessedTime;
	}
	
/*    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="FBID", nullable=false)
    @JsonIgnore
	public BrowserDetails getBrowserDetails() {
		return browserDetails;
	}
	public void setBrowserDetails(BrowserDetails browserDetails) {
		this.browserDetails = browserDetails;
	}*/
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SID == null) ? 0 : SID.hashCode());
		result = prime * result + ((lastAccessTime == null) ? 0 : lastAccessTime.hashCode());
		result = prime * result + ((sessionAccessCount == null) ? 0 : sessionAccessCount.hashCode());
		result = prime * result + ((sessionCreatedTime == null) ? 0 : sessionCreatedTime.hashCode());
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result + ((sessionLastAccessedTime == null) ? 0 : sessionLastAccessedTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionDetails other = (SessionDetails) obj;
		if (SID == null) {
			if (other.SID != null)
				return false;
		} else if (!SID.equals(other.SID))
			return false;
		if (lastAccessTime == null) {
			if (other.lastAccessTime != null)
				return false;
		} else if (!lastAccessTime.equals(other.lastAccessTime))
			return false;
		if (sessionAccessCount == null) {
			if (other.sessionAccessCount != null)
				return false;
		} else if (!sessionAccessCount.equals(other.sessionAccessCount))
			return false;
		if (sessionCreatedTime == null) {
			if (other.sessionCreatedTime != null)
				return false;
		} else if (!sessionCreatedTime.equals(other.sessionCreatedTime))
			return false;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (sessionLastAccessedTime == null) {
			if (other.sessionLastAccessedTime != null)
				return false;
		} else if (!sessionLastAccessedTime.equals(other.sessionLastAccessedTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SessionDetails [SID=" + SID + ", sessionId=" + sessionId + ", sessionCreatedTime=" + sessionCreatedTime
				+ ", sessionLastAccessedTime=" + sessionLastAccessedTime + ", sessionAccessCount=" + sessionAccessCount
				+ ", lastAccessTime=" + lastAccessTime + "]";
	}
	
}

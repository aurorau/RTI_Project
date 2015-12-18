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

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="session_details")
public class SessionDetails implements Serializable{

	private Long SID;
	private String sessionId;
	private Long sessionCreatedTime;
	private Long sessionLastAccessedTime;
	private BrowserDetails browserDetails;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SID")
	public Long getSID() {
		return SID;
	}
	public void setSID(Long sID) {
		SID = sID;
	}
	
	@Column(name="session_id", nullable=false, length=100)
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Column(name="session_created_time", nullable=false, length=100)
	public Long getSessionCreatedTime() {
		return sessionCreatedTime;
	}
	public void setSessionCreatedTime(Long sessionCreatedTime) {
		this.sessionCreatedTime = sessionCreatedTime;
	}
	
	@Column(name="session_last_accessed_time", nullable=false, length=100)
	public Long getSessionLastAccessedTime() {
		return sessionLastAccessedTime;
	}
	public void setSessionLastAccessedTime(Long sessionLastAccessedTime) {
		this.sessionLastAccessedTime = sessionLastAccessedTime;
	}
	
    @OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="FBID", nullable=false)
    @JsonIgnore
	public BrowserDetails getBrowserDetails() {
		return browserDetails;
	}
	public void setBrowserDetails(BrowserDetails browserDetails) {
		this.browserDetails = browserDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((SID == null) ? 0 : SID.hashCode());
		result = prime * result + ((browserDetails == null) ? 0 : browserDetails.hashCode());
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
		if (browserDetails == null) {
			if (other.browserDetails != null)
				return false;
		} else if (!browserDetails.equals(other.browserDetails))
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
				+ ", sessionLastAccessedTime=" + sessionLastAccessedTime + ", browserDetails=" + browserDetails + "]";
	}

	
}

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="browser_details")
public class BrowserDetails implements Serializable {
	
	private Long BID;
	private Long userAgetntId;
	private String browserName;
	private String browserVersion;
	private String browserType;
	private String refererURL;
	private DeviceDetails deviceDetails;
	private SessionDetails sessionDetails;
	private EventDetails eventDetails;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BID")
	public Long getBID() {
		return BID;
	}
	public void setBID(Long bID) {
		BID = bID;
	}
	
	@Column(name="user_agent_id", nullable=true, length=100)
	public Long getUserAgetntId() {
		return userAgetntId;
	}
	public void setUserAgetntId(Long userAgetntId) {
		this.userAgetntId = userAgetntId;
	}
	
	@Column(name="browser_name", nullable=true, length=100)
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	
	@Column(name="browser_version", nullable=true, length=100)
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	
	@Column(name="browser_type", nullable=true, length=100)
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	
	@Column(name="referer_url", nullable=true, length=150)
    public String getRefererURL() {
		return refererURL;
	}
	public void setRefererURL(String refererURL) {
		this.refererURL = refererURL;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="FDID", nullable=false)
    @JsonIgnore
	public DeviceDetails getDeviceDetails() {
		return deviceDetails;
	}
	public void setDeviceDetails(DeviceDetails deviceDetails) {
		this.deviceDetails = deviceDetails;
	}
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="FSID", nullable=false)
    @JsonIgnore
	public SessionDetails getSessionDetails() {
		return sessionDetails;
	}
	public void setSessionDetails(SessionDetails sessionDetails) {
		this.sessionDetails = sessionDetails;
	}
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="FEID", nullable=false)
    @JsonIgnore
	public EventDetails getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BID == null) ? 0 : BID.hashCode());
		result = prime * result + ((browserName == null) ? 0 : browserName.hashCode());
		result = prime * result + ((browserType == null) ? 0 : browserType.hashCode());
		result = prime * result + ((browserVersion == null) ? 0 : browserVersion.hashCode());
		result = prime * result + ((deviceDetails == null) ? 0 : deviceDetails.hashCode());
		result = prime * result + ((eventDetails == null) ? 0 : eventDetails.hashCode());
		result = prime * result + ((refererURL == null) ? 0 : refererURL.hashCode());
		result = prime * result + ((sessionDetails == null) ? 0 : sessionDetails.hashCode());
		result = prime * result + ((userAgetntId == null) ? 0 : userAgetntId.hashCode());
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
		BrowserDetails other = (BrowserDetails) obj;
		if (BID == null) {
			if (other.BID != null)
				return false;
		} else if (!BID.equals(other.BID))
			return false;
		if (browserName == null) {
			if (other.browserName != null)
				return false;
		} else if (!browserName.equals(other.browserName))
			return false;
		if (browserType == null) {
			if (other.browserType != null)
				return false;
		} else if (!browserType.equals(other.browserType))
			return false;
		if (browserVersion == null) {
			if (other.browserVersion != null)
				return false;
		} else if (!browserVersion.equals(other.browserVersion))
			return false;
		if (deviceDetails == null) {
			if (other.deviceDetails != null)
				return false;
		} else if (!deviceDetails.equals(other.deviceDetails))
			return false;
		if (eventDetails == null) {
			if (other.eventDetails != null)
				return false;
		} else if (!eventDetails.equals(other.eventDetails))
			return false;
		if (refererURL == null) {
			if (other.refererURL != null)
				return false;
		} else if (!refererURL.equals(other.refererURL))
			return false;
		if (sessionDetails == null) {
			if (other.sessionDetails != null)
				return false;
		} else if (!sessionDetails.equals(other.sessionDetails))
			return false;
		if (userAgetntId == null) {
			if (other.userAgetntId != null)
				return false;
		} else if (!userAgetntId.equals(other.userAgetntId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BrowserDetails [BID=" + BID + ", userAgetntId=" + userAgetntId + ", browserName=" + browserName
				+ ", browserVersion=" + browserVersion + ", browserType=" + browserType + ", refererURL=" + refererURL
				+ ", deviceDetails=" + deviceDetails + ", sessionDetails=" + sessionDetails + ", eventDetails="
				+ eventDetails + "]";
	}
	
}

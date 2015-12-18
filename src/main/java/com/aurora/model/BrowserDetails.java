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
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="browser_details")
public class BrowserDetails implements Serializable {
	
	private Long BID;
	private Integer userAgetntId;
	private String browserName;
	private String browserVersion;
	private String browserType;
	private DeviceDetails deviceDetails;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BID")
	public Long getBID() {
		return BID;
	}
	public void setBID(Long bID) {
		BID = bID;
	}
	
	@Column(name="user_agent_id", nullable=false, length=100)
	public Integer getUserAgetntId() {
		return userAgetntId;
	}
	public void setUserAgetntId(Integer userAgetntId) {
		this.userAgetntId = userAgetntId;
	}
	
	@Column(name="browser_name", nullable=false, length=100)
	public String getBrowserName() {
		return browserName;
	}
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}
	
	@Column(name="browser_version", nullable=false, length=100)
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	
	@Column(name="browser_type", nullable=false, length=100)
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BID == null) ? 0 : BID.hashCode());
		result = prime * result + ((browserName == null) ? 0 : browserName.hashCode());
		result = prime * result + ((browserType == null) ? 0 : browserType.hashCode());
		result = prime * result + ((browserVersion == null) ? 0 : browserVersion.hashCode());
		result = prime * result + ((deviceDetails == null) ? 0 : deviceDetails.hashCode());
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
				+ ", browserVersion=" + browserVersion + ", browserType=" + browserType + ", deviceDetails="
				+ deviceDetails + "]";
	}
	
}

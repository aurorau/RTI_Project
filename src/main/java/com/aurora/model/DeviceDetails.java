package com.aurora.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="device_details")
public class DeviceDetails implements Serializable {
	
	private Long DID;
	private String deviceType;
	private String deviceName;
	private String osName;
	private String osManufacture;
	private String orientation;
	private String width;
	private String height;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DID")
	public Long getDID() {
		return DID;
	}
	public void setDID(Long dID) {
		DID = dID;
	}
	
	@Column(name="device_type", nullable=true, length=100)
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Column(name="device_name", nullable=true, length=100)
	public String getDeviceName() {
		return deviceName;
	}
	
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Column(name="os_name", nullable=true, length=100)
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	
	@Column(name="os_manufacturer", nullable=true, length=100)
	public String getOsManufacture() {
		return osManufacture;
	}
	public void setOsManufacture(String osManufacture) {
		this.osManufacture = osManufacture;
	}
	
	@Column(name="orientation", nullable=true, length=100)
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	@Column(name="width", nullable=true, length=100)
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	
	@Column(name="height", nullable=true, length=100)
	public void setHeight(String height) {
		this.height = height;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DID == null) ? 0 : DID.hashCode());
		result = prime * result + ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((osManufacture == null) ? 0 : osManufacture.hashCode());
		result = prime * result + ((osName == null) ? 0 : osName.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
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
		DeviceDetails other = (DeviceDetails) obj;
		if (DID == null) {
			if (other.DID != null)
				return false;
		} else if (!DID.equals(other.DID))
			return false;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (osManufacture == null) {
			if (other.osManufacture != null)
				return false;
		} else if (!osManufacture.equals(other.osManufacture))
			return false;
		if (osName == null) {
			if (other.osName != null)
				return false;
		} else if (!osName.equals(other.osName))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DeviceDetails [DID=" + DID + ", deviceType=" + deviceType + ", deviceName=" + deviceName + ", osName="
				+ osName + ", osManufacture=" + osManufacture + ", orientation=" + orientation + ", width=" + width
				+ ", height=" + height + "]";
	}
}

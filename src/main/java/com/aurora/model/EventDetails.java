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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="event_details")
public class EventDetails implements Serializable {
	
	private Long EID;
	private String eventTypes;
	private String eventName;
	private String triggeredTime;
	private String coordinateX;
	private String coordinateY;
	private String screenWidth;
	private String screenHeight;
	private String orientation;
	//private BrowserDetails browserDetails;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EID")
	public Long getEID() {
		return EID;
	}
	public void setEID(Long eID) {
		EID = eID;
	}
	
	@Column(name="event_type", nullable=true, length=100)
	public String getEventTypes() {
		return eventTypes;
	}
	public void setEventTypes(String eventTypes) {
		this.eventTypes = eventTypes;
	}
	
	@Column(name="event_name", nullable=true, length=100)
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Column(name="triggered_time", nullable=true, length=100)
	public String getTriggeredTime() {
		return triggeredTime;
	}
	public void setTriggeredTime(String triggeredTime) {
		this.triggeredTime = triggeredTime;
	}
	
/*    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="FBID", nullable=false)
    @JsonIgnore
	public BrowserDetails getBrowserDetails() {
		return browserDetails;
	}

	public void setBrowserDetails(BrowserDetails browserDetails) {
		this.browserDetails = browserDetails;
	}*/
    
    @Column(name="coordinate_x", nullable=true, length=100)
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	
	@Column(name="coordinate_y", nullable=true, length=100)
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	
	@Column(name="screen_width", nullable=true, length=100)
	public String getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(String screenWidth) {
		this.screenWidth = screenWidth;
	}
	
	@Column(name="screen_height", nullable=true, length=100)
	public String getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(String screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	@Column(name="orientation", nullable=true, length=100)
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EID == null) ? 0 : EID.hashCode());
		result = prime * result + ((coordinateX == null) ? 0 : coordinateX.hashCode());
		result = prime * result + ((coordinateY == null) ? 0 : coordinateY.hashCode());
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((eventTypes == null) ? 0 : eventTypes.hashCode());
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + ((screenHeight == null) ? 0 : screenHeight.hashCode());
		result = prime * result + ((screenWidth == null) ? 0 : screenWidth.hashCode());
		result = prime * result + ((triggeredTime == null) ? 0 : triggeredTime.hashCode());
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
		EventDetails other = (EventDetails) obj;
		if (EID == null) {
			if (other.EID != null)
				return false;
		} else if (!EID.equals(other.EID))
			return false;
		if (coordinateX == null) {
			if (other.coordinateX != null)
				return false;
		} else if (!coordinateX.equals(other.coordinateX))
			return false;
		if (coordinateY == null) {
			if (other.coordinateY != null)
				return false;
		} else if (!coordinateY.equals(other.coordinateY))
			return false;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (eventTypes == null) {
			if (other.eventTypes != null)
				return false;
		} else if (!eventTypes.equals(other.eventTypes))
			return false;
		if (orientation == null) {
			if (other.orientation != null)
				return false;
		} else if (!orientation.equals(other.orientation))
			return false;
		if (screenHeight == null) {
			if (other.screenHeight != null)
				return false;
		} else if (!screenHeight.equals(other.screenHeight))
			return false;
		if (screenWidth == null) {
			if (other.screenWidth != null)
				return false;
		} else if (!screenWidth.equals(other.screenWidth))
			return false;
		if (triggeredTime == null) {
			if (other.triggeredTime != null)
				return false;
		} else if (!triggeredTime.equals(other.triggeredTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EventDetails [EID=" + EID + ", eventTypes=" + eventTypes + ", eventName=" + eventName
				+ ", triggeredTime=" + triggeredTime + ", coordinateX=" + coordinateX + ", coordinateY=" + coordinateY
				+ ", screenWidth=" + screenWidth + ", screenHeight=" + screenHeight + ", orientation=" + orientation
				+ "]";
	}

}

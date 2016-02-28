package com.aurora.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String viewportHeight;
	private String viewportWidth;
	private String numOfTaps;
	private String tagName;
	private String scrollTop;
	private String timeZone;
	private String zoneDateTime;
	private String imageName;
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
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="triggered_time", nullable=true, length=50)
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
	
	@Column(name="viewport_height", nullable=true, length=30)
	public String getViewportHeight() {
		return viewportHeight;
	}
	public void setViewportHeight(String viewportHeight) {
		this.viewportHeight = viewportHeight;
	}
	
	@Column(name="viewport_width", nullable=true, length=30)
	public String getViewportWidth() {
		return viewportWidth;
	}
	public void setViewportWidth(String viewportWidth) {
		this.viewportWidth = viewportWidth;
	}
	
	@Column(name="number_of_taps", nullable=true, length=30)
	public String getNumOfTaps() {
		return numOfTaps;
	}
	public void setNumOfTaps(String numOfTaps) {
		this.numOfTaps = numOfTaps;
	}
	
	@Column(name="tag_name", nullable=true, length=30)
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Column(name="scroll_top", nullable=true, length=30)
	public String getScrollTop() {
		return scrollTop;
	}
	public void setScrollTop(String scrollTop) {
		this.scrollTop = scrollTop;
	}
	
	@Column(name="time_zone", nullable=true, length=30)
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	@Column(name="time_zone_date_time", nullable=true, length=30)
	public String getZoneDateTime() {
		return zoneDateTime;
	}
	public void setZoneDateTime(String zoneDateTime) {
		this.zoneDateTime = zoneDateTime;
	}
	
	@Column(name="image_name", nullable=true, length=100)
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EID == null) ? 0 : EID.hashCode());
		result = prime * result
				+ ((coordinateX == null) ? 0 : coordinateX.hashCode());
		result = prime * result
				+ ((coordinateY == null) ? 0 : coordinateY.hashCode());
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result
				+ ((eventTypes == null) ? 0 : eventTypes.hashCode());
		result = prime * result
				+ ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result
				+ ((numOfTaps == null) ? 0 : numOfTaps.hashCode());
		result = prime * result
				+ ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result
				+ ((screenHeight == null) ? 0 : screenHeight.hashCode());
		result = prime * result
				+ ((screenWidth == null) ? 0 : screenWidth.hashCode());
		result = prime * result
				+ ((scrollTop == null) ? 0 : scrollTop.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		result = prime * result
				+ ((timeZone == null) ? 0 : timeZone.hashCode());
		result = prime * result
				+ ((triggeredTime == null) ? 0 : triggeredTime.hashCode());
		result = prime * result
				+ ((viewportHeight == null) ? 0 : viewportHeight.hashCode());
		result = prime * result
				+ ((viewportWidth == null) ? 0 : viewportWidth.hashCode());
		result = prime * result
				+ ((zoneDateTime == null) ? 0 : zoneDateTime.hashCode());
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
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (numOfTaps == null) {
			if (other.numOfTaps != null)
				return false;
		} else if (!numOfTaps.equals(other.numOfTaps))
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
		if (scrollTop == null) {
			if (other.scrollTop != null)
				return false;
		} else if (!scrollTop.equals(other.scrollTop))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		if (triggeredTime == null) {
			if (other.triggeredTime != null)
				return false;
		} else if (!triggeredTime.equals(other.triggeredTime))
			return false;
		if (viewportHeight == null) {
			if (other.viewportHeight != null)
				return false;
		} else if (!viewportHeight.equals(other.viewportHeight))
			return false;
		if (viewportWidth == null) {
			if (other.viewportWidth != null)
				return false;
		} else if (!viewportWidth.equals(other.viewportWidth))
			return false;
		if (zoneDateTime == null) {
			if (other.zoneDateTime != null)
				return false;
		} else if (!zoneDateTime.equals(other.zoneDateTime))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EventDetails [EID=" + EID + ", eventTypes=" + eventTypes
				+ ", eventName=" + eventName + ", triggeredTime="
				+ triggeredTime + ", coordinateX=" + coordinateX
				+ ", coordinateY=" + coordinateY + ", screenWidth="
				+ screenWidth + ", screenHeight=" + screenHeight
				+ ", orientation=" + orientation + ", viewportHeight="
				+ viewportHeight + ", viewportWidth=" + viewportWidth
				+ ", numOfTaps=" + numOfTaps + ", tagName=" + tagName
				+ ", scrollTop=" + scrollTop + ", timeZone=" + timeZone
				+ ", zoneDateTime=" + zoneDateTime + ", imageName=" + imageName
				+ "]";
	}

}

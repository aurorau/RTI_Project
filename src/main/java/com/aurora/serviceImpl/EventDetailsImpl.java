package com.aurora.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.EventDetailsDao;
import com.aurora.model.DeviceDetails;
import com.aurora.model.EventDetails;
import com.aurora.model.SessionDetails;
import com.aurora.service.BrowserDetailsService;
import com.aurora.service.EventDetailsService;
import com.aurora.util.Constants;
import com.aurora.util.EventTypes;

@Service("eventDetailsService")
public class EventDetailsImpl implements EventDetailsService {

	private EventDetailsDao  eventDetailsDao = null;
	private BrowserDetailsService browserDetailsService = null;
	
	@Autowired
	public void setEventDetailsDao(EventDetailsDao eventDetailsDao) {
		this.eventDetailsDao = eventDetailsDao;
	}
	
	@Autowired
	public void setBrowserDetailsService(BrowserDetailsService browserDetailsService) {
		this.browserDetailsService = browserDetailsService;
	}
	
	@Transactional
	public String saveEventDetails(HttpServletRequest request, SessionDetails sessionDetails,DeviceDetails deviceDetails) {
		String res = Constants.FAIL;

		try {
			 String orientation = request.getParameter("orientation");
			 String eventType = request.getParameter("eventType");
			 String eventTriggeredTime = request.getParameter("eventTriggeredTime");
			 String coordinateX = request.getParameter("coordinateX");
			 String coordinateY = request.getParameter("coordinateY");
			 String screenWidth = request.getParameter("screenWidth");
			 String screenHeight = request.getParameter("screenHeight");
			 String viewportHeight = request.getParameter("viewportHeight");
			 String viewportWidth = request.getParameter("viewportWidth");
			 String numberOfFingers = request.getParameter("numberOfFingers");
			 String tagName = request.getParameter("tagName");
			 String elementScrollTopPx = request.getParameter("elementScrollTop");
			 String scrollTop = request.getParameter("scrollTopPx");
			 String imageName = request.getParameter("imageName");
			 
			 EventDetails eventDetails = new EventDetails();
			 
			 eventDetails.setCoordinateX(coordinateX);
			 eventDetails.setCoordinateY(coordinateY);
			 eventDetails.setTriggeredTime(eventTriggeredTime);
			 eventDetails.setScreenWidth(screenWidth);
			 eventDetails.setScreenHeight(screenHeight);
			 eventDetails.setOrientation(orientation);
			 eventDetails.setNumOfTaps(numberOfFingers);
			 eventDetails.setViewportHeight(viewportHeight);
			 eventDetails.setViewportWidth(viewportWidth);
			 eventDetails.setTagName(tagName);
			 
			 if(imageName.equalsIgnoreCase("-1")){
				 eventDetails.setImageName("No Image");
			 } else {
				 eventDetails.setImageName(imageName); 
			 }
			 
			 if(eventType.equalsIgnoreCase("SE")) {
				 eventDetails.setScrollTop(scrollTop);
			 } else {
				 eventDetails.setScrollTop(elementScrollTopPx); 
			 }
			 
			 if(eventType.equalsIgnoreCase("LC")) {
				 eventDetails.setEventName(EventTypes.LEFT_CLICK.name());
				 eventDetails.setEventTypes(EventTypes.LEFT_CLICK.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("RC")) {
				 eventDetails.setEventName(EventTypes.RIGHT_CLICK.name());
				 eventDetails.setEventTypes(EventTypes.RIGHT_CLICK.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("DC")) {
				 eventDetails.setEventName(EventTypes.DB_CLICK.name());
				 eventDetails.setEventTypes(EventTypes.DB_CLICK.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("KP")) {
				 eventDetails.setEventName(EventTypes.KEY_PRESS.name());
				 eventDetails.setEventTypes(EventTypes.KEY_PRESS.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("SE")) {
				 eventDetails.setEventName(EventTypes.SCROLL_EVENT.name());
				 eventDetails.setEventTypes(EventTypes.SCROLL_EVENT.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("TE")) {
				 eventDetails.setEventName(EventTypes.TOUCH_EVENT.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_EVENT.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("DZE")) {
				 eventDetails.setEventName(EventTypes.DESKTOP_ZOOM.name());
				 eventDetails.setEventTypes(EventTypes.DESKTOP_ZOOM.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("TZE")) {
				 eventDetails.setEventName(EventTypes.TOUCH_ZOOM.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_ZOOM.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("TZE_SE")) {
				 eventDetails.setEventName(EventTypes.TOUCH_ZOOM_EVENT_SCROLL.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_ZOOM_EVENT_SCROLL.getEventTypes());
				 
			 }  else if(eventType.equalsIgnoreCase("TE_SE")) {
				 eventDetails.setEventName(EventTypes.TOUCH_SCROLL.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_SCROLL.getEventTypes());
				 
			 }  else if(eventType.equalsIgnoreCase("TM")) {
				 eventDetails.setEventName(EventTypes.TOUCH_MOVE.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_MOVE.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("DSE")) {
				 eventDetails.setEventName(EventTypes.DESKTOP_SCROLL.name());
				 eventDetails.setEventTypes(EventTypes.DESKTOP_SCROLL.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("TS")) {
				 eventDetails.setEventName(EventTypes.TOUCH_START.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_START.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("RF")) {
				 eventDetails.setEventName(EventTypes.REFRESH_EVENT.name());
				 eventDetails.setEventTypes(EventTypes.REFRESH_EVENT.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("STZE")) {
				 eventDetails.setEventName(EventTypes.SWAP_TOUCH_ZOOM.name());
				 eventDetails.setEventTypes(EventTypes.SWAP_TOUCH_ZOOM.getEventTypes());
				 
			 } else if(eventType.equalsIgnoreCase("TSE")) {
				 eventDetails.setEventName(EventTypes.TOUCH_SCROLL_EVENT.name());
				 eventDetails.setEventTypes(EventTypes.TOUCH_SCROLL_EVENT.getEventTypes());
				 
			 } else {
				 System.out.println("New Type :"+eventType );
			 }
			 
			 Map<String, String> map = getCountryDateAndTime(request.getParameter("timeZoneOffset"));
			 
			 eventDetails.setTimeZone(map.get("timeZone"));
			 eventDetails.setZoneDateTime(map.get("dateTime"));
			 
			eventDetailsDao.saveEventDetails(eventDetails);
			String status = browserDetailsService.saveBrowserDetails(request, sessionDetails, deviceDetails, eventDetails);
			
			if(status.equalsIgnoreCase(Constants.SUCCESS)){
				res = Constants.SUCCESS;
			}
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

	 public Map<String, String> getCountryDateAndTime(String timeOffset) {
		 Map<String, String> map = new HashMap<String, String>();
		 if(!timeOffset.equalsIgnoreCase("-1")) {
			 DateTime utc = new DateTime(DateTimeZone.UTC);
			 DateTimeZone tz = DateTimeZone.forOffsetMillis((Integer.parseInt(timeOffset)* 60000 * -1));
			 DateTime currentTime = utc.toDateTime(tz);
			 
			 String timeZone = currentTime.getZone().toString();
			 
			 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
			 String str = currentTime.toString(fmt);
			 String dateTime = str;
			 
			 map.put("timeZone", timeZone);
			 map.put("dateTime", dateTime);
		 }
		 return map;
	 }
	
}

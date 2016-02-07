package com.aurora.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.aurora.model.BrowserDetails;
import com.aurora.model.DeviceDetails;
import com.aurora.model.EventDetails;
import com.aurora.model.ProxyDetails;
import com.aurora.model.SessionDetails;
import com.aurora.service.BrowserDetailsService;
import com.aurora.service.DeviceDetailsService;
import com.aurora.service.EventDetailsService;
import com.aurora.service.ProxyDetailsService;
import com.aurora.service.SessionDetailsService;
import com.aurora.util.ClickDetails;
import com.aurora.util.EventTypes;
import com.aurora.util.GeoLocation;
import com.aurora.util.JsonResponce;
import com.aurora.util.ProxyDetailsDTO;
import com.aurora.util.UserDetailsDTO;
import com.maxmind.geoip.LookupService;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.BrowserType;
import nl.bitwalker.useragentutils.Manufacturer;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import nl.bitwalker.useragentutils.Version;

@Controller
public class HomePageController {
	 protected final transient Log log = LogFactory.getLog(getClass());
	 public Map<String, Object> resultMap = null;
	 public Map<String, Object> userMap = null;
	 private static LookupService lookUp;
	 List<String> proxyList = null;
	 ClickDetails clickDetails = null;
	 
	 SessionDetailsService sessionDetailsService = null;
	 BrowserDetailsService browserDetailsService = null;
	 EventDetailsService eventDetailsService = null;
	 DeviceDetailsService deviceDetailsService = null;
	 ProxyDetailsService proxyDetailsService = null;

	 
	 @Autowired
	 public void setSessionDetailsService(SessionDetailsService sessionDetailsService) {
		 this.sessionDetailsService = sessionDetailsService;
	 }
	 
	 @Autowired
	 public void setBrowserDetailsService(BrowserDetailsService browserDetailsService) {
		 this.browserDetailsService = browserDetailsService;
	 }
	 
	 @Autowired
	 public void setEventDetailsService(EventDetailsService eventDetailsService) {
		 this.eventDetailsService = eventDetailsService;
	 }
	 
	 @Autowired
	 public void setDeviceDetailsService(DeviceDetailsService deviceDetailsService) {
		 this.deviceDetailsService = deviceDetailsService;
	 }
	 
	 @Autowired
	 public void setProxyDetailsService(ProxyDetailsService proxyDetailsService) {
		 this.proxyDetailsService = proxyDetailsService;
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/")
	 public ModelAndView homePage() throws Exception {
		 return new ModelAndView("PTHome");
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/homePage")
	 public ModelAndView homePage1() throws Exception {
		 return new ModelAndView("PTHome");
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/adminPage")
	 public ModelAndView adminPage() throws Exception {
		 return new ModelAndView("adminPage");
	 }
	 
/*	 @RequestMapping(method = RequestMethod.GET, value="/gallary*")
	 public ModelAndView galleryPage() throws Exception {
		 return new ModelAndView("Gallary");
	 }*/
	 
/*	 @RequestMapping(method = RequestMethod.GET, value="/getLocationDetails")
	 public @ResponseBody JsonResponce getLocationDetails(HttpServletRequest request) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
		 String ipAddress = request.getParameter("ipAddress");
		 String detailsLong = getLocation(ipAddress).toString();
		 res.setStatus("success");
		 res.setResult(detailsLong);
		 return res;
	 }*/
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getProxyDetails")
	 public @ResponseBody JsonResponce getProxyDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		 
	     Long bid = Long.parseLong(request.getParameter("bid"));
	     
	     List<ProxyDetailsDTO> list = proxyDetailsService.getProxyDetails(bid);
		 res.setStatus("success");
		 res.setResult(list);
		 return res;
	 }
	 
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getCurrentUserCount")
	 public @ResponseBody JsonResponce getCurrentUserCount(HttpServletResponse response) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
		 res.setStatus("success");
		 res.setResult(sessionDetailsService.getCurrentUserCount());
		 return res;
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getUserDetailsBySessionId")
	 public @ResponseBody JsonResponce getUserDetailsBySessionId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
	     Long sessionPK = Long.parseLong(request.getParameter("sid"));
	     
	     List<UserDetailsDTO> list = null;
	     
	     list = sessionDetailsService.getUserDetailsBySessionId(sessionPK);
	     
		 res.setStatus("success");
		 res.setResult(list);
		 
		 return res;
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, value="/postEventDetails")
	 public  @ResponseBody JsonResponce postEventDetails(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 String sessionId = null;
		 
		 sessionId = request.getParameter("sessionID");
		 
		 if(sessionId == null || sessionId.equalsIgnoreCase("")) {
			 sessionId = request.getSession().getId();
		 }
		 
		 log.debug("**/Enter postEventDetails method/**");
		 
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "POST");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		 
		 String orientation = request.getParameter("orientation");
		 String eventType = request.getParameter("eventType");
		 String eventTriggeredTime = request.getParameter("eventTriggeredTime");
		 String coordinateX = request.getParameter("coordinateX");
		 String coordinateY = request.getParameter("coordinateY");
		 String screenWidth = request.getParameter("screenWidth");
		 String screenHeight = request.getParameter("screenHeight");
		 
		 System.out.println("eventType :"+eventType);
		 System.out.println("coordinateX :"+coordinateX);
		 System.out.println("coordinateY :"+coordinateY);
		 System.out.println("screenWidth :"+screenWidth);
		 System.out.println("screenHeight :"+screenHeight);
		 System.out.println("eventTriggeredTime :"+eventTriggeredTime);
		 System.out.println("orientation :"+orientation);
		 
		 String res1 = saveSessionDetails(request, sessionId);
		 
		 res.setStatus(res1);
		 res.setResult(sessionId);

		 log.debug("**/Exit postEventDetails method/**");
		 
		 return res;
	 }
	 
	 public String saveSessionDetails(HttpServletRequest request, String sessionId1) {
		 
		   String res = null;
		   
		   Long creationTime = request.getSession().getCreationTime();
		   String sessionId = sessionId1;
		   Long lastAccessTime = request.getSession().getLastAccessedTime();
		   
		   SessionDetails sessionDetails = null;
		   try{
			   sessionDetails = sessionDetailsService.getSessionDetailsByCreationTimeById(creationTime, sessionId);	
			   
			   if(sessionDetails == null) {
				   
				   sessionDetails = new SessionDetails();
				   sessionDetails.setSessionId(sessionId);
				   sessionDetails.setSessionLastAccessedTime(lastAccessTime);
				   sessionDetails.setSessionCreatedTime(creationTime);
				   sessionDetails.setSessionAccessCount(1L);
				   sessionDetails.setLastAccessTime(getTime());
				   
			   } else {
				   sessionDetails = sessionDetailsService.getById(sessionDetails.getSID());
				   sessionDetails.setSessionLastAccessedTime(lastAccessTime);
				   sessionDetails.setSessionAccessCount(sessionDetails.getSessionAccessCount()+1);
				   sessionDetails.setLastAccessTime(getTime());
			   }
			   
			   sessionDetailsService.saveSessionDetails(sessionDetails);		   
			   
			   setNewDeviceDetails(request,sessionDetails);
			   res = "success";
			   
		   } catch(Exception e){
			   res = "ERROR";
			   System.out.println("SaveSessionDetails Controller:"+e);
		   }
		   return res;
	 }
	 
	 public void setNewEventDetails(HttpServletRequest request,SessionDetails sessionDetails,DeviceDetails deviceDetails) {
		 
		 String orientation = request.getParameter("orientation");
		 String eventType = request.getParameter("eventType");
		 String eventTriggeredTime = request.getParameter("eventTriggeredTime");
		 String coordinateX = request.getParameter("coordinateX");
		 String coordinateY = request.getParameter("coordinateY");
		 String screenWidth = request.getParameter("screenWidth");
		 String screenHeight = request.getParameter("screenHeight");
		 
		 EventDetails eventDetails = new EventDetails();

		 eventDetails.setCoordinateX(coordinateX);
		 eventDetails.setCoordinateY(coordinateY);
		 eventDetails.setTriggeredTime(eventTriggeredTime);
		 eventDetails.setScreenWidth(screenWidth);
		 eventDetails.setScreenHeight(screenHeight);
		 eventDetails.setOrientation(orientation);
		 
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
			 eventDetails.setEventName(EventTypes.DESKTOP_ZOOM_EVENT.name());
			 eventDetails.setEventTypes(EventTypes.DESKTOP_ZOOM_EVENT.getEventTypes());
			 
		 } else if(eventType.equalsIgnoreCase("TZE")) {
			 eventDetails.setEventName(EventTypes.TOUCH_ZOOM_EVENT.name());
			 eventDetails.setEventTypes(EventTypes.TOUCH_ZOOM_EVENT.getEventTypes());
			 
		 } else {
			 System.out.println("New Type :"+eventType );
		 }
		 
		 eventDetailsService.saveEventDetails(eventDetails);
		 setNewBrowserDetails(request,sessionDetails,deviceDetails,eventDetails);
	 }
	 
	 public void setNewProxyDetails(HttpServletRequest request,BrowserDetails browserDetails) {
		 
		 proxyList = new ArrayList<String>();
		 String ipAddress = request.getHeader("X-FORWARDED-FOR");
				 //"112.135.1.252,199.189.80.13";
				 //request.getHeader("X-FORWARDED-FOR");
				
		 
		 if(ipAddress != null) {
        	String[] proxyList1 = ipAddress.split(",");
        	for(String prxy : proxyList1) {
        		String ip = prxy;
        		proxyList.add(ip);
        	}
		 }
		 
		 for(String proxyIP : proxyList) {
			 ProxyDetails proxyDetails=  new ProxyDetails();
			 GeoLocation geoLocation = getLocation(proxyIP);
			 
			 proxyDetails.setBrowserDetails(browserDetails);
			 proxyDetails.setCity(geoLocation.getCity());
			 proxyDetails.setCountryCode(geoLocation.getCountryCode());
			 proxyDetails.setCountryName(geoLocation.getCountryName());
			 proxyDetails.setIpAddress(proxyIP);
			 proxyDetails.setLatitude(geoLocation.getLatitude());
			 proxyDetails.setLongitude(geoLocation.getLongitude());
			 proxyDetails.setPostalCode(geoLocation.getPostalCode());
			 proxyDetails.setRegion(geoLocation.getRegion());
			 
			 proxyDetailsService.saveProxyDetailsService(proxyDetails);
		 }
	 }

	 
	public String getTime(){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		 Date date = new Date();
		 String result = formatter.format(date);
		 return result;
	 }

	public void setNewBrowserDetails(HttpServletRequest request, SessionDetails sessionDetails, DeviceDetails deviceDetails, EventDetails eventDetails) {
		
		String refererURL = request.getHeader("referer");
		UserAgent userAgent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        long getId = userAgent1.getId();
        Browser browser = userAgent1.getBrowser();
        String browserName = browser.getName();
        BrowserType browserType = browser.getBrowserType();
        Version browserVersion = userAgent1.getBrowserVersion();
		
		BrowserDetails browserDetails =  new BrowserDetails();
		
		browserDetails.setBrowserName(browserName);
		browserDetails.setBrowserType(browserType.toString());
		browserDetails.setBrowserVersion(browserVersion.toString());
		browserDetails.setUserAgetntId(getId);
		browserDetails.setRefererURL(refererURL);
		browserDetails.setDeviceDetails(deviceDetails);
		browserDetails.setSessionDetails(sessionDetails);
		browserDetails.setEventDetails(eventDetails);
		
		browserDetailsService.saveBrowserDetails(browserDetails);

		setNewProxyDetails(request, browserDetails);
	}
	
	public void setNewDeviceDetails(HttpServletRequest request, SessionDetails sessionDetails) {
		
		UserAgent userAgent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		OperatingSystem agent = userAgent1.getOperatingSystem(); 
        String deviceName = agent.getDeviceType().getName();
        String osName = agent.getName();
        Manufacturer osManufacture = agent.getManufacturer();
        String orientation = request.getParameter("orientation");
        
		DeviceDetails deviceDetails = new DeviceDetails();
		deviceDetails.setDeviceName(deviceName);
		//deviceDetails.setDeviceType(deviceType);
/*		deviceDetails.setHeight(screenHeight);
		deviceDetails.setWidth(screenWidth);*/
		deviceDetails.setOrientation(orientation);
		deviceDetails.setOsManufacture(osManufacture.toString());
		deviceDetails.setOsName(osName);
		 
		deviceDetailsService.saveDeviceDetails(deviceDetails);
		
		setNewEventDetails(request,sessionDetails,deviceDetails);
	}
	
    static {
        try {
            lookUp = new LookupService(
            		HomePageController.class.getResource("/GeoLiteCity.dat").getFile(),
                    LookupService.GEOIP_MEMORY_CACHE);

        } catch (IOException e) {
           // System.out.println("Could not load geo ip database: " + e.getMessage());
        }
    }
	 public static GeoLocation getLocation(long ipAddress) {
	        return GeoLocation.map(lookUp.getLocation(ipAddress));
	 }
	 
	 public static GeoLocation getLocation(String ipAddress) {
	        return GeoLocation.map(lookUp.getLocation(ipAddress));
	 }

	 public static GeoLocation getLocation(InetAddress ipAddress){
	        return GeoLocation.map(lookUp.getLocation(ipAddress));
	 }
}

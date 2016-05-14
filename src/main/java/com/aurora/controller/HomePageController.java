package com.aurora.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
import com.aurora.util.AnalyseUserDTO;
import com.aurora.util.ClickDetails;
import com.aurora.util.Constants;
import com.aurora.util.CurrentUsersDTO;
import com.aurora.util.EventTypes;
import com.aurora.util.GeoLocation;
import com.aurora.util.JsonResponce;
import com.aurora.util.LoginBean;
import com.aurora.util.ProxyDetailsDTO;
import com.aurora.util.UserCountDTO;
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
	 Map<String, String> map = null;
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
	 public ModelAndView adminPage(HttpServletRequest request, HttpServletResponse response, LoginBean loginBean) throws Exception {
		ModelAndView model = new ModelAndView("login");
		model.addObject("loginBean", loginBean);
		return model;
	 }
	 @RequestMapping(method = RequestMethod.GET, value="/dashboard")
	 public ModelAndView dashboard() throws Exception {
		 return new ModelAndView("dashboard");
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/gallary")
	 public ModelAndView galleryPage() throws Exception {
		 return new ModelAndView("Gallary");
	 }
	 
	 @RequestMapping(value="/login",method=RequestMethod.POST)
	 public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("loginBean") LoginBean loginBean){
	    ModelAndView model= null;
	    try{

	        if(loginBean.getUsername().equalsIgnoreCase("admin") && loginBean.getPassword().equalsIgnoreCase("123")){
	            System.out.println("User Login Successful");
	            request.setAttribute("loggedInUser", loginBean.getUsername());
	            model = new ModelAndView("adminPage");
	    	}else{
	    		model = new ModelAndView("login");
	    		model.addObject("loginBean", loginBean);
	            request.setAttribute("message", "Invalid credentials!!");
	    	}
	     }catch(Exception e){
	    	 System.out.println("Error :"+e);
	     }
	    return model;
     }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/heartBeat")
	 public @ResponseBody JsonResponce heartBeat(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		 
	     String status = sessionDetailsService.heartBeat(request);
	     
		 res.setStatus(status);
		 res.setResult(status);
		 return res;
	 }
	 
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
	 
	 
/*	 @RequestMapping(method = RequestMethod.GET, value="/getCurrentUserCount")
	 public @ResponseBody JsonResponce getCurrentUserCount(HttpServletResponse response) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
		 res.setStatus("success");
		 res.setResult(sessionDetailsService.getCurrentUserCount());
		 return res;
	 }*/
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getCurrentUserCount")
	 public ModelAndView getCurrentUserCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
	     List<UserCountDTO> list = null;
	     
		 Model model = new ExtendedModelMap();
		 ParamEncoder paramEncoder = new ParamEncoder(Constants.USER_COUNT_TABLE);
		 
	    	try{
	    		String sortField = ServletRequestUtils.getStringParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_SORT));
	    		int order = ServletRequestUtils.getIntParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_ORDER), 0);
	    		int page = ServletRequestUtils.getIntParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE), 0);
	    		int start = (page>0) ? (page - 1) * Constants.GRID_TABLE_SIZE : 0;
	    		String searchq = ServletRequestUtils.getStringParameter(request, Constants.PARAMETER_SEARCH);
			
	    		list = sessionDetailsService.getCurrentUserCountList(sortField,order,start, Constants.GRID_TABLE_SIZE, searchq);
	    		int listCount = sessionDetailsService.getCurrentUserCount(searchq);
			
	    		request.setAttribute(Constants.TABLE_SIZE, listCount );
	    		request.setAttribute(Constants.GRID_TABLE_SIZE_KEY, Constants.GRID_TABLE_SIZE);
	    		model.addAttribute(Constants.USER_COUNT_TABLE, list);
	    	} catch (Exception e) {
	    		System.out.println(e);
	    	}
		 return new ModelAndView("dynamicTables/dynamicUserCountTable", model.asMap());
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getUserDetailsBySessionId")
	 public ModelAndView getUserDetailsBySessionId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
	     Long sessionPK = Long.parseLong(request.getParameter("sid"));
	     List<UserDetailsDTO> list = null;
	     
		 Model model = new ExtendedModelMap();
		 ParamEncoder paramEncoder = new ParamEncoder(Constants.USER_DETAILS);
		 
	    	try{
	    		String sortField = ServletRequestUtils.getStringParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_SORT));
	    		int order = ServletRequestUtils.getIntParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_ORDER), 0);
	    		int page = ServletRequestUtils.getIntParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE), 0);
	    		int start = (page>0) ? (page - 1) * Constants.GRID_TABLE_SIZE : 0;
	    		String searchq = ServletRequestUtils.getStringParameter(request, Constants.PARAMETER_SEARCH);
			
	    		list = sessionDetailsService.getUserDetailsBySessionId(sortField,order,start, Constants.GRID_TABLE_SIZE, searchq, sessionPK);
	    		int listCount = sessionDetailsService.getUserDetailsCountBySessionId(searchq, sessionPK);
			
	    		request.setAttribute(Constants.TABLE_SIZE, listCount );
	    		request.setAttribute(Constants.GRID_TABLE_SIZE_KEY, Constants.GRID_TABLE_SIZE);
	    		model.addAttribute(Constants.USER_DETAILS, list);
	    	} catch (Exception e) {
	    		System.out.println(e);
	    	}
		 return new ModelAndView("dynamicTables/dynamicUserDetailsTable", model.asMap());
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/analyseUserBySessionId")
	 public @ResponseBody JsonResponce analyseUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
	     Long sessionPK = Long.parseLong(request.getParameter("sid"));
	     
	     AnalyseUserDTO list = null;
/*		 Model model = new ExtendedModelMap();
	     
		 ParamEncoder paramEncoder = new ParamEncoder(Constants.ANALYSE_USER_TABLE);
		 
	    	try{
	    		String sortField = ServletRequestUtils.getStringParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_SORT));
	    		int order = ServletRequestUtils.getIntParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_ORDER), 0);
	    		int page = ServletRequestUtils.getIntParameter(request, paramEncoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE), 0);
	    		int start = (page>0) ? (page - 1) * Constants.GRID_TABLE_SIZE : 0;
	    		String searchq = ServletRequestUtils.getStringParameter(request, Constants.PARAMETER_SEARCH);
			
	    		list = sessionDetailsService.analyseUserBySessionId(sortField,order,start, Constants.GRID_TABLE_SIZE, searchq, sessionPK);
	    		int listCount = sessionDetailsService.analyseUserCountBySessionId(searchq, sessionPK);
			
	    		request.setAttribute(Constants.TABLE_SIZE, listCount );
	    		request.setAttribute(Constants.GRID_TABLE_SIZE_KEY, Constants.GRID_TABLE_SIZE);
	    		model.addAttribute(Constants.ANALYSE_USER_TABLE, list);
	    	} catch (Exception e) {
	    		System.out.println(e);
	    	}
		 return new ModelAndView("dynamicTables/dynamicanalyseUserTable", model.asMap());*/
		 list = sessionDetailsService.analyseUserBySessionId("",1,1, Constants.GRID_TABLE_SIZE, "", sessionPK);
	     
		 res.setStatus("success");
		 res.setResult(list);
		 
		 return res;
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, value="/postEventDetails")
	 public  @ResponseBody JsonResponce postEventDetails(HttpServletRequest request, HttpServletResponse response, TimeZone timeZone) throws Exception {
		 
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

		 String eventType = request.getParameter("eventType");
		 String orientation = request.getParameter("orientation");
		 String eventTriggeredTime = request.getParameter("eventTriggeredTime");
		 String coordinateX = request.getParameter("coordinateX");
		 String coordinateY = request.getParameter("coordinateY");
		 String screenWidth = request.getParameter("screenWidth");
		 String screenHeight = request.getParameter("screenHeight");
		 
		 String tagName = request.getParameter("tagName");
		 String elementId = request.getParameter("elementId");
		 String elementHeight = request.getParameter("elementHeight");
		 String elementWidth = request.getParameter("elementWidth");
		 String elementOffsetTop = request.getParameter("elementOffsetTop");
		 String elementOffsetLeft = request.getParameter("elementOffsetLeft");
		 String elementClass = request.getParameter("elementClass");
		 String numberOfFingers = request.getParameter("numberOfFingers");
		 String scrollTopPx = request.getParameter("scrollTopPx");
		 String elementScrollTop = request.getParameter("elementScrollTop");
		 
		 System.out.println("eventType :"+eventType);
		 System.out.println("coordinateX :"+coordinateX);
		 System.out.println("coordinateY :"+coordinateY);
		 System.out.println("screenWidth :"+screenWidth);
		 System.out.println("screenHeight :"+screenHeight);
		 System.out.println("eventTriggeredTime :"+eventTriggeredTime);
		 System.out.println("orientation :"+orientation);
		 
		 System.out.println("tagName :"+tagName);
		 System.out.println("elementId :"+elementId);
		 System.out.println("elementClass :"+elementClass);
		 System.out.println("elementHeight :"+elementHeight);
		 System.out.println("elementWidth :"+elementWidth);
		 System.out.println("elementOffsetTop :"+elementOffsetTop);
		 System.out.println("elementOffsetLeft :"+elementOffsetLeft);
		 System.out.println("numberOfFingers :"+numberOfFingers);
		 System.out.println("scrollTopPx :"+scrollTopPx);
		 System.out.println("elementScrollTop :"+elementScrollTop);
		 
		 String res1 = sessionDetailsService.saveSessionDetails(request);
		 
		 res.setStatus(res1);
		 res.setResult(sessionId);

		 log.debug("**/Exit postEventDetails method/**");
		 return res;
	 }
	 
	 public String saveSessionDetails(HttpServletRequest request, String sessionId1) {
		 
		   String res = null;
		   
		   Long creationTime = request.getSession().getCreationTime();
		   String sessionId = sessionId1;
		   
		   String currentLocationBasedTime = getLocationBasedCurrentTime(request.getParameter("timeZoneOffset"));
		   
		   SessionDetails sessionDetails = null;
		   try{
			   sessionDetails = sessionDetailsService.getSessionDetailsByCreationTimeById(creationTime, sessionId);	
			   
			   if(sessionDetails == null) {
				   
				   sessionDetails = new SessionDetails();
				   sessionDetails.setSessionId(sessionId);
				  // sessionDetails.setSessionLastAccessedTime(lastAccessTime);
				  // sessionDetails.setSessionCreatedTime(creationTime);
				   sessionDetails.setSessionAccessCount(1L);
				   sessionDetails.setLastAccessTime(currentLocationBasedTime);
				   
			   } else {
				   sessionDetails = sessionDetailsService.getById(sessionDetails.getSID());
				   //sessionDetails.setSessionLastAccessedTime(lastAccessTime);
				   sessionDetails.setSessionAccessCount(sessionDetails.getSessionAccessCount()+1);
				   sessionDetails.setLastAccessTime(currentLocationBasedTime);
			   }
			   
			  // sessionDetailsService.saveSessionDetails(sessionDetails);		   
			   sessionDetailsService.saveSessionDetails(request);
			  // setNewDeviceDetails(request,sessionDetails);
			   res = Constants.SUCCESS;
			   
		   } catch(Exception e){
			   res = Constants.ERROR;
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
/*		 try {
			eventDetails.setTriggeredTime(setStringToDateFormat(eventTriggeredTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
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
		 
		 //eventDetailsService.saveEventDetails(eventDetails);
		// setNewBrowserDetails(request,sessionDetails,deviceDetails,eventDetails);
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
			 
			// proxyDetailsService.saveProxyDetailsService(proxyDetails);
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
		
		//browserDetailsService.saveBrowserDetails(browserDetails);

		//setNewProxyDetails(request, browserDetails);
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
		deviceDetails.setOrientation(orientation);
		deviceDetails.setOsManufacture(osManufacture.toString());
		deviceDetails.setOsName(osName);
		 
		//deviceDetailsService.saveDeviceDetails(deviceDetails);
		
		//setNewEventDetails(request,sessionDetails,deviceDetails);

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
	 public Date setStringToDateFormat(String date1) throws ParseException{
		
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        Date date = originalFormat.parse(date1);
        Date dateOut = date;
		
        return dateOut;
	 }
	 public Map<String, String> getCountryDateAndTime(String timeOffset) {
		 map = new HashMap<String, String>();
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
	 public String getLocationBasedCurrentTime(String timeOffset){
		 String dateTime = null;
		 if(!timeOffset.equalsIgnoreCase("-1")) {
			 DateTime utc = new DateTime(DateTimeZone.UTC);
			 DateTimeZone tz = DateTimeZone.forOffsetMillis((Integer.parseInt(timeOffset)* 60000 * -1));
			 DateTime currentTime = utc.toDateTime(tz);
			 
			 DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			 String str = currentTime.toString(fmt);
			 dateTime = str;
		 }
		 return dateTime;
	 }
}

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
		 list = sessionDetailsService.analyseUserBySessionId("",1,1, Constants.GRID_TABLE_SIZE, "", sessionPK);
	     
		 res.setStatus("success");
		 res.setResult(list);
		 
		 return res;
	 }
	 @RequestMapping(method = RequestMethod.GET, value="/getDeviceCount")
	 public @ResponseBody JsonResponce getDeviceCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		 JsonResponce res= new JsonResponce();
		 
	     response.setHeader("Access-Control-Allow-Origin", "*");
	     response.setHeader("Access-Control-Allow-Methods", "GET");
	     response.setHeader("Access-Control-Max-Age", "3600");
	     response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	     
	     
	     
	     Map<String, Integer> deviceCountMap = null;
	     deviceCountMap = sessionDetailsService.getDeviceCount();
	     
		 res.setStatus("success");
		 res.setResult(deviceCountMap);
		 
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

/*		 String eventType = request.getParameter("eventType");
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
		 System.out.println("elementScrollTop :"+elementScrollTop);*/
		 
		 String res1 = sessionDetailsService.saveSessionDetails(request);
		 
		 res.setStatus(res1);
		 res.setResult(sessionId);

		 log.debug("**/Exit postEventDetails method/**");
		 return res;
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
/*	 public Map<String, String> getCountryDateAndTime(String timeOffset) {
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
	 }*/
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

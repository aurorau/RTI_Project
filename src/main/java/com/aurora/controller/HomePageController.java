package com.aurora.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurora.util.ClickDetails;
import com.aurora.util.GeoLocation;
import com.aurora.util.JsonResponce;
import com.btr.proxy.search.ProxySearch;
import com.btr.proxy.util.Logger;
import com.maxmind.geoip.LookupService;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

@Controller
public class HomePageController {
	 protected final transient Log log = LogFactory.getLog(getClass());
	 public Map<String, Object> resultMap = null;
	 private static LookupService lookUp;
	 List<String> proxyList = null;
	 ClickDetails clickDetails = null;
	 
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
	 
	 @RequestMapping(method = RequestMethod.GET, value="/gallary*")
	 public ModelAndView galleryPage() throws Exception {
		 return new ModelAndView("Gallary");
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getLocationDetails")
	 public @ResponseBody JsonResponce getLocationDetails(HttpServletRequest request) throws Exception {
		 JsonResponce res= new JsonResponce();
		 
		 String ipAddress = request.getParameter("ipAddress");
		 String detailsLong = getLocation(ipAddress).toString();
		 res.setStatus("success");
		 res.setResult(detailsLong);
		 return res;
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value="/getDataForAdminPage")
	 public @ResponseBody JsonResponce getDataForAdminPage() throws Exception {
		 JsonResponce res= new JsonResponce();
		 
		 res.setStatus("success");
		 res.setResult(clickDetails);
		 return res;
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, value="/getHeaderString")
	 public  @ResponseBody JsonResponce getHeaderString(HttpServletRequest request, HttpServletResponse responce) throws Exception {
		 
		 log.debug("**/Enter getHeaderString method/**");
		 
		 JsonResponce res= new JsonResponce();
		 resultMap =  new HashMap<String, Object>();
		 proxyList = new ArrayList<String>();

		   String ipAddress = request.getHeader("X-FORWARDED-FOR");
				   //"112.135.1.252,199.189.80.13";
		   //"112.135.0.211,199.189.80.13"
		   String languages = request.getParameter("languages");
		   String location = request.getParameter("location");
		   
		   Long creationTime = request.getSession().getCreationTime();
		   String sessionId = request.getSession().getId();
		   Long lastAccessTime = request.getSession().getLastAccessedTime();
		   
		   Long leftClickCount = Long.parseLong(request.getParameter("leftClickCount"));
		   Long rightClickCount = Long.parseLong(request.getParameter("rightClickCount"));
		   Long dbClickCount = Long.parseLong(request.getParameter("dbClickCount"));
		   Long touchCount = Long.parseLong(request.getParameter("touchCount"));
		   Long zoomOutCount = Long.parseLong(request.getParameter("zoomOutCount"));
		   Long scrollCount = Long.parseLong(request.getParameter("scrollCount"));
		   Long keyPressCount = Long.parseLong(request.getParameter("keyPressCount"));
		   String orientation = request.getParameter("orientation");
		   String eventType = request.getParameter("eventType");
		   Long zoomInCount = Long.parseLong(request.getParameter("zoomInCount"));
		   Long screenWidth = Long.parseLong(request.getParameter("screenWidth"));
		   Long screenHeight = Long.parseLong(request.getParameter("screenHeight"));
		   Long touchX = Long.parseLong(request.getParameter("touchX"));
		   Long touchY = Long.parseLong(request.getParameter("touchY"));
		   Long clickX = Long.parseLong(request.getParameter("clickX"));
		   Long clickY = Long.parseLong(request.getParameter("clickY"));
		   String refererURI = request.getHeader("referer");
		   Long zoomCount = Long.parseLong(request.getParameter("zoomCount"));
		   String dataSubmitTime = request.getParameter("dataSubmitTime");
		   
	        UserAgent userAgent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	        OperatingSystem agent = userAgent1.getOperatingSystem();  
	        Browser browser = userAgent1.getBrowser();
	        String deviceName = agent.getDeviceType().getName();
	        
	        if(ipAddress != null) {
	        	String[] proxyList1 = ipAddress.split(",");
	        	for(String prxy : proxyList1) {
	        		String ip = prxy;
	        		proxyList.add(ip);
	        	}
	        }
	        
	        clickDetails = new ClickDetails();
	        
	        clickDetails.setxForwardedFor(ipAddress);
	        clickDetails.setDeviceName(deviceName);
	        clickDetails.setOs(agent.getName());
	        clickDetails.setBrowser(browser.getName());
	        clickDetails.setLeftClickCount(leftClickCount);
	        clickDetails.setRightClickCount(rightClickCount);
	        clickDetails.setDbClickCount(dbClickCount);
	        clickDetails.setKeyPressCount(keyPressCount);
	        clickDetails.setScrollCount(scrollCount);
	        clickDetails.setTouchCount(touchCount);
	        clickDetails.setOrientation(orientation);
	        clickDetails.setEventType(eventType);
	        clickDetails.setZoomOutCount(zoomOutCount);
	        clickDetails.setZoomInCount(zoomInCount);
	        clickDetails.setZoomCount(zoomCount);
	        clickDetails.setScreenWidth(screenWidth);
	        clickDetails.setScreenHeight(screenHeight);
	        clickDetails.setTouchX(touchX);
	        clickDetails.setTouchY(touchY);
	        clickDetails.setClickX(clickX);
	        clickDetails.setClickY(clickY);
	        clickDetails.setRefererURI(refererURI);
	        clickDetails.setLanguages(languages);
	        clickDetails.setLocation(location);
	        clickDetails.setCreationTime(creationTime);
	        clickDetails.setSessionId(sessionId);
	        clickDetails.setLastAccessTime(lastAccessTime);
	        clickDetails.setDataSubmitTime(dataSubmitTime);
	        clickDetails.setProxyList(proxyList);
	        
		 res.setStatus("success");
		 res.setResult(clickDetails);
		 
		 log.debug("**/Exit getHeaderString method/**");
		 
		 return res;
	 }
	
	public String getTime(Long milSec){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("dd:hh:mm:ss");
		 Date date = new Date(milSec);
		 String result = formatter.format(date);
		 return result;
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

package com.aurora.controller;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurora.util.JsonResponce;
import com.btr.proxy.search.ProxySearch;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

@Controller
@RequestMapping("/homePage*")
public class HomePageController {
	 @RequestMapping(method = RequestMethod.GET)
	 public ModelAndView hello() throws Exception {
		 //return new ModelAndView("homePage");
		 return new ModelAndView("PTHome");
	 }
	 
	 @RequestMapping(method = RequestMethod.POST, value="/getHeaderString")
	 public  @ResponseBody JsonResponce getHeaderString(HttpServletRequest request, HttpServletResponse responce) throws Exception {
		 
		 JsonResponce res= new JsonResponce();
		 
/*		 String touchEnX = null;
		 String touchEny = null;
		 Long startTime = null;
		 Long endTime = null;
		 
		 String referer = request.getParameter("referer");
		 String url = request.getParameter("url");
		 String language = request.getParameter("language");
		 String userAgent = request.getParameter("userAgent");
		 String country = request.getParameter("countryName");
		 String screenHeight = request.getParameter("screenHeight");
		 String screenWidth = request.getParameter("screenWidth");
		 String region = request.getParameter("region");
		 String city = request.getParameter("city");
		 String platform = request.getParameter("platform");
		 String touchStX = request.getParameter("tsx");
		 String touchStY = request.getParameter("tsy");
		 String clickX = request.getParameter("clickX");
		 String clickY = request.getParameter("clickY");
		 if(request.getParameter("tex") != null){
			 touchEnX = request.getParameter("tex");
			 touchEny = request.getParameter("tey"); 
			 startTime = Long.parseLong(request.getParameter("startTime"));
			 endTime = Long.parseLong(request.getParameter("endTime"));
		 }*/

		 
		 //String ipAddress = request.getRemoteAddr(); 
		 
		   //is client behind something?
		   String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		   if (ipAddress == null) {  
			   ipAddress = request.getRemoteAddr();  
			   System.out.println("X-FORWARDED-FOR :"+ipAddress);
			   System.out.println("X-Forwarded-Port :"+request.getHeader("X-FORWARDED-PORT"));
			   System.out.println("Host :"+request.getHeader("Host"));
		   }
		 
		   String userAgent = request.getParameter("userAgent");
		   System.out.println("UserAgent : "+ userAgent);
/*		   System.out.println("getAuthType :"+request.getAuthType());
		   System.out.println("getContextPath :"+request.getContextPath());
		   System.out.println("getSession_getCreationTime :"+request.getSession().getCreationTime());
		   System.out.println("getSession_getId :"+request.getSession().getId());
		   System.out.println("getSession_getLastAccessedTime :"+request.getSession().getLastAccessedTime());
		   System.out.println("getLocalAddr :"+request.getLocalAddr());
		   System.out.println("getLocalName :"+request.getLocalName());
		   System.out.println("getHeaderHOST :"+request.getHeader("Host"));
		   System.out.println("getHeaderFROM :"+request.getHeader("From"));
		   System.out.println("getHeaderReferer :"+request.getHeader("Referer"));*/
		   
		   Long creationTime = request.getSession().getCreationTime();
		   Long lastAccessTime = request.getSession().getLastAccessedTime();
		   String location = request.getParameter("location");
		   Long leftClickCount = Long.parseLong(request.getParameter("leftClickCount"));
		   Long rightClickCount = Long.parseLong(request.getParameter("rightClickCount"));
		   Long dbClickCount = Long.parseLong(request.getParameter("dbClickCount"));
		   Long touchCount = Long.parseLong(request.getParameter("touchCount"));
		   Long zoomCount = Long.parseLong(request.getParameter("zoomCount"));
		   Long scrollCount = Long.parseLong(request.getParameter("scrollCount"));
		   Long keyPressCount = Long.parseLong(request.getParameter("keyPressCount"));
		   Boolean scrollEvent = Boolean.parseBoolean(request.getParameter("scrollEvent"));
		   Boolean tapsEvent = Boolean.parseBoolean(request.getParameter("tapsEvent"));
		   String orientation = request.getParameter("orientation");
		   
/*		   System.out.println("Creation Time :"+getTime(creationTime));
		   System.out.println("Last Access Time :"+getTime(lastAccessTime));*/
		   
		   System.out.println("LeftClick Count :"+leftClickCount);
		   System.out.println("RightClick Count :"+rightClickCount);
		   System.out.println("DoubleClick Count :"+dbClickCount);
		   System.out.println("KeyPress Count :"+keyPressCount);
		   System.out.println("Scroll Event :"+scrollEvent);
		   System.out.println("Touch Count :"+touchCount);
		   System.out.println("Orientation :"+orientation);
/*		   System.out.println("Touch Count :"+touchCount);
		   System.out.println("Zoom Count :"+zoomCount);
		   System.out.println("Scroll Count :"+scrollCount);*/
		   
		  // getServerIp();
		  // getCurrentTimeBasedOnCountry(location);

/*		 System.out.println("++++++++++++");
		 System.out.println("IP address :"+ipAddress);
		 System.out.println("///////////////////");*/
		   
	        UserAgent userAgent1 = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
	        OperatingSystem agent = userAgent1.getOperatingSystem();  
	        Browser browser = userAgent1.getBrowser();
		  
	        String deviceName = agent.getDeviceType().getName();
	        
	        System.out.println("Device :"+deviceName);
	        System.out.println("OS :"+agent.getName());
	        System.out.println("Browser :"+browser.getName());
	       // catchProxy();
		 res.setStatus("success");
		 res.setResult(ipAddress);
		 
		 return res;
	 }
	 
	public String getTime(Long milSec){
		 
		 SimpleDateFormat formatter = new SimpleDateFormat("dd:hh:mm:ss");
		 Date date = new Date(milSec);
		 String result = formatter.format(date);
		 
		 return result;
	 }
	
	public void getServerIp() {
		  InetAddress ip;
		  try {
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

		  } catch (UnknownHostException e) {
			e.printStackTrace();
		  }
	}
	
	public void getCurrentTimeBasedOnCountry(String countryAndRegion) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		// Use Madrid's time zone to format the date in
		df.setTimeZone(TimeZone.getTimeZone(countryAndRegion));
		//df.setTimeZone(TimeZone.getTimeZone("Sri Lanka/Western"));
		//df.setTimeZone(TimeZone.getDefault());
		

		System.out.println("Date and time in Madrid: " + df.format(date));
        
	}
	public void catchProxy() {
        try {    
            System.setProperty("java.net.useSystemProxies","true");

            // Use proxy vole to find the default proxy
            ProxySearch ps = ProxySearch.getDefaultProxySearch();
            ps.setPacCacheSettings(32, 1000*60*5);                             
            List l = ps.getProxySelector().select(new URI("http://www.gmail.com/"));

            //... Now just do what the original did ...
            for (Iterator iter = l.iterator(); iter.hasNext(); ) {
                Proxy proxy = (Proxy) iter.next();

                System.out.println("proxy hostname : " + proxy.type());
                InetSocketAddress addr = (InetSocketAddress)
                    proxy.address();

                if(addr == null) {    
                    System.out.println("No Proxy");    
                } else {
                    System.out.println("proxy hostname : " + 
                            addr.getHostName());

                    System.out.println("proxy port : " + 
                            addr.getPort());    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
}

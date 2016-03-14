package com.aurora.serviceImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.service.AnalyseUserService;
import com.aurora.service.ProxyDetailsService;
import com.aurora.service.ReadPropertyFile;
import com.aurora.util.ProxyDetailsDTO;
import com.aurora.util.UserDetailsDTO;

@Service("analyseUserService")
public class AnalyseUserServiceImpl implements AnalyseUserService {

	private ReadPropertyFile readPropertyFile = null;
	private ProxyDetailsService proxyDetailsService = null;
	
	@Autowired
	public void setReadPropertyFile(ReadPropertyFile readPropertyFile) {
		this.readPropertyFile = readPropertyFile;
	}
	
	@Autowired
	public void setProxyDetailsService(ProxyDetailsService proxyDetailsService) {
		this.proxyDetailsService = proxyDetailsService;
	}
	
	public Map<String, Map<String, Integer>> analyseUser(List<UserDetailsDTO> dto) {
		
		Map<String,Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
		Map<String, Integer> analyseUserByBrowserId = analyseUserByBrowserId(dto);
		Map<String, Integer> analyseUserByTimeZone = analyseUserByTimeZone(dto);
		Map<String, Integer> analyseUserByOS = analyseUserByOS(dto);
		Map<String, Integer> analyseUserByEventSquence = analyseUserByEventSquence(dto);
		Map<String, Integer> analyseUserByProxies = analyseUserByProxies(dto);
		Map<String, Integer> analyseUserByLocation = analyseUserByLocation(dto);
		
		map.put("BROWSER_ID",analyseUserByBrowserId);
		map.put("TIME_ZONE",analyseUserByTimeZone);
		map.put("OS_NAME",analyseUserByOS);
		map.put("EVENT_SEQUENCE",analyseUserByEventSquence);
		map.put("PROXY_COUNT",analyseUserByProxies);
		map.put("USER_LOCATION",analyseUserByLocation);
		
		return map;
	}
	
	public Map<String, Integer> analyseUserByLocation(List<UserDetailsDTO> dto){
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<String> provedLocationList = new ArrayList<String>();
		int positiveCount = 0;
		int negetiveCount = 0;
		
		for(UserDetailsDTO dt : dto) {
			if(dt.getPid().size() > 0){
				List<String> ls = getCountryListByTimeOffset(dt.getTimeZone());
				provedLocationList = compareIPLocationAndTimeOffsetLocation(dt.getBid(),ls);
				if(provedLocationList.size() > 0 ) {
					positiveCount += 1;
				} else {
					negetiveCount += 1;
				}
			} 
		}
		map.put("NEGETIVE", negetiveCount);
		map.put("POSITIVE", positiveCount);
		return map;
	}
	
	public Map<String, Integer> analyseUserByProxies(List<UserDetailsDTO> dto){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int positiveCount = 0;
		int negetiveCount = 0;
		int numOfProxies = dto.get(0).getPid().size();
		
		for(UserDetailsDTO dt : dto) {
			if(dt.getPid().size() == numOfProxies){
				positiveCount +=1;
			} else {
				negetiveCount += 1;
			}
			numOfProxies = dt.getPid().size();
		}
		map.put("NEGETIVE", negetiveCount);
		map.put("POSITIVE", positiveCount);
		return map;
	}
	
	public Map<String, Integer> analyseUserByEventSquence(List<UserDetailsDTO> dto){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int positiveCount = 0;
		int negetiveCount = 0;
		
		for(int x = 0; x<dto.size(); x++) {
			if((x+1) <dto.size()){
				long timeDif = getTimeDifference(dto.get(x).getEventTriggeredTime(),dto.get(x+1).getEventTriggeredTime());
				
				if(timeDif > 0) {
					positiveCount += 1;
				} else {
					negetiveCount +=1;
				}
			}
		}
		map.put("NEGETIVE", negetiveCount);
		map.put("POSITIVE", positiveCount);
		return map;
	}
	
	public Map<String, Integer> analyseUserByOS(List<UserDetailsDTO> dto){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int positiveCount = 0;
		int negetiveCount = 0;
		String os  = null;
		os = dto.get(0).getOsName();
		
		for(UserDetailsDTO dt : dto) {
			if(dt.getOsName().equalsIgnoreCase(os)){
				positiveCount += 1;
			} else {
				negetiveCount += 1;
			}
			os = dt.getOsName();
		}
		map.put("NEGETIVE", negetiveCount);
		map.put("POSITIVE", positiveCount);
		return map;
	}
	
	public Map<String, Integer> analyseUserByTimeZone(List<UserDetailsDTO> dto){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int positiveCount = 0;
		int negetiveCount = 0;
		String timeZone  = null;
		timeZone = dto.get(0).getTimeZone();
		
		for(UserDetailsDTO dt : dto) {
			if(dt.getTimeZone().equalsIgnoreCase(timeZone)){
				positiveCount += 1;
			} else {
				negetiveCount += 1;
			}
			timeZone = dt.getTimeZone();
		}
		map.put("NEGETIVE", negetiveCount);
		map.put("POSITIVE", positiveCount);
		return map;
	}
	public Map<String, Integer> analyseUserByBrowserId(List<UserDetailsDTO> dto){
		Map<String, Integer> map = new HashMap<String, Integer>();
		int positiveCount = 0;
		int negetiveCount = 0;
		Long userAgentId  = null;
		userAgentId = dto.get(0).getUserAgentId();
		
		for(UserDetailsDTO dt : dto) {
			if(dt.getUserAgentId().compareTo(userAgentId) == 0){
				positiveCount += 1;
			} else {
				negetiveCount += 1;
			}
			userAgentId = dt.getUserAgentId();
		}
		map.put("NEGETIVE", negetiveCount);
		map.put("POSITIVE", positiveCount);
		return map;
	}
	public long getTimeDifference(String time1, String time2){
		DateTime crDate = getDate(time1);
		DateTime prDate = getDate(time2);
		
		long timeDiffer  = (crDate.getMillis() - prDate.getMillis());
		return timeDiffer;
	}
	public DateTime getDate(String date1){
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime date = null;
		date = fmt.parseDateTime(date1);
        return date;
	}
	
	public Map<String, String> getTimeCountryMap(){
		Map<String,String> map = readPropertyFile.getCountryTimeMap();
/*		for (Map.Entry<String, String> entry : map.entrySet()){
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}*/
		return map;
	}
	public List<String> getCountryListByTimeOffset(String offset){
		List<String> list =  new ArrayList<String>();
		Map<String,String> map = getTimeCountryMap();
		
		for (Map.Entry<String, String> entry : map.entrySet()){
			String val = entry.getValue();
			if(val.equalsIgnoreCase(offset)){
				list.add(entry.getKey());
			}
		}
		return list;
	}
	
	public List<String> compareIPLocationAndTimeOffsetLocation(Long bid,List<String> locationListByOffset){
		List<ProxyDetailsDTO> proxyDetailsList = proxyDetailsService.getProxyDetails(bid);
		List<String> proxyLocationList = new ArrayList<String>();
		List<String> positiveLocationList = new ArrayList<String>();
		
		for(ProxyDetailsDTO dto : proxyDetailsList){
			String name = dto.getCountryName().replaceAll(" ", "_");
			proxyLocationList.add(name);
		}
		
		for(String locationName : proxyLocationList){
			if(locationListByOffset.contains(locationName)){
				positiveLocationList.add(locationName);
			}
		}
		return positiveLocationList;
	}
}

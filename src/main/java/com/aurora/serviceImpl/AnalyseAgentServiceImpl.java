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

import com.aurora.dao.SessionDetailsDao;
import com.aurora.service.AnalyseAgentService;
import com.aurora.util.SessionTimeOutDTO;
import com.aurora.util.UserDetailsDTO;

@Service("analyseAgentService")
public class AnalyseAgentServiceImpl implements AnalyseAgentService {

	private SessionDetailsDao sessionDetailsDao = null;
	
	@Autowired
	public void setSessionDetailsDao(SessionDetailsDao sessionDetailsDao) {
		this.sessionDetailsDao = sessionDetailsDao;
	}
	
	public Map<String, Object> getEventCount(List<UserDetailsDTO> dto) {
		Map<String, Object> map = new HashMap<String, Object>();

		int TOTAL_COUNT = dto.size();
		long timeDiffer = getTimeDifference(dto.get(0).getZoneDateTime(), dto.get(dto.size()-1).getZoneDateTime()); // staying time
		int USER_STAYING_TIME = (int) (timeDiffer/60000);
		int AVG_TIME_TWO_EVENT = 0;
		long timeDiffer1 = 0;
		long MAX_IDLE_TIME = 0;
		String LAST_ACCESS_TIME = null;
		String FIRST_ACCESS_TIME = null;
		int NUM_OF_SESSION_TIMEOUT = 0;
		
		for(int x = 0; x<dto.size(); x++) { //getting time difference each consecutive events
			if((x+1) <dto.size()){
				long mxTD = getTimeDifference(dto.get(x).getZoneDateTime(),dto.get(x+1).getZoneDateTime());
				timeDiffer1 += mxTD;
				
				if(mxTD > MAX_IDLE_TIME) {
					MAX_IDLE_TIME = mxTD;
				}
			}
		}
		NUM_OF_SESSION_TIMEOUT = getSessionTimeOutCount(dto);
		LAST_ACCESS_TIME = dto.get(0).getZoneDateTime();
		FIRST_ACCESS_TIME = dto.get(dto.size()-1).getZoneDateTime();
		AVG_TIME_TWO_EVENT = (int)(timeDiffer1/TOTAL_COUNT)/1000; 
		
		map.put("USER_EVENT_COUNT", getUserEventCount(dto));
		map.put("MAX_IDLE_TIME", (int)MAX_IDLE_TIME/60000);
		map.put("TOTAL_COUNT", TOTAL_COUNT);
		map.put("USER_STAYING_TIME", USER_STAYING_TIME);
		map.put("AVG_TIME_TWO_EVENT", AVG_TIME_TWO_EVENT);
		map.put("LAST_ACCESS_TIME", LAST_ACCESS_TIME);
		map.put("FIRST_ACCESS_TIME", FIRST_ACCESS_TIME);
		map.put("NUM_OF_SESSION_TIMEOUT", NUM_OF_SESSION_TIMEOUT);

		return map;
	}
	
	public int getSessionTimeOutCount(List<UserDetailsDTO> dto){
		int listSize = 0;
		Long sid = dto.get(0).getSid();
		List<SessionTimeOutDTO> dt = sessionDetailsDao.getSessionIDListBySID(sid);
		
		if(dt != null){
			listSize = dt.size();
		}
		return listSize;
	}
	public long getTimeDifference(String time1, String time2){
		DateTime crDate = getDate(time1);
		DateTime prDate = getDate(time2);
		
		long timeDiffer  = (crDate.getMillis() - prDate.getMillis());
		return timeDiffer;
	}
	
	public Boolean comapareTime(String curTime, String preTime){
		DateTime crDate = getDate(curTime);
		DateTime prDate = getDate(preTime);
		Boolean status = false;
		Long timeDiffer  = (crDate.getMillis() - prDate.getMillis());
		
		if(timeDiffer < 150) {
			status = true;
		} 
		return status;
	}
	
	public DateTime getDate(String date1){
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime date = null;
		date = fmt.parseDateTime(date1);
        return date;
	}

	public String deviceIdenticication(List<UserDetailsDTO> dto) {
		List<String> list = new ArrayList<String>();
		String deviceType = "Not Yet";
		int desktopCount = 0;
		int mobileCount = 0; 
		
		String deviceTypeByEvents = deviceIdentifyByEvents(getUserEventCount(dto));
		String deviceTypeByOrientation = deviceIdentifyByOrientation(dto);
		String deviceTypeByDimention = deviceIdentifyByDimention(dto);

		list.add(deviceTypeByEvents);
		list.add(deviceTypeByOrientation);
		list.add(deviceTypeByDimention);
		
		for(String type : list) {
			if(type.equalsIgnoreCase("Desktop")) {
				desktopCount += 1;
			}
			if(type.equalsIgnoreCase("Mobile")) {
				mobileCount += 1;
			}
		}
		deviceType = getDeviceTypeByAttribute(desktopCount, mobileCount);
 		return deviceType;
	}
	public String deviceIdentifyByDimention(List<UserDetailsDTO> dto){
		String deviceType = "Not Yet";
		int desktopDevice = 0;
		int mobileDevice = 0;
		
		for(UserDetailsDTO dt : dto){
			if(Integer.parseInt(dt.getScreenHeight()) != -100) {
				if(Integer.parseInt(dt.getScreenHeight()) > 767 && Integer.parseInt(dt.getScreenWidth()) > 1023){
					desktopDevice += 1;
				} else{
					mobileDevice += 1;
				}	
			} else {
				System.out.println("FRAUD");
			}

		}
		deviceType = getDeviceTypeByAttribute(desktopDevice, mobileDevice);
		return deviceType;
	}
	public String deviceIdentifyByOrientation(List<UserDetailsDTO> dto){
		String deviceType = "Not Yet";
		int desktopDevice = 0;
		int mobileDevice = 0;
		
		for(UserDetailsDTO dt : dto){
			if(dt.getOrientation().equalsIgnoreCase("-1")){
				desktopDevice += 1;
			} else{
				mobileDevice += 1;
			}
		}
		deviceType = getDeviceTypeByAttribute(desktopDevice, mobileDevice);
		return deviceType;
	}
	public String deviceIdentifyByEvents(Map<String, Integer> map){
		int desktopDevice = 0;
		int mobileDevice = 0;
		
		if(map.get("RC_COUNT").intValue() > 0) {
			desktopDevice += 1;
		} 
		if(map.get("LC_COUNT").intValue() > 0) {
			desktopDevice += 1;
		}
		if(map.get("DC_COUNT").intValue() > 0) {
			desktopDevice += 1;
		}
		if(map.get("TS_COUNT").intValue() > 0) {
			mobileDevice += 1;
		}
		if(map.get("TM_COUNT").intValue() > 0) {
			mobileDevice += 1;
		}
		if(map.get("TZE_COUNT").intValue() > 0) {
			mobileDevice += 1;
		}
		if(map.get("STZE_COUNT").intValue() > 0) {
			mobileDevice += 1;
		}
		
		String deviceType = "Not Yet";
		deviceType = getDeviceTypeByAttribute(desktopDevice, mobileDevice);
		
		return deviceType;
	}
	
	public Map<String, Integer> getUserEventCount(List<UserDetailsDTO> dto){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int RC_COUNT = 0;
		int LC_COUNT = 0;
		int DC_COUNT = 0;
		int KP_COUNT = 0;
		int TS_COUNT = 0 ;
		int TM_COUNT = 0;
		int TZE_COUNT = 0;
		int STZE_COUNT = 0;
		int RF_COUNT = 0;
		int SE_COUNT = 0;
		int P_COUNT_TM = 0;
		int P_COUNT_TS = 0;
		int P_COUNT_TZE = 0;
		int P_COUNT_STZE = 0;
		int P_COUNT_LC = 0;
		int IMG_COUNT_TM = 0;
		int IMG_COUNT_TS = 0;
		int IMG_COUNT_TZE = 0;
		int IMG_COUNT_STZE = 0;
		int IMG_COUNT_LC = 0;
		int TOTAL_IMG_COUNT = 0;
		int TOTAL_P_COUNT = 0;
		
		for(UserDetailsDTO dt : dto){
			if(dt.getEventName().equalsIgnoreCase("LC")){
				LC_COUNT += 1;
				if(dt.getTagName().equalsIgnoreCase("P")){
					P_COUNT_LC += 1;
				} else if(dt.getTagName().equalsIgnoreCase("IMG")){
					IMG_COUNT_LC += 1;
				}
			} else if(dt.getEventName().equalsIgnoreCase("RC")) {
				RC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("DC")) {
				DC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("KP")) {
				KP_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("TS")) {
				TS_COUNT += 1;
				if(dt.getTagName().equalsIgnoreCase("P")){
					P_COUNT_TS += 1;
				} else if(dt.getTagName().equalsIgnoreCase("IMG")){
					IMG_COUNT_TS += 1;
				}
			} else if(dt.getEventName().equalsIgnoreCase("TM")) {
				TM_COUNT += 1;
				if(dt.getTagName().equalsIgnoreCase("P")){
					P_COUNT_TM += 1;
				} else if(dt.getTagName().equalsIgnoreCase("IMG")){
					IMG_COUNT_TM += 1;
				}
			} else if(dt.getEventName().equalsIgnoreCase("TZE")) {
				TZE_COUNT += 1;
				if(dt.getTagName().equalsIgnoreCase("P")){
					P_COUNT_TZE += 1;
				} else if(dt.getTagName().equalsIgnoreCase("IMG")){
					IMG_COUNT_TZE += 1;
				}
			} else if(dt.getEventName().equalsIgnoreCase("STZE")) {
				STZE_COUNT += 1;
				if(dt.getTagName().equalsIgnoreCase("P")){
					P_COUNT_STZE += 1;
				} else if(dt.getTagName().equalsIgnoreCase("IMG")){
					IMG_COUNT_STZE += 1;
				}
			} else if(dt.getEventName().equalsIgnoreCase("RF")) {
				RF_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("SE")) {
				SE_COUNT += 1;
			}
		}
		
		TOTAL_IMG_COUNT = IMG_COUNT_TM + IMG_COUNT_TS + IMG_COUNT_TZE + IMG_COUNT_STZE + IMG_COUNT_STZE + IMG_COUNT_LC;
		TOTAL_P_COUNT = P_COUNT_TM + P_COUNT_TS + P_COUNT_TZE + P_COUNT_STZE + P_COUNT_LC;
		
		
		map.put("RC_COUNT", RC_COUNT);
		map.put("LC_COUNT", LC_COUNT);
		map.put("DC_COUNT", DC_COUNT);
		map.put("KP_COUNT", KP_COUNT);
		map.put("TS_COUNT", TS_COUNT);
		map.put("TM_COUNT", TM_COUNT);
		map.put("TZE_COUNT", TZE_COUNT);
		map.put("STZE_COUNT", STZE_COUNT);
		map.put("RF_COUNT", RF_COUNT);
		map.put("SE_COUNT", SE_COUNT);
		map.put("P_COUNT_TM", P_COUNT_TM);
		map.put("P_COUNT_TS", P_COUNT_TS);
		map.put("P_COUNT_TZE", P_COUNT_TZE);
		map.put("P_COUNT_STZE", P_COUNT_STZE);
		map.put("P_COUNT_LC", P_COUNT_LC);
		map.put("IMG_COUNT_TS", IMG_COUNT_TS);
		map.put("IMG_COUNT_TM", IMG_COUNT_TM);
		map.put("IMG_COUNT_TZE", IMG_COUNT_TZE);
		map.put("IMG_COUNT_STZE", IMG_COUNT_STZE);
		map.put("IMG_COUNT_LC", IMG_COUNT_LC);
		map.put("TOTAL_IMG_COUNT", TOTAL_IMG_COUNT);
		map.put("TOTAL_P_COUNT", TOTAL_P_COUNT);
		
		return map;
		
	}
	
	public String getDeviceTypeByAttribute(int desktopDevice, int mobileDevice){
		String deviceType = "Not Yet";
		if(desktopDevice > 0 || mobileDevice > 0){
			if(desktopDevice > 0 && mobileDevice > 0){
				deviceType = "Fraud Device";
			} else {
				if(desktopDevice > 0 ) {
					deviceType = "Desktop";
				} else if(mobileDevice > 0){
					deviceType =  "Mobile";
				}
			}	
		}
		return deviceType;
	}
}

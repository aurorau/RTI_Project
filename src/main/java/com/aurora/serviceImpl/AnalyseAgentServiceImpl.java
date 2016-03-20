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
		
/*		if((MAX_IDLE_TIME/60000) > 0) {
			MAX_IDLE_TIME = (int)(MAX_IDLE_TIME/60000);
		} else {
			MAX_IDLE_TIME = (int)(MAX_IDLE_TIME/1000);
		}*/
		
		NUM_OF_SESSION_TIMEOUT = getSessionTimeOutCount(dto);
		LAST_ACCESS_TIME = dto.get(0).getZoneDateTime();
		FIRST_ACCESS_TIME = dto.get(dto.size()-1).getZoneDateTime();
		AVG_TIME_TWO_EVENT = (int)(timeDiffer1/TOTAL_COUNT)/1000; 
		
		map.put("USER_EVENT_COUNT", getUserEventCount(dto));
		map.put("MAX_IDLE_TIME", (int)(MAX_IDLE_TIME/60000));
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
		int INPT_COUNT_TS = 0;
		int INPT_COUNT_LC = 0;
		int A_COUNT_TS = 0;
		int A_COUNT_LC = 0;
		int BTN_COUNT_TS = 0;
		int BTN_COUNT_LC = 0;
		int SELECT_COUNT_TS = 0;
		int SELECT_COUNT_LC = 0;
		int OPTION_COUNT_TS = 0;
		int OPTION_COUNT_LC = 0;
		int TOTAL_INPT_COUNT = 0;
		int TOTAL_A_COUNT = 0;
		int TOTAL_BTN_COUNT = 0;
		int TOTAL_SELECT_COUNT = 0;
		int TOTAL_OPTION_COUNT = 0;
		int TYPE_COUNT_KP = 0;
		int SE_COUNT_BY_KP = 0;
		
		for(UserDetailsDTO dt : dto){
			if(dt.getEventName().equalsIgnoreCase("LC")){
				LC_COUNT += 1;
				Map<String,Integer> mapLC = getTagCountForEvent(dt);
				P_COUNT_LC += mapLC.get("pCount");
				IMG_COUNT_LC += mapLC.get("imgCount");
				INPT_COUNT_LC += mapLC.get("inputCount");
				A_COUNT_LC += mapLC.get("anchorCount");
				BTN_COUNT_LC += mapLC.get("buttonCount");
				SELECT_COUNT_LC += mapLC.get("selectCount");
				OPTION_COUNT_LC += mapLC.get("optionCount");
				
			} else if(dt.getEventName().equalsIgnoreCase("RC")) {
				RC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("DC")) {
				DC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("KP")) {
				KP_COUNT += 1;
				if(dt.getTagName().equalsIgnoreCase("INPUT")){
					TYPE_COUNT_KP += 1;
				} else if(dt.getTagName().equalsIgnoreCase("BODY")){
					SE_COUNT_BY_KP += 1;
				}
			} else if(dt.getEventName().equalsIgnoreCase("TS")) {
				TS_COUNT += 1;
				Map<String,Integer> mapTS = getTagCountForEvent(dt);
				P_COUNT_TS += mapTS.get("pCount");
				IMG_COUNT_TS += mapTS.get("imgCount");
				INPT_COUNT_TS += mapTS.get("inputCount");
				A_COUNT_TS += mapTS.get("anchorCount");
				BTN_COUNT_TS += mapTS.get("buttonCount");
				SELECT_COUNT_TS += mapTS.get("selectCount");
				OPTION_COUNT_TS += mapTS.get("optionCount");
				
			} else if(dt.getEventName().equalsIgnoreCase("TM")) {
				TM_COUNT += 1;
				Map<String,Integer> mapTM = getTagCountForEvent(dt);
				P_COUNT_TM += mapTM.get("pCount");
				IMG_COUNT_TM += mapTM.get("imgCount");
			} else if(dt.getEventName().equalsIgnoreCase("TZE")) {
				TZE_COUNT += 1;
				Map<String,Integer> mapTZE = getTagCountForEvent(dt);
				P_COUNT_TZE += mapTZE.get("pCount");
				IMG_COUNT_TZE += mapTZE.get("imgCount");
			} else if(dt.getEventName().equalsIgnoreCase("STZE")) {
				STZE_COUNT += 1;
				Map<String,Integer> mapSTZE = getTagCountForEvent(dt);
				P_COUNT_STZE += mapSTZE.get("pCount");
				IMG_COUNT_STZE += mapSTZE.get("imgCount");
			} else if(dt.getEventName().equalsIgnoreCase("RF")) {
				RF_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("SE")) {
				SE_COUNT += 1;
			}
		}
		
		TOTAL_IMG_COUNT = IMG_COUNT_TM + IMG_COUNT_TS + IMG_COUNT_TZE + IMG_COUNT_STZE + IMG_COUNT_STZE + IMG_COUNT_LC;
		TOTAL_P_COUNT = P_COUNT_TM + P_COUNT_TS + P_COUNT_TZE + P_COUNT_STZE + P_COUNT_LC;
		TOTAL_INPT_COUNT = INPT_COUNT_TS + INPT_COUNT_LC;
		TOTAL_A_COUNT = A_COUNT_TS + A_COUNT_LC;
		TOTAL_BTN_COUNT = BTN_COUNT_TS + BTN_COUNT_LC;
		TOTAL_SELECT_COUNT = SELECT_COUNT_TS + SELECT_COUNT_LC;
		TOTAL_OPTION_COUNT = OPTION_COUNT_TS + OPTION_COUNT_LC;
		
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
		map.put("INPT_COUNT_TS", INPT_COUNT_TS);
		map.put("A_COUNT_TS", A_COUNT_TS);
		map.put("BTN_COUNT_TS", BTN_COUNT_TS);
		map.put("SELECT_COUNT_TS", SELECT_COUNT_TS);
		map.put("OPTION_COUNT_TS", OPTION_COUNT_TS);
		map.put("INPT_COUNT_LC", INPT_COUNT_LC);
		map.put("A_COUNT_LC", A_COUNT_LC);
		map.put("BTN_COUNT_LC", BTN_COUNT_LC);
		map.put("SELECT_COUNT_LC", SELECT_COUNT_LC);
		map.put("OPTION_COUNT_LC", OPTION_COUNT_LC);
		map.put("TOTAL_INPT_COUNT", TOTAL_INPT_COUNT);
		map.put("TOTAL_A_COUNT", TOTAL_A_COUNT);
		map.put("TOTAL_BTN_COUNT", TOTAL_BTN_COUNT);
		map.put("TOTAL_SELECT_COUNT", TOTAL_SELECT_COUNT);
		map.put("TOTAL_OPTION_COUNT", TOTAL_OPTION_COUNT);
		map.put("TYPE_COUNT_KP", TYPE_COUNT_KP);
		
		return map;
		
	}
	
	public Map<String, Integer> getTagCountForEvent(UserDetailsDTO dt){
		
		Map<String, Integer> map =  new HashMap<String, Integer>();
		
		int pCount = 0;
		int imgCount = 0;
		int inputCount = 0;
		int selectCount = 0;
		int anchorCount = 0;
		int buttonCount = 0;
		int optionCount = 0;
		
		if(dt.getTagName().equalsIgnoreCase("P")){
			pCount += 1;
		} else if(dt.getTagName().equalsIgnoreCase("IMG")){
			imgCount += 1;
		} else if(dt.getTagName().equalsIgnoreCase("INPUT")){
			inputCount += 1;
		} else if(dt.getTagName().equalsIgnoreCase("SELECT")){
			selectCount += 1;
		} else if(dt.getTagName().equalsIgnoreCase("A")){
			anchorCount += 1;
		} else if(dt.getTagName().equalsIgnoreCase("BUTTON")){
			buttonCount += 1;
		} else if(dt.getTagName().equalsIgnoreCase("OPTION")){
			optionCount += 1;
		}
		
		map.put("pCount", pCount);
		map.put("imgCount", imgCount);
		map.put("inputCount", inputCount);
		map.put("selectCount", selectCount);
		map.put("anchorCount", anchorCount);
		map.put("buttonCount", buttonCount);
		map.put("optionCount", optionCount);
		
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

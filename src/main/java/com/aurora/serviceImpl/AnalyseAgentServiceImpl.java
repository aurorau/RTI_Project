package com.aurora.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.aurora.service.AnalyseAgentService;
import com.aurora.util.UserDetailsDTO;

@Service("analyseAgentService")
public class AnalyseAgentServiceImpl implements AnalyseAgentService {

	public Map<String, Integer> getEventCount(List<UserDetailsDTO> dto) {
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
		int TSE_COUNT = 0;
		int DSE_COUNT = 0;
		int TOTAL_COUNT = dto.size();
		int USER_STAYING_TIME = (int) getTimeDifference(dto.get(0).getZoneDateTime(), dto.get(dto.size()-1).getZoneDateTime());
		
		for(UserDetailsDTO dt : dto){
			if(dt.getEventName().equalsIgnoreCase("LC")){
				LC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("RC")) {
				RC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("DC")) {
				DC_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("KP")) {
				KP_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("TS")) {
				TS_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("TM")) {
				TM_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("TZE")) {
				TZE_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("STZE")) {
				STZE_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("RF")) {
				RF_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("TSE")) {
				TSE_COUNT += 1;
			} else if(dt.getEventName().equalsIgnoreCase("DSE")) {
				DSE_COUNT += 1;
			}
		}
		map.put("RC_COUNT", RC_COUNT);
		map.put("LC_COUNT", LC_COUNT);
		map.put("DC_COUNT", DC_COUNT);
		map.put("KP_COUNT", KP_COUNT);
		map.put("TS_COUNT", TS_COUNT);
		map.put("TM_COUNT", TM_COUNT);
		map.put("TZE_COUNT", TZE_COUNT);
		map.put("STZE_COUNT", STZE_COUNT);
		map.put("RF_COUNT", RF_COUNT);
		map.put("TSE_COUNT", TSE_COUNT);
		map.put("DSE_COUNT", DSE_COUNT);
		map.put("TOTAL_COUNT", TOTAL_COUNT);
		map.put("USER_STAYING_TIME", USER_STAYING_TIME);
		
		return map;
/*		int swapTouchZoomCount = 0;
		for(int x = 0; x<dto.size(); x++) {
			if((x+1) <dto.size() && dto.get(x).getEventName().equalsIgnoreCase("STZE")){
				if(dto.get(x+1).getEventName().equalsIgnoreCase("STZE")) {
					if(!comapareTime(dto.get(x).getZoneDateTime(),dto.get(x+1).getZoneDateTime())){
						swapTouchZoomCount += 1;
					}
				} else {
					swapTouchZoomCount += 1;
				}	
			}

		}
		map.put("swapTouchZoomCount", swapTouchZoomCount);
		return map;*/
	}

	public long getTimeDifference(String time1, String time2){
		DateTime crDate = getDate(time1);
		DateTime prDate = getDate(time2);
		
		long timeDiffer  = (crDate.getMillis() - prDate.getMillis());
		long timeInMini = (timeDiffer/60000);
		return timeInMini;
	}
	
	public Boolean comapareTime(String curTime, String preTime){
		DateTime crDate = getDate(curTime);
		DateTime prDate = getDate(preTime);
		Boolean status = false;
		Long timeDiffer  = (crDate.getMillis() - prDate.getMillis());
		
		System.out.println("Time Differ :"+timeDiffer);
		
		if(timeDiffer < 150) {
			status = true;
		} 
		return status;
	}
	
	public DateTime getDate(String date1){
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
		DateTime date = null;
		date = fmt.parseDateTime(date1);
        //Date dateOut = date;
        System.out.println("Date :"+date.getMillis());
        return date;
	}
	
	public Boolean detectTouchZoom(String eventName1, String eventName2){
		if(eventName1.equalsIgnoreCase(eventName2)){
			return true;
		} else {
			return false;
		}
	}
}

package com.aurora.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurora.dao.EventDetailsDao;
import com.aurora.model.EventDetails;
import com.aurora.service.EventDetailsService;
import com.aurora.util.Constants;

@Service("eventDetailsService")
public class EventDetailsImpl implements EventDetailsService {

	private EventDetailsDao  eventDetailsDao = null;
	
	@Autowired
	public void setEventDetailsDao(EventDetailsDao eventDetailsDao) {
		this.eventDetailsDao = eventDetailsDao;
	}
	
	public String saveEventDetails(EventDetails eventDetails) {
		String res = Constants.FAIL;
		
		try {
			eventDetailsDao.saveEventDetails(eventDetails);
			res = Constants.SUCCESS;
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

}

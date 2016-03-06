package com.aurora.service;

import javax.servlet.http.HttpServletRequest;
import com.aurora.model.DeviceDetails;
import com.aurora.model.SessionDetails;

public interface EventDetailsService {
	public String saveEventDetails(HttpServletRequest request, SessionDetails sessionDetails,DeviceDetails deviceDetails);
}

package com.aurora.dao;

import java.util.List;
import java.util.Map;

import com.aurora.model.BrowserDetails;
import com.aurora.util.BrowserAndDeviceDetailsDTO;

public interface BrowserDetailsDao {
	public void saveBrowserDetails(BrowserDetails browserDetails) throws Exception;
	public List<BrowserAndDeviceDetailsDTO> getBrowserDetails()throws Exception;
	public BrowserDetails getBrowserById(Long bid)throws Exception;
}

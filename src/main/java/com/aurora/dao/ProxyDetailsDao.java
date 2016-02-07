package com.aurora.dao;

import java.util.List;

import com.aurora.model.ProxyDetails;
import com.aurora.util.ProxyDetailsDTO;

public interface ProxyDetailsDao {
	public void saveProxyDetailsService(ProxyDetails proxyDetails) throws Exception;

	public List<ProxyDetailsDTO> getProxyDetails(Long bid);
}

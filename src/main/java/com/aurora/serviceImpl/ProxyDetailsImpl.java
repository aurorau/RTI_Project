package com.aurora.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurora.dao.ProxyDetailsDao;
import com.aurora.model.ProxyDetails;
import com.aurora.service.ProxyDetailsService;
import com.aurora.util.Constants;

@Service("proxyDetailsService")
public class ProxyDetailsImpl implements ProxyDetailsService {

	private ProxyDetailsDao proxyDetailsDao = null;
	
	@Autowired
	public void setProxyDetailsDao(ProxyDetailsDao proxyDetailsDao) {
		this.proxyDetailsDao = proxyDetailsDao;
	}
	
	public String saveProxyDetailsService(ProxyDetails proxyDetails) {
		String res = Constants.FAIL;
		
		try {
			proxyDetailsDao.saveProxyDetailsService(proxyDetails);
			res = Constants.SUCCESS;
		} catch(Exception e) {
			res = Constants.ERROR;
		}
		return res;
	}

}

package com.aurora.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aurora.dao.ProxyDetailsDao;
import com.aurora.model.BrowserDetails;
import com.aurora.model.ProxyDetails;
import com.aurora.service.ProxyDetailsService;
import com.aurora.util.Constants;
import com.aurora.util.ProxyDetailsDTO;

@Service("proxyDetailsService")
public class ProxyDetailsImpl implements ProxyDetailsService {

	private ProxyDetailsDao proxyDetailsDao = null;
	
	@Autowired
	public void setProxyDetailsDao(ProxyDetailsDao proxyDetailsDao) {
		this.proxyDetailsDao = proxyDetailsDao;
	}
	
	@Transactional
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

	@Transactional
	public List<ProxyDetailsDTO> getProxyDetails(Long bid) {
		List<ProxyDetailsDTO>  list =null;
		try {
			list = proxyDetailsDao.getProxyDetails(bid);
		}catch (Exception e){
			System.out.println("Error :"+e);
		}
		return list;
	}

}

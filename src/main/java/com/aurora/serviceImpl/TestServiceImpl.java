package com.aurora.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurora.dao.TestDao;
import com.aurora.model.Person;
import com.aurora.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	TestDao testDaoImpl = null;
	
	 @Autowired
	 public void setTestDao(TestDao testDaoImpl) {
		 this.testDaoImpl = testDaoImpl;
	 }

	public void testSave(Person person) {
		try {
			testDaoImpl.personSave(person);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

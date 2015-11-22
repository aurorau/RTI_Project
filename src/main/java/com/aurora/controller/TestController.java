package com.aurora.controller;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aurora.daoImpl.TestDaoImpl;
import com.aurora.model.Person;
import com.aurora.service.TestService;

@Controller
@RequestMapping("/testController")
public class TestController {

	 TestService testService1 = null;
	 
	 @Autowired
	 public void setTestService(TestService testService) {
		 this.testService1 = testService;
	 }
	
	 @RequestMapping(method = RequestMethod.POST, value = "/saveTest")
	 public void save(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 
		 String testName = ServletRequestUtils.getStringParameter(request, "name");
		 
		 Person person = new Person();
		 person.setName(testName);
		 
		 testService1.testSave(person);
		 
/*		
		 TestDaoImpl pdi = new TestDaoImpl();
		 pdi.personSave(person);*/
	 }
}

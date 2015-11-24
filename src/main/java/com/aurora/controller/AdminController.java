package com.aurora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*@Controller
@RequestMapping("/adminPage*")*/
public class AdminController {
	 @RequestMapping(method = RequestMethod.GET)
	 public ModelAndView hello() throws Exception {
		 return new ModelAndView("adminPage");
	 }
}

package com.csc.fresher.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
/**
 * This is a controller when user login the system will create new session
 * 
 * @author vuhuynh
 * 
 */
@Controller
@SessionAttributes("loginSession")
public class LoginSessionByCustomer {
	
	/**
	 * create new session name loginSession
	 * @param request
	 * @return
	 * 
	 * @author vuhuynh
	 */
	@RequestMapping(value="/setLoginSession", method=RequestMethod.POST)	
	public ModelAndView setSessionLogin(@RequestParam String loginId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("loginSession", loginId);
		
		modelAndView.setViewName("forward:/home.html");
		return modelAndView;
	}
}

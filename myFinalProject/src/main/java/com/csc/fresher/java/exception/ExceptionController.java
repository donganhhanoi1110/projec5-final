package com.csc.fresher.java.exception;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 

 
@ControllerAdvice
public class ExceptionController {
 
	@RequestMapping(value = "/{type:.+}", method = RequestMethod.GET)
	public ModelAndView getPages(@PathVariable("type") String type)
		throws Exception {
 
	  if ("error".equals(type)) {
		// go handleCustomException
		throw new CustomGenericException("Http 404", "Getting Error");
	  } else if ("io-error".equals(type)) {
		// go handleAllException
		throw new IOException();
	  } else {
		return new ModelAndView("home").addObject("msg", type);
	  }
 
	}
 
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
 
		ModelAndView model = new ModelAndView("404");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
 
		return model;
 
	}
 
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
 
		ModelAndView model = new ModelAndView("404");
		model.addObject("errMsg", "This is Exception");
 
		return model;
 
	}
 
}
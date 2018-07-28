package jp.com.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jp.com.exception.CustomGenericException;

@ControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView mav = new ModelAndView("error/generic_error");
		mav.addObject("code", ex.getErrCode());
		mav.addObject("message", ex.getErrMsg());
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView mav = new ModelAndView("error/generic_error");
		ex.printStackTrace();
		mav.addObject("message", "this is Exception.class");
		return mav;
	}
}

package jp.member.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import jp.com.exception.CustomGenericException;
import jp.com.resolver.ParamCollector;
import jp.com.utils.StringUtil;
import jp.member.service.MemberService;

@RestController
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		if (StringUtil.isNull(inParams.get("userEmail")) || StringUtil.isNull(inParams.get("userPassword"))) {
			throw new CustomGenericException("err-validate", "입력 데이터 오류");
		}
		Map<String, Object> outParams = memberService.login(inParams.getMap());
		if (outParams == null || outParams.isEmpty()) {
			throw new CustomGenericException("-1", "이메일 또는 비밀번호가 일치하지 않습니다.");
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		for (Entry<String, Object> entry : outParams.entrySet()) {
			request.getSession().setAttribute(entry.getKey(), entry.getValue());
		}
		mav.addObject("code", "0");
		mav.addObject("message", "로그인 성공");
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		request.getSession().invalidate();
		mav.addObject("code", "0");
		mav.addObject("message", "로그아웃이 되었습니다.");
		return mav;
	}
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", ex.getErrCode());
		mav.addObject("message", ex.getErrMsg());
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView mav = new ModelAndView("jsonView");
		ex.printStackTrace();
		mav.addObject("message", "this is Exception.class");
		return mav;
	}
}

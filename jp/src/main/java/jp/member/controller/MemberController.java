package jp.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger log = Logger.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getMember(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView();
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다.");
		}
		ArrayList<String> js = new ArrayList<>();
		js.add("member");
		mav.addObject("controlJs", js);
		mav.setViewName("member/mypage");
		return mav;
	}
	
	@RequestMapping(value = "/name", method = RequestMethod.GET)
	public ModelAndView getName(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView();
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		ArrayList<String> js = new ArrayList<>();
		js.add("member");
		mav.addObject("controlJs", js);
		mav.setViewName("member/name");
		return mav;
	}
	
	@RequestMapping(value = "/name", method = RequestMethod.PUT)
	public ModelAndView updateName(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		// null값 체크
		if (StringUtil.isNull(inParams.get("user_email").toString())
				|| StringUtil.isNull(inParams.get("cur_name").toString())
				|| StringUtil.isNull(inParams.get("new_name").toString())
				|| StringUtil.isNull(inParams.get("user_passwd").toString())) {
			mav.addObject("code", "-1");
			mav.addObject("message", "값을 다시 입력하여 주세요");
		}
		// 회원정보 업데이트
		memberService.updateName(inParams.getMap());
		// 업데이트 된 회원정보를 조회 후 세션정보 업데이트
		inParams.put("userEmail", inParams.get("G_USER_EMAIL"));
		inParams.put("userPassword", inParams.get("user_passwd"));
		Map<String, Object> outParams = memberService.login(inParams.getMap());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		request.getSession().setAttribute("G_USER_NAME", outParams.get("G_USER_NAME"));
		mav.addObject("message", "회원정보가 수정되었습니다.");
		return mav;
	}
	
	@RequestMapping(value = "/passwd", method = RequestMethod.GET)
	public ModelAndView getPasswd(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView();
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		ArrayList<String> js = new ArrayList<>();
		js.add("member");
		mav.addObject("controlJs", js);
		mav.setViewName("member/passwd");
		return mav;
	}
	
	@RequestMapping(value = "/passwd", method = RequestMethod.PUT)
	public ModelAndView updatePasswd(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		// null값 체크
		if (StringUtil.isNull(inParams.get("user_email").toString())
				|| StringUtil.isNull(inParams.get("cur_passwd").toString())
				|| StringUtil.isNull(inParams.get("new_passwd").toString())) {
			mav.addObject("code", "-1");
			mav.addObject("message", "값을 다시 입력하여 주세요");
		}
		// 회원정보 업데이트
		memberService.updatePasswd(inParams.getMap());
		mav.addObject("message", "회원정보가 수정되었습니다.");
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView getDelete(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView();
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		ArrayList<String> js = new ArrayList<>();
		js.add("member");
		mav.addObject("controlJs", js);
		mav.setViewName("member/delete");
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ModelAndView deleteMember(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		// null값 체크
		if (StringUtil.isNull(inParams.get("user_email").toString())
				|| StringUtil.isNull(inParams.get("user_passwd").toString())) {
			mav.addObject("code", "-1");
			mav.addObject("message", "값을 다시 입력하여 주세요");
		}
		// 회원정보 삭제
		memberService.deleteMember(inParams.getMap());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		request.getSession().invalidate();
		mav.addObject("message", "회원정보가 삭제되었습니다.");
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

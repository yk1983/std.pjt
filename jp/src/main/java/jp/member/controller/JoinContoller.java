package jp.member.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.com.resolver.ParamCollector;
import jp.com.utils.StringUtil;
import jp.member.service.MemberService;
import jp.schedule.service.ScheduleService;

@RestController
@RequestMapping(value = "/join")
public class JoinContoller {
	private static final Logger log = Logger.getLogger(JoinContoller.class);

	@Autowired
	MemberService memberService;
	
	@Autowired
	ScheduleService scheduleService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getJoinMemberForm(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView();
		ArrayList<String> js = new ArrayList<>();
		js.add("join");
		mav.addObject("controlJs", js);
		mav.setViewName("member/join");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView joinMember(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		// null값 체크
		if (StringUtil.isNull(inParams.get("user_name").toString())
				|| StringUtil.isNull(inParams.get("user_email").toString())
				|| StringUtil.isNull(inParams.get("user_passwd").toString())
				|| StringUtil.isNull(inParams.get("cfm_passwd").toString())) {
			mav.addObject("code", "-1");
			mav.addObject("messgae", "값을 다시 입력하여 주세요");
		}
		// 회원가입
		memberService.insertMember(inParams.getMap());
		
		int out = memberService.getMemberNo(inParams.getMap());
		inParams.put("G_USER_NO", out);
		
		// default category 추가
		inParams.put("category_no", "99");
		inParams.put("category_title", "default");
		inParams.put("category_color", "f0f0f5");
		inParams.put("category_createfg", "0");
		scheduleService.insertCategory(inParams.getMap());
		
		mav.addObject("message", "회원가입이 완료되었습니다.");
		return mav;
	}
}

package jp.schedule.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.com.exception.CustomGenericException;
import jp.com.resolver.ParamCollector;
import jp.schedule.service.ScheduleService;

@RestController
@RequestMapping(value="/schedule")
public class ScheduleController {
private static final Logger log = Logger.getLogger(ScheduleController.class);
	
	@Autowired
	ScheduleService scheduleService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCalendarView(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView();
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		if (inParams.get("G_USER_NO").toString().isEmpty() 
				|| inParams.get("G_USER_NAME").toString().isEmpty()
				|| inParams.get("G_USER_EMAIL").toString().isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		mav.setViewName("schedule/schedule");
		ArrayList<String> module = new ArrayList<>();
		module.add("calendar");
		module.add("schedule");
		ArrayList<Map<String, Object>> list = scheduleService.getCategoryList(inParams.getMap());
		mav.addObject("list", list);
		mav.addObject("module", module);
		return mav;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getScheduleList(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		if (inParams.get("G_USER_NO").toString().isEmpty() 
				|| inParams.get("G_USER_NAME").toString().isEmpty()
				|| inParams.get("G_USER_EMAIL").toString().isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		ArrayList<Map<String, Object>> list = scheduleService.getScheduleList(inParams.getMap());
		mav.addObject("data", list);
		return mav;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView getCategoryList(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		if (inParams.get("G_USER_NO").toString().isEmpty() 
				|| inParams.get("G_USER_NAME").toString().isEmpty()
				|| inParams.get("G_USER_EMAIL").toString().isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		// category data 가져오기
		ArrayList<Map<String, Object>> list = scheduleService.getCategoryList(inParams.getMap());
		mav.addObject("data", list);
		return mav;
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ModelAndView insertCategory(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		// session check
		if (inParams.isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		if (inParams.get("G_USER_NO").toString().isEmpty() 
				|| inParams.get("G_USER_NAME").toString().isEmpty()
				|| inParams.get("G_USER_EMAIL").toString().isEmpty()) {
			throw new CustomGenericException("-1", "로그인해주시기 바랍니다");
		}
		mav.addObject("code", "0");
		// null check
		if (inParams.get("category_title").toString().isEmpty()
				|| inParams.get("category_color").toString().isEmpty()) {
			mav.addObject("code", "-1");
			mav.addObject("message", "입력값을 확인해 주세요");
		}
		// category 번호 가져오기
		int out = scheduleService.getCategoryNo(inParams.get("G_USER_NO").toString());
		inParams.put("category_no", out);
		inParams.put("category_createfg", "1");
		// category 등록
		scheduleService.insertCategory(inParams.getMap());
		mav.addObject("message", "등록완료");
		return mav;
	}
	
	@RequestMapping(value = "/category/{seq}", method = RequestMethod.PUT)
	public ModelAndView updateCategory(ParamCollector inParams, @PathVariable("seq") int seq) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		// null값 체크
		if (inParams.get("category_title").toString().isEmpty()
				|| inParams.get("category_color").toString().isEmpty()) {
			mav.addObject("code", "-1");
			mav.addObject("message", "값을 다시 입력하여 주세요");
		}
		
		inParams.put("category_no", seq);
		
		// default category 체크
		if (inParams.get("category_no").toString().equals("99")) {
			mav.addObject("code", "-1");
			mav.addObject("message", "수정할 수 없는 category입니다.");
		}
		
		// category_color replace
		String out = inParams.get("category_color").toString().replace("#", "");
		inParams.put("category_color", out);
		
		// category update
		scheduleService.updateCategory(inParams.getMap());
		mav.addObject("message", "수정완료");
		return mav;
	}
	
	@RequestMapping(value = "/category/{seq}", method = RequestMethod.DELETE)
	public ModelAndView deleteCategory(ParamCollector inParams, @PathVariable("seq") int seq) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		// null값 체크
		if (inParams.get("category_no").toString().isEmpty()
				|| inParams.get("category_title").toString().isEmpty()
				|| inParams.get("category_color").toString().isEmpty()) {
			mav.addObject("code", "-1");
			mav.addObject("message", "선택된 값이 없습니다.");
		}
		
		inParams.put("category_no", seq);
		
		// default category 체크
		if (inParams.get("category_no").toString().equals("99")) {
			mav.addObject("code", "-1");
			mav.addObject("message", "삭제할 수 없는 category입니다.");
		}
		
		// category 삭제
		scheduleService.deleteCategory(inParams.getMap());
		mav.addObject("message", "삭제완료");
		return mav;
	}
	
	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	public ModelAndView insertSchedule(ParamCollector inParams) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("code", "0");
		//// null값 체크
		if (inParams.get("schedule_title").toString().isEmpty()
				|| inParams.get("schedule_content").toString().isEmpty()
				|| inParams.get("cbo_category").toString().isEmpty()
				|| inParams.get("start_date").toString().isEmpty()
				|| inParams.get("start_time").toString().isEmpty()
				|| inParams.get("end_date").toString().isEmpty()
				|| inParams.get("end_time").toString().isEmpty()) {
			mav.addObject("code", "-1");
			mav.addObject("message", "전체입력해주세요");
		}
		int out = scheduleService.getScheduleNo(inParams.get("G_USER_NO").toString());
		inParams.put("schedule_no", out);
		scheduleService.insertSchedule(inParams.getMap());
		mav.addObject("message", "등록완료!");
		return mav;
	}
	
	@RequestMapping(value = "/list/{seq}", method = RequestMethod.GET)
	public ModelAndView getUpdateSchedulePage(ParamCollector inParams, @PathVariable("seq") int seq) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("code", "0");				
		inParams.put("schedule_no", seq);	
		mav.setViewName("schedule/tabpanel/updateSchedule");
		Map<String, Object> out = scheduleService.getUpdateSchedulePage(inParams.getMap());
		mav.addObject("data", out);
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

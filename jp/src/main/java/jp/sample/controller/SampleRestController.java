package jp.sample.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.com.exception.CustomGenericException;
import jp.com.resolver.ParamCollector;
import jp.com.utils.StringUtil;
import jp.sample.service.SampleService;

@CrossOrigin
@RestController
public class SampleRestController {
	private static final Logger log = Logger.getLogger(SampleRestController.class);

	@Autowired
	SampleService sampleService;

	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public ModelAndView getSampleList(ParamCollector collector) {
		ModelAndView mav = new ModelAndView("sample/sample");
		if (collector.getMap().containsKey("mode") && collector.get("mode").equals("search")) {
			mav.setViewName("jsonView");
		}
		List<Map<String, Object>> list = sampleService.getSampleList(collector.getMap());
		mav.addObject("data", list);
		return mav;
	}

	@RequestMapping(value = "/sample/{seq}", method = RequestMethod.GET)
	public ModelAndView getSampleOne(ParamCollector collector, @PathVariable("seq") int seq) {
		ModelAndView mav = new ModelAndView("jsonView");
		collector.put("user_no", seq);
		Map<String, Object> data = sampleService.getSampleOne(collector.getMap());
		mav.addObject("data", data);
		return mav;
	}

	@RequestMapping(value = "/sample", method = RequestMethod.POST)
	public ModelAndView insertSampleData(ParamCollector collector) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		if (StringUtil.isNull(collector.get("user_name").toString())
				|| StringUtil.isNull(collector.get("user_email").toString())
				|| StringUtil.isNull(collector.get("user_passwd").toString())) {
			throw new CustomGenericException("-1", "필수입력 값 오류");
		}
		sampleService.insertSampleData(collector.getMap());
		mav.addObject("errCode", "0");
		mav.addObject("errMsg", "정상적으로 저장되었습니다.");
		return mav;
	}

	@RequestMapping(value = "/sample/{seq}", method = RequestMethod.PUT)
	public ModelAndView updateSampleData(ParamCollector collector, @PathVariable("seq") int seq) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		collector.put("user_no", seq);
		if (StringUtil.isNull(collector.get("user_no").toString())
				|| StringUtil.isNull(collector.get("user_name").toString())
				|| StringUtil.isNull(collector.get("user_email").toString())
				|| StringUtil.isNull(collector.get("user_passwd").toString())) {
			throw new CustomGenericException("-1", "필수입력 값 오류");
		}
		int count = sampleService.findUserNo(collector.getMap());
		if (count < 1) {
			throw new CustomGenericException("-2", "비밀번호 오류");
		}
		sampleService.updateSampleData(collector.getMap());
		return mav;
	}

	@RequestMapping(value = "/sample/{seq}", method = RequestMethod.DELETE)
	public ModelAndView deleteSampleData(ParamCollector collector, @PathVariable("seq") int seq) {
		ModelAndView mav = new ModelAndView("jsonView");
		collector.put("user_no", seq);
		int count = sampleService.findUserNo(collector.getMap());
		if (count < 1) {
			throw new CustomGenericException("-2", "비밀번호 오류");
		}
		sampleService.deleteSampleData(collector.getMap());
		mav.addObject("errCode", "0");
		mav.addObject("errMsg", "정상적으로 삭제되었습니다.");
		return mav;
	}

	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("errCode", ex.getErrCode());
		mav.addObject("errMsg", ex.getErrMsg());
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView mav = new ModelAndView("jsonView");
		if (log.isDebugEnabled()) {
			ex.printStackTrace();
		}
		mav.addObject("errMsg", "this is Exception.class");
		return mav;
	}
}

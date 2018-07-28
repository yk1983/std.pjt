package jp.com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IndexController {

	@RequestMapping(value = {"/", "/main", "/index"}, method = RequestMethod.GET)
	public ModelAndView mainView() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}

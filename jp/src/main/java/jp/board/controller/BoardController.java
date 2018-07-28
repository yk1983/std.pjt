package jp.board.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.board.service.BoardService;
import jp.com.resolver.ParamCollector;

@RestController
public class BoardController {
	private static final Logger log = Logger.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView boardView(ParamCollector collector) {
		ModelAndView mav = new ModelAndView("board/list");
		mav.addObject("list", getBoardList(collector.getMap()));
		return mav;
	}
	
	private List<Map<String, Object>> getBoardList(Map<String, Object> map) {
		return boardService.getBoardList(map);
	}
}

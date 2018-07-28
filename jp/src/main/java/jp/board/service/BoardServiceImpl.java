package jp.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;

	@Override
	public List<Map<String, Object>> getBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDao.getBoardList(map);
	}
}

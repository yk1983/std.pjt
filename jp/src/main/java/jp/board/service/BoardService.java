package jp.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	List<Map<String, Object>> getBoardList(Map<String, Object> map);

}

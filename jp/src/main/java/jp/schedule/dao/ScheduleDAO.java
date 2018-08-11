package jp.schedule.dao;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import jp.com.dao.AbstractDAO;

@Repository
public class ScheduleDAO extends AbstractDAO{

	public int getCategoryNo(String mnum) {
		return (int) selectOne("schedule.getCategoryNo", mnum);
	}
	
	public void insertCategory(Map<String, Object> map) {
		insert("schedule.insertCategory", map);
	}
	
	public ArrayList<Map<String, Object>> getCategoryList(Map<String, Object> map) {
		return (ArrayList<Map<String, Object>>) selectList("schedule.getCategoryList", map);
	}
	
	public void updateCategory(Map<String, Object> map) {
		update("schedule.updateCategory", map);
	}
	
	public void deleteCategory(Map<String, Object> map) {
		update("schedule.deleteCategory", map);
	}

	public int getScheduleNo(String mnum) {
		return (int) selectOne("schedule.getScheduleNo", mnum);
	}
	
	public void insertSchedule(Map<String, Object> map) {
		insert("schedule.insertSchedule", map);
	}

	public ArrayList<Map<String, Object>> getScheduleList(Map<String, Object> map) {
		return (ArrayList<Map<String, Object>>) selectList("schedule.getScheduleList", map);
	}

	public Map<String, Object> getUpdateSchedulePage(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("schedule.getUpdateSchedulePage", map);
	}

}

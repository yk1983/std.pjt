package jp.schedule.service;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.schedule.dao.ScheduleDAO;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Resource
	ScheduleDAO scheduleDAO;
	
	@Override
	public int getCategoryNo(String mnum) {
		return scheduleDAO.getCategoryNo(mnum);
	}
	
	@Override
	public void insertCategory(Map<String, Object> map) {
		scheduleDAO.insertCategory(map);
	}
	
	@Override
	public ArrayList<Map<String, Object>> getCategoryList(Map<String, Object> map) {
		return scheduleDAO.getCategoryList(map);
	}
	
	@Override
	public void updateCategory(Map<String, Object> map) {
		scheduleDAO.updateCategory(map);
	}
	
	@Override
	public void deleteCategory(Map<String, Object> map) {
		scheduleDAO.deleteCategory(map);
	}
	
	@Override
	public int getScheduleNo(String mnum) {
		return scheduleDAO.getScheduleNo(mnum);
	}
	
	@Override
	public void insertSchedule(Map<String, Object> map) {
		scheduleDAO.insertSchedule(map);
	}

	@Override
	public ArrayList<Map<String, Object>> getScheduleList(Map<String, Object> map) {
		return scheduleDAO.getScheduleList(map);
	}

	@Override
	public Map<String, Object> getUpdateSchedulePage(Map<String, Object> map) {
		return scheduleDAO.getUpdateSchedulePage(map);
	}

}

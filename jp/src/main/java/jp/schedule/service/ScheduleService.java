package jp.schedule.service;

import java.util.ArrayList;
import java.util.Map;

import jp.com.resolver.ParamCollector;

public interface ScheduleService {
	
	int getCategoryNo(String string);

	void insertCategory(Map<String, Object> map);
	
	ArrayList<Map<String, Object>> getCategoryList(Map<String, Object> map);
	
	void updateCategory(Map<String, Object> map);
	
	void deleteCategory(Map<String, Object> map);
	
	int getScheduleNo(String string);

	void insertSchedule(Map<String, Object> map);

	ArrayList<Map<String, Object>> getScheduleList(Map<String, Object> map);

	Map<String, Object> getUpdateSchedulePage(Map<String, Object> map);
	
}

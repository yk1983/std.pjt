package jp.sample.service;

import java.util.List;
import java.util.Map;

public interface SampleService {

	List<Map<String, Object>> getSampleList(Map<String, Object> map);

	Map<String, Object> getSampleOne(Map<String, Object> map);

	void insertSampleData(Map<String, Object> map);

	void updateSampleData(Map<String, Object> map);

	void deleteSampleData(Map<String, Object> map);

	int findUserNo(Map<String, Object> map);

}

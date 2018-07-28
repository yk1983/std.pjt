package jp.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.com.dao.AbstractDAO;

@Repository
public class SampleDAO extends AbstractDAO {

	public List<Map<String, Object>> getSampleList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return selectList("sample.getSampleList", map);
	}

	public Map<String, Object> getSampleOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Map<String, Object>) selectOne("sample.getSampleOne", map);
	}

	public void insertSampleData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		insert("sample.insertSample", map);
	}

	public void updateSampleData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		update("sample.updateSample", map);
	}

	public void deleteSampleData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		delete("sample.deleteSample", map);
	}

	public int findUserNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) selectOne("sample.findUserNo", map);
	}

}

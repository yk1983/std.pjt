package jp.sample.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.sample.dao.SampleDAO;

@Service
public class SampleServiceImpl implements SampleService {

	private static final Logger log = Logger.getLogger(SampleServiceImpl.class);
	
	@Autowired
	SampleDAO sampleDao;
	
	@Override
	public List<Map<String, Object>> getSampleList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sampleDao.getSampleList(map);
	}

	@Override
	public Map<String, Object> getSampleOne(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sampleDao.getSampleOne(map);
	}

	@Override
	public void insertSampleData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sampleDao.insertSampleData(map);
	}

	@Override
	public void updateSampleData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sampleDao.updateSampleData(map);
	}

	@Override
	public void deleteSampleData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sampleDao.deleteSampleData(map);
	}

	@Override
	public int findUserNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sampleDao.findUserNo(map);
	}

}

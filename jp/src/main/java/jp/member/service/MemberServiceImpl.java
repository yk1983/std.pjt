package jp.member.service;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.member.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	private static final Logger log = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	MemberDAO memberDao;
	
	@Override
	public void insertMember(Map<String, Object> map) {
		memberDao.insertMember(map);
	}

	@Override
	public Map<String, Object> login(Map<String, Object> map) {
		return memberDao.login(map);
	}

	@Override
	public void deleteMember(Map<String, Object> map) {
		memberDao.deleteMember(map);
	}

	@Override
	public Map<String, Object> getMember(Map<String, Object> map) {
		return memberDao.getMember(map);
	}

	@Override
	public void updateName(Map<String, Object> map) {
		memberDao.updateName(map);
	}

	@Override
	public void updatePasswd(Map<String, Object> map) {
		memberDao.updatePasswd(map);
	}

}

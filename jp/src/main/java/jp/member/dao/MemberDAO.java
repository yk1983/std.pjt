package jp.member.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.com.dao.AbstractDAO;

@Repository
public class MemberDAO extends AbstractDAO {
	
	public void insertMember(Map<String, Object> map) {
		insert("member.insertMember", map);
	}

	public Map<String, Object> login(Map<String, Object> map) {
		return (Map<String, Object>) selectOne("member.login", map);
	}
	
	public void deleteMember(Map<String, Object> map) {
		delete("member.deleteMember", map);
	}

	public void updateName(Map<String, Object> map) {
		update("member.updateName", map);
	}

	public void updatePasswd(Map<String, Object> map) {
		update("member.updatePasswd", map);
	}
	
	public int getMemberNo(Map<String, Object> map) {
		return  (int) selectOne("member.getMemberNo", map);
	}
	
}

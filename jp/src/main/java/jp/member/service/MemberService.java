package jp.member.service;

import java.util.Map;

public interface MemberService {
	void insertMember(Map<String, Object> map);

	Map<String, Object> login(Map<String, Object> map);

	void deleteMember(Map<String, Object> map);

	void updateName(Map<String, Object> map);

	void updatePasswd(Map<String, Object> map);
	
	int getMemberNo(Map<String, Object> map);
}

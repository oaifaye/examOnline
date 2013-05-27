package com.service.member;

import java.util.List;
import java.util.Map;

import com.dao.member.MemberDAO;
import com.model.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;
	
	
	public List<Member> findByParams(Map<String, Object> params){
		return memberDAO.findByParams(params);
	}
	
	@Override
	public void addMamber(Member member) {
		memberDAO.insert(member);
	}

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	
	
}

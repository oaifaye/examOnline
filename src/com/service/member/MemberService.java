package com.service.member;

import java.util.List;
import java.util.Map;

import com.model.Member;

public interface MemberService {
	public List<Member> findByParams(Map<String, Object> params);
	public void addMamber(Member member);
}

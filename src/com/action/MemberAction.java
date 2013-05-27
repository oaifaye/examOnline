package com.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.model.Member;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.member.MemberService;

public class MemberAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String  userName,
					password
					;
	
	private MemberService memberService;
	private Member member;
	
	
//会员登陆
	public String login(){
		if(!(null==userName||("").equals(userName)||null==password||("").equals(password))){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			params.put("password", password);
			List<Member> memberList = memberService.findByParams(params);
			if(memberList.size()!=1){
				addFieldError("error","用户名或密码错");
				return ERROR;
			}else{
				ActionContext.getContext().getSession().put("member", memberList.get(0));
				return SUCCESS;
			}
		}else{
			addFieldError("error","用户名或密码不能为空");
			return ERROR;
		}
	}
	
//注册
	public String register(){
		try{
			Assert.notNull(member);
			member.setCreateDate(new Date());
			memberService.addMamber(member);
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}

package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.Member;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.member.MemberService;

public class MemberAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	private String userName;
	private String password;
	
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

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
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

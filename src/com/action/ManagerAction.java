package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.model.Manager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.login.ManagerService;

@SuppressWarnings("serial")
public class ManagerAction extends ActionSupport {
	private ManagerService managerService;
	private String userName;
	private String password;
	private String nextAction;
	
	public String login(){
		if(!(null==userName||("").equals(userName)||null==password||("").equals(password))){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			params.put("password", password);
			List<Manager> managerList=managerService.findByParams(params);
			if(managerList.size()!=1){
				try {
					HttpServletResponse response=ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out = ServletActionContext.getResponse().getWriter();
					out.print("<script>alert('用户名密码错');location='backend/login.jsp'</script>");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}else{
				if(null==ActionContext.getContext().getSession().get("nextAction")){
					//直接登陆页面登陆
					nextAction="/backend/index.jsp";
				}else{
					//session过期，登陆后直接跳转到请求页面
					nextAction=(String) ActionContext.getContext().getSession().get("nextAction");
				}
				ActionContext.getContext().getSession().put("manager", managerList.get(0));
				return SUCCESS;
			}
		}else{
			addFieldError("error","用户名或密码不能为空");
			return ERROR;
		}
		
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
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

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
}

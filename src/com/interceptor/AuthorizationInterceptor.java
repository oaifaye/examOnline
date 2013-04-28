package com.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class AuthorizationInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = 6477996926735650473L;

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
		if(session.get("manager")== null){
			//session过期后重新登陆，直接跳转到要访问的地址
			session.put("nextAction", ServletActionContext.getRequest().getServletPath()+"?"+ServletActionContext.getRequest().getQueryString());
			
			return "relogin";
		}else{
			return actionInvocation.invoke();
		}
	}

}

package com.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	private String redirectURL = null;
	private Set<String> notCheckURLList = new HashSet<String>();
	private String sessionKey = null;
	private Set<String> reLoginNotCheckURLList = new HashSet<String>();

	public void destroy() {
		notCheckURLList.clear();
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		
		if (sessionKey == null) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		if ((!checkRequestURIIntNotFilterList(request)) && session.getAttribute(sessionKey) == null) {
			if(!(reLoginNotCheckURLList.contains(request.getServletPath()))){
				//session过期后重新登陆，直接跳转到要访问的地址
				session.setAttribute("nextAction", request.getServletPath()+"?"+request.getQueryString());
				
			}
			
			response.sendRedirect(request.getContextPath() + redirectURL);
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath()+ (request.getPathInfo() == null ? "" : request.getPathInfo());
//		System.out.println(request.getServletPath());
		return notCheckURLList.contains(uri);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
//		this.filterConfig = filterConfig;
		redirectURL = filterConfig.getInitParameter("redirectURL");
		sessionKey = filterConfig.getInitParameter("checkSessionKey");
		String notCheckURLListStr = filterConfig
				.getInitParameter("notCheckURLList");
		if (notCheckURLListStr != null) {
			String[] params = notCheckURLListStr.split(",");
			for (int i = 0; i < params.length; i++) {
				notCheckURLList.add(params[i].trim());
			}
		}
		String reLoginNotCheckURLStr = filterConfig
				.getInitParameter("reloginNotCheckURLList");
		if (reLoginNotCheckURLStr != null) {
			String[] params = reLoginNotCheckURLStr.split(",");
			for (int i = 0; i < params.length; i++) {
				reLoginNotCheckURLList.add(params[i].trim());
			}
		}
	}

}


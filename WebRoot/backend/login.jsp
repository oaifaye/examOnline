<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>" />
    <title>精品教育在线考试系统</title>
    
	
	<link rel="stylesheet" type="text/css" href="css/backend_index.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/panel.js"></script>

  </head>
  
  <body>
    <div id="greybackground"></div>
	<div class="center">
		<s:fielderror cssStyle="font-weight:bold;color:red"/>
    	<div class="tabTop">
        	<ul id="tabmenu1">
				<li><span><a>管理员入口</a></span></li>
				<li><span><a>教师入口</a></span></li>
			</ul>
		</div>
        <div class="tabFoot" >
        	<ul>
        		<s:form action="loginBackend" method="post" theme="simple">
				<li>用户名：<s:textfield name="userName" /></li>
				<li>密&nbsp;&nbsp;码：<s:password name="password"></s:password></li>
                <li><s:submit value="提交"/><s:reset value="重置"/></li>
                </s:form>
			</ul>
		</div>
		<div class="tabFoot" >
        	<ul>
        		<s:form action="loginBackend" method="post" theme="simple">
				<li>用户名：<s:textfield name="userName" /></li>
				<li>密&nbsp;&nbsp;码：<s:textfield name="password"></s:textfield></li>
                <li><s:submit value="提交"/><s:reset value="重置"/></li>
                </s:form>
			</ul>
		</div>
	</div>
  </body>
</html>

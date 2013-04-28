<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>" />
    
    <title>学力测试登陆</title>
    
	<link rel="stylesheet" type="text/css" href="css/front_login.css" />
	<link rel="stylesheet" type="text/css" href="css/common.css" />
  </head>
  
  <body>
    <div class="center">
    	<div class="register">
    		<a>新用户注册   sign up</a><button type="submit" class="button1" name="save" >立即注册</button>
    		<img class="regist_bg" src="images/regist_bg.gif" />
    	</div>
    	<s:form action="loginFrontend" theme="simple">
    		<div class="login">
    			<img src="images/xue_login.gif" />
    			<div class="validate" >
    				用户名：<s:textfield name="userName" cssClass="text" ></s:textfield><br />
    				密&nbsp;&nbsp;码：<s:password name="password" cssClass="text" ></s:password>
    				<input type="submit" value="登陆" class="button2" />
    				<s:fielderror cssStyle="font-weight:bold;color:red;list-style-type: none;"/>
    			</div>
    		</div>
    	</s:form>
    </div>
  </body>
</html>

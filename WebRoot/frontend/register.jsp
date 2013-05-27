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
    
    <title>会员注册</title>

    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/fornt_register.css" type="text/css"></link>
    
  </head>
  
  <body>
  	<s:form action="registerFrontend" theme="simple">
    <table class="register">
    	<tr class="headTr">
    		<td colspan="2"><a class="head">基本信息</a></td>
    	</tr>
    	<tr>
    		<td class="tdMust">会员类型：</td>
    		<td>
    			<label class="memberType" ><input type="radio" name="memberType" value="student" />学生</label>
    			<label class="memberType" ><input type="radio" name="memberType" value="parent" />家长</label>
    			<label class="memberType" ><input type="radio" name="memberType" value="teacher" />老师</label>
    		</td>
    	</tr>
    	<tr>
    		<td class="tdMust">用户名：</td>
    		<td><s:textfield name="member.userName" cssClass="text"/></td>
    	</tr>
    	<tr>
    		<td class="tdMust">注册邮箱：</td>
    		<td><s:textfield name="member.email" cssClass="text"/></td>
    	</tr>
    	<tr>
    		<td class="tdMust">登录密码：</td>
    		<td><s:password name="member.password" id="password" cssClass="text"/></td>
    	</tr>
    	<tr>
    		<td class="tdMust">确定密码：</td>
    		<td><input type="password" id="passwordRepeat"  class="text"/></td>
    	</tr>
    	<tr>
    		<td class="tdMust">验证码：</td>
    		<td><input type="text" id="identifyingCode"  class="text"/></td>
    	</tr>
    	<tr>
    		<td colspan="2"><input type="submit" value="立即注册" class="button2" /></td>
    	</tr>
    </table>
    </s:form>
  </body>
</html>

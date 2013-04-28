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
    	<div class="tabTop">
        	<ul id="tabmenu1">
				<li><span><a>用户管理</a></span></li>
				<li><span><a>学员管理</a></span></li>
				<li><span><a>基础信息</a></span></li>
			</ul>
		</div>
		<div class="tabFoot" >
        	<ul class="ulFoot">
				<li><span><a>试题管理</a></span></li>
				<li><span><a>关于本站</a></span></li>
				<li><span><a>技术支持</a></span></li>
			</ul>
		</div>
		<div class="tabFoot" >
        	<ul class="ulFoot">
				<li><span><a>基础信息</a></span></li>
				<li><span><a>关于本站</a></span></li>
				<li><span><a>技术支持</a></span></li>
			</ul>
		</div>
		<div class="tabFoot" >
        	<!-- 基础信息 -->
        	<ul class="ulFoot">
				<li><span><a href="<%=basePath%>initBaseInfo.action">基础信息管理</a></span></li>
			</ul>
		</div>
		
		<div class="tabTop">
        	<ul id="tabmenu1">
				<li><span><a>试题管理</a></span></li>
				<li><span><a>关于本站</a></span></li>
				<li><span><a>技术支持</a></span></li>
			</ul>
		</div>
		<div class="tabFoot" >
			<!-- 试题管理 -->
        	<ul class="ulFoot">
				<li><span><a href="<%=basePath%>listQuestion.action?gradeLevel=0">小学试题管理</a></span></li>
				<li><span><a href="<%=basePath%>listQuestion.action?gradeLevel=1">初中试题管理</a></span></li>
				<li><span><a href="<%=basePath%>listQuestion.action?gradeLevel=2">高中试题管理</a></span></li>
				<li><span><a href="<%=basePath%>listPaper.action?gradeLevel=0">小学试卷管理</a></span></li>
				<li><span><a href="<%=basePath%>listPaper.action?gradeLevel=1">初中试卷管理</a></span></li>
				<li><span><a href="<%=basePath%>listPaper.action?gradeLevel=2">高中试卷管理</a></span></li>
			</ul>
		</div>
	</div>
  </body>
</html>

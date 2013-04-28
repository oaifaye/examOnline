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
    
    <title>基础信息管理</title>

	
	<link rel="stylesheet" type="text/css" href="css/backend_listing.css" />
	<link rel="stylesheet" type="text/css" href="css/backend_baseInfo.css" />
	<script type="text/javascript" src="js/jquery.js" ></script>
	<script type="text/javascript" src="js/backend_listing.js" ></script>
	<style type="text/css">
		.selectCss{
			width: 100px;
			
		}
		.nobr br{display:none} 
	</style>

  </head>
  
  <body>
  	<%@ include file="include/menu.jsp" %>
  	<div class="greybackground"></div>
    <div class="center">
    
    	<table class="table">
    		<s:form action="addBaseGradeInfo" theme="simple" >
    			<tr >
    				<td colspan="2" >
    					基础信息
    				</td>
    			</tr>
    			<tr>
    				<td>年级添加</td>
    				<td>
    					<s:select list="#{'0':'小学','1':'初中','2':'高中'}" name="gradeInfo.gradeLevel"></s:select>
    					<s:textfield name="gradeInfo.gradeName"></s:textfield><s:submit value="保存" />
    					<s:select list="gradeInfoList" listValue="gradeName" cssClass="selectCss" ></s:select>
    				</td>
    			</tr>
    		</s:form>
    		
    		<s:form action="addBaseCourseInfo" theme="simple" >
    			<tr>
    				<td>课程添加</td>
    				<td  class="nobr">
    					<s:select name="courseInfo.gradeID" list="gradeInfoList" listKey="id" listValue="gradeName" cssClass="selectCss" ></s:select>
    					<s:textfield name="courseInfo.courseName"></s:textfield><s:submit value="保存" />
    					<s:doubleselect list="gradeInfoList" listValue="gradeName" cssClass="selectCss"
							doubleList="courseInfoMap[top.id]" doubleListValue="courseName" doubleName="1111" theme="simple" doubleCssClass="selectCss"></s:doubleselect>
    				</td>
    			</tr>
    		</s:form>
    	</table>
    	<br /><br /><br />
    </div>
  </body>
</html>

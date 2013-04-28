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
    <title>试题管理</title>
    
    <link rel="stylesheet" type="text/css" href="css/backend_listing.css" />
	<link rel="stylesheet" type="text/css" href="css/backend_listQuestion.css" />
	<script type="text/javascript" src="js/jquery.js" ></script>
	<script type="text/javascript" src="js/backend_listing.js"></script>
	<script type="text/javascript" src="js/backend_listQuestion.js"></script>
  </head>
  
  <body>
  	<%@ include file="include/menu.jsp" %>
  	<div class="greybackground"></div>
    <div class="center">
    	<table class="table">
    		<tr class="tableTitle">
    			<td>
    				<span>试题管理</span>
    			</td>
    		</tr>
    		<tr class="tableOperate">
    			<td>
    				&nbsp;&nbsp;&nbsp;
    				<a>删除</a>&nbsp;&nbsp;
    				<a id="addQuestion">添加</a>
    			</td>
    		</tr>
    	</table>
    	<table class="table">
    		<tr class="tableHead">
    			<td width="10%">
    				所属年级
    			</td>
    			<td width="10%">
    				所属科目
    			</td>
    			<td width="10%">
    				试题类型
    			</td>
    			<td class="tdContent">
    				试题标题
    			</td>
    			<td class="tdContent">
    				试题内容
    			</td>
    			<td width="10%">
    				创建人
    			</td>
    			<td width="10%">
    				创建时间
    			</td>
    		</tr>
    		<s:iterator value="pageBean.list"  status='st'>
    			<s:if test="#st.odd">
    				<tr bgcolor="#E7F0F9" class="trContent">
    			</s:if>
    			<s:else>
       				<tr bgcolor="#FFFFFF" class="trContent">
       			</s:else>
    		
    				<td width="10%">
    					<a id="test">
    					<input type="hidden" value="${id}" />
    					<s:property value="gradeName" /></a>
    				</td>
    				
    				<td width="10%">
    					<s:property value="courseName" />
    				</td>
    				<td width="10%">
    					<s:if test="questionType==0">单选题</s:if>
    					<s:if test="questionType==1">多选题</s:if>
    					<s:if test="questionType==2">主观题</s:if>
    				</td>
    				<td class="tdContent">
    					<s:property value="title" />
    				</td>
    				<td class="tdContent">
    					<s:property value="content" />
    				</td>
    				<td width="10%">
    					<s:property value="createrName" />
    				</td>
    				<td width="10%">
    					<s:date name="createDate" format="yyyy-MM-dd" />
    				</td>
    			</tr>
    		</s:iterator>
    		<tr class="tableFoot">
    			<td colspan="7">
    				<%@ include file="include/pageSupport.jsp" %>
    			</td>
   			</tr>
    	</table>
    	<br /><br /><br />
    </div>
  </body>
</html>

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
    
    <title>试卷管理</title>
    <link rel="stylesheet" type="text/css" href="css/backend_listing.css" />
	<link rel="stylesheet" type="text/css" href="css/backend_listQuestion.css" />
    
	<script type="text/javascript" src="js/jquery.js" ></script>
	<script type="text/javascript" src="js/backend_listPaper.js" ></script>

  </head>
  
  <body>
  <%@ include file="include/menu.jsp" %>
  <div class="greybackground"></div>
  <div class="center">
  	<table class="table">
  		<tr class="tableTitle">
  			<td>
  				<span>试卷管理</span>
  			</td>
  		</tr>
  		<tr class="tableOperate">
    		<td>
    			&nbsp;&nbsp;&nbsp;
    			<a>删除</a>&nbsp;&nbsp;
    			<a id="addPaper">添加</a>
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
    		<td class="tdContent">
    			试卷标题
    		</td>
    		<td class="10%">
    			总题数
    		</td>
    		<td width="7.5%">
    			总分数
    		</td>
    		<td width="10%">
    			创建人
    		</td>
    		<td width="10%">
    			创建时间
    		</td>
    		<td width="10%">
    			状态
    		</td>
    		<td width="10%">
    			操作
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
    					<s:property value="gradeName" />
    				</a>
    			</td>
    				
    			<td width="10%">
    				<s:property value="courseName" />
    			</td>
    			<td class="tdContent">
    				<s:property value="title" />
    			</td>
    			<td class="10%">
    				<s:property value="allNum" />
    			</td>
    			<td class="7.5%">
    				<s:property value="allScore" />
    			</td>
    			<td width="10%">
    				<s:property value="createrName" />
    			</td>
    			<td width="10%">
    				<s:date name="createDate" format="yyyy-MM-dd" />
    			</td>
    			<s:if test='isRelease=="1"'>
    				<td width="10%">
    					未发布
    				</td>
    				<td width="10%" class="release">
    					<a>发布</a>
    				</td>
    			</s:if>
    			<s:if test='isRelease=="0"'>
    				<td width="10%">
    					已发布
    				</td>
    				<td width="10%" class="release">
    					<a>取消发布</a>
    				</td>
    			</s:if>
    		</tr>
    	</s:iterator>
    	<tr>
    		<td class="tableFoot" colspan="9" >
    			<%@ include file="include/pageSupport.jsp" %>
    		</td>
    	</tr>
   </table>
   <br /><br /><br />
   </div>
   
  </body>
</html>

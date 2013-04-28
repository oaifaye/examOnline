<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<title>试卷添加</title>
<link rel="stylesheet" type="text/css" href="css/backend_listing.css" />
<link rel="stylesheet" type="text/css" href="css/backend_editQuesion.css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type='text/javascript' src='js/dwr_gradeAndCourse.js'></script>
<script type="text/javascript" src="js/backend_listing.js"></script>
<script type='text/javascript' src='js/backend_editPaper.js'></script>

<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/util.js'></script>
<script type='text/javascript' src='<%=basePath%>dwr/interface/baseInfoService.js'></script>

<style type="text/css">
	/**空白区域高*/
.center {
	height: 500px;
}
</style>

</head>

<body>
	<%@ include file="include/menu.jsp" %>
  	<div class="greybackground"></div>
  	<div class="center">
	<s:form id="addPaperForm" action="addPaper" theme="simple" >
		<table class="table">
			<tr>
				<td colspan="2">试卷添加</td>
			</tr>
			<tr>
				<td class="tdLeft">所属年级</td>
				<td class="tdRight">
					<s:select name="paper.gradeID" id="gradeInfoList"
						list="gradeInfoList" listKey="id" listValue="gradeName"
						headerKey="" headerValue="--请选择--" cssClass="selectCss"></s:select>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">所属科目</td>
				<td class="tdRight">
					<select name="paper.courseID" id="courseMap" class="selectCss">
						<option value="--请选择--">--请选择--</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">试卷题目</td>
				<td class="tdRight">
					<s:textfield id="title" name="paper.title"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">单选题</td>
				<td class="tdRight">
					<input class="itemType" type="checkbox" name="singleType" id="single" value="0" /> 
					数量：<s:textfield name="itemNumSingle" id="itemNumSingle" maxlength="2"  cssClass="itemNum"></s:textfield>
					分值：<s:textfield name="itemScoreSingle" id="itemScoreSingle" maxlength="2"  cssClass="itemScore"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">多选题</td>
				<td class="tdRight">
					<input class="itemType" type="checkbox" name="doubleType" id="double" value="1" /> 
					数量：<s:textfield name="itemNumDouble" id="itemNumDouble" maxlength="2"  cssClass="itemNum"></s:textfield>
					分值：<s:textfield name="itemScoreDouble" id="itemScoreDouble" maxlength="2"  cssClass="itemScore"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">简答题</td>
				<td class="tdRight">
					<input class="itemType" type="checkbox" name="subjectiveType" id="subjective" value="2" /> 
					数量：<s:textfield name="itemNumSubjective" id="itemNumSubjective" maxlength="2"  cssClass="itemNum"></s:textfield>
					分值：<s:textfield name="itemScoreSubjective" id="itemScoreSubjective" maxlength="2"  cssClass="itemScore"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">总题数</td>
				<td class="tdRight">
					<s:textfield id="allNum" name="paper.allNum" readonly="readonly" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="tdLeft">总分数</td>
				<td class="tdRight">
					<s:textfield id="allScore" name="paper.allScore" readonly="readonly" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="返回" id="backButton"/>
				</td>
			</tr>
		</table>
		<s:hidden id="id" name="paper.id" value="%{paper.id}"></s:hidden>
		<s:hidden id="courseName" value="%{paper.courseName}"></s:hidden>
		<s:hidden id="gradeLevel" name="gradeLevel" value="%{gradeLevel}" ></s:hidden>
		<s:hidden id="singleType" value="%{singleType}"></s:hidden>
		<s:hidden id="doubleType" value="%{doubleType}"></s:hidden>
		<s:hidden id="subjectiveType" value="%{subjectiveType}"></s:hidden>
	</s:form>
	<br /><br /><br />
	</div>
</body>
</html>

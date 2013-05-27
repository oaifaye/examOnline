<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<title>学力测试</title>
	
<script type="text/javascript" src="js/jquery.js"></script>
<script type='text/javascript' src='js/frontend_studentTest.js'></script>
<link rel="stylesheet" href="css/front_answering.css" type="text/css"></link>
<link rel="stylesheet" href="css/front_result.css" type="text/css"></link></head>
<body>
除简答题得分：<s:property value="lastScore" />
<a href="<%=basePath%>frontend/memberIndex.jsp" >再选张卷子</a><br />
<!-- 客观题 -->
<s:iterator value="paper.questionList" status="st" >
	<div class="testArea">
		<div class="content">
			<s:property value="#st.count"/>
			（<s:property value="score" />分）
			<s:property value="content"/>
		</div>
					<s:if test="questionType!=2">
						<div class="options">
							<dl>
								<s:if test="answerNumber>0">
									<dt>A:</dt>
									<dd>
										<s:property value="optionA" />
									</dd>
								</s:if>
								<s:if test="answerNumber>1">
									<dt>B:</dt>
									<dd>
										<s:property value="optionB" />
									</dd>
								</s:if>
								<s:if test="answerNumber>2">
									<dt>C:</dt>
									<dd>
										<s:property value="optionC" />
									</dd>
								</s:if>
								<s:if test="answerNumber>3">
									<dt>D:</dt>
									<dd>
										<s:property value="optionD" />
									</dd>
								</s:if>
								<s:if test="answerNumber>4">
									<dt>E:</dt>
									<dd>
										<s:property value="optionE" />
									</dd>
								</s:if>
								<s:if test="answerNumber>5">
									<dt>F:</dt>
									<dd>
										<s:property value="optionF" />
									</dd>
								</s:if>
								<s:if test="answerNumber>6">
									<dt>G:</dt>
									<dd>
										<s:property value="optionG" />
									</dd>
								</s:if>
								<s:if test="answerNumber>7">
									<dt>H:</dt>
									<dd>
										<s:property value="optionH" />
									</dd>
								</s:if>
							</dl>
						</div>
					</s:if>
					<s:property value="objectiveAnswer"/>
					<s:if test="tempAnswer==null"><a style="color: red;" >没写您嘞！！</s:if>
					<s:elseif test="objectiveAnswer!=tempAnswer"><a style="color: red;" ></s:elseif><br />
					
					<s:else><a ></s:else>
					<s:property value="tempAnswer"/></a><br />
	</div>
					</s:iterator>
	
</body>
</html>

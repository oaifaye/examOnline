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
</head>
<body>

	<div class="center">
		<div class="head">
			<div class="tltle"><s:property value="paper.title" /></div>
			<span class="allScore">
			试卷满分：
			<s:property value="paper.allScore" />
			分
			</span>
		</div>
		<div class="left">
			<div class="time">时间：建设中！！</div>
			<hr />
			
		<!-- 试题索引 -->
			<s:iterator value="questionTypeList" id="type">
				<div class="type">
					<h2>
					<s:if test="#type==0">
						单选题
					</s:if>
					<s:elseif test="#type==1">
						多选题
					</s:elseif>
					<s:elseif test="#type==2">
						简答题
					</s:elseif>
					</h2>
				</div>
				<div class="index">
					<s:iterator value="questionList" status="st">
						<s:if test='#type==questionType'>
							<s:if test="tempAnswer==null">
								<a class="indexButton"><s:property value="#st.count"/></a>
							</s:if>
							<s:else>
								<a class="indexButton indexFinished"><s:property value="#st.count"/></a>
							</s:else>
						</s:if>
					</s:iterator>
				</div>
				<hr />
			</s:iterator>
			
			<a id="submitPaper">试卷提交</a>
			<a id="quitButton" >退出</a>
		</div>
	<!-- 右侧答题区 -->
		<div class="right">
			<div class="testArea">
				<div class="questionContent">
					<h2>
						<a id="questionIndex"></a>
						（<s:property value="question.score" />分）
						<s:property value="question.content" />
					</h2>
				</div>
				<s:form name="questionControl" theme="simple" id="questionControl">
					<!-- 客观题 -->

					<s:if test="question.questionType!=2">
						<div class="options">
							<dl>
								<s:if test="question.answerNumber>0">
									<dt>A:</dt>
									<dd>
										<s:property value="question.optionA" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>1">
									<dt>B:</dt>
									<dd>
										<s:property value="question.optionB" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>2">
									<dt>C:</dt>
									<dd>
										<s:property value="question.optionC" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>3">
									<dt>D:</dt>
									<dd>
										<s:property value="question.optionD" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>4">
									<dt>E:</dt>
									<dd>
										<s:property value="question.optionE" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>5">
									<dt>F:</dt>
									<dd>
										<s:property value="question.optionF" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>6">
									<dt>G:</dt>
									<dd>
										<s:property value="question.optionG" />
									</dd>
								</s:if>
								<s:if test="question.answerNumber>7">
									<dt>H:</dt>
									<dd>
										<s:property value="question.optionH" />
									</dd>
								</s:if>
							</dl>
						</div>
			</div>
			<div class="objectAnswers">
				请选择:
				<s:if test="question.answerNumber>0">
					<s:if test='question.tempAnswer.indexOf("A")!=-1'>
						<label>A:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerA" value="A" checked="checked" /> </label>
					</s:if>
					<s:else>
						<label>A:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerA" value="A" /> </label>
					</s:else>
				</s:if>
				<s:if test="question.answerNumber>1">
					<s:if test='question.tempAnswer.indexOf("B")!=-1'>
						<label>B:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerB" value="B" checked="checked" /> </label>
					</s:if>
					<s:else>
						<label>B:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerB" value="B" /> </label>
						<!-- <s:checkbox label="A" name="name" value="false" fieldValue="bbb"/> -->
					</s:else>
				</s:if>
				<s:if test="question.answerNumber>2">
					<s:if test='question.tempAnswer.indexOf("C")!=-1'>
						<label>C:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerC" value="C" checked="checked" /> </label>
					</s:if>
					<s:else>
						<label>C:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerC" value="C" /> </label>
						<!-- <s:checkbox label="A" name="name" value="false" fieldValue="bbb"/> -->
					</s:else>
				</s:if>
				<s:if test="question.answerNumber>3">
					<s:if test='question.tempAnswer.indexOf("D")!=-1'>
						<label>D:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerD" value="D" checked="checked" /> </label>
					</s:if>
					<s:else>
						<label>D:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerD" value="D" /> </label>
					</s:else>
				</s:if>
				<s:if test="question.answerNumber>4">
					<s:if test='question.tempAnswer.indexOf("E")!=-1'>
						<label>E:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerE" value="E" checked="checked" /> </label>
					</s:if>
					<s:else>
						<label>E:<input type="checkbox" name="tempAnswer"
							id="objectiveAnswerE" value="E" /> </label>
					</s:else>
				</s:if>
			</div>
			</s:if>

			<!-- 主观题 -->
			<s:elseif test="question.questionType==2">
				<div class="subjectAnswers">
					<s:textarea name="question.tempAnswer" />
				</div>
		</div>
		</s:elseif>
		<div class="indexContext">
			<s:if test="currentIndex==0"><a class="previousQuestionA">上一题</a></s:if>
			<s:else>
				<a id="previousQuestion" class="previousQuestion" >上一题</a>
			</s:else>
			<s:if test="currentIndex==questionList.size()-1"><a class="nextQuestionA" >下一题</a></s:if>
			<s:else>
				<a id="nextQuestion" class="nextQuestion" >下一题</a>
			</s:else>
		</div>
		<input type="hidden" name="currentIndex" id="currentIndex" value="${currentIndex}" /> 
		<input type="hidden" id="questionType" value="${question.questionType}" />
		<input type="hidden" id="needIndex" name="needIndex" />
		</s:form>
	</div>
	</div>



	<!-- 单选题数：
	<s:property value="itemNumSingle" />
	&nbsp;&nbsp;&nbsp;&nbsp;每题
	<s:property value="itemScoreSingle" />
	分
	<br /> 多选题数：
	<s:property value="itemNumDouble" />
	&nbsp;&nbsp;&nbsp;&nbsp;每题
	<s:property value="itemScoreDouble" />
	分
	<br /> 简答题数：
	<s:property value="itemNumSubjective" />
	&nbsp;&nbsp;&nbsp;&nbsp;每题
	<s:property value="itemScoreSubjective" />
	分
	<br /> 总
	<s:property value="paper.allNum" />
	题
	<br /> 总
	<s:property value="paper.allScore" />
	分
	<br />

	<s:iterator value="questionList">
		<s:property value="title" />
		<br />
	</s:iterator>
	------------
	<s:property value="question.title" />
	<br />
	
<a id="submitPaper" >试卷提交</a>
<a id="quitButton" >退出</a>
 -->
</body>
</html>

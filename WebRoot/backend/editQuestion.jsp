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
    
    <title>试题信息添加</title>
    
	<link rel="stylesheet" type="text/css" href="css/backend_listing.css" />
	<link rel="stylesheet" type="text/css" href="css/backend_editQuesion.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/backend_listing.js"></script>
	<script type="text/javascript" src="js/backend_editQuestion.js"></script>
	<script type='text/javascript' src='js/dwr_gradeAndCourse.js'></script>
	
	<script type='text/javascript' src='<%=basePath%>dwr/engine.js'></script> 
	<script type='text/javascript' src='<%=basePath%>dwr/util.js'></script> 
	<script type='text/javascript' src='<%=basePath%>dwr/interface/baseInfoService.js'></script> 
	
  </head>
  
  <body>
  	<%@ include file="include/menu.jsp" %>
  	<div class="greybackground"></div>
    <div class="center">
    	<table class="table" id="questionTable">
    		<s:form id="questuionForm" action="addQuestion" theme="simple" >
    			<tr >
    				<td colspan="2">
    					试题添加
    				</td>
    			</tr>
    			<tr>
    				<td class="tdLeft">试题所属年级</td>
    				<td class="tdRight">
    					<s:select name="question.gradeID" id="gradeInfoList" list="gradeInfoList" listKey="id" listValue="gradeName" headerKey="" headerValue="--请选择--" cssClass="selectCss"></s:select>
    				</td>
    			</tr>
    			<tr>
    				<td class="tdLeft">试题科目</td>
    				<td class="tdRight">
    					<select name="question.courseID" id="courseMap" class="selectCss">
							<option value="--请选择--">--请选择--</option>
						</select>
					</td>
    			</tr>
    			<tr>
    				<td class="tdLeft">试题标题</td>
    				<td class="tdRight">
    					<s:textfield name="question.title" id="questionTitle" maxLength="50" cssClass="textCss"></s:textfield>
					</td>
    			</tr>
    			<tr>
    				<td class="tdLeft">试题材料<br /><a style="font-size:12px; color:red;">(如已存在请选择，如不存在请添加)</a></td>
    				<td class="tdRight">
    					<label><input type="radio" name="questionMaterialType" id="" value="0" checked="checked" />无材料</label>
    					<label><input type="radio" name="questionMaterialType" id="" value="1"/>选择材料</label>
    					<label><input type="radio" name="questionMaterialType" id="" value="2"/>添加材料</label><br />
    					<s:select name="questionMaterialID" id="questionMaterialList" list="questionMaterialList" listKey="id" listValue="content" headerKey="" headerValue="--请选择--" cssClass="selectCss" cssStyle="display: none;"></s:select>
    					<s:textarea name="questionMaterialContent"  id="questionMaterialContent" cols="50" rows="3" maxlength="1000" cssStyle="display: none;"></s:textarea>
    				</td>
    			</tr>
    			<tr>
    				<td class="tdLeft">试题题干</td>
    				<td class="tdRight">
    					<s:textarea name="question.content" id="questionContent" cols="50" rows="3" maxlength="1000" ></s:textarea>
					</td>
    			</tr>
    			<tr>
    				<td class="tdLeft">试题类型</td>
    				<td class="tdRight">
    					<label><input type="radio" name="questionType" id="single" value="0"/>单选</label>
    					<label><input type="radio" name="questionType" id="double" value="1" />多选</label>
    					<label><input type="radio" name="questionType" id="subjective" value="2" />主观题</label>
					</td>
    			</tr>
    		<!-- 客观题 -->
    			<tr class="objectiveItem">
    				<td class="tdLeft">选项个数</td>
    				<td class="tdRight">
    					<s:select name="question.answerNumber" id="optionNum" list="#{'2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8'}" cssClass="selectCss"></s:select>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项A</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionA" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项B</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionB" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项C</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionC" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项D</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionD" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项E</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionE" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项F</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionF" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项G</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionG" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objective">
    				<td class="tdLeft">选项H</td>
    				<td class="tdRight">
    					<s:textarea name="question.optionH" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr class="objectiveItem">
    				<td class="tdLeft">
    					试题答案
    				</td>
    				<td id="answerTd" class="tdRight">
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerA" value="A"/>A</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="B"/>B</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="C"/>C</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="D"/>D</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="E"/>E</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="F"/>F</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="G"/>G</label>&nbsp;&nbsp;
    					<label class="objective"><input type="checkbox" name="objectiveAnswer" id="objectiveAnswerB" value="H"/>H</label>
    				</td>
    			</tr>
    		<!-- 主观题 -->
    			<tr class="subjectiveItem">
    				<td class="tdLeft">
    					试题答案
    				</td>
    				<td class="tdRight">
    					<s:textarea name="question.subjectiveAnswer" id="subjectiveAnswer" cols="50" rows="3"></s:textarea>
    				</td>
    			</tr>
    			<tr>
    				
    				<td colspan="2">
    					<input type="submit" value="保存" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="返回" id="backButton"/>
					</td>
    			</tr>
    			<s:hidden name="question.id" id="id" value="%{question.id}"></s:hidden>
    			<s:hidden id="courseName" value="%{question.courseName}"></s:hidden>
    			<s:hidden id="gradeLevel" name="gradeLevel" value="%{gradeLevel}" ></s:hidden>
    			<s:hidden id="questionType" value="%{question.questionType}"></s:hidden>
    			<s:hidden id="objectiveAnswer" value="%{question.objectiveAnswer}"></s:hidden>
    			<s:hidden id="questionMaterialType" value="%{questionMaterialType}"></s:hidden>
    		</s:form>
    	</table>
    	
    	<br /><br /><br />
    </div>
  </body>
</html>

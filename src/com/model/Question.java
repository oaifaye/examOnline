package com.model;

import com.base.BaseEntity;

public class Question extends BaseEntity {
	private String gradeID;
	private String gradeName;
	private String gradeLevel;
	private String courseID;
	private String courseName;
	private String title;
	private String questionType;
	private String questionMaterialID;
	private String questionMaterialContent;
	private String content;
	private Integer answerNumber;
	private String objectiveAnswer;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String optionE;
	private String optionF;
	private String optionG;
	private String optionH;
	private String isDelete;
	private String subjectiveAnswer;
	private String createrName;
	//学生的答案，存在Session里，不与数据库交互
	private String tempAnswer;
	//每道题的分数，存在Session里，方便计算分数，不与数据库交互
	private int score; 
	
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public String getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionMaterialID() {
		return questionMaterialID;
	}
	public void setQuestionMaterialID(String questionMaterialID) {
		this.questionMaterialID = questionMaterialID;
	}

	public String getQuestionMaterialContent() {
		return questionMaterialContent;
	}
	public void setQuestionMaterialContent(String questionMaterialContent) {
		this.questionMaterialContent = questionMaterialContent;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAnswerNumber() {
		return answerNumber;
	}
	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}
	
	public String getObjectiveAnswer() {
		return objectiveAnswer;
	}
	public void setObjectiveAnswer(String objectiveAnswer) {
		this.objectiveAnswer = objectiveAnswer;
	}
	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getOptionE() {
		return optionE;
	}
	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}
	public String getOptionF() {
		return optionF;
	}
	public void setOptionF(String optionF) {
		this.optionF = optionF;
	}
	public String getOptionG() {
		return optionG;
	}
	public void setOptionG(String optionG) {
		this.optionG = optionG;
	}
	public String getOptionH() {
		return optionH;
	}
	public void setOptionH(String optionH) {
		this.optionH = optionH;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getSubjectiveAnswer() {
		return subjectiveAnswer;
	}
	public void setSubjectiveAnswer(String subjectiveAnswer) {
		this.subjectiveAnswer = subjectiveAnswer;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getTempAnswer() {
		return tempAnswer;
	}
	public void setTempAnswer(String tempAnswer) {
		this.tempAnswer = tempAnswer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}

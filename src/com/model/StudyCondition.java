package com.model;

import com.base.BaseEntity;

public class StudyCondition extends BaseEntity {
	private String studentID;
	private String courseID;
	private String gradeID;
	private String paperID;
	private String examState;
	private int score;
	private String isDelete;
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID; 
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getGradeID() {
		return gradeID;
	}
	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}
	public String getPaperID() {
		return paperID;
	}
	public void setPaperID(String paperID) {
		this.paperID = paperID;
	}
	public String getExamState() {
		return examState;
	}
	public void setExamState(String examState) {
		this.examState = examState;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}

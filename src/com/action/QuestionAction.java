package com.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.Page;
import com.model.CourseInfo;
import com.model.GradeInfo;
import com.model.Manager;
import com.model.Question;
import com.model.QuestionMaterial;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.baseInfo.BaseInfoService;
import com.service.question.QuestionService;

public class QuestionAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private BaseInfoService baseInfoService;
	private QuestionService questionService;
	private String gradeLevel;
	private List<GradeInfo> gradeInfoList;
	private List<CourseInfo> courseInfoList;
	private List<QuestionMaterial> questionMaterialList;
	private Page pageBean;
	private Question question;
	// private QuestionMaterial questionMaterial;
	private String questionMaterialID;
	private String questionType;
	private String objectiveAnswer;
	private String questionMaterialType;
	private String questionMaterialContent;
	private Integer currentPage;
	private String questionId;

	/** 初始化editQuestion.jsp */
	public String initQuestion() {
		if (!(null == gradeLevel || ("").equals(gradeLevel))) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("gradeLevel", gradeLevel);
				params.put("isDelete", "1");
				gradeInfoList = baseInfoService.findGradeInfoByParams(params);

				questionMaterialList = questionService
						.findQuestionMaterial(params);
				return SUCCESS;
			} catch (Exception e) {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	/** 试题添加 */
	public String addQuestion() {
		try {
			// 添加材料
			if (!(("0").equals(questionMaterialType))) {
				// 修改材料
				QuestionMaterial questionMaterial = new QuestionMaterial();
				questionMaterial.setContent(questionMaterialContent);
				if (("1").equals(questionMaterialType)) {
					questionMaterial.setId(questionMaterialID);
					questionService.updateQuestionMaterial(questionMaterial);
					// 新建材料
				} else if (("2").equals(questionMaterialType)) {
					questionMaterial.setGradeID(question.getGradeID());
					questionMaterial.setCourseID(question.getCourseID());
					questionMaterial.setGradeLevel(gradeLevel);
					questionMaterial.setIsDelete("1");

					questionMaterialID = questionService
							.proInsertQuestionMaterial(questionMaterial);
				}
				question.setQuestionMaterialID(questionMaterialID);
			}

			question.setGradeLevel(gradeLevel);
			question.setQuestionType(questionType);
			if(!("2".equals(questionType))){
				question.setObjectiveAnswer(objectiveAnswer.replace(" ", ""));
			}
			
			question.setIsDelete("1");
			Manager createBy = (Manager) ActionContext.getContext()
					.getSession().get("manager");
			if (null==question.getId()||("").equals(question.getId())) {
				//新建试题
				question.setCreateBy(createBy.getId());
				question.setCreateDate(new Date());
				questionService.addQuestion(question);
			} else {
				// 修改试题
				question.setUpdateBy(createBy.getId());
				question.setUpdateDate(new Date());
				questionService.updateQuestion(question);
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String listQuestion() {
		if (!(null == gradeLevel || ("").equals(gradeLevel))) {
			try {
				if (currentPage == null || currentPage < 0) {
					currentPage = 1;
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("gradeLevel", gradeLevel);
				params.put("isDelete", "1");
				pageBean = questionService.queryForPage(params, currentPage, 2);
				return SUCCESS;
			} catch (Exception e) {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	/** 修改时，初始化editQuestion.jsp页面 */
	public String editQuestion() {
		try {
			question = questionService.findQuestionById(questionId);
			// 准备年级下拉框
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("gradeLevel", question.getGradeLevel());
			params.put("isDelete", "1");
			gradeInfoList = baseInfoService.findGradeInfoByParams(params);
			// 准备下拉框
			questionMaterialList = questionService.findQuestionMaterial(params);
			// 判断是否有材料
			if (null == question.getQuestionMaterialID()
					|| "".equals(question.getQuestionMaterialID())) {
				questionMaterialType = "0";
			} else {
				questionMaterialType = "1";
				// 材料下拉框选中项
				questionMaterialID = question.getQuestionMaterialID();
				// 给材料文本框赋值
				questionMaterialContent = question.getQuestionMaterialContent();
			}
			gradeLevel=question.getGradeLevel();
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public BaseInfoService getBaseInfoService() {
		return baseInfoService;
	}

	public void setBaseInfoService(BaseInfoService baseInfoService) {
		this.baseInfoService = baseInfoService;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public List<GradeInfo> getGradeInfoList() {
		return gradeInfoList;
	}

	public void setGradeInfoList(List<GradeInfo> gradeInfoList) {
		this.gradeInfoList = gradeInfoList;
	}

	public List<CourseInfo> getCourseInfoList() {
		return courseInfoList;
	}

	public void setCourseInfoList(List<CourseInfo> courseInfoList) {
		this.courseInfoList = courseInfoList;
	}

	public List<QuestionMaterial> getQuestionMaterialList() {
		return questionMaterialList;
	}

	public void setQuestionMaterialList(
			List<QuestionMaterial> questionMaterialList) {
		this.questionMaterialList = questionMaterialList;
	}

	public Page getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page pageBean) {
		this.pageBean = pageBean;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	// public QuestionMaterial getQuestionMaterial() {
	// return questionMaterial;
	// }
	//
	// public void setQuestionMaterial(QuestionMaterial questionMaterial) {
	// this.questionMaterial = questionMaterial;
	// }

	public String getQuestionMaterialID() {
		return questionMaterialID;
	}

	public void setQuestionMaterialID(String questionMaterialID) {
		this.questionMaterialID = questionMaterialID;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getObjectiveAnswer() {
		return objectiveAnswer;
	}

	public void setObjectiveAnswer(String objectiveAnswer) {
		this.objectiveAnswer = objectiveAnswer;
	}

	public String getQuestionMaterialType() {
		return questionMaterialType;
	}

	public void setQuestionMaterialType(String questionMaterialType) {
		this.questionMaterialType = questionMaterialType;
	}

	public String getQuestionMaterialContent() {
		return questionMaterialContent;
	}

	public void setQuestionMaterialContent(String questionMaterialContent) {
		this.questionMaterialContent = questionMaterialContent;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

}

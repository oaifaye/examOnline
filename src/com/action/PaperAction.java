package com.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.common.Page;
import com.model.CourseInfo;
import com.model.GradeInfo;
import com.model.Manager;
import com.model.Paper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.baseInfo.BaseInfoService;
import com.service.paper.PaperService;

public class PaperAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String  singleType,
					itemNumSingle,
					itemScoreSingle,
					doubleType,
					itemNumDouble,
					itemScoreDouble,
					subjectiveType,
					itemNumSubjective,
					itemScoreSubjective,
					paperId,
					gradeLevel
					;
	
	private Paper paper;
	private Page pageBean;
	private Integer currentPage;
	private BaseInfoService baseInfoService;
	private PaperService paperService;
	private List<GradeInfo> gradeInfoList;
	private List<CourseInfo> courseInfoList;

	/** 初始化editPaper.jsp */
	public String initPaper() {
		if (!(null == gradeLevel || ("").equals(gradeLevel))) {
			try {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("gradeLevel", gradeLevel);
				params.put("isDelete", "1");
				gradeInfoList = baseInfoService.findGradeInfoByParams(params);
				return SUCCESS;
			} catch (Exception e) {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String listPaper() {
		if (!(null == gradeLevel || ("").equals(gradeLevel))) {
			try {
				if (currentPage == null || currentPage < 0) {
					currentPage = 1;
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("gradeLevel", gradeLevel);
				params.put("isDelete", "1");
				pageBean = paperService.queryForPage(params, currentPage, 2);
				return SUCCESS;
			} catch (Exception e) {
				return ERROR;
			}
		} else {
			return ERROR;
		}
	}

	public String addPaper() {
		try {
			Assert.notNull(paper);
			StringBuffer itemType = new StringBuffer();
			StringBuffer itemNum = new StringBuffer();
			StringBuffer itemScore = new StringBuffer();
			if (null != singleType) {
				// 单选
				itemType.append(singleType);
				itemNum.append(itemNumSingle);
				itemScore.append(itemScoreSingle);
			}
			if (null != doubleType) {
				// 多选
				itemType.append(",").append(doubleType);
				itemNum.append(",").append(itemNumDouble);
				itemScore.append(",").append(itemScoreDouble);
			}
			if (null != subjectiveType) {
				// 简答
				itemType.append(",").append(subjectiveType);
				itemNum.append(",").append(itemNumSubjective);
				itemScore.append(",").append(itemScoreSubjective);
			}
			paper.setItemType(itemType.toString());
			paper.setItemNum(itemNum.toString());
			paper.setItemScore(itemScore.toString());
			paper.setGradeLevel(gradeLevel);
			paper.setIsRelease("1");
			paper.setIsDelete("1");
			Manager loginManager = (Manager) ActionContext.getContext()
					.getSession().get("manager");
			if (null==paper.getId()||("").equals(paper.getId())) {
				//新建试题
				paper.setCreateBy(loginManager.getId());
				paper.setCreateDate(new Date());
				paperService.addPaper(paper);
			} else {
				// 修改试题
				paper.setUpdateBy(loginManager.getId());
				paper.setUpdateDate(new Date());
				paperService.updatePaper(paper);
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String editPaper(){
		try {
			paper = paperService.findPaperById(paperId);
			// 准备年级下拉框
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("gradeLevel", paper.getGradeLevel());
			params.put("isDelete", "1");
			gradeInfoList = baseInfoService.findGradeInfoByParams(params);
			// 准备下拉框
			gradeLevel=paper.getGradeLevel();
			
			//准备试题类型及数量
			String[] itemTypeArray = paper.getItemType().split(",");
			String[] itemNumArray = paper.getItemNum().split(",");
			String[] itemScoreArray = paper.getItemScore().split(",");
			for(int i = 0 ; i < itemTypeArray.length ; i++){
				if(itemTypeArray[i].equals("0")){
					singleType=itemTypeArray[i];
					itemNumSingle=itemNumArray[i];
					itemScoreSingle=itemScoreArray[i];
				}
				if(itemTypeArray[i].equals("1")){
					doubleType=itemTypeArray[i];
					itemNumDouble=itemNumArray[i];
					itemScoreDouble=itemScoreArray[i];
				}
				if(itemTypeArray[i].equals("2")){
					subjectiveType=itemTypeArray[i];
					itemNumSubjective=itemNumArray[i];
					itemScoreSubjective=itemScoreArray[i];
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	public String releasePaper(){
		try {
			paper = paperService.findPaperById(paperId);
			gradeLevel=paper.getGradeLevel();
			if(paper.getIsRelease().equals("1")){
			//发布试卷
				paper.setIsRelease("0");
			}else if(paper.getIsRelease().equals("0")){
			//取消发布试卷
				paper.setIsRelease("1");
			}
			paperService.updatePaper(paper);
			return SUCCESS;
		}catch (Exception e) {
			return ERROR;
		}
	}

	// ====================================================================================
	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public BaseInfoService getBaseInfoService() {
		return baseInfoService;
	}

	public void setBaseInfoService(BaseInfoService baseInfoService) {
		this.baseInfoService = baseInfoService;
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

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public PaperService getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}

	public String getItemNumSingle() {
		return itemNumSingle;
	}

	public void setItemNumSingle(String itemNumSingle) {
		this.itemNumSingle = itemNumSingle;
	}

	public String getItemScoreSingle() {
		return itemScoreSingle;
	}

	public void setItemScoreSingle(String itemScoreSingle) {
		this.itemScoreSingle = itemScoreSingle;
	}

	public String getItemNumDouble() {
		return itemNumDouble;
	}

	public void setItemNumDouble(String itemNumDouble) {
		this.itemNumDouble = itemNumDouble;
	}

	public String getItemScoreDouble() {
		return itemScoreDouble;
	}

	public void setItemScoreDouble(String itemScoreDouble) {
		this.itemScoreDouble = itemScoreDouble;
	}

	public String getItemNumSubjective() {
		return itemNumSubjective;
	}

	public void setItemNumSubjective(String itemNumSubjective) {
		this.itemNumSubjective = itemNumSubjective;
	}

	public String getItemScoreSubjective() {
		return itemScoreSubjective;
	}

	public void setItemScoreSubjective(String itemScoreSubjective) {
		this.itemScoreSubjective = itemScoreSubjective;
	}

	public Page getPageBean() {
		return pageBean;
	}

	public void setPageBean(Page pageBean) {
		this.pageBean = pageBean;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public String getSingleType() {
		return singleType;
	}

	public void setSingleType(String singleType) {
		this.singleType = singleType;
	}

	public String getDoubleType() {
		return doubleType;
	}

	public void setDoubleType(String doubleType) {
		this.doubleType = doubleType;
	}

	public String getSubjectiveType() {
		return subjectiveType;
	}

	public void setSubjectiveType(String subjectiveType) {
		this.subjectiveType = subjectiveType;
	}

}

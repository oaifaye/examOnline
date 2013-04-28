package com.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;

import com.model.CourseInfo;
import com.model.GradeInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.service.baseInfo.BaseInfoService;

@SuppressWarnings("serial")
public class BaseInfoAction extends ActionSupport {

	private BaseInfoService baseInfoService;
	private GradeInfo gradeInfo;
	private CourseInfo courseInfo;
	private List<GradeInfo> gradeInfoList;
	private List<CourseInfo> courseInfoList;
	private Map<String ,List<CourseInfo>> courseInfoMap=new HashMap<String ,List<CourseInfo>>();
	
	/**初始化editbaseInfo.jsp*/
	public String initBaseInfo(){
		HashMap<String, Object> params = new HashMap<String ,Object>();
		params.put("isDelete", "1");
		//年级
		gradeInfoList=baseInfoService.findAllGradeInfo(params);
		//课程二级下拉菜单
		for(int i=0;i<gradeInfoList.size();i++){
			params.put("gradeID", gradeInfoList.get(i).getId());
			baseInfoService.findCourseInfoByGradeID(params);
			courseInfoMap.put(gradeInfoList.get(i).getId(), baseInfoService.findCourseInfoByGradeID(params));
		}
		return SUCCESS;
	}
	/**年级添加*/
	public String addBaseGradeInfo(){
		Assert.notNull(gradeInfo);
		try{
			gradeInfo.setIsDelete("1");
			baseInfoService.addGradeInfo(gradeInfo);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}

	}
	
	/**课程添加*/
	public String addBaseCourseInfo(){
		Assert.notNull(courseInfo);
		try{
			courseInfo.setIsDelete("1");
			baseInfoService.addCourseInfo(courseInfo);
			return SUCCESS;
		}catch(Exception e){
			return ERROR;
		}
	}
	
	public BaseInfoService getBaseInfoService() {
		return baseInfoService;
	}

	public void setBaseInfoService(BaseInfoService baseInfoService) {
		this.baseInfoService = baseInfoService;
	}

	public GradeInfo getGradeInfo() {
		return gradeInfo;
	}

	public void setGradeInfo(GradeInfo gradeInfo) {
		this.gradeInfo = gradeInfo;
	}

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}
	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
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
	public Map<String, List<CourseInfo>> getCourseInfoMap() {
		return courseInfoMap;
	}
	public void setCourseInfoMap(Map<String, List<CourseInfo>> courseInfoMap) {
		this.courseInfoMap = courseInfoMap;
	}
	
}

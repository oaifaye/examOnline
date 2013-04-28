package com.service.baseInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.courseInfo.CourseInfoDAO;
import com.dao.gradeInfo.GradeInfoDAO;
import com.model.CourseInfo;
import com.model.GradeInfo;

public class BaseInfoServiceImpl implements BaseInfoService {

	private GradeInfoDAO gradeInfoDAO;
	private CourseInfoDAO courseInfoDAO;

	//年级
	public void addGradeInfo(GradeInfo entity){
		gradeInfoDAO.insert(entity);
	}
	
	public List<GradeInfo> findAllGradeInfo(Map<String, Object> params){
		return gradeInfoDAO.findAllGradeInfo(params);
	}
	
	public List<GradeInfo> findGradeInfoByParams(Map<String, Object> params) {
		return gradeInfoDAO.findByParams(params);
	}
	
	//课程	
	public void addCourseInfo(CourseInfo entity){
		courseInfoDAO.insert(entity);
	}
	
	public List<CourseInfo> findCourseInfoByGradeID(Map<String, Object> params){
		return courseInfoDAO.findByParams(params);
	}
	
	
	
	//dwr
	public Map<String, String> findCourseInfoByGradeID(ArrayList<String> array){
		
		
		List<CourseInfo> courseInfoList;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("gradeID", array.get(0));
			params.put("isDelete", array.get(1));
			courseInfoList=courseInfoDAO.findByParams(params);
			Map<String, String> courseInfoMap=new HashMap<String, String>();;
			for(int i=0;i<courseInfoList.size();i++){
				courseInfoMap.put(courseInfoList.get(i).getId(), courseInfoList.get(i).getCourseName());
			}
			return courseInfoMap;
	}
	
//	public String courseSelected(String ){
//		
//	}
	
//	================================================================
	public GradeInfoDAO getGradeInfoDAO() {
		return gradeInfoDAO;
	}

	public void setGradeInfoDAO(GradeInfoDAO gradeInfoDAO) {
		this.gradeInfoDAO = gradeInfoDAO;
	}

	public CourseInfoDAO getCourseInfoDAO() {
		return courseInfoDAO;
	}

	public void setCourseInfoDAO(CourseInfoDAO courseInfoDAO) {
		this.courseInfoDAO = courseInfoDAO;
	}
	
	
}

package com.service.baseInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.model.CourseInfo;
import com.model.GradeInfo;

public interface BaseInfoService {
	//年级
	public void addGradeInfo(GradeInfo entity);
	public List<GradeInfo> findAllGradeInfo(Map<String, Object> params) ;
	public List<GradeInfo> findGradeInfoByParams(Map<String, Object> params);
	//课程
	public void addCourseInfo(CourseInfo entity);
	public List<CourseInfo> findCourseInfoByGradeID(Map<String, Object> params);
	public Map<String, String> findCourseInfoByGradeID(ArrayList<String> array);
}

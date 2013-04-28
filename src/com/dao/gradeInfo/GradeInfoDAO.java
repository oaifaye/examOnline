package com.dao.gradeInfo;

import java.util.List;
import java.util.Map;

import com.base.BaseDao;
import com.model.GradeInfo;

public interface GradeInfoDAO extends BaseDao<GradeInfo> {
	
	public List<GradeInfo> findAllGradeInfo(Map<String, Object> params) ;
}

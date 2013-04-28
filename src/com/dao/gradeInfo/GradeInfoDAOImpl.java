package com.dao.gradeInfo;

import java.util.List;
import java.util.Map;

import com.base.IbatisGenericDaoImpl;
import com.model.GradeInfo;

public class GradeInfoDAOImpl extends IbatisGenericDaoImpl<GradeInfo> implements
		GradeInfoDAO {

	public List<GradeInfo> findAllGradeInfo(Map<String, Object> params) {
		return super.findAll("GradeInfo.find", params);
	}

	

}

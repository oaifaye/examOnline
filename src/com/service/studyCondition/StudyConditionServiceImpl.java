package com.service.studyCondition;

import com.dao.studyCondition.StudyConditionDAO;
import com.model.StudyCondition;

public class StudyConditionServiceImpl implements StudyConditionService {
	StudyConditionDAO studyConditionDAO;

	@Override
	public void addStudyCondition(StudyCondition studyCondition) {
		studyConditionDAO.insert(studyCondition);
	}

	public StudyConditionDAO getStudyConditionDAO() {
		return studyConditionDAO;
	}

	public void setStudyConditionDAO(StudyConditionDAO studyConditionDAO) {
		this.studyConditionDAO = studyConditionDAO;
	}

}

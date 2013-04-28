package com.service.question;

import java.util.List;
import java.util.Map;

import com.common.Page;
import com.model.Question;
import com.model.QuestionMaterial;

public interface QuestionService {

	public void addQuestion(Question entity);
	public Question findQuestionById(String questionId);
	public void updateQuestion(Question entity);
	public String proInsertQuestionMaterial(QuestionMaterial questionMaterial);
	public List<QuestionMaterial> findQuestionMaterial(Map<String, Object> params);
	public void updateQuestionMaterial(QuestionMaterial questionMaterial);
	public Page queryForPage(Map<String, Object> params, int currentPage,int pageSize);
}
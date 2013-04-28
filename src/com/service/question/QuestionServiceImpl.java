package com.service.question;

import java.util.List;
import java.util.Map;

import com.common.Page;
import com.dao.question.QuestionDAO;
import com.dao.questionMaterial.QuestionMaterialDAO;
import com.model.Question;
import com.model.QuestionMaterial;

public class QuestionServiceImpl implements QuestionService {

	private QuestionDAO questionDAO;
	private QuestionMaterialDAO questionMaterialDAO;
	
	@Override
	public void addQuestion(Question entity) {
		questionDAO.insert(entity);
		
	}
	
	public Question findQuestionById(String questionId){
		return questionDAO.findByPrimaryKey(questionId);
	}
	
	public void updateQuestion(Question entity){
		questionDAO.update(entity);
	}
	
	public Page queryForPage(Map<String, Object> params, int currentPage,int pageSize){
		String sqlid = "Question.find";
		String sqlids="Question.getQuestionNum";
		int pageNo = Page.countOffset(pageSize, currentPage);
		int allRow = questionDAO.findRowNum(sqlids, params);
		//获取总页数
		int totalPage = Page.countTotalPage(pageSize, allRow);
		List<Question> questionList = questionDAO.findByPage(sqlid, params, pageNo, pageSize);
		Page page = new Page();
		page.setAllRow(allRow);
		page.setCurrentPage(currentPage);
		page.setList(questionList);
		page.setPageSize(pageSize);
		page.setTotalPage(totalPage);
		return page;
	}
	
	public String proInsertQuestionMaterial(QuestionMaterial questionMaterial){
		return questionMaterialDAO.proInsert(questionMaterial);
	}
	
	public List<QuestionMaterial> findQuestionMaterial(Map<String, Object> params){
		return questionMaterialDAO.findByParams(params);
	}
	
	public void updateQuestionMaterial(QuestionMaterial questionMaterial){
		questionMaterialDAO.update(questionMaterial);
	}

	
	
	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	public QuestionMaterialDAO getQuestionMaterialDAO() {
		return questionMaterialDAO;
	}

	public void setQuestionMaterialDAO(QuestionMaterialDAO questionMaterialDAO) {
		this.questionMaterialDAO = questionMaterialDAO;
	}

	
}

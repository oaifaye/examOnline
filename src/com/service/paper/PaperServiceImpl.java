package com.service.paper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.common.Page;
import com.common.RandomUtil;
import com.dao.paper.PaperDAO;
import com.dao.question.QuestionDAO;
import com.model.Paper;
import com.model.Question;

public class PaperServiceImpl implements PaperService {
	private PaperDAO paperDAO;
	private QuestionDAO questionDAO;

	@Override
	public void addPaper(Paper paper) {
		paperDAO.insert(paper);
	}

	// 分页查询
	public Page queryForPage(Map<String, Object> params, int currentPage,
			int pageSize) {
		String sqlid = "Paper.find";
		String sqlids = "Paper.getPaperNum";
		int pageNo = Page.countOffset(pageSize, currentPage);
		int allRow = paperDAO.findRowNum(sqlids, params);
		int totalPage = Page.countTotalPage(pageSize, allRow);
		List<Paper> paperList = paperDAO.findByPage(sqlid, params, pageNo,
				pageSize);
		// 获取总页数
		Page page = new Page();
		page.setAllRow(allRow);
		page.setCurrentPage(currentPage);
		page.setList(paperList);
		page.setPageSize(pageSize);
		page.setTotalPage(totalPage);
		return page;
	}

	// 学员答题时，初始化试卷
	public Paper initPaper(String paperId) {
		//Paper paper = new Paper();
		int itemNumSingle = 0;
		int itemNumDouble = 0;
		int itemNumSubjective = 0;
		int itemScoreSingle= 0;
		int itemScoreDouble =0;
		int itemScoreSubjective =0;
		Paper paper = paperDAO.findByPrimaryKey(paperId);
		String[] itemTypeArray=paper.getItemType().split(",");
		String[] itemNumArray=paper.getItemNum().split(",");
		String[] itemScoreArray=paper.getItemScore().split(",");
		//提取各个类型试题的题数与分数
		for(int i = 0 ; i < itemTypeArray.length ; i++){
			if(itemTypeArray[i].equals("0")){
				itemNumSingle=Integer.parseInt(itemNumArray[i]);
				itemScoreSingle = Integer.parseInt(itemScoreArray[i]);
			}
			if(itemTypeArray[i].equals("1")){
				itemNumDouble=Integer.parseInt(itemNumArray[i]);
				itemScoreDouble=Integer.parseInt(itemScoreArray[i]);
			}
			if(itemTypeArray[i].equals("2")){
				itemNumSubjective=Integer.parseInt(itemNumArray[i]);
				itemScoreSubjective=Integer.parseInt(itemScoreArray[i]);
			}
		}
		//计算每种题总题数
		String sqlids="Question.getQuestionNum";
		List<Question> questionList=new ArrayList<Question>();
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("courseID", paper.getCourseID());
		params.put("isDelete", "1");
		if(itemNumSingle!=0){
			params.put("questionType", "0");
			List<Question> singleQuestionList = findQuestionByNum(sqlids,itemNumSingle,params);
			//插入分数
			for(Question singleQuestion : singleQuestionList){
				singleQuestion.setScore(itemScoreSingle);
			}
			questionList.addAll(singleQuestionList);
		}
		if(itemNumDouble!=0){
			params.put("questionType", "1");
			List<Question> doubleQuestionList = findQuestionByNum(sqlids,itemNumSingle,params);
			//插入分数
			for(Question doubleQuestion : doubleQuestionList){
				doubleQuestion.setScore(itemScoreDouble);
			}
			questionList.addAll(doubleQuestionList);
		}
		if(itemNumSubjective!=0){
			params.put("questionType", "2");
			List<Question> subjectiveQuestionList = findQuestionByNum(sqlids,itemNumSingle,params);
			//插入分数
			for(Question subjectiveQuestion : subjectiveQuestionList){
				subjectiveQuestion.setScore(itemScoreSubjective);
			}
			questionList.addAll(subjectiveQuestionList);
		}
		
		paper.setQuestionList(questionList);
		return paper;
	}
	
	//用试卷中某类型试题的数量随机出List<Question>  (调用方法)
	private List<Question> findQuestionByNum(String sqlids,int itemNum,Map<String, Object> params){
		//为随机准备最大数
		int allRow = questionDAO.findRowNum(sqlids, params);
		RandomUtil random = new RandomUtil(allRow, itemNum);
		//开始随机
		Set<Integer> randomSet = random.getResultSet();
		Iterator<Integer> iterator = randomSet.iterator();
		List<Question> questionList=questionDAO.findByParams(params);
		List<Question> questionListRandom= new ArrayList<Question>();
		while(iterator.hasNext()){
			//按数字选取List中的Question
			Question question = questionList.get(iterator.next());
			questionListRandom.add(question);
		}
		return questionListRandom;
	}

	@Override
	public Paper findPaperById(String paperId) {
		Paper paper = paperDAO.findByPrimaryKey(paperId);
		return paper;
	}

	@Override
	public void updatePaper(Paper paper) {
		paperDAO.update(paper);
	}

	public PaperDAO getPaperDAO() {
		return paperDAO;
	}

	public void setPaperDAO(PaperDAO paperDAO) {
		this.paperDAO = paperDAO;
	}

	public QuestionDAO getQuestionDAO() {
		return questionDAO;
	}

	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

}

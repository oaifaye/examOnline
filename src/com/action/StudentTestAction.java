package com.action;

import java.util.List;

import com.model.Paper;
import com.model.Question;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.paper.PaperService;
import com.service.question.QuestionService;

public class StudentTestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String paperId;
	private PaperService paperService;
	private QuestionService questionService;
	private Paper paper;
	private int itemNumSingle;
	private int itemScoreSingle;
	private int itemNumDouble;
	private int itemScoreDouble;
	private int itemNumSubjective;
	private int itemScoreSubjective;
	private List<Question> questionList;
	private int currentIndex;
	private Question question;
	private String tempAnswer;
	private int lastScore;

	// 初始化试卷
	public String initPaper() {
		// 确定paper,并将paperList存入Session
		paper = initQuestionListInSession();
		if (paper.getQuestionList().size() > 0) {
			String[] itemTypeArray = paper.getItemType().split(",");
			String[] itemNumArray = paper.getItemNum().split(",");
			String[] itemScoreArray = paper.getItemScore().split(",");
			// 提取各个类型试题的题数与分数
			for (int i = 0; i < itemTypeArray.length; i++) {
				if (itemTypeArray[i].equals("0")) {
					itemNumSingle = Integer.parseInt(itemNumArray[i]);
					itemScoreSingle = Integer.parseInt(itemScoreArray[i]);
				}
				if (itemTypeArray[i].equals("1")) {
					itemNumDouble = Integer.parseInt(itemNumArray[i]);
					itemScoreDouble = Integer.parseInt(itemScoreArray[i]);
				}
				if (itemTypeArray[i].equals("2")) {
					itemNumSubjective = Integer.parseInt(itemNumArray[i]);
					itemScoreSubjective = Integer.parseInt(itemScoreArray[i]);
				}
			}
			question = paper.getQuestionList().get(0);
			// 初始化hidden
			currentIndex = 0;
			return "initQuestion";
		}else{
			return "exception";
		}
		
	}

	// 下一题
	public String nextQuestion() {
		questionList = gainQuestionListInSession();
		if (questionList != null) {
			// 记住本题答案
			controlQuestion();

			// 开始准备下一题
			if (currentIndex >= 0 && currentIndex < questionList.size() - 1) {
				currentIndex = currentIndex + 1;
			} else {
				currentIndex = questionList.size() - 1;
			}
			return "initQuestion";
		} else {
			return "listPaper";
		}

	}

	// 上一题
	public String previousQuestion() {
		questionList = gainQuestionListInSession();
		if (questionList != null) {
			// 记住本题答案
			controlQuestion();

			// 开始准备下一题
			if (currentIndex > 0 && currentIndex <= questionList.size() - 1) {
				currentIndex = currentIndex - 1;

			} else {
				currentIndex = 0;
			}
			return "initQuestion";
		} else {
			return "listPaper";
		}
	}

	// 上一题或下一题跳转到的action（主要为去掉paramater中的缓存）
	public String initQuestion() {
		questionList = gainQuestionListInSession();
		if (questionList != null) {
			question = questionList.get(currentIndex);
		}
		return "answering";
	}

	// 判卷
	public String marking() {
		questionList = gainQuestionListInSession();
		if (questionList != null) {
			// 记住本题答案
			controlQuestion();
			lastScore = 0;
			// questionList=gainQuestionListInSession();
			for (Question q : questionList) {
				if (!q.getQuestionType().equals("2")
						&& q.getTempAnswer() != null
						&& q.getTempAnswer().equals(q.getObjectiveAnswer())) {
					// 客观题判卷
					lastScore += q.getScore();
				}
			}
			ActionContext.getContext().getSession().remove("paper");
			return "initResult";
		} else {
			lastScore = 0;
			return "listPaper";
		}

	}

	// 初始化result.jsp，以免刷新后跳转别处
	public String initResult() {
		return "result";
	}

	// 退出答题
	public String quitPaper() {
		questionList = null;
		ActionContext.getContext().getSession().remove("paper");
		return "listPaper";
	}

	// 记住本题答案（调用方法）
	public void controlQuestion() {
		if (currentIndex >= 0 && currentIndex < questionList.size()) {

			if (tempAnswer != null) {
				// 记住本题答案
				Question q = questionList.get(currentIndex);
				if (!(questionList.get(currentIndex).getQuestionType()
						.equals("2"))) {
					// 本题为客观题
					tempAnswer = tempAnswer.replace(" ", "");
					q.setTempAnswer(tempAnswer);
				}
				// 记住本题答案

				questionList.set(currentIndex, q);
			}
		}
		tempAnswer = null;
	}

	// 答题时从Session中获取questionList（调用方法）
	public List<Question> gainQuestionListInSession() {
		Paper paperInSession = (Paper) ActionContext.getContext().getSession()
				.get("paper");
		// 判断questionList是否在Session中
		if (paperInSession != null && paperInSession.getQuestionList() != null) {
			questionList = paperInSession.getQuestionList();
		} else {
			questionList = null;
		}
		return questionList;
	}

	// 获取Session中的questionList，如Session中没有paper，新建一个paper（调用方法）
	public Paper initQuestionListInSession() {
		paper = (Paper) ActionContext.getContext().getSession().get("paper");
		// 判断questionList是否在Session中
		if (paper != null && paper.getQuestionList() != null) {
			questionList = paper.getQuestionList();
			// Session中有，但paper的id有变化（新的一张试卷）
			if (!paper.getId().equals(paperId)) {
				paper = paperService.initPaper(paperId);
				ActionContext.getContext().getSession().put("paper", paper);
			}
		} else {
			// 在Session中新建一个paper(只在初始化才会用到)
			paper = paperService.initPaper(paperId);
			ActionContext.getContext().getSession().put("paper", paper);
		}
		return paper;
	}

	// ================================================================================================
	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public PaperService getPaperService() {
		return paperService;
	}

	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public int getItemNumSingle() {
		return itemNumSingle;
	}

	public void setItemNumSingle(int itemNumSingle) {
		this.itemNumSingle = itemNumSingle;
	}

	public int getItemScoreSingle() {
		return itemScoreSingle;
	}

	public void setItemScoreSingle(int itemScoreSingle) {
		this.itemScoreSingle = itemScoreSingle;
	}

	public int getItemNumDouble() {
		return itemNumDouble;
	}

	public void setItemNumDouble(int itemNumDouble) {
		this.itemNumDouble = itemNumDouble;
	}

	public int getItemScoreDouble() {
		return itemScoreDouble;
	}

	public void setItemScoreDouble(int itemScoreDouble) {
		this.itemScoreDouble = itemScoreDouble;
	}

	public int getItemNumSubjective() {
		return itemNumSubjective;
	}

	public void setItemNumSubjective(int itemNumSubjective) {
		this.itemNumSubjective = itemNumSubjective;
	}

	public int getItemScoreSubjective() {
		return itemScoreSubjective;
	}

	public void setItemScoreSubjective(int itemScoreSubjective) {
		this.itemScoreSubjective = itemScoreSubjective;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getTempAnswer() {
		return tempAnswer;
	}

	public void setTempAnswer(String tempAnswer) {
		this.tempAnswer = tempAnswer;
	}

	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

}

package com.action;

import java.util.ArrayList;
import java.util.List;
import com.model.Member;
import com.model.Paper;
import com.model.Question;
import com.model.StudyCondition;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.paper.PaperService;
import com.service.question.QuestionService;
import com.service.studyCondition.StudyConditionService;

public class StudentTestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String  paperId, 
					userName,//作为Session中paper的索引
					tempAnswer
					;
	
	private int itemNumSingle,
				itemScoreSingle,
				itemNumDouble,
				itemScoreDouble,
				itemNumSubjective,
				itemScoreSubjective,
				currentIndex,
				needIndex,
				lastScore
				;
	
	private Question question;
	private Paper paper;
	private PaperService paperService;
	private QuestionService questionService;
	private StudyConditionService studyConditionService;
	private List<String> questionTypeList=new ArrayList<String>();
	private List<Question> questionList;
	

// 初始化试卷
	public String initPaper() {
	 // 确定paper,并将paperList存入Session
		paper = initQuestionListInSession();
		if (paper.getQuestionList().size() > 0) {
			String[] itemTypeArray = paper.getItemType().split(",");
			questionTypeList.clear();
			for(String s:itemTypeArray){
				questionTypeList.add(s);
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
	
//按索引定位试题
	public String gainQuestionByIndex() {
		questionList = gainQuestionListInSession();
		if (questionList != null) {
			// 记住本题答案
			controlQuestion();

			// 按索引开始准备下一题
			currentIndex = needIndex;
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
		//记录成绩
			StudyCondition studyCondition = new StudyCondition();
			Member member = (Member)ActionContext.getContext().getSession().get("member");
			//获取paper
			gainPaperInSession();
			studyCondition.setStudentID(member.getId());
			studyCondition.setCourseID(paper.getCourseID());
			studyCondition.setGradeID(paper.getGradeID());
			studyCondition.setPaperID(paper.getId());
			studyCondition.setScore(lastScore);
			studyCondition.setIsDelete("1");
			studyConditionService.addStudyCondition(studyCondition);
			
			ActionContext.getContext().getSession().remove(userName);
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
		userName=String.valueOf(ActionContext.getContext().getSession().get("member"));
		if(userName!=null){
			ActionContext.getContext().getSession().remove(userName);
		}
		return "listPaper";
	}

// 记住本题答案（调用方法）
	private void controlQuestion() {
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
	private List<Question> gainQuestionListInSession() {
		gainPaperInSession();
		
		// 判断questionList是否在Session中
		if (paper != null && paper.getQuestionList() != null) {
			questionList = paper.getQuestionList();
		} else {
			questionList = null;
		}
		return questionList;
	}

// 初始化页面时，获取Session中的questionList，如Session中没有paper，新建一个paper（调用方法）
	private Paper initQuestionListInSession() {
		gainPaperInSession();
		// 判断questionList是否在Session中
		if (paper != null && paper.getQuestionList() != null) {
			questionList = paper.getQuestionList();
			// Session中有，但paper的id有变化（新的一张试卷）
			if (!paper.getId().equals(paperId)) {
				paper = paperService.initPaper(paperId);
				ActionContext.getContext().getSession().put(userName, paper);
			}
		} else {
			// 在Session中新建一个paper(只在初始化才会用到)
			paper = paperService.initPaper(paperId);
			ActionContext.getContext().getSession().put(userName, paper);
		}
		return paper;
	}
	
//从Session中根据“member”获取paper（调用方法）
	private void gainPaperInSession(){
		Member member=(Member) ActionContext.getContext().getSession().get("member");
		userName=member.getUserName();
		if(null!=userName){
			paper = (Paper) ActionContext.getContext().getSession()
					.get(userName);
		}
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

	public StudyConditionService getStudyConditionService() {
		return studyConditionService;
	}

	public void setStudyConditionService(StudyConditionService studyConditionService) {
		this.studyConditionService = studyConditionService;
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

	public int getNeedIndex() {
		return needIndex;
	}

	public void setNeedIndex(int needIndex) {
		this.needIndex = needIndex;
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

	public List<String> getQuestionTypeList() {
		return questionTypeList;
	}

	public void setQuestionTypeList(List<String> questionTypeList) {
		this.questionTypeList = questionTypeList;
	}

}

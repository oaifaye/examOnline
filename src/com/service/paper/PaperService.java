package com.service.paper;

import java.util.Map;

import com.common.Page;
import com.model.Paper;

public interface PaperService {

	public void addPaper(Paper paper);
	
	//分页查询
	public Page queryForPage(Map<String, Object> params, int currentPage,int pageSize);
	
	public Paper findPaperById(String paperId);
	
	public void updatePaper(Paper paper);
	// 学员答题时，初始化试卷
	public Paper initPaper(String paperId);
}

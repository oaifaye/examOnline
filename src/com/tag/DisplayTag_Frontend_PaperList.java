package com.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.Page;
import com.model.Paper;
import com.service.paper.PaperService;

public class DisplayTag_Frontend_PaperList extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String gradeLevel;

	@SuppressWarnings("unchecked")
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		WebApplicationContext wc = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
		PaperService paperService = (PaperService) wc.getBean("paperService");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gradeLevel", gradeLevel);
		params.put("isDelete", "1");
		List<Paper> pageList = paperService.queryForPage(params, 1, 5).getList();
		
		try {
			
			if(pageList.size()!=0&&pageList!=null){
				int s;
				if(pageList.size()<5){
					s=pageList.size();
				}else{
					s=5;
				}
				for(int i=0;i<s;i++){
					out.println("<a href=\"initPaperFrontend.action?gradeLevel="+gradeLevel+"&paperId="+pageList.get(i).getId()+"\" >"+pageList.get(i).getTitle()+"</a>");
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			return super.doEndTag();
	}

	public String getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	
	
}

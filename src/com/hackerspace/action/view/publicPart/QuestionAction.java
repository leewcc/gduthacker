package com.hackerspace.action.view.publicPart;

import java.util.Map;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Question;
import com.hackerspace.service.QuestionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QuestionAction extends ActionSupport{
	private int page;
	private int id;
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String selectQuestion() {
		try{
			//第一步：	创建常见问题服务
			QuestionService qs = new QuestionService();
			
			//第二步：	获取常见问题
			PageElem<Question> pe = qs.selectQuestion(page);
			
			//第三步：	保存到请求
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("questions", pe);
			
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	

	
	public String seeQuestion() {
		try{
			//第一步：	创建常见问题服务
			QuestionService qs = new QuestionService();
			
			//第二步：	获取问题
			Question q = qs.findQuestion(id);
			
			//第三步：	保存到请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("question", q);
			
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
}

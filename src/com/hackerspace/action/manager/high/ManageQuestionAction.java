package com.hackerspace.action.manager.high;

import java.security.spec.DSAGenParameterSpec;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Question;
import com.hackerspace.service.MessageService;
import com.hackerspace.service.QuestionService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ManageQuestionAction extends ActionSupport implements ModelDriven{
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String selectQuestion() {
		try{
			//第一步：	创建常见问题服务
			QuestionService qs = new QuestionService();
			
			//第二步：	执行查询的方法
			PageElem<Question> pe = qs.selectQuestion(page);
			
			//第三步：	将数据放进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("questions", pe);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}	
	}
	
	private Question question;
	private boolean status;  //true-发布  false-编辑
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void validateEditQuestion() {
		String content = question.getContent();
		String answer = question.getAnswer();
		
		if(content == null || "".equals(content))
			this.addFieldError("content", "请输入问题");
		else{
			if(content.length() > 250)
				this.addFieldError("content", "问题不能超过250个字");
		}
		
		if(answer == null || "".equals(answer))
			this.addFieldError("answer", "请输入答案");
		else{
			if(answer.length() > 250)
				this.addFieldError("answer", "答案不能超过250个字");
		}
	}
	
	/**
	 * 方法说明：	发布或修改常见问题
	 * @return
	 */
	public String editQuestion() {
		try{
			//第一步：	创建问题服务
			QuestionService qs = new QuestionService();
						
			//第三步：	执行发布或修改的方法
			if(status){
				
				question.setDate(new Timestamp(new Date().getTime()));
				qs.create(question);
			}else{
				Question q = qs.findQuestion(question.getId());
				q.setContent(question.getContent());
				q.setAnswer(question.getAnswer());
				q.setDate(new Timestamp(new Date().getTime()));
				qs.update(q);
			}
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String deleteQuestion() {
		try{
			//第一步：	创建问题服务
			QuestionService qs = new QuestionService();
			
			//第二步：	执行删除留言
			qs.delete(question.getId());
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String findQuestion() {
		try{
			//第一步：	创建留言服务
			QuestionService qs = new QuestionService();
			
			//第二步：	获取留言
			question = qs.findQuestion(question.getId());
			
			//第三步：	将留言保存进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("question", question);
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}

	@Override
	public Object getModel() {
		if(question == null)
			question = new Question();
		
		return question;
	}
}

package com.hackerspace.service;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.MessageDao;
import com.hackerspace.dao.QuestionDao;
import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Question;

public class QuestionService {
	private BaseDao bd;
	private QuestionDao qd;
	
	public QuestionService() {
		bd = new BaseDao<>();
	}
	
	/**
	 * 方法说明：	查询常见问题
	 * @param page-页数
	 * @throws Exception
	 */
	public PageElem<Question> selectQuestion(int page)throws Exception {
		//第一步：	创建留言对象
				PageElem<Question> ques = new PageElem<>();
				ques.setCurrentPage(page);
				ques.setRows(QuestionDao.selectCount());
				ques.setPageShow(10);
				
				//第二步： 查询留言
				ques.setPageElem(QuestionDao.selectQuestion(ques.getStartSearch(), ques.getPageShow()));
				
				return ques;
	}
	
	public void create(Question q)  {
		bd.create(q);
	}
	
	public void update(Question q) {
		bd.update(q);
	}
	
	public void delete(int id) {
		Question q = (Question)bd.find(Question.class, id);
		bd.delete(q);
	}
	
	public Question findQuestion(int id){
		return (Question)bd.find(Question.class, id);
	}
}

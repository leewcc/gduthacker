package com.hackerspace.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.MessageDao;
import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;

public class MessageService {
	private BaseDao bd;
	private MessageDao md;
	public MessageService(){
		bd = new BaseDao<>();
	}
	
	
	public void update(List<Message> messages) throws Exception{
		bd.update(messages);
	}
	
	public PageElem<Message> messageBox(int page, User u) throws Exception{
		// 第一步： 创建留言对象
		PageElem<Message> mess = new PageElem<>();
		mess.setCurrentPage(page);
		mess.setRows(MessageDao.messageBoxCount(u));
		mess.setPageShow(10);
		mess.setPageElem(MessageDao.messageBox(u, mess.getStartSearch(), mess.getPageShow()));
		return mess;
	}
	
	public int getUnReadMess(User u)throws Exception {
		return MessageDao.getUnreadMess(u);
	}
	
	/**
	 * 方法说明：	发送留言
	 * @param m-待发送的留言
	 * @throws Exception
	 */
	public void SendMessage(Message m) throws Exception{
		bd.create(m);
	}
	
	/**
	 * 方法说明：	查询我的留言
	 * @param u-要查询的用户
	 * @return	
	 * @throws Exception
	 */
	public PageElem<Message> selectMyMess(User u, int page) throws Exception{
		// 第一步： 创建留言对象
		PageElem<Message> mess = new PageElem<>();
		mess.setCurrentPage(page);
		mess.setRows(MessageDao.selectMyMessCount(u));
		mess.setPageShow(10);
		mess.setPageElem(MessageDao.selectMyMess(u,mess.getStartSearch(), mess.getPageShow()));
		return mess;
	}
	
	/**
	 * 方法说明：	后台查询待回复的留言
	 * @param page-要查询的页数
	 * @return
	 * @throws Exception
	 */
	public PageElem<Message> selectMessage(int page) throws Exception{
		//第一步：	创建留言对象
		PageElem<Message> mess = new PageElem<>();
		mess.setCurrentPage(page);
		mess.setRows(MessageDao.selectCountMess());
		mess.setPageShow(10);
		
		//第二步： 查询留言
		mess.setPageElem(MessageDao.selectMess(mess.getStartSearch(), mess.getPageShow()));
		
		return mess;
	}
	
	/**
	 * 方法说明：	回复留言
	 * @param id-被回复的留言id
	 * @param reply-回复的留言
	 * @throws Exception
	 */
	public void replyMessage(int id, Message reply) throws Exception{
		//第一步：	根据id找到被回复的留言
		Message m = (Message)bd.find(Message.class, id);
		m.getReply().add(reply);
		
		//第二步：	插入回复的留言
		bd.create(reply);
		
		//第三步：	更新关系
		bd.update(m);
	}
	
	/**
	 * 方法说明：	删除留言
	 * @param id-被删除的留言id
	 * @throws Exception
	 */
	public void deleteMessage(int id) throws Exception{
		Message m = (Message)bd.find(Message.class, id);
		bd.delete(m);
	}
	
	/**
	 * 方法说明：	给用户群发信息
	 * @param content-消息内容
	 * @param user-被留言的用户
	 * @throws Exception
	 */
	public void messToUser(String content, List<Integer> user) throws Exception{
		List<Message> mess = new ArrayList<>();
		
		for(int id : user) {
			Message m = new Message();
			m.setContent(content);
			m.setStatus(3);
			m.setDate(new Timestamp(new Date().getTime()));
			User u = (User)bd.find(User.class, id);
			m.setUser(u);
			
			mess.add(m);
		}
		
		bd.createBatch(mess);
	}
	
	public Message find(int id) throws Exception{
		return (Message)bd.find(Message.class, id);
	}
}

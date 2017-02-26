package com.hackerspace.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.UserDao;
import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.util.MD5Util;


public class UserService {
	private final int COUNT = 10;
	BaseDao<User> bd = new BaseDao<>();
	
	/**
	 * 方法说明：查询待审核的注册用户
	 * @param page-请求页数
	 * @return	对应页数的用户
	 * @throws Exception
	 */
	public PageElem<User> selectRegister(int page) throws Exception{
		PageElem<User> pe = new PageElem<>();
		
		//第一步：	获取未审核注册请求的用户数量
		pe.setRows(UserDao.selectCountByRequest(1));
		pe.setCurrentPage(page);
		pe.setPageShow(COUNT);
		
		//第二步：	获取未审核注册请求的用户
		int star = pe.getStartSearch();
		pe.setPageElem(UserDao.selectByRequest(1, star, COUNT));
		
		return pe;
	}
	
	/**
	 * 方法说明：	管理员处理用户注册请求
	 * @param id-被处理的用户id
	 * @param isPass-是否通过标志
	 * @return	"null"-用户不存在		"success"-处理成功
	 * @throws Exception
	 */
	public String handleRegister(int id, boolean isPass) throws Exception{
		//第一步：	根据id获取用户
		User u = bd.find(User.class, id);
		
		//第二步：	判断用户是否存在
		if(u == null)
			return "null";
		
		//第三步：	执行处理审核
		if(isPass){
			u.setRequest(3);
			bd.update(u);
		}else{
			bd.delete(u);
		}
		
		Message m = new Message();
		m.setContent("您的注册请求已经通过，如果你对此有什么疑惑，请留言给我们，我们会耐心地解答你的问题，感谢你的加入！");
		m.setUser(u);
		m.setStatus(3);
		m.setDate(new Timestamp(new Date().getTime()));
		MessageService ms = new MessageService();
		ms.SendMessage(m);
		
		return "success";	
	}
	
	/**
	 * 方法说明：根据角色获取用户
	 * @param role
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public PageElem<User> selectByRole(int role, int page) throws Exception{
		PageElem<User> pe = new PageElem<>();
		
		//第一步：	获取未审核注册请求的用户数量
		pe.setRows(UserDao.selectCountByRole(role, 3));
		pe.setCurrentPage(page);
		pe.setPageShow(COUNT);
		
		//第二步：	获取未审核注册请求的用户
		int star = pe.getStartSearch();
		pe.setPageElem(UserDao.selectByRole(role, 3, star, COUNT));
		
		return pe;
	}
	
	public List<User> selectByRole(int role) throws Exception {
		return UserDao.selectByRole(role, 3, 0, UserDao.selectCountByRole(role, 3));
	}
	
	/**
	 * 方法说明：根据id获取指定用户
	 * @param id-用户id
	 * @return	对应的用户
	 * @throws Exception
	 */
	public User getUser(int id) throws Exception{
		return bd.find(User.class, id);
	}
	
	public User getUser(String card) throws Exception {
		return UserDao.selectByCard(card);
	}
	
	/**
	 * 方法说明：	删除指定id的用户
	 * @param id-用户id
	 * @throws Exception
	 */
	public boolean delete(int id) throws Exception{
		//第一步：	获取对应的用户
		User u = bd.find(User.class, id);
		
		//第二步：	删除用户
		bd.delete(u);
		
		u = bd.find(User.class, id);
		if(u != null)
			return false;
			
		return true;
	}
	
	/**
	 * 方法说明：	重置指定id的用户密码
	 * @throws Exception
	 */
	public void resetPassword(int id) throws Exception{
		//第一步：	获取对应的用户
		User u = bd.find(User.class, id);
				
		//第二步：	重置密码
		u.setPassword(MD5Util.GetMD5Code("123456"));
		
		//第二步：	删除用户
		bd.update(u);
	}
	
	/**
	 * 方法说明：	使用用户输入的姓名、登陆去获取用户
	 * @param name-姓名
	 * @param password-密码
	 * @return	用户
	 * @throws Exception
	 */
	public User login(String name, String password) throws Exception {
		//第一步：	获取用户
		return UserDao.login(name, password);
	}
	
	public String register(User user) throws Exception {
		//第一步：	根据用户学号获取用户
		User u = UserDao.selectByCard(user.getCard());
		
		//第二步：	判断用户是否存在，如果存在，则注册失败，用户已存在
		if(u != null){
			return "exist";
		}
		
		//第三步：	生成用户
		bd.create(user);
		return "success";
	}
	
	public void update(User user) throws Exception {
		bd.update(user);
	}

	public boolean updateUserPicture(User u) {
		UserDao ud=new UserDao();
		return ud.updateUserPicture(u);
	}
}

package com.hackerspace.action.view.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.hackerspace.model.Security;
import com.hackerspace.model.User;
import com.hackerspace.service.SecurityService;
import com.hackerspace.service.UserService;
import com.hackerspace.util.MD5Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PersonalUserAction extends ActionSupport implements ModelDriven{
	private String oldPass;			//旧密码
	private String newPass;			//新密码
	private String newPassA;		//第二次输入新密码
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private int id0;	//使用密保修改密码的问题
	private String answer0;		//使用密保修改密码的答案
	
	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getNewPassA() {
		return newPassA;
	}

	public void setNewPassA(String newPassA) {
		this.newPassA = newPassA;
	}

	public int getQuestion0() {
		return id0;
	}

	public void setQuestion0(int id0) {
		this.id0 = id0;
	}

	public String getAnswer0() {
		return answer0;
	}

	public void setAnswer0(String answer0) {
		this.answer0 = answer0;
	}

	
	public void validateUpdatePassByPass() {
		if(oldPass == null || "".equals(oldPass))
			this.addFieldError("oldPass", "请输入原密码");
		else{
			int num = oldPass.length();
			if(num < 6 || num > 15)
				this.addFieldError("oldPass", "密码只能6-15位");
		}
		
		if(newPass == null || "".equals(newPass))
			this.addFieldError("passwordN", "请输入密码");
		else{
			if(newPass.length() < 6 || newPass.length() > 15)
				this.addFieldError("passwordN", "密码只能6-15位");
			else{
				if(newPassA == null || "".equals(newPassA))
					this.addFieldError("passwordNA", "请再次输入密码");
				else{
					if(!newPass.equals(newPassA))
						this.addFieldError("passwordNA", "两次输入密码不相同");
				}
					
			}
		}
	}
	
	/**
	 * 方法说明：根据旧密码修改密码
	 * @return
	 */
	public String updatePassByPass(){
		try{
			UserService us = new UserService();
			
			//第一步：	获取session内的用户
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			User u = (User)ses.get(role);
			
			//第二步：	判断旧密码是否输入正确
			if(u != null && (MD5Util.GetMD5Code(oldPass)).equals(u.getPassword())){
				
				//第三步：	判断两次输入密码是否相同
					u.setPassword(MD5Util.GetMD5Code(newPass));
					us.update(u);
					req.put("message", "修改成功");
					return "success";
			}
			
			
			req.put("message", "原密码不正确");
			return "false";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	private int id1;
	private int id2;
	private int id3;
	private String question1;
	private String question2;
	private String question3;
	private String answer1;
	private String answer2;
	private String answer3;
	
	
	
	public int getId0() {
		return id0;
	}

	public void setId0(int id0) {
		this.id0 = id0;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int getId3() {
		return id3;
	}

	public void setId3(int id3) {
		this.id3 = id3;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	
	
	
	public void validateSetSecurities() {
		if(question1.equals(question2) || question2.equals(question3) || question1.equals(question3))
			this.addFieldError("question", "请不要选择相同的问题");
		else{
			if(answer1 == null || "".equals(answer1))
				this.addFieldError("answer1", "请输入答案");
			
			if(answer2 == null || "".equals(answer2))
				this.addFieldError("answer2", "请输入答案");
			
			if(answer3 == null || "".equals(answer3))
				this.addFieldError("answer3", "请输入答案");
		}
	}
	
	/**
	 * 方法说明：	设置密保
	 * @return
	 */
	public String setSecurities() {
		try{
			//第四步：	获取用户，并设置密保
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			User u = (User)ses.get(role);
			
			//第一步:		创建第一个密保问题
			Security s1 = new Security();
			s1.setQuestion(question1);
			s1.setAnswer(answer1);
			s1.setUser(u);
			
			//第二步:		创建第二个密保问题
			Security s2 = new Security();
			s2.setQuestion(question2);
			s2.setAnswer(answer2);
			s2.setUser(u);
			
			//第三步:		创建第三个密保问题
			Security s3 = new Security();
			s3.setQuestion(question3);
			s3.setAnswer(answer3);
			s3.setUser(u);
			
			//第四步：	将密保问题封装进集合内
			List<Security> s = new ArrayList<>(3);
			s.add(s1);
			s.add(s2);
			s.add(s3);
					
			//第五步：	创建用户服务，并执行更新方法
			SecurityService ss = new SecurityService();
			List<Security> securities = (List<Security>)ses.get("securities");
			if(securities == null)
				securities = ss.getMySecurity(u);
			
			ss.delete(securities);
			ses.remove("securities");
			ss.creare(s);
			
			req.put("message", "设置成功");
			
			if(role.equals("manager")){
				req.put("url", "/GDUTHackerSpace/Web/jsp/Manager/Common/Common/ManagerPage.jsp");
			}else{		
				req.put("url", "/GDUTHackerSpace/Web/jsp/View/User/Public/User/UserMess.jsp");
			}
			
			return "success";
				
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 方法说明：	修改问题
	 * @return
	 */
	public String findSecurities() {
		try{
			//第一步：	获取用户，并获取密保
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			User u = (User)ses.get(role);
			
			SecurityService ss = new SecurityService();
			
			List<Security> securities = ss.getMySecurity(u);
			
			if(securities.size() <= 0)
				return "set";
			else{
				ses.put("securities", securities);
				return "edit";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	
	public void validateConfirmSecurities() {
		if(answer1 == null || "".equals(answer1))
			this.addFieldError("answer1", "请输入答案");
		
		if(answer2 == null || "".equals(answer2))
			this.addFieldError("answer2", "请输入答案");
		
		if(answer3 == null || "".equals(answer3))
			this.addFieldError("answer3", "请输入答案");
	}
	
	public String confirmSecurities(){
		try{
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			
			List<Security> securities = (List<Security>)ses.get("securities");
			
			if(securities == null)
				return "null";
			
			if(securities.get(0).getAnswer().equals(answer1) && securities.get(1).getAnswer().equals(answer2) 
					&& securities.get(2).getAnswer().equals(answer3)){
				return SUCCESS;
			}else{
				req.put("error", "答案不正确");
				return "fail";
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String goHome() {
		return SUCCESS;
	}
	
	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}

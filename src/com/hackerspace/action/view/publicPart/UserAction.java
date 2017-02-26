 package com.hackerspace.action.view.publicPart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.Picture;
import com.hackerspace.model.Security;
import com.hackerspace.model.User;
import com.hackerspace.service.SecurityService;
import com.hackerspace.service.UserService;
import com.hackerspace.util.CookieUtils;
import com.hackerspace.util.MD5Util;
import com.hackerspace.util.PictureUtil;
import com.hackerspace.util.UploadFileUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class UserAction extends ActionSupport implements ModelDriven {
	private User user;
	private String code;
	
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private CookieUtils cookieUtils =new CookieUtils();
	
	private final int imgBoxSize=300;
	private final int viewImgHeight=300;
	private final int viewImgWidth=300;
	

	private int num;
	private String answer;
	
	private String passwordN;
	private String passwordNA;
	


	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}	
	public String getPasswordN() {
		return passwordN;
	}
	public void setPasswordN(String passwordN) {
		this.passwordN = passwordN;
	}
	public String getPasswordNA() {
		return passwordNA;
	}
	public void setPasswordNA(String passwordNA) {
		this.passwordNA = passwordNA;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	//第一个：	登陆
	public String login(){
		try{
			//第五步：	登陆成功，将用户保存进sessin
			ActionContext ac = ActionContext.getContext();
			Map res = ac.getSession();
			Map req = (Map)ac.get("request");
			
			String rand = (String)res.get("rand");
			
			if(rand != null)
				rand = rand.toLowerCase();
			
			code = code.toLowerCase();
			
			if(!code.equals(rand)){
				req.put("codeE","验证码不正确");
				return INPUT;
			}
			
			//第一步：	创建用户服务
			UserService us = new UserService();
			
			//第二步：	执行登陆操作
			User u = us.login(user.getCard(), MD5Util.GetMD5Code(user.getPassword()));
			
			//第三步：	判断是否存在用户，没有则登陆失败
			if(u == null  ){
				req.put("error", "用户名或密码错误");
				return "login";
			}
			
			//第四步：	判断该用户是否已通过审核
			if(u.getRequest() != 3){
				req.put("message", "您的注册请求已成功提交，我们将会尽快完成审核，对您造成不便，真的万分抱歉！");
				req.put("url", "/GDUTHackerSpace/Web/jsp/View/Public/User/Login.jsp");
				return "fail";
			}
			
			if(u.getRole() > 3){
				req.put("error", "该用户不存在");
				return "login";
			}
			
			
			//第五步：	登陆成功，将用户保存进sessin
			res.put("user", u);
			//存进cookie中
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response =ServletActionContext.getResponse();
			if("1".equals(request.getParameter("cookieFlag"))) {
				Cookie cookie =cookieUtils.addCookie(u);
				response.addCookie(cookie);
			}
			
			return "success";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public void validateLogin(){
		String card = user.getCard();
		String password = user.getPassword();
		
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
		
		if(password == null || "".equals(password))
			this.addFieldError("password", "请输入密码");
		else {
			int num = password.length();
			if(num < 6 || num > 15)
				this.addFieldError("password", "密码只能5-15位");
		}
		
		if(code == null || "".equals(code))
			this.addFieldError("code", "请输入验证码");
	}
	
	
	
	public void validateInputBase() {
		String name = user.getName();
		String password = user.getPassword();
		String card = user.getCard();
		String idCard = user.getIdCard();
		String institute = user.getInstitute();
		String major = user.getMajor();
		String contact = user.getContact();
			
		if(idCard == null || "".equals(idCard))
			this.addFieldError("idCard", "请输入身份证");	
		else {
			int num = idCard.length();
			if(num != 18)
				this.addFieldError("idCard", "请输入正确的身份证");
		}
		
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
		
		if(password == null || "".equals(password))
			this.addFieldError("password", "请输入密码");
		else {
			int num = password.length();
			if(num < 6 || num > 15)
				this.addFieldError("password", "密码只能5-15位");
		}
		
		if(name == null || "".equals(name))
			this.addFieldError("name", "请输入姓名");
		  
		if(institute == null || "".equals(institute))
			this.addFieldError("institute", "请输入学院");
		
		if(major == null || "".equals(major))
			this.addFieldError("major", "请输入专业");
		
		if(contact == null || "".equals(contact))
			this.addFieldError("contact", "请输入联系方式");
		
		
	}
	
	public String inputBase(){
		try{
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			
			UserService us = new UserService();
			
			User u = us.getUser(user.getCard());
			
			if(u != null){
				req.put("error", "该学号已被注册");
				return "exist";
			}
			
			user.setPassword(MD5Util.GetMD5Code(user.getPassword()));
			ses.put("register", user);
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}	
	}
	
	private String imgPath;
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void validateInputPicture() {
		String picture = imgPath;
		if(picture == null || "".equals(picture))
			this.addFieldError("cardPic", "请上传你的证件照");
		else
			user.setCardPic(picture);
	}
	
	public String inputPicture() {
		Picture p=new Picture();
		HttpServletRequest request=ServletActionContext.getRequest();
		String imgPath=user.getCardPic();//图片原路径
		String savePath= ServletActionContext.getServletContext().getRealPath("/Web/images/portrait")+imgPath.substring(imgPath.lastIndexOf("/"));
		imgPath=savePath.replace("portrait", "temp");
		
		//获得图框的属性
		p.setWid(viewImgWidth);
		p.setHei(viewImgHeight);
		
		//存入数据库
		if(submitPhoto(imgPath,savePath,p)){
		
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			
			User u = (User)ses.get("register");
			if(u == null){
				req.put("message", "请填写基本信息");
				return "fail";
			}
			
			u.setPicture(imgPath);
			return SUCCESS;
		}
		
		request.setAttribute("message", "上传失败");
		return "fail";
			
	}
	
	public void validateInputCardPic() {
		String picture = imgPath;
		if(picture == null || "".equals(picture))
			this.addFieldError("cardPic", "请上传你的证件照");
		else
			user.setPicture(picture);
	}
	
	
	public String inputCardPic() {
		try{

			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map) ac.get("request");

			User u = (User) ses.get("register");
			if (u == null) {
				req.put("message", "请填写基本信息");
				return "fail";
			}

			u.setCardPic(imgPath);
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	private String question1;
	private String question2;
	private String question3;
	private String answer1;
	private String answer2;
	private String answer3;

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
			
			User u = (User)ses.get("register");
			if(u == null){
				req.put("message", "请填写基本信息");
				return "fail";
			}
			
			u.setRole(1);
			u.setRequest(1);
			
			UserService us = new UserService();
			
			if("esist".equals(us.register(u))){
				req.put("message", "该用户已被注册");
				return "fail";
			}
			
			
			u = us.getUser(u.getCard());
			
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
			
			ss.creare(s);
			
			req.put("message", "恭喜您注册成功，我们将会未来三天尽快处理你的请求，若对您造成不便，望请原谅");
			req.put("url", "/GDUTHackerSpace/Web/jsp/View/Public/User/Login.jsp");
			return "success";
				
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	public String logout() {
		try{
			//第一步：	获取Session
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			ses.remove("user");
			
			return SUCCESS;
			
		}catch(Exception e){
			e.getMessage();
			return ERROR;
		}
	}
	
	public void validateGetSecurity() {
		String card = user.getCard();
		if(card == null || "".equals(card))
			this.addFieldError("card", "请输入学号");
		else{
			int num = card.length();
			if(num < 6 || num > 15)
				this.addFieldError("card", "学号只能6-15位");
		}
		
	}
	
	public String getSecurity(){
		try{
			UserService us = new UserService();
			SecurityService ss = new SecurityService();
			
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			Map ses = ac.getSession();
			
			User u = us.getUser(user.getCard());
			
			if(u == null){
				req.put("error", "没有该用户");
				return "fail";
			}
			
			List<Security> securities = ss.getMySecurity(u);
			if(securities.size() == 0) {
				req.put("error", "该用户还未设置密保");
				return "fail";
			}
			
			ses.put("recurities", securities);
			ses.put("id", u.getId());
			return SUCCESS;
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void validateConfirmSecurity() {
		if(answer == null || "".equals(answer))
			this.addFieldError("answer", "请输入你的答案");
	}
	
	public String confirmSecurity() {
		try{
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			Map req = (Map)ac.get("request");
			List<Security> securities = (List<Security>)ses.get("recurities");
			
			Security s = securities.get(num);
			if(answer.equals(s.getAnswer()))
				return SUCCESS;
			else{
				req.put("error", "回答不正确");
				return INPUT;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void validateSetPassword() {
		if(passwordN == null || "".equals(passwordN))
			this.addFieldError("passwordN", "请输入密码");
		else{
			if(passwordN.length() < 6 || passwordN.length() > 15)
				this.addFieldError("passwordN", "密码只能6-15位");
			else{
				if(passwordNA == null || "".equals(passwordNA))
					this.addFieldError("passwordNA", "请再次输入密码");
				else{
					if(!passwordN.equals(passwordNA))
						this.addFieldError("passwordNA", "两次输入密码不相同");
				}
					
			}
		}
	}
	
	public String setPassword() {
		try{
			UserService us = new UserService();
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			
			User u = us.getUser((Integer)ses.get("id"));
			
			u.setPassword(MD5Util.GetMD5Code(passwordN));
			us.update(u);
			
			ses.remove("securities");
			ses.remove("id");
			
			return SUCCESS; 
			
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	@Override
	public Object getModel() {
		if(user == null)
			user = new User();
		
		return user;
	}
	
	
	private int way;
	
	public int getWay() {
		return way;
	}
	public void setWay(int way) {
		this.way = way;
	}
	/**
	 * 准备图片
	 * @return
	 */
	public String prepareUserPicture() {
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("imgBoxSize", imgBoxSize);
		request.setAttribute("viewImgHeight",viewImgHeight);
		request.setAttribute("viewImgWidth", viewImgWidth);
		request.setAttribute("fileShowUrl",request.getParameter("imageUrl"));
		return way == 1 ? "success" : (way == 2 ? "cardPic" : "picture");
	}
	
	/**
	 * @param imgPath 原图的地址
	 * @param savePath 保存图片的地址
	 * 跳转到团队头像的页面
	 * 说明：上传图片
	 * @return
	 */
	public boolean submitPhoto(String imgPath,String savePath,Picture p) {
		HttpServletRequest request=ServletActionContext.getRequest();
		int x=(int)Double.parseDouble(request.getParameter("x"));
		int y=(int)Double.parseDouble(request.getParameter("y"));
		int w=(int)Double.parseDouble(request.getParameter("w"));
		int h=(int)Double.parseDouble(request.getParameter("h"));
		int boxSize=(int)Double.parseDouble(request.getParameter("imgBoxSize"));
		p.setX(x);
		p.setY(y);
		p.setW(w);
		p.setH(h);
		p.setOldPictureUrl(imgPath);
		p.setPictureUrl(savePath);

		if(PictureUtil.submitPhoto(p, boxSize)) {
			
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 上传图片
	 * @return
	 */
	public String uploadUserPicture() {
		HttpServletRequest request=ServletActionContext.getRequest();
		fileFileName=UUID.randomUUID().toString()+fileFileName.substring(fileFileName.lastIndexOf("."));//改图片名
		String filePath="/images/temp/";
		//显示的位置
		String fileShowUrl="/GDUTHackerSpace/Web/"+filePath+fileFileName;
		//在磁盘的位置
		String fileUrl=ServletActionContext.getServletContext().getRealPath("/Web");
		fileUrl+=filePath;//照片地址
		System.out.println("照片地址"+fileUrl);
		if(this.getFileFileName().endsWith(".exe"))//违法文件 
		{
			return ERROR;
		}
		UploadFileUtil.uploadFile(this.getFile(),this.getFileFileName(), fileUrl);
		int w=1;
		int h=1;
		try {
			BufferedImage image=ImageIO.read(file);
			w=image.getWidth();
			h=image.getHeight();
			if(w>h) {//宽比上大
				h=imgBoxSize*h/w;
				w=imgBoxSize;
			}else {//长比宽大
				w=imgBoxSize*w/h;
				h=imgBoxSize;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//设置截图属性
		request.setAttribute("imageWidth",w);
		request.setAttribute("imageHeight", h);
		request.setAttribute("imgBoxSize",imgBoxSize);
		request.setAttribute("viewImgHeight", viewImgHeight);
		request.setAttribute("viewImgWidth",viewImgWidth);
		request.setAttribute("fileShowUrl", fileShowUrl);
		request.setAttribute("id",user.getId());
		
		
		return way == 2 ? "cardPic" : (way == 3 ? "picture" : "success");
	}
	public String updateUserPicture() throws Exception{
		Picture p=new Picture();
		HttpServletRequest request=ServletActionContext.getRequest();
		String imgPath=request.getParameter("imgPath");//图片原路径
		Integer id=Integer.parseInt(request.getParameter("id"));
		String savePath=ServletActionContext.getServletContext().getRealPath("/Web/images/portrait")+imgPath.substring(imgPath.lastIndexOf("/"));
		imgPath=savePath.replace("portrait", "temp");
		
		//获得图框的属性
		p.setWid(viewImgWidth);
		p.setHei(viewImgHeight);
		
		//存入数据库
		if(submitPhoto(imgPath,savePath,p)) {//成功则保存图片
			
			UserService us=new UserService();
			User u= us.getUser(id);
			String fileUrl="/GDUTHackerSpace/Web/images/portrait"+imgPath.substring(imgPath.lastIndexOf("/"));
			u.setPicture(fileUrl);
			us.update(u);
			
			ActionContext ac = ActionContext.getContext();
			Map ses = ac.getSession();
			
			if(u.getRole() < 4){
				ses.put("user", u);
			}else{
				ses.put("manager", u);
			}
			
			request.setAttribute("id",id);
			request.setAttribute("imageWidth", imgBoxSize*viewImgWidth/viewImgHeight);
			request.setAttribute("imageHeight", imgBoxSize);
			request.setAttribute("imgBoxSize", imgBoxSize);
			request.setAttribute("viewImgHeight",viewImgHeight);
			request.setAttribute("viewImgWidth",viewImgWidth);
			request.setAttribute("fileShowUrl",fileUrl);
			return  SUCCESS;
		}
		return ERROR;
	}
}

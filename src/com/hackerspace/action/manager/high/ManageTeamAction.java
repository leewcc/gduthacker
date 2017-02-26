package com.hackerspace.action.manager.high;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import com.hackerspace.model.PageElem;
import com.hackerspace.model.Picture;
import com.hackerspace.model.Team;
import com.hackerspace.model.TeamUser;
import com.hackerspace.service.PictureService;
import com.hackerspace.service.TeamService;
import com.hackerspace.service.TeamUserService;
import com.hackerspace.util.PictureUtil;
import com.hackerspace.util.UploadFileUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.accessibility.internal.resources.accessibility;
import com.sun.prism.Image;

import freemarker.core.ReturnInstruction.Return;

public class ManageTeamAction extends ActionSupport{
	private int pageE;
	private int pageD;
	
	public File file;
	private String fileFileName;
	private String fileContentType;
	
	
	private final int imgBoxSize=500;
	private final int viewImgHeight=300;
	private final int viewImgWidth=300;
	
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


	public int getPageE() {
		return pageE;
	}

	public void setPageE(int pageE) {
		this.pageE = pageE;
	}
	
	public int getPageD() {
		return pageD;
	}

	public void setPageD(int pageD) {
		this.pageD = pageD;
	}

	public String selectTeam() {
		try{
			//第一步：	创建团队服务
			TeamService ts = new TeamService();
			
			//第二步：	执行查询仍存在团队和已删除团队的方法
			PageElem<Team> teamsE = ts.selectTeam(pageE, 1);
			PageElem<Team> teamsD = ts.selectTeam(pageD, 0);
			
			//将对象保存在请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("teamsE", teamsE);
			req.put("teamsD", teamsD);
			
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	private int id;		//团队的id
	private int way;	//处理方式  1-修改团队资料  2-查看团队信息

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}
	
	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String findTeam() {
		try{
			//第一步：	创建团队服务
			TeamService ts = new TeamService();

			
			//第二步：	获取团队
			Team t = ts.find(id);

			//第三步：	将团队存进请求中
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);
			
			//第四步：	根据不同的操作调用相应的方法
			return way == 1? "edit" : "mess";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	private String name;
	private String des;
	private String motto;
	private Date createTime;
	private boolean isCreate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public boolean isCreate() {
		return isCreate;
	}

	public void setCreate(boolean isCreate) {
		this.isCreate = isCreate;
	}

	public void validateEditTeam() {
		
		if (name == null || "".equals(name))
			this.addFieldError("name", "请输入团队名");
		else {
			if (name.length() > 100)
				this.addFieldError("name", "团队名字过长");
		}

		if (motto == null || "".equals(motto))
			this.addFieldError("motto", "请输入团队口号");
		else {
			if (motto.length() > 200)
				this.addFieldError("motto", "简介不能超过100个字");
		}
		
		if(createTime == null)
			this.addFieldError("createTime", "请设置团队的创建时间");
	}
	
	public String editTeam() {
		try{
			//第一步:：创建团队服务
			TeamService ts = new TeamService();
			Team t = null;
			
			//第二步：判断是创建团队还是编辑团队
			if(isCreate){
				t = new Team();
				t.setName(name);
				t.setMotto(motto);
				t.setCreateTime(createTime);
				t.setStatus(1);
				ts.create(t);
			}else{
				t = ts.find(id);
				t.setName(name);
				t.setMotto(motto);
				t.setCreateTime(createTime);
				ts.update(t);
			}
			
			//第三步：	将团队对象放进请求
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);
			
			return "mess";
			
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
	}
	
	
	public String findDes() {
		try{
			TeamService ts = new TeamService();
			
			Team t = ts.getTeam(id);
			
			ActionContext ac = ActionContext.getContext();
			Map req = (Map)ac.get("request");
			req.put("team", t);
			
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public String editDes() {
		try{
			TeamService ts = new TeamService();
			
			Team t = ts.find(id);
			
			t.setBrief(des);
			
			ts.update(t);
			
			return SUCCESS;
			
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
		
		
	}
	
	
	public String deleteTeam() {
		try{
			ActionContext ac = ActionContext.getContext();
			Map res = ac.getSession();
			Map req = (Map)ac.get("request");
			
			TeamService ts = new TeamService();
			
			Team team = new Team();
			team.setId(id);
			
			boolean result = ts.deleteTeam(team);
			
			if(result) {
				return SUCCESS;
			}else {
				req.put("error", "删除失败，请重新尝试");
				return "fail";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	/**
	 * 更新团队的图片
	 * @author: CHEN
	 */
	public String updateTeamPicture() throws Exception{
		Picture p=new Picture();
		HttpServletRequest request=ServletActionContext.getRequest();
		String imgPath=request.getParameter("imgPath");//图片原路径
		Integer id=Integer.parseInt(request.getParameter("id"));
		String savePath=ServletActionContext.getServletContext().getRealPath("/Web/images/team")+imgPath.substring(imgPath.lastIndexOf("/"));
		imgPath=savePath.replace("team", "temp");
		
		if(submitPhoto(imgPath,savePath,p)) {//成功则保存图片
			
			TeamService ts=new TeamService();
			Team t= ts.find(id);
			String fileUrl="/GDUTHackerSpace/Web/images/team"+imgPath.substring(imgPath.lastIndexOf("/"));
			t.setPicture(fileUrl);
			ts.update(t);
			request.setAttribute("imageHeight",viewImgHeight);
			request.setAttribute("imageWidth", viewImgWidth);
			request.setAttribute("id",id);
			request.setAttribute("imgBoxSize", imgBoxSize);
			request.setAttribute("viewImgHeight",viewImgHeight);
			request.setAttribute("viewImgWidth",viewImgWidth);
			request.setAttribute("fileShowUrl",fileUrl);
			return  SUCCESS;
		}
		return ERROR;
	}
	
	/**
	 * @param imgPath 原图的地址
	 * @param savePath 保存图片的地址
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
	 * 上传团队图片
	 * @author CHEN
	 */
	public String uploadTeamPicture() {
		HttpServletRequest request=ServletActionContext.getRequest();
		fileFileName=UUID.randomUUID().toString()+fileFileName.substring(fileFileName.indexOf("."));//改图片名
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
		
		try {
			BufferedImage bi=ImageIO.read(new FileInputStream(file));
			int h=bi.getHeight();
			int w=bi.getWidth();
			if(w>h) {
				h=(imgBoxSize*h)/w;
				w=imgBoxSize;
			} else {
				w=(imgBoxSize*w)/h;
				h=imgBoxSize;
			}
			request.setAttribute("imageHeight",h);
			request.setAttribute("imageWidth", w);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//设置截图属性
		request.setAttribute("imgBoxSize",imgBoxSize);
		request.setAttribute("viewImgHeight", viewImgHeight);
		request.setAttribute("viewImgWidth",viewImgWidth);
		request.setAttribute("fileShowUrl", fileShowUrl);
		request.setAttribute("id",Integer.parseInt(request.getParameter("id")));
		
		
		return SUCCESS;
	}
	
	public String prepareTeamPicture () {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("imBoxSize", imgBoxSize);
		request.setAttribute("imageWidth", viewImgWidth);
		request.setAttribute("imageHeight",viewImgWidth);
		request.setAttribute("viewImgHeight",viewImgHeight );//截图的高度
		request.setAttribute("viewImgWidth", viewImgWidth);//截图的宽度
		request.setAttribute("fileShowUrl", request.getParameter("imageUrl"));
		return "success";
	}
}

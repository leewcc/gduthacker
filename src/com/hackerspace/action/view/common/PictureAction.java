package com.hackerspace.action.view.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hackerspace.model.Picture;
import com.hackerspace.service.PictureService;
import com.hackerspace.util.PictureUtil;
import com.hackerspace.util.UploadFileUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.prism.Image;

public class PictureAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public File file;
	private String fileFileName;
	private String fileContentType;
	
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	public String getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


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

	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * ckeditor图片上传
	 * @return
	 * @throws Exception
	 */
   public String uploadCkPhoto() throws Exception {  
       HttpServletResponse response = ServletActionContext.getResponse();  
       response.setCharacterEncoding("utf-8");  
       PrintWriter out = response.getWriter();  
 
       // CKEditor提交的很重要的一个参数  
       String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");   
         
       String expandedName = "";  //文件扩展名  
       if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {  
           //IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg  
           expandedName = ".jpg";  
       }else if(uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")){  
           //IE6上传的png图片的headimageContentType是"image/x-png"  
           expandedName = ".png";  
       }else if(uploadContentType.equals("image/gif")){  
           expandedName = ".gif";  
       }else if(uploadContentType.equals("image/bmp")){  
           expandedName = ".bmp";  
       }else{  
           out.println("<script type=\"text/javascript\">");    
           out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");   
           out.println("</script>");  
           return null;  
       }  
         
       if(upload.length() > 5*1024*1024){  
           out.println("<script type=\"text/javascript\">");    
           out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",''," + "'文件大小不得大于5M');");   
           out.println("</script>");  
           return null;  
       }  
         
         
       InputStream is = new FileInputStream(upload);  
       String uploadPath = ServletActionContext.getServletContext()     
               .getRealPath("/Web");  
       String uploadName = java.util.UUID.randomUUID().toString();  //采用时间+UUID的方式随即命名  
       uploadPath+="/images/picture";
       uploadName = uploadName+expandedName;  
       File toFile = new File(uploadPath, uploadName);  
       OutputStream os = new FileOutputStream(toFile);     
       byte[] buffer = new byte[1024];     
       int length = 1;  
       while ((length = is.read(buffer)) > 0) {     
           os.write(buffer, 0, length);     
       }     
       is.close();  
       os.close();  
       // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
       out.println("<script type=\"text/javascript\">");    
       out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "/GDUTHackerSpace/Web/images/picture/" + uploadName + "','')");    
       out.println("</script>");  
         
       return null;  
    }  
	/**
	 * 说明：上传单个照片
	 * @throws Exception 
	 */
	public  String uploadPhoto() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		String belong=request.getParameter("belong");
		System.out.println("照片上传");
		fileFileName=UUID.randomUUID().toString()+fileFileName.substring(fileFileName.lastIndexOf("."));//改图片名
		String filePath="/images/temp/"+belong+"/";
		//显示的位置
		String fileShowUrl="/GDUTHackerSpace/Web"+filePath+fileFileName;
		//在磁盘的位置
		String fileUrl=ServletActionContext.getServletContext().getRealPath("/Web");
		fileUrl+=filePath;//照片地址
		System.out.println("照片地址"+fileUrl);
		if(this.getFileFileName().endsWith(".exe"))//违法文件 
		{
			return ERROR;
		}
		UploadFileUtil.uploadFile(this.getFile(),this.getFileFileName(), fileUrl);
		
	
		
		
		Map<String, Integer> m=new HashMap<String, Integer>();
		returnPhotoSize(m,belong);
	
		//设置截图属性
		request.setAttribute("imageWidth", m.get("imageWidth"));
		request.setAttribute("imageHeight", m.get("imageHeight"));
		request.setAttribute("imgBoxSize", m.get("imgBoxSize"));
		request.setAttribute("viewImgHeight", m.get("viewImgHeight"));
		request.setAttribute("viewImgWidth",m.get("viewImgWidth"));
		request.setAttribute("fileShowUrl", fileShowUrl);
		request.setAttribute("id",Integer.parseInt(request.getParameter("id")));
		request.setAttribute("belong",request.getParameter("belong"));
		
		
		return SUCCESS;
	}
	/**
	 * 准备栏目的图片
	 */
	public String prepareColumnPhoto() {
		HttpServletRequest request=ServletActionContext.getRequest();
		//设置截图属性
		String belong=request.getParameter("belong");
		System.out.println(belong);
		request.setAttribute("id", Integer.parseInt(request.getParameter("id")));
		request.setAttribute("fileShowUrl",request.getParameter("fileShowUrl") );
		request.setAttribute("belong", belong);
		Map<String, Integer> m=new HashMap<String, Integer>();
		returnPhotoSize(m,belong);
		request.setAttribute("imageWidth", m.get("viewImgWidth") );
		request.setAttribute("imageHeight",  m.get("viewImgHeight"));
		request.setAttribute("imgBoxSize", m.get("imgBoxSize"));
		request.setAttribute("viewImgHeight", m.get("viewImgHeight"));
		request.setAttribute("viewImgWidth",m.get("viewImgWidth"));
		return SUCCESS;
	}
	/**
	 * 显示栏目图片
	 * @return
	 */
	public String showManagerPhoto() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String belong=request.getParameter("belong");
		if(belong==null) {
			belong="1";
		}
		PictureService ps=new PictureService();
		List<Picture> l = ps.findPicture(belong);
		request.setAttribute("belong", belong);
		request.setAttribute("imageUrlList", l);;
		return SUCCESS;
		
	}
	
	/**
	 * 说明：提交栏目的图片
	 * @return
	 */
	public String uploadColumnPhoto() {
		Picture p=new Picture();
		HttpServletRequest request=ServletActionContext.getRequest();
		String belong =request.getParameter("belong");
		String imgPath=request.getParameter("imgPath");//图片原路径
		Integer id=Integer.parseInt(request.getParameter("id"));
		String savePath=ServletActionContext.getServletContext()
				.getRealPath("/Web/images/column")+"\\"+belong+imgPath
				.substring(imgPath.lastIndexOf("/"));
		imgPath=savePath.replace("column", "temp");
		
		//获得图框的属性
		Map<String,Integer> m=new HashMap<String, Integer>();
		returnPhotoSize(m, belong);
		p.setWid(m.get("viewImgWidth"));
		p.setHei(m.get("viewImgHeight"));
		
		if(submitPhoto(imgPath,savePath,p)) {//成功则保存图片
			
			PictureService ps=new PictureService();
			p=ps.getPicture(id);
			p.setPictureUrl("/GDUTHackerSpace/Web/images/column/"+belong+imgPath.substring(imgPath.lastIndexOf("/")));
			ps.updatePicture(p);
			
			request.setAttribute("id",id);
			
			request.setAttribute("belong",belong);
			request.setAttribute("imageWidth", m.get("imageWidth"));
			request.setAttribute("imageHeight", m.get("imageHeight"));
			request.setAttribute("imgBoxSize", m.get("imgBoxSize"));
			request.setAttribute("viewImgHeight", m.get("viewImgHeight"));
			request.setAttribute("viewImgWidth",m.get("viewImgWidth"));
			return  SUCCESS;
		}
		return ERROR;
	}
	/**
	 * 按照栏目返回imgBoxSize viewImgWidth viewImgHeight
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void returnPhotoSize(Map<String,Integer> p,String belong) {
		int s=0;
		if(belong.equals("1"))//首页轮播
		{
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 180);
		} else if(belong.equals("2")){//页尾图片
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",100);
			p.put("viewImgHeight", 50);
		} else if("11".equals(belong)) {//学院简介
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("12".equals(belong)) {//师资力量
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("13".equals(belong)) {//新闻动态
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("14".equals(belong)) {//办事服务
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("15".equals(belong)) {//创客教育
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("16".equals(belong)) {//创客项目
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("17".equals(belong)) {//创客之窗
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		} else if("18".equals(belong)) {//校企合作
			s=420;
			p.put("imgBoxSize", s);
			p.put("viewImgWidth",400);
			p.put("viewImgHeight", 150);
		}
		//获得图片的属性
		BufferedImage image;
		int w=1;
		int h=1;
		if(file==null) {
			p.put("imageHeight", 1);
			p.put("imageWidth", 1);
			return ;
		}
		try {
			image = ImageIO.read(new FileInputStream(file));
			w=image.getWidth();
			h=image.getHeight();
			System.out.println("处理前图片的尺寸"+h+":"+w);
			if(w>h) {//宽比长大
				h=(s*h)/w;
				w=s;
			} else {//长比宽大
				w=(s*w)/h;
				h=s;
			}
			System.out.println("处理后图片的尺寸"+h+":"+w);
			p.put("imageHeight", h);
			p.put("imageWidth", w);
		} catch (Exception e) {
			p.put("imageHeight", 0);
			p.put("imageWidth", 0);
			e.printStackTrace();
		}
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
}

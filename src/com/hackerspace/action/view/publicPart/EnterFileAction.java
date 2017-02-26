package com.hackerspace.action.view.publicPart;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;

import com.hackerspace.dao.EnterApplyDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.ApplyFile;
import com.hackerspace.util.BrowserUtils;
import com.hackerspace.util.Log;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/apply")

public class EnterFileAction extends ActionSupport{
		private static final long serialVersionUID = 1L;
		private static Logger log = Log.get(EnterFileAction.class);
	    private String fileName;
	    private String downFileName;
	    
	    public void setFileName(String fileName) throws UnsupportedEncodingException {
	        Log.debug(log, "得到原始文件名：{}", fileName);
	 
	        // 转码
	        fileName = URLDecoder.decode(fileName, "UTF-8");
	 
	        Log.debug(log, "转码后的文件名：{}", fileName);
	 
	        this.fileName = fileName;
	    }
	 
	    /**
	     * 如果传过来的fileName参数有路径处理后只返回文件名
	     * @return  例如path/aaa.txt，返回aaa.txt
	     * @throws UnsupportedEncodingException
	     */
	    public String getDownFileName() throws UnsupportedEncodingException {
	    	System.out.println("getDownFileName");
	        fileName = StringUtils.defaultIfEmpty(fileName, "");
	 
	        // 判断path/aaa/bbb.txt和path\aaa\bbb.txt两种情况
	        String tempFileName = StringUtils.substringAfterLast(fileName, "/");
	        if (tempFileName.length() == 0) {
	            tempFileName = StringUtils.substringAfterLast(fileName, "\\");
	        }
	        if (tempFileName.length() == 0) {
	            tempFileName = fileName;
	        }
	 
	        // 处理中文
	        Log.debug(log, "去除路径信息后得到文件名：{}", tempFileName);
	 
	        // 处理IE浏览器乱码问题
	        if (BrowserUtils.isIE(ServletActionContext.getRequest())){
	            downFileName = java.net.URLEncoder.encode(tempFileName, "UTF-8");
	        } else {
	            downFileName = new String(tempFileName.getBytes(), "ISO8859-1");
	        }
	        
	        EnterApplyDao eApplyDao = new EnterApplyDao();
	        ApplyFile applyFile = null;
	        try {
	        	applyFile = eApplyDao.queryApplyFile();
			} catch (DaoException e) {
				
			}
	        
	        if (applyFile == null) {
	        	return "";
	        }
	        
	        return applyFile.getFileName();
	    }
	 
	    public InputStream getInputStream() throws IOException{
	    	
	    	System.out.println("getInputStream" + ""
	    	 + ServletActionContext.getServletContext().getRealPath("/enterfile/" + getDownFileName()));
	    	try {
	    	return ServletActionContext.getServletContext().getResourceAsStream("/enterfile/" + getDownFileName());
	    	} catch (Exception e) {
	    		Log.error(log, e, "getInputStream()输入流获取失败");
	    		ServletActionContext.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    		return null;
	    	}
	    	
	    	
	    }
	    
	    @Action(value="download",results={
	    		@Result(name="success",type = "stream",
	    		params ={ "contentType", "application/octet-stream;charset=ISO8859-1", "inputName",
	    	    		  "inputStream", "contentDisposition", "attachment;filename=${downFileName}", "bufferSize", "4096" } )		
	    })
	    public String execute() {
	    	
	    	return SUCCESS;
	    }
	    
	    
	   
	}
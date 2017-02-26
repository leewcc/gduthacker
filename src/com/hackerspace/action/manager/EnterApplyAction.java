package com.hackerspace.action.manager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import com.hackerspace.dao.EnterApplyDao;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.ApplyFile;
import com.hackerspace.model.Message;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("mystruts")
@Namespace("/manager/enterapply")

public class EnterApplyAction extends ActionSupport{

	private static Logger log = Log.get(EnterApplyAction.class);

	private File file;

	private String fileFileName;

	private String fileContentType;

	private ApplyFile applyFile;

	private EnterApplyDao dADao = new EnterApplyDao();

	private static String message = null;    // 错误提示信息
		
	
	@Action(value="upload", results={
			@Result(name="success", type="redirect", location="query"),
			@Result(name="input", type="redirect", location="query")
	})
	public String uploadFile() {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Session s = HibernateUtil.sessionFactory.openSession();
		
		//获取文件上传路径
		String realPath = ServletActionContext.getServletContext().getRealPath("/enterfile");
		if (file == null) {
			message = "请选择文件";
			return "input";
		}
		
		Log.debug(log, "uploadFile()参数:realPath={},fileName={},fileType={}",
				realPath, fileFileName, fileContentType);
		
		boolean flag = false; // 无异常
		try {
			// 保存的文件
			File saveFile = new File(new File(realPath), fileFileName);
			applyFile = new ApplyFile();
			applyFile.setFileByte(saveFile.length()+"");
			applyFile.setFileName(fileFileName);
			applyFile.setFileTime(new Timestamp(System.currentTimeMillis()));
			applyFile.setFileUrl("");
			applyFile.setDownloadNum(0);

			dADao.insertApplyFile(applyFile, s);
			
			if (!saveFile.getParentFile().exists())
				saveFile.getParentFile().mkdirs();
			
			FileUtils.copyFile(file, saveFile);
			
			
		} catch (DaoException e) {
			Log.error(log, e, "sql 数据库异常");
			flag = true;
		} catch (IOException e) {
			Log.error(log, e, "文件传输错误");
			flag = true;
		} catch (Exception e) {
			Log.error(log, e, "uploadFile异常");
			flag = true;
		} finally {
			if (flag == true) {
				message = "文件上传失败";
				s.getTransaction().rollback();
				return "input";
			}
			s.close();
		}
		
        return SUCCESS;
	}
	
	/**
	 * 查询已上传文件
	 * @return
	 * @throws IOException
	 */
	@Action(value="query", results={
			@Result(name="success", location="/Web/jsp/Manager/Public/EnterApply/FileUpload.jsp")
	})
	public String queryFile() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		try {
			if ( (applyFile = dADao.queryApplyFile() ) == null ) {
				
			}
		} catch (DaoException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		if (message != null) {
			hsq.setAttribute("msg", message);
			message = null;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除已上传文件
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete", results={
			@Result(name="success", type="redirect",location="query")
	})
	public String deleteEnterApply() throws IOException {
		HttpServletRequest hsq = ServletActionContext.getRequest();
		HttpServletResponse hsr = ServletActionContext.getResponse();
		
		Integer id = null;
		try {
			id = Integer.valueOf(hsq.getParameter("id"));
		} catch (NumberFormatException e) {
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		
		Session s = HibernateUtil.sessionFactory.openSession();
		
		try {
			if ( dADao.deleteApplyFile(s) == 1) {
				Log.warn(log, "文件上传存在异常, 数据库中不止一个文件数据");
			}
			// 获取文件所在文件夹的路径
			String realPath =  ServletActionContext.getServletContext().getRealPath("/enterfile");
			
			File saveDir = new File(realPath);
			// 获取文件夹下所有文件
			File[] saveFiles = saveDir.listFiles();
			
			for (int i = 0; i < saveFiles.length; i++) 
				saveFiles[i].delete();
			
		}catch (DaoException e) {
			Log.error(log, e, "文件删除失败, sql 语句错误或数据库链接失败");
			s.getTransaction().rollback();
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
		 catch (Exception e) {
			Log.error(log, e, "文件删除失败, 文件操作错误");
			s.getTransaction().rollback();
			hsr.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		} finally {
			s.close();
		}
			
		message = "删除成功";
		return SUCCESS;	
	}



	public void setApplyFile(ApplyFile applyFile) {
		this.applyFile = applyFile;
	}
	
	public ApplyFile getApplyFile() {
		return applyFile;
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
}

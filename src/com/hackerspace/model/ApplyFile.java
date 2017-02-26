package com.hackerspace.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_apply_file")
public class ApplyFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private Integer id;

	@Column(name = "user_id",columnDefinition="int(11) default -1")
	private Integer userId;
	
	@Column(name = "file_name",columnDefinition="varchar(255) default ''")
	private String fileName;
	
	@Column(name = "file_time",columnDefinition="datetime default '2016-03-10 11:11:11'")
	private Timestamp fileTime;
	
	@Column(name = "file_byte",columnDefinition="varchar(255) default ''")
	private String fileByte;
	
	@Column(name = "file_url",columnDefinition="varchar(255) default ''")
	private String fileUrl;
	
	@Column(name = "download_num",columnDefinition="int(11) default 0")
	private Integer downloadNum;
	
	public ApplyFile() {}
	public ApplyFile(int id, String fileName, Object fileTime, String fileByte, int downloadNum) {
		this.id = id;
		this.fileName = fileName;
		this.fileTime = (Timestamp)fileTime;
		this.fileByte = fileByte;
		this.downloadNum = downloadNum;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Timestamp getFileTime() {
		return fileTime;
	}

	public void setFileTime(Timestamp fileTime) {
		this.fileTime = fileTime;
	}

	public String getFileByte() {
		return fileByte;
	}

	public void setFileByte(String fileByte) {
		this.fileByte = fileByte;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}
	
	
}

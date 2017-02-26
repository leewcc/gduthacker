package com.hackerspace.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "t_project_show")
public class ProjectShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private Integer id;

	@Transient
	private String author;
	
	@Column(name="author_id", columnDefinition="int(11) default -1")
	private Integer authorId;
	
	@Column(name = "show_title",columnDefinition="varchar(250) default ''")
	private String title;

	@Column(name = "show_content",columnDefinition = "text")
	private String content;

	@Column(name = "show_time",columnDefinition="datetime  default '0000-00-00 00:00:00'")
	private Timestamp time;

	@Column(name = "show_status",columnDefinition="bit default 0")
	private byte status;
	
	@Column(name = "hit_num", columnDefinition="int default 1")
	private Integer hitNum;
	

	
	public ProjectShow() {
		
	}
	
	public ProjectShow(Integer id, Integer hitNum, String title, String content, Object time, String author) {
		this.id = id;
		this.hitNum = hitNum;
		this.title = title;
		this.content = content;
		this.time = (Timestamp)time;
		this.author = author;
		
	}
	
	public ProjectShow(String title, String author, Object time, Integer hitNum, String content) {
		this.title = title;
		this.author = author;
		this.time = (Timestamp)time;
		this.hitNum = hitNum;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setHitNum(Integer hitNum) {
		this.hitNum = hitNum;
	}
	
	public Integer getHitNum() {
		return hitNum;
	}
	
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}

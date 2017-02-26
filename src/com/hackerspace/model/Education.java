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
@Table(name = "t_education")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private Integer id;

	@Column(name = "education_author_id",columnDefinition="int default -1")
	private Integer authorId;

	@Column(name = "education_title",columnDefinition="varchar(250) default ''")
	private String title;

	@Column(name = "education_content",columnDefinition = "text")
	private String content;

	@Column(name = "education_time",columnDefinition="datetime default '0000-00-00 00:00:00'")
	private Timestamp time;

	@Column(name = "education_status",columnDefinition="bit default 0")
	private byte status;//0 代表没有发布 1代表已经发布

	@Column(name = "education_tag",columnDefinition="bit default 0")
	private byte tag;//0 代表讲座论坛  1代表实践实训
	
	@Transient
	private String authorName;
	
	public Education() {
		super();
	}
	
	
	
	public Education(Integer id, String title, Object time, byte status,
			byte tag, String authorName) {
		super();
		this.id = id;
		this.title = title;
		this.time = (Timestamp)time;
		this.status = status;
		this.tag = tag;
		this.authorName = authorName;
	}

	public Education(Integer id, String title, Object time, byte status,
			 String authorName) {
		super();
		this.id = id;
		this.title = title;
		this.time = (Timestamp)time;
		this.status = status;
		this.tag = tag;
		this.authorName = authorName;
	}


	public Education(int id, String title, Object time, String authorName) {
		this.id = id;
		this.title = title;
		this.time = (Timestamp) time;
		this.authorName = authorName;
		
	}
	
	
	
	public Education(Integer id, String title, String content, Object time,
			byte status, byte tag, String authorName) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = (Timestamp)time;
		this.status = status;
		this.tag = tag;
		this.authorName = authorName;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

}

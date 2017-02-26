package com.hackerspace.model;

import java.sql.Timestamp;

import javax.persistence.*;

import org.hibernate.annotations.CascadeType;



@Entity
@Table(name = "t_project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private Integer id;

	@Column(name = "team_id",columnDefinition="int(11) default -1")
	private Integer teamId;

	@Transient
	private String author;
	
	@Column(name = "author_id", columnDefinition="int(11) default -1")
	private Integer authorId;
	
	
	@Column(name = "project_title",columnDefinition="varchar(250) default ''")
	private String title;

	

	@Column(name = "project_content",columnDefinition="varchar(250) default ''")
	private String content;

	@Column(name = "project_time",columnDefinition="datetime default '0000-00-00 00:00:00'")
	private Timestamp time;

	@Column(name = "project_status",columnDefinition="bit default 0")
	private byte status;

	@Column(name = "project_tag",columnDefinition="bit default 0")
	private byte tag;
	
	@Transient
	private String teamName;
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Project() {
		super();
	}
	
	public Project(int id, String title, Object time, byte tag, String name) {
		this.id = id;
		this.title = title;
		this.time = (Timestamp) time;
		this.tag = tag;
		this.teamName = name;
	}
	
	public Project(int id, String title, Object time, byte tag, String name, int authorId) {
		this.id = id;
		this.title = title;
		this.time = (Timestamp) time;
		this.tag = tag;
		this.teamName = name;
		this.authorId = authorId;
	}
	
	@Transient
	private String rName;   // 项目负责人名字
	public Project(int id, String title, Object time, String teamName, String rName) {
		this.id = id;
		this.title = title;
		this.time = (Timestamp) time;
		this.teamName = teamName;
	}
	
	public Project(String title, String teamName,  Object time, String content, String rName) {
		this.title = title;
		this.teamName = teamName;
		this.time = (Timestamp) time;
		this.content = content;
		this.rName = rName;
	}
	
	
	public Project(int id, String title, Object time, String name) {
		this.id = id;
		this.title = title;
		this.time = (Timestamp) time;
		this.teamName = name;
	}
	public Project(String title, Object time, String content, Integer teamId, String teamName) {
		this.title = title;
		this.time = (Timestamp) time;
		this.content = content;
		this.teamId = teamId;
		this.teamName = teamName;
	}
	
	public void setrName(String rName) {
		this.rName = rName;
	}
	
	public String getrName() {
		return rName;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	
	public void setAuthorId(String authorId) {
		this.authorId = Integer.valueOf(authorId);
	}
	
	public Integer getAuthorId() {
		return authorId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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
	
	public void setStatus(String status) {
		this.status = Byte.valueOf(status);
	}
	
	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	public void setTag(String tag) {
		this.tag = Byte.valueOf(tag);
	}
}

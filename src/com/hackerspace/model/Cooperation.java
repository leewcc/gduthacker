package com.hackerspace.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_cooperation")
public class Cooperation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 主键

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_id", columnDefinition="int(11) default -1")
	private User author; // 编写人

	@Column(name = "cooperation_name",columnDefinition="varchar(250) default ''")
	private String name; // 标题名字

	@Column(name = "cooperation_des",columnDefinition = "text")
	private String des; // 简介

	@Column(name = "cooperation_url",columnDefinition="varchar(250) default ''")
	private String url; // 链接

	@Column(name = "cooperation_date",columnDefinition="datetime default '2000-00-00 00:00:00'")
	private Timestamp date; // 日期

	@Column(name = "cooperation_tag",columnDefinition="bit(1) default 0")
	private byte tag; //0-合作项目 1-合作企业 

	@Column(name = "cooperation_status",columnDefinition="bit(1) default 0")
	private byte status; // 0-草稿箱 1-已发布

		
	
	
	public Cooperation() {
		super();
	}

	public Cooperation(Integer id, User author, String name, Object date,
			byte tag, byte status) {
		super();
		this.id = id;
		this.author = author;
		this.name = name;
		this.date = (Timestamp)date;
		this.tag = tag;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}


	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	

}

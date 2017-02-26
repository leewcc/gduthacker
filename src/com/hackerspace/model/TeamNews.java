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
import javax.persistence.Transient;

import org.hibernate.annotations.*;

@Entity
@Table(name = "t_team_news")
public class TeamNews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="author_id", referencedColumnName="id")
	private User author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="team_id", referencedColumnName="id")
	private Team team;
	
	@Column(name = "news_title",columnDefinition="varchar(250) default ''")
	private String title;
	
	@Column(name = "news_content",columnDefinition="varchar(250) default ''")
	private String content;
	
	@Column(name = "news_time",columnDefinition="datetime default '0000-00-00 00:00:00'")
	private Timestamp time;
	
	@Column(name = "news_status",columnDefinition="int(2) default 0")
	private int status;	//0-待审核  1-已通过 2-未通过
	
	@Column(name = "news_tag",columnDefinition="bit default 0")
	private byte tag;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}
		
}

package com.hackerspace.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.MessageDao;

@Entity
@Table(name = "t_message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "message_content",columnDefinition="varchar(250) default ''")
	private String content;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Message.class)
	@JoinColumns(value = { @JoinColumn(name = "message_parent", referencedColumnName = "id") })
	private List<Message> reply;

	@Column(name = "message_date",columnDefinition="datetime default '0000-00-00 00:00:00'")
	private Timestamp date;

	@Column(name = "message_status",columnDefinition="int(11) default -1")
	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Message> getReply() {
		return reply;
	}

	public void setReply(List<Message> reply) {
		this.reply = reply;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

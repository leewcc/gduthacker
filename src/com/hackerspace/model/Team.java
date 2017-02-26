package com.hackerspace.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import com.hackerspace.util.HibernateUtil;

@Entity
@Table(name = "t_team")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "team_name",columnDefinition="varchar(100) default ''")
	private String name;

	@Basic(fetch=FetchType.LAZY)
	@Column(name = "team_brief",columnDefinition="text")
	private String brief;

	@Column(name="team_motto", columnDefinition="varchar(200) defaukt ''")
	private String motto;
	
	@Column(name="team_create_time", columnDefinition="date")
	private Date createTime;
	
	@Column(name = "team_status",columnDefinition="int(11) default -1")
	private int status;

	@Column(name = "team_picture",columnDefinition="varchar(250) default ''")
	private String picture;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@JoinTable(name = "t_team_user", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> users;
	
	public Team() {
		
	}
	
	public Team(Integer id, String teamName) {
		this.id = id;
		this.name = teamName;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void getTeamUser(){
		if(users == null){
			Session s = HibernateUtil.sessionFactory.openSession();
			s.update(this);
			getUsers();
			s.close();
		}
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

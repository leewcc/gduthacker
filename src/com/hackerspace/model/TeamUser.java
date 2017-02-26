package com.hackerspace.model;

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

@Entity
@Table(name = "t_team_user")
public class TeamUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 主键

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "team_id",columnDefinition="int(11) default -1")
	private Team team; // 图队

	@ManyToOne
	@JoinColumn(name = "user_id",columnDefinition="int(11) default -1")
	private User user; // 用户

	@Column(name = "t_u_status",columnDefinition="int(11) default -1")
	private int status; // 0-申请入队  2-申请退出该团队    3-团队负责人； 4-属于该队  5-已退出团队

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Transient
	public String getUserStatus(){
		switch (status) {
		case 1:
			return "申请入队";
			
		case 2:
			return "申请离队";
			
		case 3:
			return "负责人";
			
		case 4:
			return "普通成员";
			
		case 5:
			return "已离队";

		default:
			return "";
		}
	}
}

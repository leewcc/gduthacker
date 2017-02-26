package com.hackerspace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private int id;

	@Column(name = "team_id",columnDefinition="int(11) default -1")
	private int teamId;// 团队id

	@Column(name = "user_id",columnDefinition="int(11) default -1")
	private int userId;// 用户id

	@Column(name = "user_status",columnDefinition="int(11) default -1")
	private int userStatus;

	public int getMemberId() {
		return id;
	}

	public void setMemberId(int id) {
		this.id = id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

}

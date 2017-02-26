package com.hackerspace.model;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * Created by tianx on 2016/2/29.
 */
@Entity
@Table(name = "t_studio_apply")
public class StudioApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Basic
	@Column(name = "team_id",columnDefinition="int(11) default -1")
	private int teamId;
	@Basic
	@Column(name = "studio_time",columnDefinition="int(11) default -1")
	private int studioTime;
	@Basic
	@Column(name = "studio_apply_time",columnDefinition="datetime default '0000-00-00 00:00:00'")
	private Timestamp applyTime;
	@Basic
	@Column(name = "studio_apply_status",columnDefinition="bool default 0")
	private boolean applyStatus;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getStudioTime() {
		return studioTime;
	}

	public void setStudioTime(int studioTime) {
		this.studioTime = studioTime;
	}

	public Timestamp getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Timestamp applyTime) {
		this.applyTime = applyTime;
	}

	public boolean isApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(boolean applyStatus) {
		this.applyStatus = applyStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StudioApply sdApply = (StudioApply) o;

		if (id != sdApply.id)
			return false;
		if (teamId != sdApply.teamId)
			return false;
		if (studioTime != sdApply.studioTime)
			return false;
		if (applyTime != sdApply.applyTime)
			return false;
		if (applyTime != null ? !applyTime.equals(sdApply.applyTime)
				: sdApply.applyTime != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + teamId;
		result = 31 * result + studioTime;
		result = 31 * result + (applyTime != null ? applyTime.hashCode() : 0);
		result = 31 * result + (applyStatus ? 1 : 0);
		return result;
	}
}

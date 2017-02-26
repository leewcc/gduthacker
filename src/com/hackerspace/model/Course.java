package com.hackerspace.model;

import javax.persistence.*;

import java.io.Serializable;

/**
 * Created by tianx on 2016/2/29.
 */
@Entity
@Table(name = "t_course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Basic
	@Column(name = "teacher_id",columnDefinition="int(11) default -1")
	private int teacherId;
	@Basic
	@Column(name = "course_name",columnDefinition="varchar(250) default ''")
	private String name;
	@Basic
	@Column(name = "course_brief",columnDefinition="varchar(250) default ''")
	private String brief;
	@Basic
	@Column(name = "course_time",columnDefinition="varchar(250) default ''")
	private String time;
	@Basic
	@Column(name = "course_type", columnDefinition = "enum('1', '2')")
	private String type;
	@Basic
	@Column(name = "course_credit",columnDefinition="int(11) default -1")
	private int credit;
	@Basic
	@Column(name = "course_limit_num",columnDefinition="int(11) default -1")
	private int limitNum;
	@Basic
	@Column(name = "course_select_num",columnDefinition="int(11) default -1")
	private int selectNum;
	@Basic
	@Column(name = "course_isopen",columnDefinition="boolean default 0")
	private Boolean courseIsOpen;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	public int getSelectNum() {
		return selectNum;
	}

	public void setSelectNum(int selectNum) {
		this.selectNum = selectNum;
	}

	public Boolean getCourseIsOpen() {
		return courseIsOpen;
	}

	public void setCourseIsOpen(Boolean courseIsOpen) {
		this.courseIsOpen = courseIsOpen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Course course = (Course) o;

		if (id != course.id)
			return false;
		if (teacherId != course.teacherId)
			return false;
		if (credit != course.credit)
			return false;
		if (limitNum != course.limitNum)
			return false;
		if (selectNum != course.selectNum)
			return false;
		if (name != null ? !name.equals(course.name) : course.name != null)
			return false;
		if (brief != null ? !brief.equals(course.brief) : course.brief != null)
			return false;
		if (time != null ? !time.equals(course.time) : course.time != null)
			return false;
		if (type != null ? !type.equals(course.type) : course.type != null)
			return false;
		if (courseIsOpen != null ? !courseIsOpen.equals(course.courseIsOpen)
				: course.courseIsOpen != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + teacherId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (brief != null ? brief.hashCode() : 0);
		result = 31 * result + (time != null ? time.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + credit;
		result = 31 * result + limitNum;
		result = 31 * result + selectNum;
		result = 31 * result
				+ (courseIsOpen != null ? courseIsOpen.hashCode() : 0);
		return result;
	}
}

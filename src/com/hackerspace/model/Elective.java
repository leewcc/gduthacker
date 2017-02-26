package com.hackerspace.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tianx on 2016/2/29.
 */
@Entity
@Table(name = "t_elective")
public class Elective {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Basic
	@Column(name = "course_id",columnDefinition="int(11) default -1")
	private int courseId;
	@Basic
	@Column(name = "student_id",columnDefinition="int(11) default -1")
	private int studentId;
	@Basic
	@Column(name = "student_score",columnDefinition="int(11) default -1")
	private int studentScore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(int studentScore) {
		this.studentScore = studentScore;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Elective elective = (Elective) o;

		if (id != elective.id)
			return false;
		if (courseId != elective.courseId)
			return false;
		if (studentId != elective.studentId)
			return false;
		if (studentScore != elective.studentScore)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + courseId;
		result = 31 * result + studentId;
		result = 31 * result + studentScore;
		return result;
	}
}

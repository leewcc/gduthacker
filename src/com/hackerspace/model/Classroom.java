package com.hackerspace.model;

import javax.persistence.*;

/**
 * Created by tianx on 2016/2/29.
 */
@Entity
@Table(name = "t_classroom")
public class Classroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "classroom_num",columnDefinition="varchar(50) default ''")
	private String num;
	@Column(name = "classroom_isopen",columnDefinition="boolean default 0")
	private boolean classroomIsOpen;
	@Column(name="classroom_position", columnDefinition="varchar(50) default ''")
	private String position;
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPosition() {
		return position;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public boolean isClassroomIsOpen() {
		return classroomIsOpen;
	}

	public void setClassroomIsOpen(boolean classroomIsOpen) {
		this.classroomIsOpen = classroomIsOpen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Classroom classroom = (Classroom) o;

		if (id == classroom.id)
			return true;
		

		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}
}

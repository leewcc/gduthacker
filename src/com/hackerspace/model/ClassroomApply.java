package com.hackerspace.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hackerspace.util.TimeParseUtil;

/**
 * Created by tianx on 2016/2/29.
 */
@Entity
@Table(name = "t_classroom_apply")
public class ClassroomApply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic
	@Column(name = "team",columnDefinition="varchar(100) default ''")
	private String team;
	@Basic
	@Column(name = "classroom_status",columnDefinition="int(11) default -1")
	private int status;   //1-早上  2-下午   3-晚上  4-全天
	@Basic
	@Column(name = "classroom_apply_date",columnDefinition="datetime default '0000-00-00 00:00:00'")
	private Date date;			//申请日期
	@Basic
	@Column(name = "classroom_isopen",columnDefinition="bit default 0")
	private byte classroomIsOpen;

	@Column(name="classroom_user" , columnDefinition="varchar(50) default ''")
	private String user;				//申请人名字
	
	@Column(name="classroom_contact" , columnDefinition="varchar(50) default ''")
	private String contact;		//申请人联系方式
	
	@Column(name ="classroom_ispass" , columnDefinition="tinyint default -2")
	private Integer isPass;     // -1: 未处理; 0: 未通过; 1: 已通过
	
	@Column(name="classroom_reason" , columnDefinition="varchar(400) default ''")
	private String reason;
	
	@Column(name="post_time", columnDefinition="datetime default '2016-01-01 00:00:00'")
	private Timestamp postTime;

	@Transient
	private String position;
	
	@Transient
	private String number;   // 申请课室号
	
	@ManyToOne
	@JoinColumn(name="classroom_id", referencedColumnName="id")
	private Classroom classroom;
	
	public ClassroomApply() {
		
	}
	public ClassroomApply(Integer id, String user, String team, Object date, Integer status, Classroom classroom, 
			Object postTime, Integer isPass) {
		this.id = id;
		this.user = user;
		this.team = team;
		this.date = (Date)date;
		this.status = status;
		this.classroom = classroom;
		this.postTime = (Timestamp) postTime;
		this.isPass = isPass;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return number;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	
	public Timestamp getPostTime() {
		return postTime;
	}
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setStatus(String status) throws NumberFormatException{
		this.status = Integer.valueOf(status);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDate(String date) throws ParseException {
		
		this.date = TimeParseUtil.StringtoDate(date);
		
	}
	public byte getClassroomIsOpen() {
		return classroomIsOpen;
	}

	public void setClassroomIsOpen(byte classroomIsOpen) {
		this.classroomIsOpen = classroomIsOpen;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer isPass() {
		return isPass;
	}

	public void setPass(Integer isPass) {
		this.isPass = isPass;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	
}

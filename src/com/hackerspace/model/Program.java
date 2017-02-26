package com.hackerspace.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_program")
public class Program {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "program_name",columnDefinition="varchar(250) default ''")
	private String name;
	@Column(name = "program_rank",columnDefinition="int(11) default -1")
	private Integer rank;//栏目等级
	@Column(name = "program_num",columnDefinition="int(11) default -1")
	private Integer num;//栏目序号
	
	@Column(name = "program_url",columnDefinition="varchar(250) default ''")
	private String url;//栏目链接
	
	@Column(name = "program_status"	,columnDefinition="int(11) default -1")
	private int status;//栏目状态 -1不开 0一级 其他数字id绑定值

	
	@Transient
	private ArrayList<Program> programs;
	

	public ArrayList<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(ArrayList<Program> programs) {
		this.programs = programs;
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
package com.hackerspace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_project_team")
public class TeamProjectRela {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private Integer id;
}

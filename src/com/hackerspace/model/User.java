package com.hackerspace.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.hackerspace.dao.PowerDao;

@Entity
@Table(name="t_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;				//主键id
	@Column (name="user_name",columnDefinition="varchar(250) default ''")
	private String name;			//姓名
	
	@Column (name="user_id_card",columnDefinition="varchar(250) default ''")
	private String idCard;			//身份证
	
	@Column (name="user_card_pic",columnDefinition="varchar(250) default ''")
	private String cardPic;			//学生证扫描件
	
	@Column (name="user_institute",columnDefinition="varchar(250) default ''")
	private String institute;		//学院
	
	@Column (name="user_major",columnDefinition="varchar(250) default ''")
	private String major;			//专业
	
	@Column (name="user_gender",columnDefinition="bool default 0")
	private boolean gender;			//性别
	
	@Column (name="user_picture",columnDefinition="varchar(250) default ''")
	private String picture;			//头像
	
	@Column (name="user_contact",columnDefinition="varchar(40) default ''")
	private String contact;
	
	@Column (name="user_role",columnDefinition="int(11) default -1")
	private int role;				//角色   1-普通用户 2-高级用户 3-录入员 4-管理员 5-高级管理员
	
	@Column (name="user_request",columnDefinition="int(11) default -1")
	private int request;			//请求标志  1-普通用户申请 2-高级用户申请 3-请求通过者 0-其他

	@Column (name="user_card",columnDefinition="varchar(250) default ''" , unique=true)
	private String  card;			//学号
			
	@Column (name="user_password",columnDefinition="varchar(250) default ''")
	private String password;		//密码
		
	@ManyToMany(mappedBy = "users")
	private List<Team> teams;
	
	@Transient
	private Power power;	//权限，临时属性，不存放到数据库
	
	public Power getPower() {
		if(power == null){
			power =  PowerDao.getMyPower(this);
		}
		
		return power;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setCardPic(String cardPic) {
		this.cardPic = cardPic;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setRequest(int request) {
		this.request = request;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		if(name == null)
			return "";
		
		return name;
	}

	public String getIdCard() {
		if(idCard == null)
			return "";
		
		return idCard;
	}

	public String getCardPic() {
		if(cardPic == null)
			return "";
		
		return cardPic;
	}

	public String getInstitute() {
		if(institute == null)
			return "";
		
		return institute;
	}

	public String getMajor() {
		if(major == null)
			return "";
		
		return major;
	}

	public boolean isGender() {
		return gender;
	}

	public String getPicture() {
		if(picture == null)
			return "";
		
		return picture;
	}

	public String getContact() {
		if(contact == null)
			return "";
		
		return contact;
	}

	public int getRole() {
		return role;
	}

	public int getRequest() {
		return request;
	}

	public String getCard() {
		if(card == null)
			return "";
		
		return card;
	}

	public String getPassword() {
		return password;
	}

	public List<Team> getTeams() {
		return teams;
	}

	
		
}

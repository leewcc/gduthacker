//package com.hackerspace.model;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="t_checkuser")
//public class CheckUser{
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private Integer id;				//主键id
//	
//	
//	
//	@Column (name="user_card",columnDefinition="varchar(250) default ''")
//	private String  Crad;			//学号
//	
//	
//	
//	@Column (name="user_password",columnDefinition="varchar(250) default ''")
//	private String password;		//密码
//	
//	
//	
//	@OneToMany (fetch=FetchType.LAZY, targetEntity=Security.class, 
//			cascade={
//				CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH
//			})
//	@JoinColumns(value = {@JoinColumn(name="user_id", referencedColumnName="id")})
//	private List<Security> securities;	//用户密保
//	
//	@ManyToMany(mappedBy = "users")
//	private List<Team> teams;
//	
//	@OneToOne (fetch=FetchType.LAZY, targetEntity=Security.class, 
//			cascade={
//				CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH
//			})
//	@JoinColumns(value = {@JoinColumn(name="user_id", referencedColumnName="id")})
//	private Power power;
//	
//	public List<Security> getSecurities() {
//		return securities;
//	}
//	public void setSecurities(List<Security> securities) {
//		this.securities = securities;
//	}
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getCrad() {
//		return Crad;
//	}
//	public void setStuCrad(String Crad) {
//		this.Crad = Crad;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	public List<Team> getTeams() {
//		return teams;
//	}
//	public void setTeams(List<Team> teams) {
//		this.teams = teams;
//	}
//	public Power getPower() {
//		return power;
//	}
//	public void setPower(Power power) {
//		this.power = power;
//	}
//	
//}

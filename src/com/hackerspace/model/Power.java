package com.hackerspace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="t_power")
public class Power {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@Column (name="can_user",columnDefinition="int(11) default -1")
	private boolean canUser;
	
	@Column (name="can_picture",columnDefinition="int(11) default -1")
	private boolean canPicture;
	
	@Column (name="can_serivce",columnDefinition="int(11) default -1")
	private boolean canSerivce;
	
	@Column (name="can_system",columnDefinition="int(11) default -1")
	private boolean canSystem;
	
	@Column (name="can_message",columnDefinition="int(11) default -1")
	private boolean canMessage;
	public boolean isCanUser() {
		return canUser;
	}
	public void setCanUser(boolean canUser) {
		this.canUser = canUser;
	}
	public boolean isCanPicture() {
		return canPicture;
	}
	public void setCanPicture(boolean canPicture) {
		this.canPicture = canPicture;
	}
	public boolean isCanSerivce() {
		return canSerivce;
	}
	public void setCanSerivce(boolean canSerivce) {
		this.canSerivce = canSerivce;
	}
	public boolean isCanSystem() {
		return canSystem;
	}
	public void setCanSystem(boolean canSystem) {
		this.canSystem = canSystem;
	}
	public boolean isCanMessage() {
		return canMessage;
	}
	public void setCanMessage(boolean canMessage) {
		this.canMessage = canMessage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

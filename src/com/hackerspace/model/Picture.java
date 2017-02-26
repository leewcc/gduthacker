package com.hackerspace.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_picture")
public class Picture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 主键
	private Integer id;

	@Column(name = "picture_url",columnDefinition="varchar(250) default ''")
	// 图片存储的位置
	private String pictureUrl;

	@Column(name = "picture_belong",columnDefinition="varchar(250) default ''")
	// 图片所属的栏目
	private String pictureBelong;

	@Column(name = "picture_loader_id",columnDefinition="int(11) default -1")
	// 上传者id
	private int uploaderId;
	@Transient
	private int x;// x轴的起点
	@Transient
	private int y;// y轴的起点
	@Transient
	private int w;// x轴的宽度
	@Transient
	private int h;// y轴的高度
	@Transient
	private int wid;// 要设置的宽度
	@Transient
	private int hei;// 要设置的高度

	@Transient
	private String oldPictureUrl;// 原图的位置

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		if("\\".equals(File.separator)) {
			this.pictureUrl = pictureUrl;//.replaceAll("/", "\\");
		} else if("/".equals(File.separator)) {//  /的情况
			this.pictureUrl = pictureUrl.replaceAll("\\", "/");
		} else {
			this.pictureUrl = pictureUrl.replaceAll("\\", File.separator).replaceAll("/", File.separator);
		}
		
	}

	public String getPictureBelong() {
		return pictureBelong;
	}

	public void setPictureBelong(String pictureBelong) {
		this.pictureBelong = pictureBelong;
	}

	

	public int getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(int uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getOldPictureUrl() {
		return oldPictureUrl;
	}

	public void setOldPictureUrl(String oldPictureUrl) {
		if("\\".equals(File.separator)) {
			this.oldPictureUrl = oldPictureUrl;//.replaceAll("/", "\\");
		} else if("/".equals(File.separator)) {//  /的情况
			this.oldPictureUrl = oldPictureUrl.replaceAll("\\", "/");
		} else {
			this.oldPictureUrl = oldPictureUrl.replaceAll("\\", File.separator).replaceAll("/", File.separator);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public int getHei() {
		return hei;
	}

	public void setHei(int hei) {
		this.hei = hei;
	}



}

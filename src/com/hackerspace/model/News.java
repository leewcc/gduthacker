package com.hackerspace.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_news")
public class News {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "author_id", columnDefinition = "int(11) default -1")
	private Integer authorId;
	@Column(name = "news_title", columnDefinition = "varchar(250) default ''")
	private String title;
	@Column(name = "news_content", columnDefinition = "text")
	private String content;
	@Column(name = "news_time", columnDefinition = "datetime default '0000-00-00 00:00:00'")
	private Timestamp time;
	@Column(name = "click_num", columnDefinition = "int(11) default 0")
	private int clickNum;// 0表示学校新闻 1表示活动新闻
	@Column(name = "news_status", columnDefinition = "bit default 0")
	private byte status;// 0-表示 草稿  1-表示已发布
	@Column(name = "news_istop", columnDefinition = "bit default 0")
	private byte isTop;//0-没置顶 1-已置顶
	@Column(name = "news_tag", columnDefinition = "bit default 0")
	private byte tag;// 0表示学校新闻 1表示活动新闻
	
	@Transient
	private String authorName;
	
	@Transient
	private String imageUrl;
	
	public News(Integer id,String title, String content, Object time, int clickNum,
			byte isTop, byte tag, String authorName) {
		super();
		this.id=id;
		this.title = title;
		this.content = content;
		this.time = (Timestamp)time;
		this.clickNum = clickNum;
		this.isTop = isTop;
		this.tag = tag;
		this.authorName = authorName;
	}


	public News(Integer id, String title, Object time, byte isTop,byte status,String authorName) {
		super();
		this.id = id;
		this.title = title;
		this.time=(Timestamp)time;
		this.status=status;
		this.isTop = isTop;
		this.authorName = authorName;
	}

	
	public News() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getIsTop() {
		return isTop;
	}

	public void setIsTop(byte isTop) {
		this.isTop = isTop;
	}

	public byte getTag() {
		return tag;
	}

	public void setTag(byte tag) {
		this.tag = tag;
	}

	  
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	



	public int getClickNum() {
		return clickNum;
	}


	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public String toString() {
		return "News [id=" + id + ", authorId=" + authorId + ", title=" + title
				+ ", content=" + content + ", time=" + time + ", status="
				+ status + ", isTop=" + isTop + ", tag=" + tag
				+ ", authorName=" + authorName + "]";
	}

}

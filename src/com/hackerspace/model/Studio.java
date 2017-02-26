package com.hackerspace.model;

import javax.persistence.*;

/**
 * Created by tianx on 2016/2/29.
 */
@Entity
@Table(name = "t_studio")
public class Studio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic
	@Column(name = "studio_num",columnDefinition="int(11) default -1")
	private int studioNum;
	@Basic
	@Column(name = "studio_isopen",columnDefinition="bit default 0")
	private byte studioIsOpen;
	
	// many to one;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudioNum() {
		return studioNum;
	}

	public void setStudioNum(int studioNum) {
		this.studioNum = studioNum;
	}

	public byte getStudioIsOpen() {
		return studioIsOpen;
	}

	public void setStudioIsOpen(byte studioIsOpen) {
		this.studioIsOpen = studioIsOpen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Studio studio = (Studio) o;

		System.out.println(false);
		if (id != studio.id)
			return false;
		if (studioNum != studio.studioNum)
			return false;
		if (studioIsOpen != studio.studioIsOpen)
			return false;

		System.out.println("true");
		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + studioNum;
		result = 31 * result + ((studioIsOpen == 1) ? 1 : 0);

		System.out.println(result);
		return result;
	}
}

package com.hackerspace.service;

import com.hackerspace.dao.TestDao;
import com.hackerspace.model.Picture;

/**
 * Waring:文件后面加上service
 * @author CHEN
 *
 */
public class TestService {

	TestDao td=new TestDao();
	public boolean addPicture(Picture p) {
		return td.addPicture(p);
		
	}

}

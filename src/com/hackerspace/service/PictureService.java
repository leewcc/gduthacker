package com.hackerspace.service;

import java.util.List;

import com.hackerspace.dao.PictureDao;
import com.hackerspace.model.Picture;

public class PictureService {
	

	public List<Picture> findPicture(String name) {
		PictureDao pd=new PictureDao();
		return pd.findPicture(name);
	}

	public boolean updatePicture(Picture p) {
		PictureDao pd=new PictureDao();
		return pd.updatePicture(p);
		
	}
	
	public Picture getPicture(int id) {
		PictureDao pd=new PictureDao();
		return pd.getPicture(id);
	}
	
	public String getOnePicture(String belong) {
		PictureDao pd=new PictureDao();
		return  pd.getOnePicture(belong).getPictureUrl();
	}

}

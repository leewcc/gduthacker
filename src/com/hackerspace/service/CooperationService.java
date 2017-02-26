package com.hackerspace.service;

import com.hackerspace.dao.CooperationDao;
import com.hackerspace.model.Cooperation;
import com.hackerspace.model.PageElem;

public class CooperationService {

	public boolean saveCooperation(Cooperation news) {
		CooperationDao nd=new CooperationDao();
		return nd.saveCooperation(news);
	}
	
	public boolean findCooperation(byte status,byte tag,PageElem<Cooperation> pe) {
		CooperationDao nd=new CooperationDao();
		nd.findCooperation(status, tag, pe);
		return true;
	}

	public boolean deletePublishedCooperation(int id) {
		CooperationDao nd=new CooperationDao();
		return nd.deletePublishedCooperation(id);
		
	}

	public boolean findDrafts(byte status,PageElem<Cooperation> pageElem) {
		CooperationDao nd=new CooperationDao();
		nd.findDrafts(status, pageElem);
		return  true;
	}


	public boolean updateCooperation(Cooperation news) {
		CooperationDao nd=new CooperationDao();
		return nd.updateCooperation(news);
	}

	public Cooperation getOneArticle(int id) {
		CooperationDao nd=new CooperationDao();
		
		return nd.getOneArticle(id);
	}


}

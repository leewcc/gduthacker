package com.hackerspace.service;

import com.hackerspace.dao.CooperationDao;
import com.hackerspace.dao.EducationDao;
import com.hackerspace.model.Cooperation;
import com.hackerspace.model.Education;
import com.hackerspace.model.PageElem;

public class EducationService {
	public boolean saveEducation(Education education) {
		EducationDao nd=new EducationDao();
		return nd.saveEducation(education);
	}
	
	public boolean findEducation(byte status,byte tag,PageElem<Education> pe) {
		EducationDao nd=new EducationDao();
		nd.findEducation(status, tag, pe);
		return true;
	}

	public boolean deletePublishedEducation(int id) {
		EducationDao nd=new EducationDao();
		return nd.deletePublishedEducation(id);
		
	}

	public boolean findDrafts(byte status,PageElem<Education> pageElem) {
		EducationDao nd=new EducationDao();
		return nd.findDrafts(status, pageElem);
		
	}


	public boolean updateEducation(Education news) {
		EducationDao nd=new EducationDao();
		return nd.updateEducation(news);
	}

	public Education getOneArticle(int id) {
		EducationDao nd=new EducationDao();
		
		return nd.getOneArticle(id);
	}
}

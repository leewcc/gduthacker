package com.hackerspace.service;

import java.util.List;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.dao.SecurityDao;
import com.hackerspace.model.Security;
import com.hackerspace.model.User;

public class SecurityService {
	private BaseDao bd;
	
	public SecurityService() {
		bd = new BaseDao<>();
	}
	
	public void creare(List<Security> securities) throws Exception{
		bd.createBatch(securities);
	}
	
	public void delete(List<Security> securities) throws  Exception{
		bd.deleteBatch(securities);
	}
	
	public List<Security> getMySecurity(User u) throws Exception{
		return SecurityDao.getMySecurity(u);
	}
}

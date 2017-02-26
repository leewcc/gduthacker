package com.hackerspace.service;

import com.hackerspace.dao.BaseDao;
import com.hackerspace.model.Power;

public class PowerSerivce {
	private BaseDao<Power> bd;
	
	public PowerSerivce(){
		bd =new BaseDao<>();
	}
	
	public void serPower(Power P) throws Exception{
		bd.update(P);
	}
	
	public void create(Power p) throws Exception{
		bd.create(p);
	}
}

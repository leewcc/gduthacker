package com.hackerspace.action.manager.superm;

import com.hackerspace.model.Power;
import com.hackerspace.service.PowerSerivce;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ManagePowerAction extends ActionSupport implements ModelDriven{
	private Power p;
	
	public Power getP() {
		return p;
	}

	public void setP(Power p) {
		this.p = p;
	}

	public String setPower() {
		try{
			//第一步：	创建权限服务
			PowerSerivce ps = new PowerSerivce();
			
			//第二步：	执行设置权限的方法
			ps.serPower(p);
			
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("设置权限出错");
		}
	}
	
	@Override
	public Object getModel() {
		if(p == null)
			p = new Power();
		
		return p;
	}
}

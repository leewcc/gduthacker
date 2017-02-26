package com.hackerspace.action;

import com.hackerspace.model.Picture;
import com.hackerspace.service.TestService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Waring:文件后面加上action，实现ModelDriven接口
 * @author CHEN
 * 
 */
public class TestAction extends ActionSupport implements ModelDriven{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TestService ts=new TestService();
	private Picture p;
	@Override
	public Object getModel() {
		if(p==null) {p=new Picture();}
		return p;
	}
	/**
	 * 返回值：插入数据库的主键值
	 * 功能：保存Picture对象
	 * 说明:暂无
	 */
	public String addPicture() {
		System.out.println(p.getPictureBelong());
		if(ts.addPicture(p))
			return SUCCESS;
		else return "fail";
	}
	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
	
	
}

package com.hackerspace.util;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.dao.NewsDao;
import com.hackerspace.model.Picture;


public class Test {
	public static void main(String[] args) {

		addPicture();
	}
	
	public static void addPicture() {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=s.beginTransaction();
		//准备Picture对象
		Picture p=new Picture();
		p.setPictureBelong("1");
		p.setPictureUrl("1.jpg");
		p.setUploaderId(1);
		//把对象存入session
		//返回主键值
		System.out.println(s.save(p));
		//提交事务
		t.commit();
		//关闭session
		s.close();
	}
	public static void deletePicture() {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=s.beginTransaction();
		//准备Picture对象
		Picture p=(Picture)s.load(Picture.class,1);
		//把对象存入session
		//返回主键值
		s.delete(p);
		//提交事务
		t.commit();
		//关闭session
		s.close();
	}
	
}

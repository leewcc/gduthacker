package com.hackerspace.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.Picture;
import com.hackerspace.util.HibernateUtil;

public class TestDao {
	
	/**
	 * 说明：保存单个图片
	 */
	public boolean addPicture(Picture p) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=null;
		try {
			 t=s.beginTransaction();
			//把对象存入session
			//返回主键值
			s.save(p);
			//提交事务
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(t!=null){
				t.rollback();//回滚
			}
			return false;
		} finally {
			//关闭session
			s.close();
			
		}
	}
	/**
	 * 说明：保存批量图片
	 */
	public static void main(String[] args) {
		List<Picture> l=new ArrayList<Picture>();
		for(int i=0;i<10;i++) {
			Picture p=new Picture();
			p.setId(1);
			p.setPictureBelong(""+i);
			
			l.add(p);}
		TestDao td=new TestDao();
		td.addPictureList(l);
		
	}
	public boolean addPictureList(List<Picture> pl){
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=null;
		try {
			 t=s.beginTransaction();
			//把对象存入session
			//返回主键值
			for(Picture p:pl) {
				s.save(p);
			}
			//提交事务
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(t!=null){
				t.rollback();//回滚
			}
			return false;
		} finally {
			//关闭session
			s.close();
			
		}
	}
	/**
	 * * @param id:照片的id
	 * 说明：删除照片
	 */
	public boolean deletePicture(int id) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=null;
		try {
			 t=s.beginTransaction();
			//准备要删除的对象
			Picture p=(Picture)s.load(Picture.class, id);
			s.delete(p);
			//提交事务
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(t!=null){
				t.rollback();//回滚
			}
			return false;
		} finally {
			//关闭session
			s.close();
			
		}
	}
	/**
	 * @param idl:要删除的图片id集合
	 * 说明：批量删除照片
	 */
	public boolean deletePicture(List<Integer> idl) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=null;
		try {
			 t=s.beginTransaction();
			//准备要删除的对象
			 for(Integer id:idl) {
				 Picture p=(Picture)s.load(Picture.class, id);
				 s.delete(p);
			}
			//提交事务
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(t!=null){
				t.rollback();//回滚
			}
			return false;
		} finally {
			//关闭session
			s.close();
		}
	}
	/**
	 * * @param id:照片的id
	 * 说明：更新照片
	 */
	public boolean UpdatePicture(Picture p) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=null;
		try {
			 t=s.beginTransaction();
			//准备要更新的对象
			s.update(p);
			//提交事务
			t.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(t!=null){
				t.rollback();//回滚
			}
			return false;
		} finally {
			//关闭session
			s.close();
			
		}
	}
	/**
	 * * @param des:照片的描述
	 * 说明：根据描述查找返回照片
	 */
	public List<Picture> findPicture(String des) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			//进行查找，统一使用HQL语句
			 String hql="from t_picture where picture_belong=:des";
			 Query q=s.createQuery(hql);
			 q.setString("des",des);
			 List<Picture> pl=q.list();
			return pl;
		} finally {
			//关闭session
			s.close();
			
		}
	}
}

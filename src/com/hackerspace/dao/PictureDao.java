package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.Picture;
import com.hackerspace.util.HibernateUtil;

public class PictureDao {

	/**
	 * * @param name:照片的栏目
	 * 说明：根据描述查找返回照片
	 */
	public List<Picture> findPicture(String belong) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			//进行查找，统一使用HQL语句
			 String hql="from Picture where pictureBelong=:belong";
			 Query q=s.createQuery(hql);
			 q.setString("belong",belong);
			 List<Picture> pl=q.list();
			return pl;
		} finally {
			//关闭session
			s.close();
			
		}
	}

	/**
	 * * @param id:照片的id
	 * 说明：更新照片
	 */
	public boolean updatePicture(Picture p) {
		//获得session缓存
		Session s=HibernateUtil.sessionFactory.openSession();
		//开启事务
		Transaction t=null;
		try {
			 t=s.beginTransaction();
			//准备要更新的对象
			s.saveOrUpdate(p);
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
	 * 获得一个图片对象
	 */
	public Picture getPicture(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Picture p=(Picture)s.get(Picture.class, id);
		s.close();
		return p;
	}

	public Picture getOnePicture(String belong) {
		Session s=HibernateUtil.sessionFactory.openSession();
		String hql="from Picture where pictureBelong=:belong";
		Transaction t=s.beginTransaction();
		Query query=s.createQuery(hql);
		query.setString("belong", belong);
		try {
			t.commit();
			if(query.list().size()>0) {
				return (Picture)query.list().get(0);
			} else {
				return new Picture();
			}
		} finally {
			s.close();
		}
	}
}

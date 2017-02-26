package com.hackerspace.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.Cooperation;
import com.hackerspace.model.PageElem;
import com.hackerspace.util.HibernateUtil;

public class CooperationDao {
	/**
	 * 存储合作
	 * @param cooperation
	 * @return
	 */
	public boolean saveCooperation(Cooperation cooperation) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=null;
		try {
			t=s.beginTransaction();
			s.save(cooperation);
			t.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		} finally {
			s.close();
		}
	}
	/**
	 * 返回合作
	 * @param status
	 * @param tag
	 * @return
	 */
	public static boolean findCooperation(byte status,byte tag,PageElem<Cooperation> pe) {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			String hql="select count(*) from Cooperation where status=:status and tag=:tag";
			Query q=s.createQuery(hql);
			q.setByte("status", status);
			q.setByte("tag",tag);
			Long totalNum=(Long)q.list().get(0);
			pe.setRows(totalNum.intValue());
			
			hql="select new Cooperation(c.id,c.author,c.name,c.date,c.tag,c.status) "
					+ "from Cooperation as c "
					+ "where c.status=:status and c.tag=:tag order by c.id desc";
			q=s.createQuery(hql);
			q.setByte("status", status);
			q.setByte("tag",tag);
			q.setMaxResults(pe.getPageShow());
			q.setFirstResult(pe.getStartSearch());
			List<Cooperation> list=(ArrayList<Cooperation>)q.list();
			pe.setPageElem(list);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
	}
//	public static void main(String[] args) {
//		PageElem<Cooperation> pe=new PageElem<Cooperation>();
//		pe.setPageShow(1);
//		findCooperation((byte)1,(byte)1,pe);
//		
//	}
	/**
	 * 返回草稿箱
	 * @param status
	 * @return
	 */
	public boolean findDrafts(byte status,PageElem<Cooperation> pe) {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			String hql="select count(*) from Cooperation where status=:status";
			Query q=s.createQuery(hql);
			q.setByte("status", status);
			Long totalNum=(Long)q.list().get(0);
			pe.setRows(totalNum.intValue());

			hql="select new Cooperation(c.id,c.author,c.name,c.date,c.tag,c.status) "
					+ "from Cooperation as c "
					+ "where c.status=:status";
			q=s.createQuery(hql);
			q.setByte("status", status);//草稿箱
			q.setMaxResults(pe.getPageShow());
			q.setFirstResult(pe.getStartSearch());
			ArrayList<Cooperation> list=(ArrayList<Cooperation>) q.list();
			pe.setPageElem(list);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
	}
	public boolean deletePublishedCooperation(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Cooperation n=(Cooperation) s.load(Cooperation.class,id);
		s.delete(n);
		t.commit();
		s.close();
		return true;
	}


	public boolean updateCooperation(Cooperation cooperation) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.update(cooperation);
		t.commit();
		s.close();
		return true;
	}
	public Cooperation getOneArticle(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		try {
			String hql="from Cooperation as c where c.id=:id ";
			Query q=s.createQuery(hql);
			q.setInteger("id", id);
			ArrayList<Cooperation> list=(ArrayList<Cooperation>) q.list();
			t.commit();
			return list.get(0);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			s.close();
		}
	}
}

package com.hackerspace.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;

import com.hackerspace.exception.DaoException;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Project;
import com.hackerspace.model.ProjectShow;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;

public class ProjectShowDao {
	
	private static Logger log1 = Log.get(ProgramDao.class);
	
	public ProjectShowDao() {}
	
	public ProjectShowDao(Logger log) {
		ProjectShowDao.log1 = log;
	}
	
	/**
	 * 查询项目路演的列表
	 * @param pageElem
	 * @param status 1：已发布; 0: 未发布
	 * @return
	 * @throws DaoException 
	 */
	public void queryProjectShows(PageElem<ProjectShow> pageElem, byte status) throws DaoException {
		Session s=HibernateUtil.sessionFactory.openSession();
		
		try {
			String hql = "SELECT new ProjectShow(p.id, p.hitNum, p.title, p.content, p.time, u.name) FROM ProjectShow as p, User as u WHERE p.status = :status and p.authorId = u.id ORDER BY p.time DESC";
			
			s.beginTransaction();
			Query query = s.createQuery(hql);
			query.setByte("status", status);
			query.setFirstResult(pageElem.getStartSearch());
			query.setMaxResults(pageElem.getPageShow());  
			
	        List<ProjectShow> list = query.list();
	        
	        s.getTransaction().commit();
	        pageElem.setPageElem(list);
	        pageElem.setRows(list.size());
		}catch (HibernateException e) {
			s.getTransaction().rollback();
			Log.error(log1, e, "queryProjectShows(pageElem)操作失败");
			throw new DaoException();
		} finally {
			s.close();
		}
		
	}
	
	
	public int updateProjectShowSta(int id, byte status) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		int rows = 0;
		try {
			String hql = "UPDATE ProjectShow SET status = :status WHERE id = :id";
			
			session.beginTransaction();
			
			Query query =  session.createQuery(hql);
			query.setByte("status", status);
			query.setInteger("id", id);
			
			rows = query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			Log.error(log1, e, "updateProjectShowSta异常");
			throw new DaoException();
		} finally {
			session.close();
		}
		
		return rows;
	}
	
	
	public int deleteProjectShow(int id) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		int rows = 0;
		
		try {
			String hql = "DELETE ProjectShow WHERE id = :id";
			
			session.beginTransaction();
			
			Query query = session.createQuery(hql);
			query.setInteger("id", id);
	
			rows = query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			Log.error(log1, e, "deleteProjectShow操作失败");
			throw new DaoException();
		} finally {
			session.close();
		}
		
		return rows;
	}
	
	public ProjectShow queryProjectShow(int id) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		List<ProjectShow> list = null;
		try {
			String hql = "SELECT new ProjectShow(ps.title, u.name, ps.time, ps.hitNum, ps.content) "
					+ " FROM ProjectShow as ps, User as u "
					+ " WHERE ps.id = :id and ps.authorId = u.id";
			
			session.beginTransaction();
			
			Query query = session.createQuery(hql);
			query.setInteger("id", id);
			
			
			list = query.list();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log1, e, "queryProjectShows(pageElem)操作失败");
			throw new DaoException();
		} finally {
			session.close();
		}
		
		return list.size() == 1? list.get(0) : null;
	}
	
	
	public int updateHitNum(int id) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		int rows = 0;
		try {
			session.beginTransaction();
			String hql = "UPDATE ProjectShow SET hitNum=hitNum+1 WHERE id = :id";
			
			Query query =  session.createQuery(hql);
			query.setInteger("id", id);
			
			rows = query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log1, e, "queryProjectShows(pageElem)操作失败");
			throw new DaoException();
		} finally {
			session.close();
		}
		
		return rows;
	}


	public int updateProjectShowContent(int id, String content, String title, Timestamp time, int authorId) throws DaoException {
		Session s = HibernateUtil.sessionFactory.openSession();
		int rows = 0;
		try {
			String hql = "UPDATE ProjectShow SET title=:title , content=:content, time=:time, authorId=:authorId WHERE id=:id";
			
			s.beginTransaction();
			
			Query query = s.createQuery(hql);
			query.setString("content", content);
			query.setString("title", title);
			query.setTimestamp("time", time);
			query.setInteger("id", id);
			query.setInteger("authorId", authorId);
			
	        rows = query.executeUpdate();
	        
	        s.getTransaction().commit();
		}catch (HibernateException e) {
			s.getTransaction().rollback();
			Log.error(log1, e, "queryProjectShows(pageElem)操作失败");
			throw new DaoException();
		} finally {
			s.close();
		}
		
		return rows;
	}
}

package com.hackerspace.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;

import com.hackerspace.exception.DaoException;
import com.hackerspace.model.Classroom;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;

public class ClassroomDao {
	
	private static Logger log = Log.get(ClassroomDao.class);
	
	public ClassroomDao() {}
	
	public ClassroomDao(Logger log) {
		ClassroomDao.log = log;
	}
	
	/**
	 * 查询所有课室
	 * @return
	 * @throws DaoException
	 */
	public List<Classroom> selectClassroom() throws DaoException{
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		List<Classroom> list = null;
		try {
			String hql = "from Classroom";
			
			session.beginTransaction();
			
			query = session.createQuery(hql);
			list = query.list();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomDao", query.getQueryString());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return list;
	}
	
	
	public int deleteClassroom(int id) throws DaoException {	
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		int rows = 0;
		try {
			String hql = "DELETE Classroom WHERE id = :id";
			
			session.beginTransaction();
			
			query = session.createQuery(hql);
			query.setInteger("id", id);
			
			rows = query.executeUpdate();
			
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomDao", query.getQueryString());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return rows;
	}
}

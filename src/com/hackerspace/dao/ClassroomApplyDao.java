package com.hackerspace.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;

import com.hackerspace.exception.DaoException;
import com.hackerspace.model.Classroom;
import com.hackerspace.model.ClassroomApply;
import com.hackerspace.model.PageElem;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;

public class ClassroomApplyDao {
	
	private static Logger log = Log.get(ClassroomApplyDao.class);
	
	public ClassroomApplyDao() {}
	
	public ClassroomApplyDao(Logger log) {
		ClassroomApplyDao.log = log;
	}
	
	/**
	 * 方法说明：	查询某个时间段已经被申请的课室
	 * @param d-日期
	 * @param status 1-早上 2-下午 3-晚上 4-整天
	 * @return
	 * @throws Exception
	 */
	public List<ClassroomApply> selectClassroomApply(Date d, int status) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();
		List<ClassroomApply>  list = null;
		Query query = null;
		try {
			String hql = null;
			session.beginTransaction();
			
			if (status == 4) {
				 hql = "select cp.classroom from ClassroomApply as cp where cp.date = :date";
				 query = session.createQuery(hql);
				 query.setDate("date", d);
			}else {
				 hql = "select cp.classroom from ClassroomApply as cp where cp.date = :date and cp.status = :time";
				 query = session.createQuery(hql);
				 query.setDate("date", d);
				 query.setInteger("time", status);
			}
			
			list = query.list();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomApplyDao", query.getQueryString());
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return list;
	}

	
	
	
	public void  queryClassroomApplies(PageElem<ClassroomApply> pageElem) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		
		try {
			session.beginTransaction();
			String hql = " from ClassroomApply as cp "
					+ "WHERE cp.classroomIsOpen = :isOpen "
					+ "ORDER BY cp.isPass, cp.postTime desc";	
			
			query = session.createQuery(hql);
			query.setByte("isOpen", (byte)1); 
			query.setFirstResult(pageElem.getStartSearch());
			query.setMaxResults(pageElem.getPageShow());
			
			List<ClassroomApply> l = query.list();	
			pageElem.setRows(l.size());
			pageElem.setPageElem(l);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomApplyDao", query.getQueryString());
			throw new DaoException(e);
		} finally {
			session.close();
		}
	}
	
	public int updateStatu(int id, Integer isPassed) throws DaoException {
		// -1 未处理l; 0 未通过; 1 已通过
		Session session = HibernateUtil.sessionFactory.openSession();
		Query q = null;
		int rows = 0;
		try {
			String hql = "UPDATE ClassroomApply SET isPass=:isPass WHERE id = :id";
			session.beginTransaction();
			q= session.createQuery(hql);
			q.setInteger("isPass", isPassed);
			q.setInteger("id", id);
			
			rows = q.executeUpdate();
		
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomApplyDao", q.getQueryString());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return rows;
	}

	public List<ClassroomApply> queryClassroomApply(int status, String date) throws DaoException {	
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		List<ClassroomApply> list = null;
		try {
			String hql = null;
			session.beginTransaction();
			
			if (status == 4) {
				 hql = " from ClassroomApply  where date = :date";
				 query = session.createQuery(hql);
				 query.setString("date", date);
			}else {
				 hql = " from ClassroomApply  where date = :date and status = :status";	
				 query = session.createQuery(hql);	
				 query.setString("date", date);
				 query.setInteger("status", status);
			}
			
			list = query.list();
			session.getTransaction().commit();
		}catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomApplyDao", query.getQueryString());
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return list;
	}
	
	
	public List<Classroom> queryClassroomAll () throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		List<Classroom> list = null;
		try {
			String hql = "from Classroom where classroomIsOpen = true";
			
			session.beginTransaction();
			
			query = session.createQuery(hql);
			
			list = query.list();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ClassroomApplyDao", query.getQueryString());
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return list;
	}


	public void create(ClassroomApply cra) throws DaoException{
		Session session = HibernateUtil.sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			Classroom room = (Classroom)session.load(Classroom.class, cra.getClassroom().getId());
			cra.setClassroom(room);
			session.save(cra);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			Log.error(log, e, "apply classroom DAO is Exception");
			session.getTransaction().rollback();
			throw new DaoException();
		} finally {
			session.close();
		}
	}
}

package com.hackerspace.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.ApplyFile;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;

public class EnterApplyDao {
	
	
	private static Logger log;
	
	public EnterApplyDao() {}
	
	public EnterApplyDao(Logger log) {
		EnterApplyDao.log = log;
	}
	
	public void insertApplyFile(ApplyFile file, Session session) throws DaoException {	
		
		try {
			session.beginTransaction();
			session.save(file);
			session.getTransaction().commit();
		}catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={createR}]: ",e.getMessage());
			throw new DaoException(e);
		}
		
	}
	
	
	public ApplyFile queryApplyFile() throws DaoException {
		Session s=HibernateUtil.sessionFactory.openSession();
		Query query = null;
		List<ApplyFile> list = null;
		try {
			String hql = "from ApplyFile";
			
			s.beginTransaction();
			
			query=  s.createQuery(hql);
			
	        list = query.list();  
	        
	        s.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "EnterApplyDao", query.getQueryString());
			throw new DaoException(e);
		}finally {
			s.close();
		}
		
		if (list.size() > 1) {
			Log.warn(log, "上传文件数已经超出; = {}", list.size());
		}
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * 删除表中所有数据
	 * @param s
	 * @return
	 * @throws DaoException
	 */
	public int deleteApplyFile(Session s) throws DaoException {
		Query query = null;
		int rows = 0;
		try {
			String hql = "DELETE FROM ApplyFile";
	
			s.beginTransaction();
			
			query  = s.createQuery(hql);
			
			rows = query.executeUpdate();
			
			s.getTransaction().commit();	
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "EnterApplyDao", query.getQueryString());
			throw new DaoException(e);
		}
		
		return rows;
	}
	
}

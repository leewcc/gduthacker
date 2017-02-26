package com.hackerspace.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.map.StaticBucketMap;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hackerspace.exception.DaoException;
import com.hackerspace.util.Log;
import com.hackerspace.util.HibernateUtil;




public class BaseTDao<T> {
	
	private static Logger log = Log.get(BaseTDao.class);
	
	public BaseTDao() {
		
	}
	
	public BaseTDao(Logger log) {
		BaseTDao.log = log;
	}
	
	public  void create(T object) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		
		}catch (HibernateException e) {
			session.getTransaction().rollback();
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={method}]: ",
					e.getMessage());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "create: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		}
	}

	public  Serializable createR(T object) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		Serializable result;
		try {
			session.beginTransaction();
			result = session.save(object);
			session.getTransaction().commit();
		}catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={createR}]: ",e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "createR: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		} 
		
		return result;
	}
	
	public void update(T object) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		}catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={createR}]: ",e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "createR: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		} 
	}

		
	public  void delete(T object) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={delete}]: ",e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "delete: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		} 
	}

		
	public T find(Class<? extends T> clazz, Serializable id) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			return (T)session.get(clazz, id);
		}catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={find}]: ",e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "find: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		} 
	}

	public List<T> list(String sql) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			return session.createQuery(sql).list();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={createR}]: ",e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "createR: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		} 
	}
	
	public int executeSql(String sql) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			return session.createQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			Log.error(log,  "SQL exception[msg={}, dao={BaseTDao}, method={executeSql}]: ",e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log, e, "executeSql: " + e.getMessage());
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			session.close();
		} 
	}
}

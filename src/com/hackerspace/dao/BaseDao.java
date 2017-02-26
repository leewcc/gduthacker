package com.hackerspace.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;

import com.hackerspace.util.HibernateUtil;




public class BaseDao<T> {

	public  void create(T object) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	public void createBatch(List objects){
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			
			for(Object object : objects)
				session.save(object);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}
	
	public  void update(T object) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.update(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}
	
	public void update(List<T> objects) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			
			for(Object object : objects)
				session.update(object);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

		
	public  void delete(T object) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.delete(object);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}
	
	public void deleteBatch(List<T> objects){
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			
			for(T t : objects)
				session.delete(t);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

		
	public T find(Class<? extends T> clazz, Serializable id) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			return (T)session.get(clazz, id);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public List<T> list(String sql) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			return session.createQuery(sql).list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}

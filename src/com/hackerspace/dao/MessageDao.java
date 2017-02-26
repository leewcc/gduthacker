package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hackerspace.model.Message;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.User;
import com.hackerspace.util.HibernateUtil;

public class MessageDao {
	public static int selectMyMessCount(User u) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select count(m.id) from Message m where m.user = :user  ";
			Query query = session.createQuery(hql);
				
			query.setParameter("user", u);
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static List<Message> selectMyMess(User u, int star, int count) {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Message m where m.user = :user and m.status <= 2";
			Query query = session.createQuery(hql);
			
			query.setParameter("user", u);
			query.setFirstResult(star);
			query.setMaxResults(count);
			
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static int messageBoxCount(User u) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select count(m.id) from Message m where m.user = :user and m.status > 2 ";
			Query query = session.createQuery(hql);
				
			query.setParameter("user", u);
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static int getUnreadMess(User u) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select count(m.id) from Message m where m.user = :user and m.status = 3 ";
			Query query = session.createQuery(hql);
				
			query.setParameter("user", u);
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static List<Message> messageBox(User u, int star, int count) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Message m where m.user = :user and m.status > 2 order by m.status";
			Query query = session.createQuery(hql);
			
			query.setParameter("user", u);
			query.setFirstResult(star);
			query.setMaxResults(count);
			
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static List<Message> selectMess(int star, int end) {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Message m where m.status < 2 order by m.status";
			Query query = session.createQuery(hql);
			
			query.setFirstResult(star);
			query.setMaxResults(end);
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static int selectCountMess() {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select count(m.id) from Message m where m.status < 3 ";
			Query query = session.createQuery(hql);
						
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}

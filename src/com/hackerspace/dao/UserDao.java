package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hackerspace.model.User;
import com.hackerspace.util.HibernateUtil;

public class UserDao {
	public static int selectCountByRole(int role, int request) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "select COUNT(u.id) as number from User u where u.role = :role and u.request = :request ";
			Query query = session.createQuery(hql);
			
			query.setInteger("role", role);
			query.setInteger("request", request);
			
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static List<User> selectByRole(int role, int request, int star, int end) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "select u from User u where u.role = :role and u.request = :request";
			Query query = session.createQuery(hql);
			
			query.setInteger("role", role);
			query.setInteger("request", request);
			query.setFirstResult(star);
			query.setMaxResults(end);
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static int selectCountByRequest(int request) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "select COUNT(u.id) as number from User u where u.request = :request ";
			Query query = session.createQuery(hql);
			
			query.setInteger("request", request);
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static List<User> selectByRequest(int request, int star, int end) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "select u from User u where u.request = :request ";
			Query query = session.createQuery(hql);
			
			query.setInteger("request", request);
			query.setFirstResult(star);
			query.setMaxResults(end);
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static User login(String card,String password) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "from User u where card = :card and password = :password";
			Query query = session.createQuery(hql);
			
			query.setString("card", card);
			query.setString("password", password);
			List list =  query.list();
			
			if(list.isEmpty())
				return null;
			
			return (User)list.get(0);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static User selectByCard(String card) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "from User u where card = :card ";
			Query query = session.createQuery(hql);
			
			query.setString("card", card);
			List list =  query.list();
			
			if(list.isEmpty())
				return null;
			
			return (User)list.get(0);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public boolean updateUserPicture(User u) {
		Session s=HibernateUtil.sessionFactory.openSession();
		String hql="update User set picture=:picture where id=:id";
		Query q=s.createQuery(hql);
		q.setString("picture", u.getPicture());
		q.setInteger("id", u.getId());
		try {
			if(q.executeUpdate()>=0) return true;
			else return false; 
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		} finally {
			s.close();
		}
	}
	
}

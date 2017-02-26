package com.hackerspace.dao;

import java.util.List;

import javax.transaction.SystemException;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.Team;
import com.hackerspace.model.User;
import com.hackerspace.util.HibernateUtil;

public class TeamDao {
	public static Team getTeam(int id) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "from Team t where t.id = :id";
			Query query = session.createQuery(hql);
			
			query.setInteger("id", id);

			List list =  query.list();
			if(list.isEmpty())
				return null;
			
			return (Team)list.get(0);
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static List<Team> selectTeam(int star, int count, int status) throws Exception {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "from Team t where t.status = :status";
			Query query = session.createQuery(hql);
			
			query.setInteger("status", status);
			query.setFirstResult(star);
			query.setMaxResults(count);
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static int selectCount(int status) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "select count(t.id) from Team t where t.status = :status";
			Query query = session.createQuery(hql);
			
			query.setInteger("status", status);
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public boolean updateTeamPicture(Team t) {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			String hql="update Team set picture=:picture where id=:id";
			Query q=session.createQuery(hql);
			q.setString("picture", t.getPicture());
			q.setInteger("id", t.getId());
			q.executeUpdate();
		} catch  (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
	
	public static boolean deleteTeam(Team team) {
		Session session = HibernateUtil.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		try{
			String hql = "delete from TeamUser where team = :team";
			Query query = session.createQuery(hql);
			query.setParameter("team", team);
			query.executeUpdate();
			
			hql = "delete from Team where id=:id";
			query = session.createQuery(hql);
			query.setParameter("id", team.getId());
			query.executeUpdate();
			transaction.commit();
		}catch(Exception e) {
			try {
				transaction.rollback();
			} catch (IllegalStateException e1) {
				
			}
			return false;
			
		}finally {
			session.close();
		}
		
		return true;
	}
}

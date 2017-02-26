package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.hackerspace.model.Team;
import com.hackerspace.model.TeamUser;
import com.hackerspace.model.User;
import com.hackerspace.util.HibernateUtil;

public class TeamUserDao {
	public static List<TeamUser>  selectByTeam(Team t, int star, int count, boolean isMember) throws Exception {
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "from TeamUser tu where tu.team = :team ";
			if(isMember)
				hql += "and tu.status> 2";
			else
				hql += "and tu.status < 3";
					
			Query query = s.createQuery(hql);
			
			query.setParameter("team", t);
			query.setFirstResult(star);
			query.setMaxResults(count);
			
			return query.list();
		}finally{
			s.close();
		}
	}
	
	public static int selectCount(Team t, boolean isMember) throws Exception{
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "select count(tu.id) from TeamUser tu where tu.team = :team ";
			if(isMember)
				hql += "and tu.status> 2";
			else
				hql += "and tu.status < 3";
					
			Query query = s.createQuery(hql);
			query.setParameter("team", t);
			return ((Number)query.iterate().next()).intValue();

		}finally{
			s.close();
		}
	}
	
	public static List<TeamUser> selectMyTeam(User u) {
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "select tu.team from TeamUser tu where tu.user = :user ";
	
			Query query = s.createQuery(hql);
			
			query.setParameter("user", u);

			return query.list();
		}finally{
			s.close();
		}
	}
	
	public static TeamUser getStatus(User u, Team t) throws Exception{
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "from TeamUser tu where tu.user = :user and tu.team = :team ";
	
			Query query = s.createQuery(hql);
			
			query.setParameter("user", u);
			query.setParameter("team", t);

			List list =  query.list();
			if(!list.isEmpty())
				return (TeamUser)list.get(0);
			
			return null;
		}finally{
			s.close();
		}
	}
	
	public static TeamUser getManager(Team t) throws Exception{
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "from TeamUser tu where tu.status = 3 and tu.team = :team ";
	
			Query query = s.createQuery(hql);
			
			query.setParameter("team", t);

			List list =  query.list();
			if(!list.isEmpty())
				return (TeamUser)list.get(0);
			
			return null;
		}finally{
			s.close();
		}
	}
	
	public static int getTeamMemberNumber(Team t) throws Exception{
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "select count(tu.id) from TeamUser tu where tu.status in (3, 4) and tu.team = :team ";
	
			Query query = s.createQuery(hql);
			
			query.setParameter("team", t);

			List list =  query.list();
			
			return ((Number)query.iterate().next()).intValue();
			
		}finally{
			s.close();
		}
	}
}

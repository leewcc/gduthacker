package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hackerspace.model.Team;
import com.hackerspace.model.TeamNews;
import com.hackerspace.util.HibernateUtil;

public class TeamNewsDao {
	public static List<TeamNews> selectNews(Team t, int star, int count, int status) throws Exception{
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "from TeamNews tn where tn.team = :team and tn.status = :status";
			Query query = s.createQuery(hql);
			
			query.setParameter("team", t);
			query.setParameter("status", status);
			query.setFirstResult(star);
			query.setMaxResults(count);
			
			return query.list();
		}finally{
			s.close();
		}
	}
	
	public static int selectCount(Team t, int status)throws Exception {
		Session s = HibernateUtil.sessionFactory.openSession();
		try{
			String hql = "select count(tn.id) from TeamNews tn where tn.team = :team and tn.status = :status";
			Query query = s.createQuery(hql);
			
			query.setParameter("team", t);
			query.setParameter("status", status);
				
			return ((Number)query.iterate().next()).intValue();
		}finally{
			s.close();
		}
	}
}

package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hackerspace.model.Security;
import com.hackerspace.model.User;
import com.hackerspace.util.HibernateUtil;

public class SecurityDao {
	public static List<Security> getMySecurity(User u) throws Exception{
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "from Security s where s.user = :user";
			Query query = session.createQuery(hql);
			
			query.setParameter("user", u);
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}

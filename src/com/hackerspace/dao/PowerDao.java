package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hackerspace.model.Power;
import com.hackerspace.model.User;
import com.hackerspace.util.HibernateUtil;

public class PowerDao {
	public static Power getMyPower(User u) {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Power p where p.user = :user ";
			Query query = session.createQuery(hql);
			
			query.setParameter("user", u);
			
			List<Power> list  = query.list();
			if(list.isEmpty())
				return null;
			
			return list.get(0);
			
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}

package com.hackerspace.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hackerspace.model.Question;
import com.hackerspace.util.HibernateUtil;

public class QuestionDao {
	public static List<Question> selectQuestion(int star, int count) {
		Session session = HibernateUtil.sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "from Question q order by q.date";
			Query query = session.createQuery(hql);
			
			query.setFirstResult(star);
			query.setMaxResults(count);
			return query.list();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
	
	public static int selectCount() {
		Session session = HibernateUtil.sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select count(q.id) from Question q ";
			Query query = session.createQuery(hql);
						
			return ((Number)query.iterate().next()).intValue();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}
}

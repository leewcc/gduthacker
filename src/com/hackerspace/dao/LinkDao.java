package com.hackerspace.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.Link;
import com.hackerspace.util.HibernateUtil;

public class LinkDao {

	public List<Link> getLists() {
		Session s=HibernateUtil.sessionFactory.openSession();
		Query q=s.createQuery("from Link");
		List<Link> l=(ArrayList<Link>) q.list();
		return l;
	}
	
	public boolean updateLists(List<Link> l) {
		if(l==null) return true;
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		t.begin();
		for(Link ll:l) {
			s.update(ll);
		}
		t.commit();
		s.close();
		return  true;
		
	}
	
	public boolean saveLists(List<Link> l) {
		if(l==null) return true;
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		t.begin();
		for(Link ll:l) {
			s.save(ll);
		}
		t.commit();
		s.close();
		return  true;
		
	}
	
	public boolean deleteLists(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Query q=s.createQuery("delete from Link where id=:id");
		q.setInteger("id", id);
		q.executeUpdate();
		s.close();
		return  true;
	}
}

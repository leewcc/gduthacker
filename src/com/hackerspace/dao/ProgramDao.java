package com.hackerspace.dao;

import java.util.ArrayList;

import javax.persistence.QueryTimeoutException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.HaskerSystem;
import com.hackerspace.model.Program;
import com.hackerspace.util.HibernateUtil;

public class ProgramDao {

	/**
	 * 获得菜单
	 * status 0 一级；其他数字 二级	
	 */
	public ArrayList<Program> getProgram(int status) {
		Session s =HibernateUtil.sessionFactory.openSession();
		String hql="from Program where status=:status and rank!=-1 order by num";
		Query q=s.createQuery(hql);
		q.setInteger("status", status);
		ArrayList<Program> al=(ArrayList<Program>)q.list();
		s.close();
		return al;
	}
	/**
	 * 获得管理的菜单
	 * @param id
	 * @return
	 */
	public ArrayList<Program> getManagerProgram(Integer id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		String hql="from Program where status=:status order by num" ;
		Query q=s.createQuery(hql);
		q.setInteger("status", id);
		ArrayList<Program> al=(ArrayList<Program>)q.list();
		s.close();
		return al;
	}

	/**
	 * 更新栏目名
	 * @param p
	 * @return
	 */
	public boolean saveProgram(Program p) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		String hql="update Program set name=:name ,status=:status where id=:id";
		Query query=s.createQuery(hql);
		query.setString("name",p.getName());
		query.setInteger("status",p.getStatus());
		query.setInteger("id", p.getId());
		query.executeUpdate();
		t.commit();
		s.close();
		return true;
	}

	/**
	 * 获得系统名
	 * @return
	 */
	public HaskerSystem getSystemName() {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Query q=s.createQuery("from HaskerSystem");
		HaskerSystem hs=null;
		if(q.list().size()>0){
			hs=(HaskerSystem)q.list().get(0);
		} else {
			hs=new HaskerSystem();
			hs.setName("没有系统名");
			hs.setId(1);
		}
		t.commit();
		s.close();
		return hs;
	}
	
	/**
	 * 获得系统名
	 * @param hs
	 * @return
	 */
	public boolean updateSystemName(HaskerSystem hs) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.saveOrUpdate(hs);
		t.commit();
		s.close();
		return true;
	}

	/**
	 * 调整菜单顺序
	 * @param ihid
	 * @param ifid
	 * @return
	 */
	public boolean moveProgram(int ihid, int ifid) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		String string="update Program set num=num+1 where id=:id";
		Query q=s.createQuery(string);
		q.setInteger("id", ihid);
		q.executeUpdate();
		string="update Program set num=num-1 where id=:id";
		q=s.createQuery(string);
		q.setInteger("id", ifid);
		q.executeUpdate();
		t.commit();
		s.close();
		return true;
	}
	/**
	 * 设置可见不可见
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean operationProgram(int id,int status) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		String string ="update Program set rank=:status where id=:id";
		Query query=s.createQuery(string);
		query.setInteger("status",status);
		query.setInteger("id", id);
		int flag=query.executeUpdate();
		t.commit();
		s.close();
		return flag==1? true:false;
	}

	

}

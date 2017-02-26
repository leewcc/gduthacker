package com.hackerspace.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hackerspace.model.News;
import com.hackerspace.model.PageElem;
import com.hackerspace.util.HibernateUtil;

public class NewsDao {

	/**
	 * 存储新闻
	 * @param news
	 * @return
	 */
	public boolean saveNews(News news) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=null;
		try {
			t=s.beginTransaction();
			s.save(news);
			t.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			s.close();
		}
		return false;
	}
	/**
	 * 返回新闻
	 * @param status
	 * @param tag
	 * @return
	 */
	public boolean findNews(byte status,byte tag,PageElem<News> pe) {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			String hql="select count(*) from News where status=:status and tag=:tag";
			Query q=s.createQuery(hql);
			q.setByte("status", status);
			q.setByte("tag",tag);
			Long totalNum=(Long)q.list().get(0);
			pe.setRows(totalNum.intValue());
			
			hql="select new News(n.id,n.title,n.time,n.isTop,n.status,u.name) "
					+ "from News as n,User as u "
					+ "where n.status=:status and n.tag=:tag and u.id=n.authorId order by n.isTop desc, n.id desc";
			q=s.createQuery(hql);
			q.setByte("status", status);
			q.setByte("tag",tag);
			q.setMaxResults(pe.getPageShow());
			q.setFirstResult(pe.getStartSearch());
			List<News> list=q.list();
			pe.setPageElem(list);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
	}
	/**
	 * 返回草稿箱
	 * @param status
	 * @return
	 */
	public boolean findDrafts(byte status,PageElem<News> pe) {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			String hql="select count(*) from News where status=:status";
			Query q=s.createQuery(hql);
			q.setByte("status", status);
			Long totalNum=(Long)q.list().get(0);
			pe.setRows(totalNum.intValue());

			hql="select new News(n.id,n.title,n.time,n.isTop,n.status,u.name) "
					+ "from News as n,User as u "
					+ "where n.status=:status and u.id=n.authorId order by n.id desc";
			q=s.createQuery(hql);
			q.setByte("status", status);//草稿箱
			q.setMaxResults(pe.getPageShow());
			q.setFirstResult(pe.getStartSearch());
			ArrayList<News> list=(ArrayList<News>) q.list();
			pe.setPageElem(list);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
	}
	/**
	 * 删除新闻
	 * @param id
	 * @return
	 */
	public boolean deletePublishedNews(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		News n=(News) s.load(News.class,id);
		s.delete(n);
		t.commit();
		s.close();
		return true;
	}

	
	/**
	 * 置顶新闻
	 * @param id
	 * @param isTop
	 * @return
	 */
	public boolean takeToTop(int id,byte isTop) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Query q=s.createQuery("update News set isTop=:isTop where id=:id");
		q.setInteger("id", id);
		q.setByte("isTop", isTop);
		q.executeUpdate();
		s.close();
		return true;
	}
	/**
	 *更新新闻
	 * @param news
	 * @return
	 */
	public boolean updateNews(News news) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.update(news);
		t.commit();
		s.close();
		return true;
	}
	/**
	 * 获得一篇新闻
	 * @param id
	 * @return
	 */
	public News getOneArticle(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		try {
			String hql="update News set clickNum=clickNum+1 where id=:id";
			Query q=s.createQuery(hql);
			q.setInteger("id", id);
			q.executeUpdate();
			hql="select new News(n.id,n.title,n.content,n.time,n.clickNum,n.isTop,n.tag,u.name) "
					+ "from News as n,User as u "
					+ "where n.id=:id and u.id=n.authorId";
			q=s.createQuery(hql);
			q.setInteger("id", id);
			ArrayList<News> list=(ArrayList<News>) q.list();
			t.commit();
			return list.get(0);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			s.close();
		}
	}
	/**
	 * 显示置顶新闻
	 * @return
	 */
	public ArrayList<News> findTopNews() {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			Transaction t=s.beginTransaction();
			String hql="from News where isTop=1";
			Query q=s.createQuery(hql);
			t.commit();
			return (ArrayList<News>) q.list();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
		return null;
	}
	
	
}

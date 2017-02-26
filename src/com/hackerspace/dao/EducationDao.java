package com.hackerspace.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;

import com.hackerspace.exception.DaoException;
import com.hackerspace.model.Education;
import com.hackerspace.model.Education;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Project;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;

public class EducationDao {
	
	private static Logger log;
	
	public EducationDao() {}
	public EducationDao(Logger log) {
		EducationDao.log = log;
	}
	
	public boolean queryEducations(PageElem<Education> pageElem, byte tag, byte status) {
		// tag: 0 讲座 ; 1 实践实训
		Log.info(log, "queryEducations(pageElem, tag, byte)方法进入");
		
		Session s=HibernateUtil.sessionFactory.openSession();
		
		try {
			String hql = "select new Education(e.id, e.title, e.time, u.name) "
					+ "from Education as e, User as u "
					+ "where e.tag=:tag and e.status=:status and e.authorId = u.id";
			
			Query query =  s.createQuery(hql);
			query.setByte("tag", tag);
			query.setByte("status", status);
//			query.setFirstResult(pageElem.getStartSearch());
//			query.setMaxResults(pageElem.getPageShow());  
	        List<Education> list = query.list();  
	        
	        pageElem.setPageElem(list);
	        pageElem.setRows(list.size());
	        
	        Log.error(log, "queryEducations(pageElem, tag, status)查询成功; rows={}", pageElem.getRows());
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(log, e, "queryEducations(pageElem, tag, status)查询失败");
		} finally {
			s.close();
		}
		
		
		return false;
	}
	
	public Education queryEducation(int id) throws DaoException {
		BaseTDao<Education> bTDao = new BaseTDao<Education>();
			
		return bTDao.find(Education.class, id);
		
	}
	
	
	public int updateEduSta(int id, byte status) {
		Log.info(log, "updateEduSta()进入");
		Session s = HibernateUtil.sessionFactory.openSession();
		
		try {
		Query query = s.createQuery("UPDATE Education set status=:status WHERE id=:id");
		
		query.setByte("status", status);
		query.setInteger("id", id);
		
		Log.info(log, "updateEduSta(id, status)操作成功");
		
		return query.executeUpdate();
		} catch (Exception e) {
			Log.error(log, e, "updateEduSta(id, status)失败");
			return -1;
		} finally {
			s.close();
		}
	}
	
	
	public boolean deleteEducation(int id) {
		Log.info(log, "deleteEducation(id)进入");
		Session s = HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		try {
			
			String hql = "DELETE FROM Education WHERE id=:id";
			
			Query query  = s.createQuery(hql);
			
			query.setInteger("id", id);
			
			query.executeUpdate();
			
			t.commit();
			Log.info(log, "deleteEducation(id)操作成功");
		
			return true;
		} catch (Exception e) {
			Log.error(log, e, "deleteEducation(id)失败");
			return false;
		} finally {
			
			s.close();
		}
	}
	
	/**
	 * @author CHEN 
	 */
	
	/**
	 * 存储合作
	 * @param cooperation
	 * @return
	 */
	public boolean saveEducation(Education education) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=null;
		try {
			t=s.beginTransaction();
			s.save(education);
			t.commit();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		} finally {
			s.close();
		}
	}
	/**
	 * 返回合作
	 * @param status
	 * @param tag
	 * @return
	 */
	public static boolean findEducation(byte status,byte tag,PageElem<Education> pe) {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			String hql="select count(*) from Education where status=:status and tag=:tag";
			Query q=s.createQuery(hql);
			q.setByte("status", status);
			q.setByte("tag",tag);
			Long totalNum=(Long)q.list().get(0);
			pe.setRows(totalNum.intValue());
			
			hql="select new Education(e.id,e.title,e.time,e.status,e.tag,u.name) "
					+ "from Education as e ,User as u "
					+ "where e.status=:status and e.tag=:tag and u.id=e.authorId order by e.id desc";
			q=s.createQuery(hql);
			q.setByte("status", status);
			q.setByte("tag",tag);
			q.setMaxResults(pe.getPageShow());
			q.setFirstResult(pe.getStartSearch());
			List<Education> list=(ArrayList<Education>)q.list();
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
	public boolean findDrafts(byte status,PageElem<Education> pe) {
		Session s=HibernateUtil.sessionFactory.openSession();
		try {
			String hql="select count(*) from Education where status=:status";
			Query q=s.createQuery(hql);
			q.setByte("status", status);
			Long totalNum=(Long)q.list().get(0);
			pe.setRows(totalNum.intValue());

			hql="select new Education(e.id,e.title,e.time,e.status,e.tag,u.name) "
					+ "from Education as e ,User as u "
					+ "where e.status=:status and u.id=e.authorId order by e.id desc";
			q=s.createQuery(hql);
			q.setByte("status", status);//草稿箱
			q.setMaxResults(pe.getPageShow());
			q.setFirstResult(pe.getStartSearch());
			ArrayList<Education> list=(ArrayList<Education>) q.list();
			pe.setPageElem(list);
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			s.close();
		}
	}
	public boolean deletePublishedEducation(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		Education n=(Education) s.load(Education.class,id);
		s.delete(n);
		t.commit();
		s.close();
		return true;
	}

	/**
	 * 更新教育内容
	 * @param education
	 * @return
	 */
	public boolean updateEducation(Education education) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		s.update(education);
		t.commit();
		s.close();
		return true;
	}
	/**
	 * 获得教育内容
	 * @param id
	 * @return
	 */
	public Education getOneArticle(int id) {
		Session s=HibernateUtil.sessionFactory.openSession();
		Transaction t=s.beginTransaction();
		try {
			String hql="select new Education(n.id,n.title,n.content,n.time,n.status,n.tag,u.name) "
					+ "from Education as n,User as u "
					+ "where n.id=:id and u.id=n.authorId";

			Query q=s.createQuery(hql);
			q.setInteger("id", id);
			ArrayList<Education> list=(ArrayList<Education>) q.list();
			t.commit();
			return list.get(0);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			s.close();
		}
	}
}

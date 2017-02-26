package com.hackerspace.dao;

/**
 * author: tianxin 
 */
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import com.hackerspace.exception.DaoException;
import com.hackerspace.model.PageElem;
import com.hackerspace.model.Project;
import com.hackerspace.model.Team;
import com.hackerspace.util.HibernateUtil;
import com.hackerspace.util.Log;


public class ProjectDao {
	
	private static Logger log1 = Log.get(ProjectDao.class);
	
	public ProjectDao() {}
	
	public ProjectDao(Logger logger) {
		ProjectDao.log1 = logger;
	}
	
	/**
	 * query all projects by status & tag
	 * @param pageElem
	 * @param status: 0未发布;1已发布
	 * @param tag
	 * @throws DaoException 
	 */
	public void queryProject(PageElem<Project> pageElem, byte status, byte tag) throws DaoException {
		Session s=HibernateUtil.sessionFactory.openSession();
		Query query = null;
		try {
			String hql = "select new Project(p.id, p.title, p.time, p.tag, t.name) "
					+ "from Project as p, Team as t "
					+ "where  p.teamId = t.id and p.status=:status and p.tag=:tag "
					+ " ORDER BY p.time DESC";
			
			s.beginTransaction();
			query =  s.createQuery(hql);
			query.setByte("status", status);
			query.setByte("tag", tag);
			query.setFirstResult(pageElem.getStartSearch());
			query.setMaxResults(pageElem.getPageShow());  
			query.getQueryString();
	        @SuppressWarnings("unchecked")
			List<Project> list = query.list();  
	        
	        s.getTransaction().commit();
	        pageElem.setRows(list.size());
	        pageElem.setPageElem(list);
		} catch (HibernateException e) {
			Log.error(log1,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ProgramDao", query.getQueryString());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log1, e, "query: " + e.getMessage());
			throw new DaoException(e);
		} finally {
			s.close();
		}
	        
		
	}
	
	/**
	 * 查询全部工程
	 * @param pageElem
	 * @param tag
	 * @throws DaoException 
	 */
	public void queryProjectAll(PageElem<Project> pageElem, byte tag) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		try {
			String hql = "select new Project(p.id, p.title, p.time,  t.name) "
			+ "from Project as p, Team as t "
			+ "where  p.teamId = t.id and p.status=:status and p.tag=:tag";
			
			session.beginTransaction();
			
			query =  session.createQuery(hql);
			query.setByte("tag", tag);
			query.setByte("status", (byte)1);
			query.setFirstResult(pageElem.getStartSearch());
			query.setMaxResults(pageElem.getPageShow()); 
	        List<Project> list = query.list();  
	        
	        session.getTransaction().commit();
	        
	        pageElem.setRows(list.size());
	        pageElem.setPageElem(list);
	        
		} catch (HibernateException e) {
			Log.error(log1,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ProgramDao", query.getQueryString());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log1, e, "query: " + e.getMessage());
			throw new DaoException(e);
		} finally {
			session.close();
		}
	}
	
	/**
	 * 更新项目的状态
	 * @param id
	 * @param status 0:未发布; 1:已发布
	 * @return
	 * @throws DaoException 
	 */
	public int updateProjectSta(int id, byte status) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		Query query = null;
		int rows = 0;
		try {
			String hql = "UPDATE Project SET status =:status WHERE id = :id";
			
			session.beginTransaction();
			
			query =  session.createQuery(hql);
			query.setInteger("id", id);
			query.setByte("status", status);
			rows = query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log1,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ProgramDao", query.getQueryString());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log1, e, "query: " + e.getMessage());
			throw new DaoException(e);
		} finally {
			session.close();
		}

		return rows;
	}
	
	
	public List<Team> queryTeams() throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		List<Team> teams = null;
		Query query = null;
		try {
			String hql = "select new Team(t.id, t.name) "
					+ "from Team as t where t.status = 1";
			
			session.beginTransaction();
			
			query =  session.createQuery(hql);
			
			teams = query.list();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log1,  "SQL exception[msg={}, dao={}, query={}]: ",
						e.getMessage(), "ProgramDao", query.getQueryString());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log1, e, "query: " + e.getMessage());
			throw new DaoException(e);
		} finally {
			session.close();
		}
		
		return teams;
		
		
	}
	
	public int deleteProject(int id) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		int rows = 0;
		Query query = null;
		try {
			String hql = "DELETE Project WHERE id = :id";
			
			session.beginTransaction();
			
			query = session.createQuery(hql);
			
			query.setInteger("id", id);
			
			rows = query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log1,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ProgramDao", query.getQueryString());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log1, e, "query: " + e.getMessage());
			throw new DaoException(e);
		} finally {
			session.close();
		}

		
		return rows;
	}

	
	public Project queryOne(int id) throws DaoException {
		Session session = HibernateUtil.sessionFactory.openSession();
		List<Project> list = null;
		Query query = null;
		try {
			String hql = "SELECT new Project(p.title, p.time, p.content, t.id, t.name) "
					+ " FROM Project as p, Team as t "
					+ " WHERE p.id = :id and p.teamId=t.id ";
			
			session.beginTransaction();
			
			query = session.createQuery(hql);
			
			query.setInteger("id", id);
			
			list = query.list();
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			Log.error(log1,  "SQL exception[msg={}, dao={}, query={}]: ",
					e.getMessage(), "ProgramDao", query.getQueryString());
			throw new DaoException(e);
		} catch (Exception e) {
			Log.error(log1, e, "query: " + e.getMessage());
			throw new DaoException(e);
		} finally {
			session.close();
		}

		
		return list.size() > 0 ? list.get(0) : null;
	}
	
	
	
}

package com.hackerspace.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;

public class HibernateUtil {
	public static  SessionFactory sessionFactory;
	
	private static Logger log = Log.get(HibernateUtil.class);
	
	/**
	 * 创建configuration对象
	 */
	static{
		try {
			Configuration configuration = new Configuration();
			//configuration.configure();
			/**
			 * 加载hibernate的配置文件
			 */
			configuration.configure("hibernate.cfg.xml");
			
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable e) {
			Log.error(log, e, "hibernateUtil exception");
		}
	}
}

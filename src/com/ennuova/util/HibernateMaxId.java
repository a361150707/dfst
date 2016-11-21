package com.ennuova.util;


import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("hibernateMaxId")
@Transactional
public class HibernateMaxId {

	private static SessionFactory sessionFactory;
	
	public static Long findMaxIdByTablename(String tablename) {
		
		System.out.println(tablename);
		
		Long maxId=1L;
		Long maxId2 = (Long)sessionFactory.getCurrentSession().createQuery(//
				"select max(id) from "+tablename).uniqueResult();
		if(maxId2!=null){
			maxId=maxId2+1;
		}
		return maxId;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("static-access")
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

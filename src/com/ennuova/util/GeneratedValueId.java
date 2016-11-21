package com.ennuova.util;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

public class GeneratedValueId implements IdentifierGenerator, Configurable{ 
	
	
	@Resource
	HibernateMaxId hibernateMaxId;
	
	@SuppressWarnings("static-access")
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
		
		String tablename=arg1.getClass().getSimpleName();
		return hibernateMaxId.findMaxIdByTablename(tablename);
	}

	public void configure(Type arg0, Properties arg1, Dialect arg2)
			throws MappingException {

	}  
  
  
}  

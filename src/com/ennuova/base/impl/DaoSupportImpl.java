package com.ennuova.base.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.RescueItem;
import com.ennuova.entity.TestInsert;
import com.ennuova.util.MyBeanUtils;
import com.ennuova.util.PageBean;
import com.ennuova.util.QueryHelper;

@Repository("daoSupport")
@Transactional
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {
	
	protected HibernateTemplate hibernateTemplate;
	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;
	protected Class<T> clazz;
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public DaoSupportImpl(){
		//适用反射技术得到T的真实类型
		//获取当前new对象的泛型父类类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获取第一个类型参数的真实类型
		this.clazz=(Class<T>) pt.getActualTypeArguments()[0];
	    //System.out.println("getclass-->"+clazz);
	}

	@Override
	public T loadById(Long id) {
		if (id == null) {
			return null;
		} else {
			return hibernateTemplate.load(clazz, id);
		}
	}
	
	@Override
	public RescueItem loadByIdi(Long id) {
		if (id == null) {
			return null;
		} else {
			return hibernateTemplate.load(RescueItem.class, id);
		}
	}
	
	@Override
	public T save(T entity) {
		hibernateTemplate.save(entity);
		return entity;
	}
	public Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
        return sessionFactory.getCurrentSession();
    }
	@Override
	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			hibernateTemplate.delete(obj);
		}
	}

	@Override
	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public T getById(Long id) {
		if (id == null) {
			return null;
		} else {
			return hibernateTemplate.get(clazz, id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return hibernateTemplate.find("from " + clazz.getSimpleName()+" order by id");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Long getMaxId(String tableName, String keyName){
		StringBuffer sb = new StringBuffer("");
		String keyValue = "";
		if(keyName != null && !keyName.isEmpty() && tableName != null && !tableName.isEmpty()){
			sb.append("select max(").append(keyName).append(") ");
			sb.append("from ").append(tableName);
			
			List list = new ArrayList();
			list = sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).list();
			if(list != null && list.size() > 0 && list.get(0) != null){
				keyValue = list.get(0).toString();
				return Long.parseLong(keyValue) + 1L;
			} else{
				return 1L;
			}
		} else{
			return 1L;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null||ids.length==0) {
			return Collections.EMPTY_LIST;
		} else {
			StringBuffer sb = new StringBuffer("");
			for(int i=0;i<ids.length;i++){
				if(i!=ids.length-1){
				sb.append(ids[i]).append(",");
				} else{
					sb.append(ids[i]);
				}
			}
			
			return hibernateTemplate.find("from " + clazz.getSimpleName() + 
					" where id in ("+sb.toString()+")");
		}
	}
	
    //分页最终版
	@Override
	@SuppressWarnings("rawtypes")
	public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
//		System.out.println("DaoSupportImpl.getPageBean()");
		List<Object> para = queryHelper.getParameters();
		
		//查询列表
		Query query = sessionFactory.getCurrentSession().createQuery(queryHelper.getListQueryHql());//创建查询对象
		
		if (para != null) {//设置参数
			for (int i = 0; i < para.size(); i++) {
				query.setParameter(i, para.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize);	//设置取值的开始位置
		query.setMaxResults(pageSize);	//设置读取数据的记录条数
		List list = query.list();	//执行查询
		
		//查询数量
		Query querycount = sessionFactory.getCurrentSession().createQuery(queryHelper.getCountQueryHql());//创建查询对象
		if (para != null) {//设置参数
			for (int i = 0; i < para.size(); i++) {
				querycount.setParameter(i, para.get(i));
			}
		}
		Long count = (Long) querycount.uniqueResult();//执行查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}
	
	public <X> List<X> findHql(final String hql) {
        return createQuery(hql).list();
    }
    
    public <X> List<X> findHql(final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }
    
    /**
     * 通过自定义的sql来执行语句
     * @author sududa
     * @date 2016-05-28
     * @param sql
     * @param param
     * @return
     */
    public Integer executeSql(final String sql,final Object... param){
    	return this.jdbcTemplate.update(sql, param);
    }
    
    public List<T> findListbySql(final String sql) {
		Query querys = getSession().createSQLQuery(sql);
		return querys.list();
	}
    
    /**
     * 通过自定义的查询sql查询单条记录
     * @author sududa
     * @date 2016-05-30
     * @param sql
     * @param objs
     * @return
     */
    public Map<String, Object> findOneForJdbc(final String sql, final Object... objs) {
		try {
			return this.jdbcTemplate.queryForMap(sql, objs);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
    
    /**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * @author sududa
	 * @date 2016-05-31
	 */
    public List<Map<String, Object>> findForJdbc(final String sql,final Object... objs) {
    	return this.jdbcTemplate.queryForList(sql, objs);
	}
    
    public <X> List<X> findHql(final String hql, final Map<String, Object> values) {
        return createQuery(hql, values).list();
    }
    public <X> List<X> findSql1(final String sql) {
        return createSQLQuery1(sql).list();
    }
    public <X> List<X> findSql(final String sql,Class clazz) {
        return createSQLQuery(sql).addEntity(clazz).list();
    }
    public <X> List<X> findSql(final String sql,Class clazz, final Object... values) {
        return createSQLQuery(sql, values).addEntity(clazz).list();
    }
    
    public <X> List<X> findSql(final String sql,Class clazz, final Map<String, Object> values) {
        return createSQLQuery(sql, values).addEntity(clazz).list();
    }
    public <X> X findUnique(final String hql, final Object... values) {
    	Query query = createQuery(hql, values);
    	List<X> list = query.list();
    	if(list != null && list.size() > 0)
    	{
    		return list.get(0);
    	}else
    	{
    		return null;
    	}
    }

    public <X> X findUnique(final String hql, final Map<String, Object> values) {
    	Query query = createQuery(hql, values);
    	List<X> list = query.list();
    	if(list != null && list.size() > 0)
    	{
    		return list.get(0);
    	}else
    	{
    		return null;
    	}
    }
    
    public Query createQuery(final String hql, final Object... values) {
        Assert.hasText(hql, "queryString不能为空");
        Query query = getSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    public Query createQuery(final String hql, final Map<String, Object> values) {
        Assert.hasText(hql, "queryString不能为空");
        Query query = getSession().createQuery(hql);
        if (values != null) {
            query.setProperties(values);
        }
        return query;
    }
    
    public SQLQuery createSQLQuery(final String sql, final Object... values) {
        Assert.hasText(sql, "queryString不能为空");
        SQLQuery query = getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }
    public SQLQuery createSQLQuery1(final String sql, final Object... values) {
        Assert.hasText(sql, "queryString不能为空");
        SQLQuery query = getSession().createSQLQuery(sql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        return query;
    }
    public SQLQuery createSQLQuery(final String sql, final Map<String, Object> values) {
        Assert.hasText(sql, "queryString不能为空");
        SQLQuery query = getSession().createSQLQuery(sql);
        if (values != null) {
            query.setProperties(values);
        }
        return query;
    }
    
    /**
	 * jdbc sql分页查询返回实体对象
	 *
	 */
	public <E> List<E> findObjForJdbc(String sql, int page, int rows,
			Class<E> clazz,Object ...args) {
		List<E> rsList = new ArrayList<E>();
		// 封装分页SQL
		//sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
		String oracleSql = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}"; //oracle
		sql = createPageSql(oracleSql, sql, page, rows);
		List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql,args);

		E po = null;
		for (Map<String, Object> m : mapList) {
			try {
				po = clazz.newInstance();
				MyBeanUtils.copyMap2Bean(po, m);
				rsList.add(po);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rsList;
	}
	
	 /**
		 * jdbc sql分页查询返回实体对象
		 *
		 */
		public <E> List<E> findObj2ForJdbc(String sql, 
				Class<E> clazz,Object ...args) {
			List<E> rsList = new ArrayList<E>();
			// 封装分页SQL
			//sql = JdbcDao.jeecgCreatePageSql(sql, page, rows);
			List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql,args);
			E po = null;
			for (Map<String, Object> m : mapList) {
				try {
					po = clazz.newInstance();
					MyBeanUtils.copyMap2Bean(po, m);
					rsList.add(po);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return rsList;
		}
		
	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		String oracleSql = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}"; //oracle
		// 封装分页SQL
		sql = createPageSql(oracleSql, sql, page, rows);
		return this.jdbcTemplate.queryForList(sql, objs);
	}

	
	/**
	 * jdbc查询单条记录
	 * @param sql
	 * @param objs
	 * @return
	 */
	public Map<String, Object> findOneForJdbc2(String sql, Object... objs) {
		try {
			return this.jdbcTemplate.queryForMap(sql, objs);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	/**
	 * jdbc查询单条记录返回对象
	 * @param sql
	 * @param objs
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <E> E findOneForJdbc(String sql,Class<E> clazz,Object... objs) {
		Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, objs);
		E po = null;
		try {
			po = clazz.newInstance();
			MyBeanUtils.copyMap2Bean(po, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return  po;
	}
	
	/**
	 * 按照数据库类型，封装SQL
	 */
	private static String createPageSql(String oracleSql, String sql, int page, int rows){
		int beginNum = (page - 1) * rows;
		String[] sqlParam = new String[3];
		sqlParam[0] = sql;
		sqlParam[1] = beginNum+"";
		sqlParam[2] = rows+"";
		int beginIndex = (page-1)*rows;
		int endIndex = beginIndex+rows;
		sqlParam[2] = Integer.toString(beginIndex);
		sqlParam[1] = Integer.toString(endIndex);
		sql = MessageFormat.format(oracleSql, sqlParam);
		return sql;
	}
    
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * 使用指定的检索标准检索数据并分页返回数据-采用预处理方式
	 * @author sududa
	 * @date 2016年7月8日
	 * @param sql 自定义sql语句
	 * @param page 当前页
	 * @param rows 每页显示条数
	 * @param objs 参数
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findForJdbcPage(String sql, int page, int rows, Object... objs) {
		if(page<=0 && rows <= 0){
    		return this.jdbcTemplate.queryForList(sql, objs);
    	}
    	// 封装分页SQL
    	String oracleSql = "select * from (select row_.*,rownum rownum_ from ({0}) row_ where rownum <= {1}) where rownum_>{2}"; //oracle
		sql = createPageSql(oracleSql, sql, page, rows);
		return this.jdbcTemplate.queryForList(sql, objs);
	}
	
	/**
	 * hibernate的批量插入
	 * @author sududa
	 * @date 2016年11月18日
	 * @param carloList 实体集合
	 * @param rows 一次要以几条数据插入,如果插入条数大于实体，则全部插入
	 * @return void
	 */
	public void saveWithBatch(List<T> entityList,final Integer rows){
		for (int i = 0; i < entityList.size(); i++) {
			getSession().save(entityList.get(i));
			if (i % rows == 0) {
				// rows个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于rows小于2*rows的不保存
		getSession().flush();
		getSession().clear();
	}
	
	
	
}
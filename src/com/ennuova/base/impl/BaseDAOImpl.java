package com.ennuova.base.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.util.Assert;

import com.ennuova.base.BaseDAO;
import com.ennuova.util.PageBean;


/**
 * Dao实现类 - Dao实现类基类
 * author:王志明
 */
public class BaseDAOImpl<T> extends
		DaoSupportImpl<T> implements BaseDAO<T> {


	public T get(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		Assert.notNull(clazz,
				"get(String propertyName, Object value):entityClass is NULL");
		String hql = "from " + clazz.getName() + " as model where model."
				+ propertyName + " = ?";
		return this.findUnique(hql, value);

	}

	public List<T> getList(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		Assert.notNull(clazz,"getList(String propertyName, Object value):entityClass is NULL");
		String hql = "from " + clazz.getName() + " as model where model."
				+ propertyName + " = ?";
		return this.findHql(hql, value);
	}

	public List<T> getAll() {
		Assert.notNull(clazz, "getAll():entityClass is NULL");
		String hql = "from " + clazz.getName();
		return this.findHql(hql);
	}
	
	  public long countHqlResult(final String hql, final Map<String, Object> values)
	  {
	        String fromHql = hql;
	        // select子句与order by子句会影响count查询,进行简单的排除.
	        fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
	        fromHql = StringUtils.substringBefore(fromHql, "order by");
	        String countHql = "select count(*) " + fromHql;
	
	        Long count = 0L;
	        try
	        {
	            count = findUnique(countHql, values);
	            if(count == null)
	            	count = 0L;
	        }
	        catch (Exception e)
	        {
	            //throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
	        	e.printStackTrace();
	        }
	        return count;
	    }

	  public long countHqlResult(final String hql, final Object... values)
	  {
	        String fromHql = hql;
	        // select子句与order by子句会影响count查询,进行简单的排除.
	        fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
	        fromHql = StringUtils.substringBefore(fromHql, "order by");
	        String countHql = "select count(*) " + fromHql;
	
	        Long count = 0L;
	        try
	        {
	            count = findUnique(countHql, values);
	            if(count == null)
	            	count = 0L;
	        }
	        catch (Exception e)
	        {
	            throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
	        }
	        return count;
	    }
	  private long countSqlResult(final String sql, final Object... values)
	    {
	        /*
	         * String fromSql = sql; //select子句与order by子句会影响count查询,进行简单的排除.
	         * fromSql = "from " + StringUtils.substringAfter(fromSql, "from");
	         * fromSql = StringUtils.substringBefore(fromSql, "order by");
	         * 
	         * String countSql = "select count(*) " + fromSql;
	         */

	        String countSql = "select count(1) from (" + sql + ") A";

	        Long count = 0L;
	        try
	        {
	            count = ((Number) createSQLQuery(countSql, values).uniqueResult()).longValue();
	        }
	        catch (Exception e)
	        {
	            throw new RuntimeException("sql can't be auto count, hql is:" + countSql, e);
	        }
	        return count;
	    }
	    
	    private long countSqlResult(final String sql, final Map<String, Object> values)
	    {
	        /*
	         * String fromSql = sql; //select子句与order by子句会影响count查询,进行简单的排除.
	         * fromSql = "from " + StringUtils.substringAfter(fromSql, "from");
	         * fromSql = StringUtils.substringBefore(fromSql, "order by");
	         * 
	         * String countSql = "select count(*) " + fromSql;
	         */

	        String countSql = "select count(1) from (" + sql + ") a";

	        Long count = 0L;
	        try
	        {
	            count = ((Number) createSQLQuery(countSql, values).uniqueResult()).longValue();
	        }
	        catch (Exception e)
	        {
	            throw new RuntimeException("sql can't be auto count, hql is:" + countSql, e);
	        }
	        return count;
	    }

	  

	public boolean isExist(String propertyName, Object value) {
		Assert.notNull(clazz,
						"isExist(String propertyName, Object value):entityClass is NULL");
		Assert.hasText(propertyName, "propertyName must not be empty");
		Assert.notNull(value, "value is required");
		T object = get(propertyName, value);
		return (object != null);
	}

	/**
	 * HQL分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param hql
	 * @param values
	 * @return
	 */
	public PageBean findPageByHql(int pageNum, int pageSize, String hql, Object... values)
    {
        // count查询
        long totalCount = countHqlResult(hql, values);
        // 按分页查询结果集
        Query query = createQuery(hql, values);
        setPageParameter(query, pageNum, pageSize);
        List list= query.list();
        return new PageBean(pageNum, pageSize, (int)totalCount, list);
    }
    /**
     * SQL分页查询
     * @param pageNum
     * @param pageSize
     * @param sql
     * @param values
     * @return
     */
    public PageBean findPageBySql(int pageNum, int pageSize, String sql, Object... values)
    {
        // count查询
        long totalCount =countSqlResult(sql, values);
        // 按分页查询结果集
        Query query = createSQLQuery(sql, values);
        //转成实体类Map
		//query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        setPageParameter(query, pageNum, pageSize);
        List list= query.list();
        return new PageBean(pageNum, pageSize, (int)totalCount, list);
    }
    private  Query setPageParameter(final Query query, final int pageNum, final int pageSize)
	{
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query;
	}

}
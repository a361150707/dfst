package com.ennuova.base;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ennuova.entity.RescueItem;
import com.ennuova.util.PageBean;
import com.ennuova.util.QueryHelper;
import com.sun.istack.internal.FinalArrayList;

public interface DaoSupport<T> {

	/**
	 * 保存
	 * @param entity
	 */
	T save(T entity);
	/**
	 * 删除
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 修改
	 * @param entity
	 */
	void update(T entity);
	 
	
	/**
     * 根据ID获取资料
     * @param id
     * @return Entity
     */
	T getById(Long id);
	
	
	/**
     * 根据ID获取资料
     * @param id
     * @return Entity
     */
	T loadById(Long id);
	
	/**
	 * 临时
	 * @param id
	 * @return
	 */
	RescueItem loadByIdi(Long id);
	
    /**
     * 根据ID数组获取资料
     * @param ids
     * @return
     */
	List<T> getByIds(Long ids[]);
	
	/**
	 * 获取表的所有资料
	 * @return
	 */
	List<T> findAll();
	
	/**
     * 根据表名获取最大主键值，并返回主键值+1
     * @param tableName
     * @return Long
     */
	Long getMaxId(String tableName, String keyName);
	
	/**
	 * 新方法
	 * @param pageNum
	 * @param pageSize
	 * @param queryHelper
	 * @return
	 */
	PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper);
	
	/**
     * 按HQL查询对象列表.
     */
    public abstract <X> List<X> findHql(final String hql);

    /**
     * 按HQL查询对象列表.
     * 
     * @param values 数量可变的参数,按顺序绑定.
     */
    public abstract <X> List<X> findHql(final String hql, final Object... values);
    
    /**
     * 通过自定义的sql来执行语句
     * @author sududa
     * @date 2016-05-28
     * @param sql
     * @param param
     * @return
     */
    public abstract Integer executeSql(final String sql, final Object... param);
    
    /**
     * 通过自定义的查询sql查询单条记录
     * @author sududa
     * @date 2016-05-30
     * @param sql
     * @param objs
     * @return
     */
    public abstract Map<String, Object> findOneForJdbc(final String sql, final Object... objs);
    
    /**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * @author sududa
	 * @date 2016-05-31
	 */
	public abstract List<Map<String, Object>> findForJdbc(final String sql, final Object... objs);

    /**
     * 按HQL查询对象列表.
     * 
     * @param values 命名参数,按名称绑定.
     */
    public abstract <X> List<X> findSql(final String sql,Class clazz, final Map<String, Object> values);
    
    /**
     * 按HQL查询对象列表.
     */
    public abstract <X> List<X> findSql(final String sql,Class clazz);

    /**
     * 按HQL查询对象列表.
     * 
     * @param values 数量可变的参数,按顺序绑定.
     */
    public abstract <X> List<X> findSql(final String sql,Class clazz, final Object... values);

    /**
     * 按HQL查询对象列表.
     * 
     * @param values 命名参数,按名称绑定.
     */
    public abstract <X> List<X> findHql(final String hql, final Map<String, Object> values);
    /**
     * 按HQL查询唯一对象.
     * 
     * @param values 数量可变的参数,按顺序绑定.
     */
    public abstract <X> X findUnique(final String hql, final Object... values);

    /**
     * 按HQL查询唯一对象.
     * 
     * @param values 命名参数,按名称绑定.
     */
    public abstract <X> X findUnique(final String hql, final Map<String, Object> values);

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * @param values 数量可变的参数,按顺序绑定.
     */
    public abstract Query createQuery(final String hql, final Object... values);

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * @param values 命名参数,按名称绑定.
     */
    public abstract Query createQuery(final String hql, final Map<String, Object> values);
    /**
     * 根据SQL语句查询
     * @param sql
     * @param values
     * @return
     */
    public SQLQuery createSQLQuery(final String sql, final Object... values);
    
    /**
     * 根据查询SQL与参数列表创建SQLQuery对象.
     * @param values 命名参数,按名称绑定.
     */
    public abstract SQLQuery createSQLQuery(final String sql, final Map<String, Object> values);
    
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
	public List<Map<String, Object>> findForJdbcPage(final String sql, final int page,
			final int rows, Object... objs);
	
	/**
	 * hibernate的批量插入
	 * @author sududa
	 * @date 2016年11月18日
	 * @param entityList 实体集合
	 * @param rows 一次要以几条数据插入,如果插入条数大于实体，则全部插入
	 * @return void
	 */
	public void saveWithBatch(List<T> entityList,final Integer rows);
	
    
	
}

package com.ennuova.base;

import java.util.List;
import java.util.Map;

/**
 * Dao接口 - Dao基接口
 * author:王志明
 */

public interface BaseDAO<T> extends DaoSupport<T>{
	
	/**
	 * 根据属性名和属性值获取实体对象.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象
	 */
	public T get(String propertyName, Object value);
	
	/**
	 * 根据属性名和属性值获取实体对象集合.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            属性值
	 * @return 实体对象集合
	 */
	public List<T> getList(String propertyName, Object value);

	/**
	 * 获取所有实体对象集合.
	 * 
	 * @return 实体对象集合
	 */
	public List<T> getAll();
	
    /**
     * 执行count查询获得本次Hql查询所能获得的对象总数.
     * <p> 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
     * 
     * @param hql hql语句
     * @param values 参数
     * 
     * @return long 条数
     */
    public abstract long countHqlResult(final String hql, final Object... values);
    
    /**
     * 执行count查询获得本次Hql查询所能获得的对象总数.
     * <p> 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
     * 
     * @param hql hql语句
     * @param values 参数
     * 
     * @return long 条数
     */
    public abstract long countHqlResult(final String hql, final Map<String, Object> values);
	
	/**
	 * 根据属性名判断数据是否已存在.
	 * 
	 * @param propertyName
	 *            属性名称
	 * @param value
	 *            值
	 * @return boolean true :存在
	 * 				   false:不存在
	 */
	public boolean isExist(String propertyName, Object value);
	
//    /**
//     * 按SQL分页查询
//     * 
//     * @param vo 分页参数.
//     * @param sql sql语句.
//     * @param values 命名参数,按名称绑定.
//     * 
//     * @return Page 分页查询结果.result里面是Map对象.
//     */
//    public abstract Page findPageBySQL(final BaseVO vo, final String sql, final Map<String, Object> values);
//    
//    /**
//     * 按SQL分页查询
//     * 
//     * @param vo 分页参数.
//     * @param sql sql语句.
//     * @param values 数量可变的查询参数,按顺序绑定.
//     * 
//     * @return Page 分页查询结果.result里面是Map对象.
//     */
//    public abstract Page findPageBySQL(final BaseVO vo, final String sql, final Object... values);
	
}
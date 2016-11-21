package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.BaseDAO;
import com.ennuova.entity.Cuslocation;

/**
 * 客户位置的dao
 * @author sududa
 * @date 2016年10月18日
 *
 */
public interface CusLocationDao extends BaseDAO<Cuslocation> {

	/**
	 * 根据客户id查找位置信息
	 *@author sududa
	 *@date 2016年10月18日
	 * @param cusId
	 * @return
	 */
	Map<String, Object> findByCusId(String cusId);

	/**
	 * 修改客户的位置信息
	 *@author sududa
	 *@date 2016年10月18日
	 * @param cuslocation
	 * @return
	 */
	Integer updateByCusId(Cuslocation cuslocation);

	/**
	 * 根据客户id删除客户的位置信息
	 *@author sududa
	 *@date 2016年10月19日
	 * @param cusId
	 * @return
	 */
	Integer deleteByCusId(String cusId);

	/**
	 * 查询附近的人列表
	 *@author sududa
	 *@date 2016年10月19日
	 * @param page 当前页(默认值为1)
	 * @param rows 每页显示的条数(默认值为10)
	 * @param interest
	 * @param authentication
	 * @param loginId 
	 * @param sex 
	 * @return
	 */
	List<Map<String, Object>> doList(Integer page, Integer rows,String interest, String authentication, String loginId, String sex);

	/**
	 * 查看附近人的详情
	 *@author sududa
	 *@date 2016年10月19日
	 * @param id
	 * @return
	 */
	Map<String, Object> doDetail(String id);

	
}

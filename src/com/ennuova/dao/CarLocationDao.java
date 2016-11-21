package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.BaseDAO;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.Cuslocation;
import com.ennuova.entity.TestInsert;

/**
 * 车辆位置dao
 * @author sududa
 * @date 2016年11月4日
 *
 */
public interface CarLocationDao extends BaseDAO<CarLocation> {

	/**
	 * 查询除了自己的车以外的附近的车
	 * @author sududa
	 * @param rows 
	 * @param page 
	 * @param cusId 
	 * @date 2016年11月4日
	 */
	List<Map<String, Object>> findListBynotSelf(Integer page, Integer rows, String cusId);

	/**
	 * 根据客户的id查询该客户是否有存入了车的位置
	 * @author sududa
	 * @param cusId
	 * @date 2016年11月4日
	 */
	Map<String, Object> findByCusId(String cusId);

	/**
	 * 根据客户的id删除客户的默认车辆的位置信息
	 * @author sududa
	 * @date 2016年11月7日
	 */
	Integer doDelByCusId(String cusId);

	/**
	 * 查询附近的人列表
	 * @author sududa
	 * @date 2016年11月8日
	 */
	List<Map<String, Object>> findListBynotSelfWithParams(Integer page, Integer rows, CarLocation carLocation);


	
}

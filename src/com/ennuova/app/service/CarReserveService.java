package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.CarReserveEntity;

/**
 * 车辆预约的接口
 * @author sududa
 * @date 2016年9月13日
 *
 */
public interface CarReserveService {


	/**
	 * 保存车辆预约的信息,预约变更记录，员工变更记录
	 *@author sududa
	 *@date 2016年9月13日
	 * @param carReserveEntity
	 * @return
	 */
	Integer saveReserveWithStateAndStaff(CarReserveEntity carReserveEntity);

	/**
	 * 根据客户id查找相关信息，用于预约的初始化
	 *@author sududa
	 *@date 2016年9月14日
	 * @param cusId
	 * @return
	 */
	Map<String, Object> findInitMap(String cusId);

	/**
	 * 根据客户id和预约的id取消预约记录
	 *@author sududa
	 *@date 2016年9月14日
	 * @param cusId
	 * @param reserveId
	 * @param remarks 
	 * @param updateState 
	 * @return
	 */
	Integer doCancleReserve(String cusId, String reserveId, String remarks, Integer updateState);

	/**
	 * 根据客户id和预约类型查询客户的预约历史记录
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @param reserveType
	 * @param rows 
	 * @param page 
	 * @return
	 */
	List<Map<String, Object>> findListByCusIdAndReserveType(String cusId, String reserveType, Integer page, Integer rows);

	/**
	 * 根据管家id查找对应的管家的信息
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @return
	 */
	Map<String, Object> findUserByCusId(String userId);

	/**
	 * 根据客户id查找其下的sa列表
	 *@author sududa
	 *@date 2016年9月18日
	 * @param cusId
	 * @param rows 
	 * @param page 
	 * @param roleType 
	 * @return
	 */
	List<Map<String, Object>> findsaList(String cusId, String roleType, Integer page, Integer rows);

	/**
	 * 根据预约id查找预约的订单详情记录
	 *@author sududa
	 *@date 2016年9月19日
	 * @param reserveId
	 * @param reserveType 
	 * @return
	 */
	List<Map<String, Object>> findReserveOrderList(String reserveId, String reserveType);

	/**
	 * 根据门店id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表
	 *@author sududa
	 *@date 2016年9月23日
	 * @param storeId
	 * @param roleType
	 * @param page
	 * @param rows
	 * @return
	 */
	List<Map<String, Object>> findUserListByStoreIdAndroleType(String storeId, String roleType, Integer page,
			Integer rows);

	/**删除订单
	 * @param reserveId
	 * @param delFlag
	 * @return
	 */
	Integer doDelete(String reserveId, Integer delFlag);

}

package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.CarReserveEntity;

/**
 * 车辆预约的dao接口
 * @author sududa
 * @date 2016年9月13日
 *
 */
public interface CarReserveDao {

	/**
	 * 保存预约记录
	 *@author sududa
	 *@date 2016年9月13日
	 * @param carReserveEntity
	 * @return
	 */
	CarReserveEntity saveCarReserve(CarReserveEntity carReserveEntity);

	/**
	 * 根据预约类型和排序值查找预约状态
	 *@author sududa
	 *@date 2016年9月14日
	 * @param reserveType
	 * @param orderNum
	 * @return
	 */
	Map<String, Object> findReserveStateByTypeAndOrder(Integer reserveType, int orderNum);

	/**
	 * 根据客户id查找相关信息，用于预约的初始化
	 *@author sududa
	 *@date 2016年9月14日
	 * @param cusId
	 * @return
	 */
	Map<String, Object> findInitMap(String cusId);

	/**
	 * 根据预约id取消预约
	 *@author sududa
	 *@date 2016年9月14日
	 * @param reserveId
	 * @param updateState 
	 * @return
	 */
	Integer doCancleReserveByCusIdAndReserveId(String reserveId, Integer updateState);

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
	 * @param userId
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
	 * @param roleCode 
	 * @return
	 */
	List<Map<String, Object>> findsaList(String cusId, String roleCode, Integer page, Integer rows);

	/**
	 * 根据预约id查找预约的订单详情记录
	 *@author sududa
	 *@date 2016年9月19日
	 * @param reserveId
	 * @return
	 */
	List<Map<String, Object>> findReserveOrderList(String reserveId, String reserveType);

	/**
	 * 根据门店id查询服务顾问(SA),管家(CRM),销售顾问(SC)列表
	 *@author sududa
	 *@date 2016年9月23日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	List<Map<String, Object>> findUserListByStoreIdAndroleType(String storeId, String roleType, Integer page,
			Integer rows);
	/**删除订单记录
	 * @param reserveId
	 * @param delFlag
	 * @return
	 */
	Integer doDelete(String reserveId, Integer delFlag);

	/**
	 * 根据预约id和预约状态查找预约的记录
	 * @author sududa
	 * @date 2016年11月8日
	 */
	Map<String, Object> findByReserveIdAndState(String reserveId, Integer updateState);
}

package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnSsxcrecode;

/**实时行程记录dao
 * @author 李智辉 
 * 2015-12-10下午1:19:34
 */
public interface CnSsxcrecodeDao extends DaoSupport<CnSsxcrecode> {
	/**根据车辆Id查询最新的一条数据
	 * @param fclId
	 * @return CnSsxcrecode
	 */
	CnSsxcrecode getNewCnSsxcrecode(Long fclId);
	/**根据车辆id获取实时位置
	 * @param fclid
	 * @return CnSsxcrecode
	 */
	CnSsxcrecode getRealTimeLocation(Long fclid,String initTime);
	/**根据车辆ID和行程结束时间查询该时间的车辆LBS信息
	 * @param fclid
	 * @return CnSsxcrecode
	 */
	CnSsxcrecode getXcrecodeLBS(Long fclid,String selectTime,String selectTimeFiveAgo);
	/**根据车辆ID和行程开始时间查询该时间的车辆LBS信息
	 * @param fclid
	 * @return CnSsxcrecode
	 */
	CnSsxcrecode getXcrecodeLBSByBegin(Long fclid,String selectTime,String selectTimeFiveAgo);
	/**根据开始时间和结束时间获取该行程的所以GPS集合
	 * @param xcrecodeId
	 * @param carId
	 * @return List<CnSsxcrecode>
	 */
	List<Map<String, Object>> getCnSsxcrecodeByTime(String beginTime,String endTime,Long carId);
	
	/**
	 * 根据客户id获取客户车辆的最新的位置信息,即车辆的实时行程记录
	 * @author sududa
	 * @date 2016年10月31日
	 */
	Map<String, Object> findByCusId(String cusId);
	/**
	 * 根据车辆id获取车辆的最新的位置信息,即车辆的实时行程记录
	 * @author sududa
	 * @date 2016年11月2日
	 */
	Map<String, Object> findByCarId(String carId);
	
}

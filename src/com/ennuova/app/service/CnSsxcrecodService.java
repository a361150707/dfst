package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.CarMonitor;

/**实时行程记录service
 * @author 李智辉 
 * 2015-12-11上午9:27:16
 */
public interface CnSsxcrecodService {
	/**根据车辆id获取车辆的当前位置信息
	 * @param fclid
	 * @return Map<String,Object>
	 */
	Map<String,Object> getCarNowLBS(Long fclid);
	/**根据车辆Id获取当前五秒内最新的数据
	 * @param fclid
	 * @return CarMonitor
	 */
	CarMonitor getCarMonitor(Long fclid);
	/**根据车辆Id获取最新的数据
	 * @param fclid
	 * @return CarMonitor
	 */
	CarMonitor getCarMonitorMax(Long fclid);
	/**根据车辆id获取车辆的当前位置信息
	 * @param fclid
	 * @return Map<String,Object>
	 */
	Map<String,Object> getRealTimeLocation(Long fclid);
	/**根据行程Id获取LBS集合
	 * @param xcoredId
	 * @param carId
	 * @return 
	 */
	List<Map<String, Object>> getLBSList(Long xcoredId,Long carId);
	/**根据车辆ID和开始结束时间获取LBS集合
	 * @param beginTime
	 * @param endTime
	 * @param carId
	 * @return
	 */
	List<Map<String, Object>> getCnSsxcrecodeByTime(String beginTime,String endTime,Long carId);
}

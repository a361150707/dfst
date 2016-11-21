package com.ennuova.dao;

import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CarMonitor;
import com.ennuova.entity.CnSsclxx;

/**
 * 实时车辆信息Dao
 * @author dofast-pc
 */
public interface CnSsclxxDao extends DaoSupport<CnSsclxx>{
	/**根据车辆Id和当前时间五秒前的时间获取车辆最新的一条数据
	 * @param fclid
	 * @return CarMonitor
	 */
	CarMonitor getCarMonitor(Long fclid, String startTime);

	/**
	 * 根据车辆ID获取里程数
	 * @param carId
	 * @return
	 */
	String getKmByCarinfoId(long carId);

	/**
	 * 通过客户id查询该客户的默认车的总里程和总油耗
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> findFirstOneByCusId(String loginId);

	/**
	 * 通过车辆id查询该客户的默认车的总里程和总油耗
	 * @author sududa
	 * @date 2016年11月2日
	 */
	Map<String, Object> findFirstOneByCarId(String carId);
}

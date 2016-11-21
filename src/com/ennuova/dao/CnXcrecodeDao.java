package com.ennuova.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnXcrecode;

/**行程管理dao
 * @author 李智辉 
 * 2015-12-9上午10:19:39
 */
public interface CnXcrecodeDao extends DaoSupport<CnXcrecode>{
	/**根据车辆Id,开始时间和结束时间获取相关的行程记录列表
	 * @param fclid
	 * @param startTime
	 * @param endTime
	 * @return List<CnXcrecode>
	 */
	List<CnXcrecode> queryCnXcrecodeListByEndTime(Long fclid,String startTime,String endTime);

	/**根据车辆Id,行程序号
	 * @param fclid
	 * @return List<CnXcrecode>
	 */
	CnXcrecode findByXcxhAndcarId(Long carId, String fxcxh);
	
	/**根据车辆Id查询一年的数据
	 * @param fclid
	 * @return List<CnXcrecode>
	 */
	List<CnXcrecode> getOneYearData(Long fclid);
	/**根据车辆Id和日期查询一天的行程
	 * @param fclid
	 * @param time
	 * @return CnXcrecode
	 */
	List<CnXcrecode> getXcrecodeDataByTime(Long fclid,String time);
	/**根据上一次查询的最大id和车辆Id查询出所有大于此id的所有信息
	 * @param fclid
	 * @param maxId
	 * @return
	 */
	List<CnXcrecode> getOneYearData(Long fclid,Long maxId);

	/**
	 * 根据客户的id获取客户的行程列表取前10条
	 * @author sududa
	 * @date 2016年10月28日
	 */
	List<Map<String, Object>> findByCusIdWithPage(Integer page, Integer rows, String loginId);

	/**
	 * 根据车辆id查询当天的行程记录
	 * @author sududa
	 * @date 2016年11月4日
	 */
	List<Map<String, Object>> findListByCarIdAndDate(String defCarId);

	/**
	 * 根据车辆id分页查询车辆的行程记录信息
	 * @author sududa
	 * @date 2016年11月4日
	 */
	List<Map<String, Object>> findByCarIdWithPage(Integer page, Integer rows, String defCarId);
}

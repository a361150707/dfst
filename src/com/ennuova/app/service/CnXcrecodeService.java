package com.ennuova.app.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ennuova.entity.CnXcrecode;
import com.ennuova.entity.XcRecord;
import com.ennuova.entity.XcrecodeVO;

/**行程管理service
 * @author 李智辉 
 * 2015-12-9下午1:26:43
 */
public interface CnXcrecodeService {
	/** 根据车辆id和查询日期获取当天的所有行程记录
	 * @param fclid
	 * @param status
	 * @return List<CnXcrecode>getOneYearData
	 */
	Map<String, Object> getLbsList(Long fclid,int status);
	/**根据车辆Id获取车辆一年的行程记录
	 * @param fclid
	 * @return List<XcRecord>
	 */
	List<XcrecodeVO> getXcRecordList(Long fclid,String time);
	/**根据车辆Id和上一次查询的最大Id获取大于此Id的所有行程信息
	 * @param fclid
	 * @param maxId
	 * @return List<XcRecord>
	 */
	List<XcRecord> getXcRecordListByMaxId(Long fclid, Long maxId);
}

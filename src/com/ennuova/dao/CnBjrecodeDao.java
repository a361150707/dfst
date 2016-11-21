package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnBjrecode;

/**
 * 报警记录DAo
 * @author dofast-pc
 */
public interface CnBjrecodeDao extends DaoSupport<CnBjrecode> {
	/**根据车辆Id，开始时间，结束时间查询报警记录
	 * @param fclid
	 * @param startTime
	 * @param endTime
	 * @return List<CnBjrecode>
	 */
	List<String> getCnBjrecodeList(Long fclid,String startTime,String endTime);
}

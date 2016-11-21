package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.BaseDAO;
import com.ennuova.entity.ReserveRescue;

/**
 * 
* @Title: ReserveRescueDao.java 
* @Package com.ennuova.dao 
* @Description: 道路救援(描述) 
* @author felix
* @date 2016年4月25日
* @version V1.0
 */
public interface ReserveRescueDao extends BaseDAO<ReserveRescue>{

	/**
	 * 
	* @Title: ReserveRescueDao.java 
	* @Package com.ennuova.dao 
	* @Description: 检索用户道路救援记录(描述) 
	* @author felix
	* @date 2016年4月25日
	* @version V1.0
	 */
	List<ReserveRescue> queryReserveRescue(long cusId);
	
	/**
	 * 
	* @Title: ReserveRescueDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取道路救援预约详情(描述) 
	* @author felix
	* @date 2016年4月25日
	* @version V1.0
	 */
	ReserveRescue getReserveRescue(long reserveRescueId);
}

package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CarRemindSet;

/**
 * 
* @Title: CarRemindSetDao.java 
* @Package com.ennuova.dao 
* @Description: 车务提醒配置dao(描述) 
* @author felix
* @date 2016年4月19日
* @version V1.0
 */
public interface CarRemindSetDao extends DaoSupport<CarRemindSet>{

	/**
	 * 
	* @Title: CarRemindSetDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取车辆id的车务配置信息(描述) 
	* @author felix
	* @date 2016年4月19日
	* @version V1.0
	 */
	CarRemindSet getbyCarId(long carId);
}

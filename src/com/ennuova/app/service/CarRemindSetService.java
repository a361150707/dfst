package com.ennuova.app.service;

import com.ennuova.entity.CarRemindSet;

/**
 * 
* @Title: CarRemindSetService.java 
* @Package com.ennuova.app.service 
* @Description: 车务提醒配置(描述) 
* @author felix
* @date 2016年4月19日
* @version V1.0
 */
public interface CarRemindSetService {

	/**
	 * 
	* @Title: CarRemindSetService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取对应车辆车务配置(描述) 
	* @author felix
	* @date 2016年4月19日
	* @version V1.0
	 */
	CarRemindSet getCarRemind(long carId);
	
	/**
	 * 
	* @Title: CarRemindSetService.java 
	* @Package com.ennuova.app.service 
	* @Description: 修改车务提醒配置(描述) 
	* @author felix
	* @date 2016年4月19日
	* @version V1.0
	 */
	int updateCarRemind(CarRemindSet carRemind);
	
	/**
	 * 
	* @Title: CarRemindSetService.java 
	* @Package com.ennuova.app.service 
	* @Description: 保存车务提醒配置(描述) 
	* @author felix
	* @date 2016年4月19日
	* @version V1.0
	 */
	int saveCarRemind(CarRemindSet carRemind);
	
}

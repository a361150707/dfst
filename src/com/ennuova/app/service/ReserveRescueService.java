package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.RescueItem;
import com.ennuova.entity.ReserveRescue;

/**
 * 
* @Title: ReserveRescueService.java 
* @Package com.ennuova.app.service 
* @Description: 道路救援服务(描述) 
* @author felix
* @date 2016年4月25日
* @version V1.0
 */
public interface ReserveRescueService {
	
	/**
	 * 
	* @Title: ReserveRescueService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取救援记录(描述) 
	* @author felix
	* @date 2016年4月25日
	* @version V1.0
	 */
	List<ReserveRescue> queryReserveRescue(long cusId);
	
	/**
	 * 
	* @Title: ReserveRescueService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取救援详情(描述) 
	* @author felix
	* @date 2016年4月25日
	* @version V1.0
	 */
	ReserveRescue getReserveRescue(long reserveRescueId);
	
	/**
	 * 
	* @Title: ReserveRescueService.java 
	* @Package com.ennuova.app.service 
	* @Description: 预约道路救援(描述) 
	* @author felix
	* @date 2016年4月25日
	* @version V1.0
	 */
	int saveReserveRescue(ReserveRescue reserveRescue);
	
	RescueItem getById(long id);
}

package com.ennuova.app.service;

import com.ennuova.entity.CnSbljinfo;

/**
 * 
* @Title: CnSbljinfoService.java 
* @Package com.ennuova.app.service 
* @Description: 盒子设备信息(描述) 
* @author felix
* @date 2016年4月27日
* @version V1.0
 */
public interface CnSbljinfoService {

	/**
	 * 
	* @Title: CnSbljinfoService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取设备信息(描述) 
	* @author felix
	* @date 2016年4月27日
	* @version V1.0
	 */
	CnSbljinfo getSbInfo(String fsbxlh);
}

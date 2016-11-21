package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.CustomerVehiclemodel;

public interface CustomerVehiclemodelService {

	/**
	 * 
	* @Title: CustomerVehiclemodelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取用户收藏车型集合(描述) 
	* @author felix
	* @date 2016年4月14日
	* @version V1.0
	 */
	List<CustomerVehiclemodel> queryCusModel(long cusId);

	/**
	 * 
	* @Title: CustomerVehiclemodelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 关注车型(描述) 
	* @author felix
	* @date 2016年4月14日
	* @version V1.0
	 */
	int saveCusModel(CustomerVehiclemodel cusModel);
	
	/**
	 * 
	* @Title: CustomerVehiclemodelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 删除用户关注(描述) 
	* @author felix
	* @date 2016年4月14日
	* @version V1.0
	 */
	int delCusModel(long cusModelId);
}

package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.PubVehiclemodel;

public interface PubVehicleModelService {

	/**
	 * 
	* @Title: PubVehicleModelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取车型(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	List<PubVehiclemodel> queryModel(PubVehiclemodel model,long cusId,int type);
	
	/**
	 * 
	* @Title: PubVehicleModelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取单个车型详情(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	PubVehiclemodel getModel(long id,long customerId);
	
	/**
	 * 
	* @Title: PubVehicleModelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 更新浏览数(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	int updateBorrowNum(long id);
	
	/**
	 * 
	* @Title: PubVehicleModelService.java 
	* @Package com.ennuova.app.service 
	* @Description: 更新收藏数(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	int updateAttentionNum(long id);
}

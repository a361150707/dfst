package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubVehiclemodel;

public interface PubVehicleModelDao extends DaoSupport<PubVehiclemodel>{

	/**
	 * 
	* @Title: PubVehicleModelDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取车型，type为0按热度排序，type为1按默认排序(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	List<PubVehiclemodel> queryModel(PubVehiclemodel model,int type);
	
	/**
	 * 
	* @Title: PubVehicleModelDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取单条车型(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	PubVehiclemodel getModel(long id);
	
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

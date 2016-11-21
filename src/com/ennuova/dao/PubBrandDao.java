package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubBrand;

public interface PubBrandDao extends DaoSupport<PubBrand>{

	/**
	 * 
	* @Title: PubBrandDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取车辆品牌(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	public PubBrand getBrand(PubBrand brand);
}

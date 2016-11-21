package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubLine;

public interface PubLineDao extends DaoSupport<PubLine> {

	/**
	 * 
	* @Title: PubLineDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取车系(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	PubLine queryLine(PubLine line);
}

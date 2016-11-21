package com.ennuova.app.service;

import com.ennuova.entity.PubLine;

public interface PubLineService {

	/**
	 * 
	* @Title: PubLineService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取车系及车系下的车型(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	PubLine queryLine(PubLine line);
}

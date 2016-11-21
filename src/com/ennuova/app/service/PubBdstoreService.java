package com.ennuova.app.service;

import java.io.Serializable;
import java.util.Map;

public interface PubBdstoreService {

	public int updateDefault(long cusId);
	public long getIsExistId(long storeId,long cusId);
	public int updateCusDefault(long id);
	public int save(long storeId,long cusId);
	boolean delCusStore(long id);
	
	/**
	 * 根据客户的id查找客户绑定的门店id
	 *@author sududa
	 *@date 2016年10月8日
	 * @param customerId
	 * @return
	 */
	public Map<String, Object> findByCusId(long customerId);
	
}

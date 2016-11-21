package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.PubStore;

public interface PubStoreService {

	/**
	 * 
	 * @Description: 获取单个门店信息
	 * @param @param id
	 * @return PubStore  
	 * @author felix
	 * @date 2015-12-1
	 */
	public PubStore queryStoreById(long id);
	
	public List<PubStore> querySomeStore(Long cid);
	
	public boolean sava(PubStore ps);
	
	public List<PubStore> queryMyStore(Long cusId);
	
	/**根据地理信息获取附近门店
	 * @param realTimeAddress
	 * @return List<PubStore> 
	 */
	List<PubStore> getPubStoreByAddress(String realTimeAddress);
	/**获取所有的门店
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getPubStoreAll();
}

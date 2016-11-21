package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubStore;

public interface PubStoreDao extends DaoSupport<PubStore>{
	
	public PubStore getPubStorebyId(long id);
	public List<PubStore> querySomeStore(long cid);
	public List<PubStore> queryMyStore(long cusId);
	/**根据地址信息获取当前城市4s店列表
	 * @param realTimeAddress
	 * @return List<PubStore>
	 */
	List<PubStore> getPubStoreByAddress(String realTimeAddress);
	/**获取所有的门店信息
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getPubAllStore();
}

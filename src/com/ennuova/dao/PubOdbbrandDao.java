package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubOdbbrand;

/**
 * ODB品牌DAO
 */
public interface PubOdbbrandDao extends DaoSupport<PubOdbbrand>{

	List<PubOdbbrand> findChilrend();

	void bachSave(String brand);
	/**根据ODB品牌子Id查询父Id
	 * @param dId
	 * @return Long
	 */
	Long getParentIdByDetailId(Long dId);
}

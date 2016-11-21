package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnGzm;

/**
 * 故障码表DAo
 * @author dofast-pc
 */
public interface CnGzmDao extends DaoSupport<CnGzm>{

	/**
	 * 根据故障码查询
	 * @param faultcode
	 * @return
	 */
	CnGzm findByFno(String faultcode);

}

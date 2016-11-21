package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubDatalmb;

/**
 * 数据流DAo
 * @author dofast-pc
 */
public interface PubDatalmbDao extends DaoSupport<PubDatalmb>{

	/**
	 * 根据编号查询
	 * @param fcode
	 * @return
	 */
	PubDatalmb findByFcode(String fcode);

}

package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubAlarmsetting;

/**
 * 报警设置DAO
 * @author dofast-pc
 */
public interface PubAlarmsettingDao extends DaoSupport<PubAlarmsetting>{

	/**
	 * 根据Code查询报警设置
	 * @param string
	 * @return
	 */
	PubAlarmsetting findByFcodeAndBIsuse(String fcode,Long bIsuse);


	/**
	 * 根据Code查询报警设置
	 * @param string
	 * @return
	 */
	PubAlarmsetting findByFcode(String fcode);

}

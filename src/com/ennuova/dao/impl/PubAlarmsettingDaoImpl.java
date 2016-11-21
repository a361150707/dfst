package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubAlarmsettingDao;
import com.ennuova.entity.PubAlarmsetting;

/**
 * 报警设置DAOIMPL
 */
@Repository("pubAlarmsettingDao")
public class PubAlarmsettingDaoImpl extends DaoSupportImpl<PubAlarmsetting>
		implements PubAlarmsettingDao {

	/**
	 * 根据code查询
	 */
	public PubAlarmsetting findByFcodeAndBIsuse(String fcode,Long bIsuse) {
		return (PubAlarmsetting) getSessionFactory().getCurrentSession()
				.createQuery(//
						"from PubAlarmsetting where fcode=? and bisuse=?")//
				.setParameter(0, fcode)//
				.setParameter(1, bIsuse)
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}
	
	/**
	 * 根据code查询
	 */
	public PubAlarmsetting findByFcode(String fcode) {
		return (PubAlarmsetting) getSessionFactory().getCurrentSession()
				.createQuery(//
						"from PubAlarmsetting where fcode=?")//
				.setParameter(0, fcode)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}


}

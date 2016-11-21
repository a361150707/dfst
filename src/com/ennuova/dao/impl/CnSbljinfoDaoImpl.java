package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnSbljinfoDao;
import com.ennuova.entity.CnSbljinfo;

/**
 * 设备登录信息dao
 * 
 * @author janson
 */
@Repository("cnSbljinfoDao")
public class CnSbljinfoDaoImpl extends DaoSupportImpl<CnSbljinfo> implements
		CnSbljinfoDao {

	/**
	 * 根据Ip和端口查询
	 * 
	 * @param ip
	 * @param port
	 */
	public CnSbljinfo findByIpAndPort(String ip, long port) {
		return (CnSbljinfo) getSessionFactory().getCurrentSession()
				.createQuery(//
						"from  CnSbljinfo where floginip=? and floginport=?")
				.setParameter(0, ip)//
				.setParameter(1, port)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	/**
	 * 根据Ip、端口、设备序列、imei查询
	 * 
	 * @param ip
	 * @param port
	 * @param fsbxuh
	 * @param fsbimei
	 */
	public CnSbljinfo findByIpAndPortAndFsbxlhAndFsbmiei(String ip, long port,
			String fsbxlh, String fsbimei) {
		return (CnSbljinfo) getSessionFactory()
				.getCurrentSession()
				.createQuery(//
						"from  CnSbljinfo where floginip=? and floginport=? and fsbxlh=? and fsbimei=?")
				.setParameter(0, ip)//
				.setParameter(1, port)//
				.setParameter(2, fsbxlh)//
				.setParameter(3, fsbimei)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	/**
	 * 根据设备序列、imei查询
	 * 
	 * @param serialNumber
	 * @param imei
	 * @return
	 */
	public CnSbljinfo findByFsbxlhAndFsbmiei(String serialNumber, String imei) {
		return (CnSbljinfo) getSessionFactory().getCurrentSession()
				.createQuery(//
						"from  CnSbljinfo where fsbxlh=? and fsbimei=?")
				.setParameter(0, serialNumber)//
				.setParameter(1, imei)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	public CnSbljinfo findByFsbxlh(String fsbxlh) {
		return (CnSbljinfo) getSessionFactory().getCurrentSession()
				.createQuery(//
						" from CnSbljinfo where fsbxlh=?")
				.setParameter(0, fsbxlh)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
	}
}

package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CnSbljinfo;

/**设备登录信息dao
 * @author janson 
 */
public interface CnSbljinfoDao extends DaoSupport<CnSbljinfo>{

	/**根据Ip和端口查询
	 * @param ip
	 * @param port
	 */
	CnSbljinfo findByIpAndPort(String ip, long port);
	
	/**根据Ip、端口、设备序列、imei查询
	 * @param ip
	 * @param port
	 * @param fsbxuh
	 * @param fsbimei
	 */
	CnSbljinfo findByIpAndPortAndFsbxlhAndFsbmiei(String ip, long port,String fsbxlh,String fsbimei);

	/**
	 * 根据设备序列、imei查询
	 * @param serialNumber
	 * @param imei
	 * @return
	 */
	CnSbljinfo findByFsbxlhAndFsbmiei(String serialNumber, String imei);
	
	/**
	 * 
	* @Title: CnSbljinfoDao.java 
	* @Package com.ennuova.dao 
	* @Description: 根据盒子序列号查询(描述) 
	* @author felix
	* @date 2016年4月27日
	* @version V1.0
	 */
	CnSbljinfo findByFsbxlh(String fsbxlh);

}

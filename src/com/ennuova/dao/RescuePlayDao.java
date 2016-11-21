package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.RescuePlay;

/**
 * 
* @Title: RescuePlayDao.java 
* @Package com.ennuova.dao 
* @Description: 道路救援项目(描述) 
* @author felix
* @date 2016年4月22日
* @version V1.0
 */
public interface RescuePlayDao extends DaoSupport<RescuePlay>{

	List<RescuePlay> queryRescuePlay();
	/**
	 * 查看救援的历史详情
	 * @author sududa
	 * @date 2016年7月19日
	 * @param id
	 * @return
	 */
	Map<String, Object> findDetail(String id);
}

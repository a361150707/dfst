package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.CustomerVehiclemodel;

public interface CustomerVehiclemodelDao extends DaoSupport<CustomerVehiclemodel>{

	/**
	 * 
	* @Title: CustomerVehiclemodelDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取用户关注车型(描述) 
	* @author felix
	* @date 2016年4月14日
	* @version V1.0
	 */
	List<CustomerVehiclemodel> queryCusModel(long cusId);
	/**根据用户ID和车型ID查询用户是否已经关注
	 * @param userId
	 * @param vehileId
	 * @return List<CustomerVehiclemodel>
	 */
	List<CustomerVehiclemodel> queryUserIsFollow(Long userId,Long vehileId);
}

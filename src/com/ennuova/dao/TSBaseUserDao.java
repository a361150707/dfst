package com.ennuova.dao;

import java.util.Map;

import com.ennuova.entity.TSBaseUser;

public interface TSBaseUserDao {

	/**
	 * 根据车辆ID获取服务顾问信息
	 * @param carId
	 * @return
	 */
	TSBaseUser getTsBaseUser(long carId);

	/**
	 *根据登陆的ID，获取顾问信息
	 * @author 陈晓珊
	 * @date 2016年10月24日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doDetail(String loginId);

}

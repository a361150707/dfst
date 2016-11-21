package com.ennuova.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CarRemindSetDao;
import com.ennuova.entity.CarRemindSet;

/**
 * 
* @Title: CarRemindSetDaoImpl.java 
* @Package com.ennuova.dao.impl 
* @Description: 车务提醒配置dao(描述) 
* @author felix
* @date 2016年4月19日
* @version V1.0
 */
@Repository("remindSetDao")
public class CarRemindSetDaoImpl extends DaoSupportImpl<CarRemindSet> implements CarRemindSetDao{

	@Override
	public CarRemindSet getbyCarId(long carId) {
		@SuppressWarnings("unchecked")
		List<CarRemindSet> remindList = (List<CarRemindSet>)createQuery(" from CarRemindSet where carinfoId = ?", carId).list();
		if(remindList.size() > 0) return remindList.get(0);
		else return null;
	}

}

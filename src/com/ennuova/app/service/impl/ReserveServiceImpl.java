package com.ennuova.app.service.impl;

import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.config.AppResult;
import com.ennuova.app.service.ReserveService;
import com.ennuova.dao.ReserveMaintainDao;
import com.ennuova.dao.ReserveRemewalDao;
import com.ennuova.dao.ReserveRepairDao;
@Service("reserveService")
public class ReserveServiceImpl implements ReserveService {
	@Resource
	private ReserveMaintainDao reserveMaintainDao;
	@Resource
	private ReserveRemewalDao reserveRemewalDao;
	@Resource
	private ReserveRepairDao reserveRepairDao;
	@Override
	public AppResult updateReserve(Long reserveId, int type) {
		
		AppResult appResult = new AppResult();
		//0表示保养预约1维修预约2续保预约
		if (type==0) {
			appResult = reserveMaintainDao.findReserveMaintainById(reserveId);
			return appResult;
		}else if (type==1){
			appResult = reserveRepairDao.findReserveRepairById(reserveId);
			return appResult;
		}else {
			appResult = reserveRemewalDao.findReserveRemewalById(reserveId);
			return appResult;
		}
	}
}

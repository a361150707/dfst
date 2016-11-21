package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.CarRemindSetService;
import com.ennuova.dao.CarRemindSetDao;
import com.ennuova.entity.CarRemindSet;

@Service("remindSetService")
public class CarRemindSetServiceImpl implements CarRemindSetService{
	
	@Resource
	private CarRemindSetDao remindSetDao;

	@Transactional(readOnly=true)
	@Override
	public CarRemindSet getCarRemind(long carId) {
		CarRemindSet remind = remindSetDao.getbyCarId(carId);
		if(remind != null)	return remind;
		else return null;
	}

	@Override
	public int updateCarRemind(CarRemindSet carRemind) {
		int result = 0;
		try {
			remindSetDao.update(carRemind);
			result = 1;
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public int saveCarRemind(CarRemindSet carRemind) {
		int result = 0;
		try {
			remindSetDao.save(carRemind);
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

}

package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwCusalarmsService;
import com.ennuova.dao.OwCusalarmsDao;
import com.ennuova.entity.OwCusalarms;

@Service("owCusalarmsService")
public class OwCusalarmsServiceImpl implements OwCusalarmsService{

	@Resource
	private OwCusalarmsDao owCusalarmsDao;
	
	@Override
	public List<OwCusalarms> queryAlarmByCus(long cusId) {
		return owCusalarmsDao.queryAlarmsByCus(cusId);
	}

	@Override
	public int updateSwitch(long id, long sw) {
		return owCusalarmsDao.updateSwitch(id, sw);
	}

}

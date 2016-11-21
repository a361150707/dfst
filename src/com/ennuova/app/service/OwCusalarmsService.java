package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.OwCusalarms;

public interface OwCusalarmsService {

	List<OwCusalarms> queryAlarmByCus(long cusId);
	int updateSwitch(long id,long sw);
}

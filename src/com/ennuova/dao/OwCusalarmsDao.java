package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwCusalarms;

public interface OwCusalarmsDao extends DaoSupport<OwCusalarms>{

	List<OwCusalarms> queryAlarmsByCus(long cusId);
	int updateSwitch(long id,long sw);
	OwCusalarms queryAlarmsByCusAndPubAlarmsetting(Long rRecuser,
			Long pubAlarmsettingId);
}

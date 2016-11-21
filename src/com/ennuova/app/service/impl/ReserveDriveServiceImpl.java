package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.ReserveDriveService;
import com.ennuova.dao.PubVehicleModelDao;
import com.ennuova.dao.ReserveDriveDao;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.entity.ReserveDrive;

@Service("rDriveService")
public class ReserveDriveServiceImpl implements ReserveDriveService{

	@Resource
	private ReserveDriveDao rDriveDao;
	@Resource
	private PubVehicleModelDao pubVehicleModelDao;

	@Override
	public int reserveDrive(ReserveDrive rd) {
		try {
			PubVehiclemodel pubVehiclemodel = pubVehicleModelDao.getById(rd.getVehiclemodelId());
			rd.setBrandName("宝马");
			//rd.setLineName(pubVehiclemodel.getPubLine().getFname());
			rd.setBrandId(1L);
			rd.setLineId(pubVehiclemodel.getPubLine().getId());
			rd.setVehiclemodelName(pubVehiclemodel.getFname());
			rDriveDao.save(rd);			
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	
}

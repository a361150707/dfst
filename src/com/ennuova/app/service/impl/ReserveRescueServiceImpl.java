package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.ReserveRescueService;
import com.ennuova.dao.ReserveRescueDao;
import com.ennuova.entity.RescueItem;
import com.ennuova.entity.ReserveRescue;
@Service("reserveRescueService")
public class ReserveRescueServiceImpl implements ReserveRescueService {
	@Resource
	private ReserveRescueDao reserveRescueDao;

	@Transactional(readOnly=true)
	@Override
	public List<ReserveRescue> queryReserveRescue(long cusId) {
		return reserveRescueDao.queryReserveRescue(cusId);
	}

	@Transactional(readOnly=true)
	@Override
	public ReserveRescue getReserveRescue(long reserveRescueId) {
		return reserveRescueDao.getReserveRescue(reserveRescueId);
	}

	@Override
	public int saveReserveRescue(ReserveRescue reserveRescue) {
		int result = 0;
		try {
			reserveRescueDao.save(reserveRescue);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		return result;
	}

	@Override
	public RescueItem getById(long id) {
		return  reserveRescueDao.loadByIdi(id);
	}
	
	
}

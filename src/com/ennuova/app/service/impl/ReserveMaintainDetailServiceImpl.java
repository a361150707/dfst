package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.ReserveMaintainDetailService;
import com.ennuova.dao.ReserveMaintainDetailDao;
@Service("reserveMaintainDetailService")
public class ReserveMaintainDetailServiceImpl implements ReserveMaintainDetailService {
	@Resource
	private ReserveMaintainDetailDao reserveMaintainDetailDao;
}

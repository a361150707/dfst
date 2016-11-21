package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.TSBaseUserService;
import com.ennuova.dao.TSBaseUserDao;
import com.ennuova.entity.TSBaseUser;
@Service("tSBaseUserService")
public class TSBaseUserServiceImpl implements TSBaseUserService {
	@Resource
	private TSBaseUserDao tsBaseUserDao;

	@Override
	public TSBaseUser getTsBaseUser(long carId) {
		return tsBaseUserDao.getTsBaseUser(carId);
	}
}

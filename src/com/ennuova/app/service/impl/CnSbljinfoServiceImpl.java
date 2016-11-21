package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CnSbljinfoService;
import com.ennuova.dao.CnSbljinfoDao;
import com.ennuova.entity.CnSbljinfo;
@Service
public class CnSbljinfoServiceImpl implements CnSbljinfoService {

	@Resource
	private CnSbljinfoDao cnSbljinfoDao;
	
	@Override
	public CnSbljinfo getSbInfo(String fsbxlh) {
		return cnSbljinfoDao.findByFsbxlh(fsbxlh);
	}

}

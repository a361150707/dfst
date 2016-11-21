package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwWyfkService;
import com.ennuova.dao.OwWyfkDao;
import com.ennuova.entity.OwWyfk;

@Service("owWyfkService")
public class OwWyfkSerivceImpl implements OwWyfkService{

	@Resource
	private OwWyfkDao owWyfkDao;
	
	@Override
	public void save(String ffkr, String ftel, String fknr) {
	    OwWyfk fk = new OwWyfk();
	    fk.setFfkr(ffkr);
	    fk.setFknr(fknr);
	    fk.setFtel(ftel);
	    owWyfkDao.save(fk);
	}

}

package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwNewcarindexService;
import com.ennuova.dao.OwNewcarindexDao;
import com.ennuova.entity.OwNewcarindex;

@Service("owNewcarindexService")
public class OwNewcarindexServiceImpl implements OwNewcarindexService {

	@Resource
	private OwNewcarindexDao owNewcarindexDao;
	
	@Override
	public List<OwNewcarindex> queryNewcarindex(long fcompany) {
		List<OwNewcarindex> newcarList= null;
		try {
			newcarList=owNewcarindexDao.queryNewcarIndex(fcompany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newcarList;
	}

	@Override
	public boolean save(OwNewcarindex newcarIndex) {
		boolean result=true;
		try {
			owNewcarindexDao.save(newcarIndex);
		} catch (Exception e) {
			result=false;
			e.printStackTrace();
		}
		return result;
	}

}

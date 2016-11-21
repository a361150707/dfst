package com.ennuova.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwNewcarService;
import com.ennuova.dao.OwNewcarDao;
import com.ennuova.entity.OwNewcar;
import com.ennuova.util.PageBean;

@Service("owNewcarService")
public class OwNewcarServiceImpl implements OwNewcarService {
	
	@Resource
	private OwNewcarDao owNewcarDao;
	
	@Override
	public PageBean queryNewcar(int pageNum, int pageSize, String fstate) {
		return owNewcarDao.queryNewcar(pageNum, pageSize, fstate);
	}

	@Override
	public boolean save(OwNewcar newcar) {
		try {
			owNewcarDao.save(newcar);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<OwNewcar> queryAllByState(long fstate,long fcompanyId) {
		List<OwNewcar> carList=new ArrayList<OwNewcar>();
		try {
			carList=owNewcarDao.queryAllByState(fstate,fcompanyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return carList;
	}

	@Override
	public OwNewcar getNewcarById(long id) {
		OwNewcar newcar=new OwNewcar();
		try {
			newcar=owNewcarDao.getOwNewcarById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newcar;
	}

}

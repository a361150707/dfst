package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwDiscountindexService;
import com.ennuova.dao.OwDiscountindexDao;
import com.ennuova.entity.OwDiscountindex;

@Service("owDiscountindexService")
public class OwDiscountindexServiceImpl implements OwDiscountindexService{

	@Resource
	private OwDiscountindexDao owDiscountindexDao;
	
	@Override
	public List<OwDiscountindex> queryDiscountindex(long fcompany) {
		List<OwDiscountindex> discountindexList= null;
		try {
			discountindexList=owDiscountindexDao.queryDiscountIndex(fcompany);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return discountindexList;
	}

}

package com.ennuova.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwDiscountactionService;
import com.ennuova.dao.OwDiscountactionDao;
import com.ennuova.entity.OwDiscountaction;

@Service("owDiscountService")
public class OwDiscountactionServiceImpl implements OwDiscountactionService{

	@Resource
	private OwDiscountactionDao owDiscountactionDao;
	
	@Override
	public List<OwDiscountaction> queryAllByState(long fstate, long fcompanyId) {
		List<OwDiscountaction> discountList=new ArrayList<OwDiscountaction>();
		try {
			discountList=owDiscountactionDao.queryAllByState(fstate,fcompanyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return discountList;
	}

	@Override
	public OwDiscountaction getDiscountById(long id) {
		OwDiscountaction discount=new OwDiscountaction();
		try {
			discount=owDiscountactionDao.getOwDiscountById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return discount;
	}

}

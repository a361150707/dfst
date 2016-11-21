package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwDiscountindex;

public interface OwDiscountindexDao extends DaoSupport<OwDiscountindex>{

	//获取促销优惠内容
	public List<OwDiscountindex> queryDiscountIndex(long fcompany);
}

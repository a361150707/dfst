package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwDiscountaction;

public interface OwDiscountactionDao extends DaoSupport<OwDiscountaction>{

    public List<OwDiscountaction> queryAllByState(long fstate,long fcompanyId);
	
	public OwDiscountaction getOwDiscountById(long id);
}

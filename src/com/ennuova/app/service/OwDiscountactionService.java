package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.OwDiscountaction;

public interface OwDiscountactionService {

	public List<OwDiscountaction> queryAllByState(long fstate,long fcompanyId);
	public OwDiscountaction getDiscountById(long id);
}

package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.OwNewcarindex;

public interface OwNewcarindexService {

	public List<OwNewcarindex> queryNewcarindex(long fcompany);
	public boolean save(OwNewcarindex newcarIndex);
}

package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwNewsindex;



public interface OwNewsindexDao extends DaoSupport<OwNewsindex>{

	public List<OwNewsindex> queryNewsIndex(long fcompany,long ftype);
	
	
}

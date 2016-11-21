package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwNewcarindex;

public interface OwNewcarindexDao extends DaoSupport<OwNewcarindex> {
	
	//获取新闻首页内容
	public List<OwNewcarindex> queryNewcarIndex(long fcompany);
}

package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.OwNewcar;
import com.ennuova.util.PageBean;

public interface OwNewcarDao extends DaoSupport<OwNewcar> {

	//分页获取新车上市
	public PageBean queryNewcar(int pageNum,int pageSize,String fstate);
	
	//根据状态及门店id获取新车上市
	public List<OwNewcar> queryAllByState(long fstate,long fcompanyId);
	
	
	public OwNewcar getOwNewcarById(long id);
	
}

package com.ennuova.app.service;

import java.util.List;

import com.ennuova.entity.OwNewcar;
import com.ennuova.util.PageBean;

public interface OwNewcarService {

	public PageBean queryNewcar(int pageNum,int pageSize,String fstate);
	public boolean save(OwNewcar newcar);
	public List<OwNewcar> queryAllByState(long fstate,long fcompanyId);
	public OwNewcar getNewcarById(long id);
}

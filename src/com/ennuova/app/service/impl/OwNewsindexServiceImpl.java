package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwNewsindexService;
import com.ennuova.dao.OwNewsindexDao;
import com.ennuova.entity.OwNewsindex;

@Service("owNewsindexService")
public class OwNewsindexServiceImpl implements OwNewsindexService{

	@Resource
	private OwNewsindexDao owNewsindexDao;
	
	@Override
	public List<OwNewsindex> queryNewsindex(long fcompany,long ftype) {
		List<OwNewsindex> newsindexList= null;
		try {
			newsindexList=owNewsindexDao.queryNewsIndex(fcompany,ftype);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsindexList;
	}

}

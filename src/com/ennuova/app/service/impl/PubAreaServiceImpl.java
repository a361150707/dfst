package com.ennuova.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubAreaService;
import com.ennuova.dao.PubAreaDao;
import com.ennuova.entity.PubArea;

@Service("pubAreaService")
public class PubAreaServiceImpl implements PubAreaService {
	
	@Resource
	private PubAreaDao pubAreaDao;

	@Override
	public List<PubArea> queryPubArea(long fid, long fgrade) {
		List<PubArea> areaList=new ArrayList<PubArea>();
		try {
			areaList=pubAreaDao.queryPubArea(fid, fgrade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaList;
	}

	@Override
	public List<PubArea> queryCityByName(String cname) {
		List<PubArea> areaList=new ArrayList<PubArea>();
		try {
			areaList=pubAreaDao.queryCityByName(cname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaList;
	}

}

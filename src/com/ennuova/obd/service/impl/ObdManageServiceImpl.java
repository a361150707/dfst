package com.ennuova.obd.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.dao.PubGjupperDao;
import com.ennuova.entity.PubGjupper;
import com.ennuova.obd.model.Modulelist;
import com.ennuova.obd.service.ObdManageService;


/**
 * obd版本查询和日志上传
 * @author dofastlwj
 */
@Transactional
@Service("obdManageService")
public class ObdManageServiceImpl implements ObdManageService{

	@Resource
	PubGjupperDao pubGjupperDao;
	
	/**
	 * 根据设备获取各个模块信息
	 */
	@Override
	public List<Modulelist> getModulelist(String device_id) {
		List<Modulelist> modulelist=new ArrayList<Modulelist>();
		
		List<PubGjupper> gjuppers=pubGjupperDao.getBySbxlh(device_id);
		for(PubGjupper gjupper:gjuppers){
			Modulelist module=new Modulelist();
			module.setModule_name(gjupper.getModuleName());
			module.setModule_ver(gjupper.getModuleVer());
			module.setUrl(gjupper.getUrl());
			modulelist.add(module);
		}
		return modulelist;
	}

}

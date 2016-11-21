package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.PubBrandService;
import com.ennuova.dao.PubBrandDao;
import com.ennuova.entity.PubBrand;

@Service("pubBrandService")
public class PubBrandServiceImpl implements PubBrandService {

	@Resource
	private PubBrandDao pubBrandDao;
	
	/**
	 * 
	* @Title: PubBrandServiceImpl.java 
	* @Package com.ennuova.app.service.impl 
	* @Description: 获取单个品牌(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	@Transactional(readOnly=true)
	@Override
	public PubBrand getBrand(PubBrand brand) {
		return pubBrandDao.getBrand(brand);
	}

}

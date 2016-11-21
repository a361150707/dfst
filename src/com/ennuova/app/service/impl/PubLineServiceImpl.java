package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.PubLineService;
import com.ennuova.dao.PubLineDao;
import com.ennuova.entity.PubLine;

@Service("pubLineService")
public class PubLineServiceImpl implements PubLineService {

	@Resource
	private PubLineDao pubLineDao;
	
	/**
	 * 
	* @Title: PubLineServiceImpl.java 
	* @Package com.ennuova.app.service.impl 
	* @Description: 获取车系及下属车型列表(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	@Transactional(readOnly=true)
	@Override
	public PubLine queryLine(PubLine line) {
		return pubLineDao.queryLine(line);
	}

}

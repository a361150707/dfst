package com.ennuova.app.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubAppeditionService;
import com.ennuova.dao.PubAppeditonDao;
import com.ennuova.entity.PubAppediton;

/**版本相关服务
 * @author 李智辉
 * @time 2016-2-23
 */
@Service
public class PubAppeditionServiceImpl implements PubAppeditionService{
	@Resource
	private PubAppeditonDao appeditonDao;
	@Override
	public PubAppediton getPubAppediton(String appType) {
		PubAppediton appediton = new PubAppediton();
		try {
			appediton = appeditonDao.getEditonByAppType(appType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appediton;
	}

}

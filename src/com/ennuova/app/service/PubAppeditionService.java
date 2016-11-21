package com.ennuova.app.service;

import com.ennuova.entity.PubAppediton;

/**
 * @author 李智辉
 * @time 2016-2-23
 */
public interface PubAppeditionService {
	/**根据app类型获取最新的版本信息
	 * @param appType
	 * @param edition
	 * @return PubAppediton
	 */
	PubAppediton getPubAppediton(String appType);
}

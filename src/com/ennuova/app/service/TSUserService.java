package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.Cuslocation;

/**
 * 顾问的服务接口
 * @author 陈晓珊
 * @date 2016年10月24日
 *
 */
public interface TSUserService {

	/**
	 * 获取顾问详情
	 * @author 陈晓珊
	 * @date 2016年10月24日
	 * @param loginId
	 * @return
	 */
	Map<String, Object> doDetail(String loginId);
	
}

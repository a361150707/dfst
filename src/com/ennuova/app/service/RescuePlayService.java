package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.RescuePlay;

public interface RescuePlayService {

	/**
	 * 
	* @Title: RescuePlayService.java 
	* @Package com.ennuova.app.service 
	* @Description: 获取救援方案及项目(描述) 
	* @author felix
	* @date 2016年4月22日
	* @version V1.0
	 */
	List<RescuePlay> queryRescuePlay();
	/**
	 * 查看救援的历史详情
	 * @author sududa
	 * @date 2016年7月19日
	 * @param id
	 * @return
	 */
	Map<String, Object> findDetail(String id);
}

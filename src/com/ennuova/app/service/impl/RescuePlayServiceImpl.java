package com.ennuova.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.RescuePlayService;
import com.ennuova.dao.RescuePlayDao;
import com.ennuova.entity.RescuePlay;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Service("rescuePlayService")
public class RescuePlayServiceImpl implements RescuePlayService{

	@Resource
	private RescuePlayDao rescuePlayDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<RescuePlay> queryRescuePlay() {
		return rescuePlayDao.queryRescuePlay();
	}

	/**
	 * 查看救援的历史详情
	 * @author sududa
	 * @date 2016年7月19日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> findDetail(String id) {
		Map<String, Object> resultMap = rescuePlayDao.findDetail(id);
		//加载图片的头部路劲
		String getLineImage = resultMap.get("lineImage")+"";
		if(StringUtil.isNotEmpty(getLineImage)){
			String imgUrl = UrlUtil.getInstance().getImgurl();
			resultMap.put("lineImage", imgUrl + getLineImage);
		}
		return resultMap;
	}
}

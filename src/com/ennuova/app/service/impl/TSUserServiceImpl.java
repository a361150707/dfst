package com.ennuova.app.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.TSUserService;
import com.ennuova.dao.TSBaseUserDao;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Service("tsUserServiceImpl")
public class TSUserServiceImpl implements TSUserService {
	
	@Resource
	TSBaseUserDao tsBaseUserDao;

	/**
	 * 获取顾问详情
	 * @author 陈晓珊
	 * @date 2016年10月24日
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doDetail(String loginId) {
		Map<String, Object> map = tsBaseUserDao.doDetail(loginId);
		String imgUrl = UrlUtil.getInstance().getImgurl();//获取图片的头地址
		if(StringUtil.isNotEmpty(map)){
			if(StringUtil.isNotEmpty(map.get("SIGNATUREFILE"))){//图片加上地址栏的头信息
				map.put("SIGNATUREFILE", imgUrl+map.get("SIGNATUREFILE"));
			}else{
				map.put("SIGNATUREFILE", imgUrl);
			}
			if(StringUtil.isEmpty(map.get("REALNAME"))){
				map.put("REALNAME", "匿名");
			}
			if(StringUtil.isEmpty(map.get("EMAIL"))){
				map.put("EMAIL", "未设置");
			}
			if(StringUtil.isEmpty(map.get("MOBILEPHONE"))){
				map.put("MOBILEPHONE", "未设置");
			}
			if(StringUtil.isEmpty(map.get("OFFICEPHONE"))){
				map.put("OFFICEPHONE", "未设置");
			}
			if(StringUtil.isEmpty(map.get("PRACTITIONERS_YEAR"))){
				map.put("PRACTITIONERS_YEAR", "未知");
			}
			if(StringUtil.isEmpty(map.get("ROLENAME"))){
				map.put("ROLENAME", "未知");
			}
		}
		return map;
	}
	
}

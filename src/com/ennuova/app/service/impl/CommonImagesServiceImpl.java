package com.ennuova.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.CommonImagesService;
import com.ennuova.dao.CommonImagesDao;
import com.ennuova.entity.CommonImages;
import com.ennuova.util.UrlUtil;

@Service("ciService")
public class CommonImagesServiceImpl implements CommonImagesService{

	@Resource
	private CommonImagesDao ciDao;

	@Transactional(readOnly=true)
	@Override
	public List<CommonImages> queryImages(int tp, long tpId) {
		String imgUrl = UrlUtil.getInstance().getImgsurl();
		String imgsUrl = UrlUtil.getInstance().getImgsurl();
		List<CommonImages> imgList = ciDao.queryImages(tp, tpId);
		if(imgList.size()>0){
			for (CommonImages commonImages : imgList) {
				String img = commonImages.getFpath();
				System.out.println(img);
				commonImages.setFpath(imgsUrl+img);
				commonImages.setThpath(imgsUrl+img);
			}
			return imgList;
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getImgNumByLine(int type, Long id) {
		// TODO Auto-generated method stub
		return ciDao.getImgNumByLine(type, id);
	}

	
}

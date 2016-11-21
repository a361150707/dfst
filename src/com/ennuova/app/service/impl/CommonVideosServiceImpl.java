package com.ennuova.app.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CommonVideosService;
import com.ennuova.dao.ClickGoodDao;
import com.ennuova.dao.CommonImagesDao;
import com.ennuova.dao.CommonVideosDao;
import com.ennuova.entity.CommonVideos;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.UrlUtil;
@Service
public class CommonVideosServiceImpl implements CommonVideosService {
	@Resource
	private CommonVideosDao commonVideosDao;
	@Resource
	private ClickGoodDao clickGoodDao;
	@Override
	public CommonVideos queryVideos(int tp, long tpId) {
		CommonVideos commonVideos = new CommonVideos();
		try {
			String imgUrl = UrlUtil.getInstance().getImgurl();
			String imgsUrl = UrlUtil.getInstance().getImgsurl();
			//commonVideos = commonVideosDao.queryVideos(tp, tpId).get(0);
			commonVideos.setFpath(imgUrl+commonVideos.getFpath());
			commonVideos.setFthumbpath(imgsUrl+commonVideos.getFthumbpath());
		} catch (Exception e) {
			return commonVideos;
		}
		
		return commonVideos;
	}
	@Override
	public List<Map<String, Object>> queryVideoList(int tp, long tpId, int page,Long userId) {
		List<Map<String, Object>> commonVideos = commonVideosDao.queryVideos(tp, tpId,page);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Map<String, Object> map : commonVideos) {
			Long clickNum = ConvertTools.stringToLong((map.get("GOOD_COUNT")).toString());
			Long id = ConvertTools.stringToLong((map.get("ID")).toString());
			if(id!=null&&clickNum>0){
				Long isTrue = clickGoodDao.userIsClickGood(id, userId, 2);
				if(isTrue==0){
					map.put("userIsClickGood", false);
				}else{
					map.put("userIsClickGood", true);
				}
			}else{
				map.put("userIsClickGood", false);
			}
			Date date = (Timestamp)map.get("CREATE_TIME");
			if(date!=null){
				map.put("createDateStr", dateFormat.format(date));
			}else{
				map.put("createDateStr", "未知");
			}
			
		}
		return commonVideos;
	}

}

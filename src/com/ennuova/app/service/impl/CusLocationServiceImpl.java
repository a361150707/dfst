package com.ennuova.app.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.CusLocationService;
import com.ennuova.dao.CusLocationDao;
import com.ennuova.dao.PubContactsDao;
import com.ennuova.dao.PubDynamicDao;
import com.ennuova.entity.Cuslocation;
import com.ennuova.util.MapDistanceUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Service("cusLocationServiceImpl")
public class CusLocationServiceImpl implements CusLocationService {
	
	/**
	 * 客户位置的dao
	 */
	@Resource
	private CusLocationDao cusLocationDao;
	
	/**
	 * 动态的dao
	 */
	@Resource
	private PubDynamicDao pubDynamicDao;
	
	/**
	 * 关注 联系人的dao
	 */
	@Resource
	private PubContactsDao pubContactsDao;

	/**
	 * 获取客户的位置
	 *@author sududa
	 *@date 2016年10月18日
	 * @param cuslocation
	 * @return
	 */
	@Override
	public Integer doGetLocation(Cuslocation cuslocation) {
		String cusId = cuslocation.getCusId()+"";
		if(StringUtil.isNotEmpty(cusId)){
			Map<String, Object> cusLocationMap = cusLocationDao.findByCusId(cusId);
			if(StringUtil.isNotEmpty(cusLocationMap)){//表示已经插入了，不能重复插入到数据库
				Integer resultInt = cusLocationDao.updateByCusId(cuslocation);
				return resultInt;
			}
			Cuslocation resultEntity = cusLocationDao.save(cuslocation);
			if(StringUtil.isNotEmpty(resultEntity)){//表示插入成功
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 根据客户id清除位置信息
	 *@author sududa
	 *@date 2016年10月19日
	 * @param cusId
	 * @return
	 */
	@Override
	public Integer doCleanLocation(String cusId) {
		Map<String, Object> cusLocationMap = cusLocationDao.findByCusId(cusId);
		if(StringUtil.isNotEmpty(cusLocationMap)){//表示还没有清除
			Integer resultInt = cusLocationDao.deleteByCusId(cusId);
			return resultInt;
		}else{//表示已经清除了
			return 1;
		}
	}

	/**
	 * 附近的人列表
	 *@author sududa
	 *@date 2016年10月19日
	 * @param page 当前页(默认值为1)
	 * @param rows 每页显示的条数(默认值为10)
	 * @param interest 兴趣
	 * @param authentication 认证的车主
	 * @param loginId 当前登录人的id
	 * @param lat 自己的经度
	 * @param lng 自己的纬度
	 * @param sex 性别
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doList(Integer page, Integer rows, String interest, String authentication, String loginId, String lng, String lat, String sex) {
		String loginType = "1";//登录的类型1-车主,2-员工
		List<Map<String, Object>> followList = pubContactsDao.findFollowByLoginIdAndType(loginId,loginType);
		List<Map<String, Object>> resultList = cusLocationDao.doList(page,rows,interest,authentication,loginId,sex);
		if(StringUtil.isNotEmpty(resultList) && resultList.size() > 0){
			String imgUrl = UrlUtil.getInstance().getImgurl();//获取图片的头地址
			double d;
			DecimalFormat df = new DecimalFormat("0.0");
			if(StringUtil.isNotEmpty(followList) && followList.size() > 0){//表示有关注了
				for (Map<String, Object> map : resultList) {
					String getLng = map.get("LNG")+"";
					String getLat = map.get("LAT")+"";
					if(StringUtil.isnotObjectsNotEmpty(getLng,getLat,lng,lat)){
						d = MapDistanceUtil.getDistance(Double.valueOf(lng), Double.valueOf(lat), Double.valueOf(getLng), Double.valueOf(getLat));
						map.put("DISTANCE", df.format(d));//加上距离
					}else{
						map.put("DISTANCE", "");//加上距离
					}
					String temp = "";
					for(Map<String, Object> followMap : followList){
						if((map.get("ID")+"").equals((followMap.get("followId")+""))){
							temp = followMap.get("followId")+"";
						}
					}
					if(StringUtil.isNotEmpty(temp)){
						map.put("ISFOLLOW", "1");
					}else{
						map.put("ISFOLLOW", "");
					}
//					System.out.println("======================"+followList.size());
				}
			}else{//表示都没有关注过人
				for (Map<String, Object> map : resultList) {
					String getLng = map.get("LNG")+"";
					String getLat = map.get("LAT")+"";
					if(StringUtil.isnotObjectsNotEmpty(getLng,getLat,lng,lat)){
						d = MapDistanceUtil.getDistance(Double.valueOf(lng), Double.valueOf(lat), Double.valueOf(getLng), Double.valueOf(getLat));
						System.out.println("=========="+d);
						map.put("DISTANCE", df.format(d));//加上距离
					}else{
						map.put("DISTANCE", "");//加上距离
					}
					map.put("ISFOLLOW", "");
				}
			}
		}
//		MapDistanceUtil.sortList(resultList);
		return resultList;
	}


}

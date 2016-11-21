package com.ennuova.app.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ennuova.app.service.PubDynamicService;
import com.ennuova.dao.ClickGoodDao;
import com.ennuova.dao.CnSsclxxDao;
import com.ennuova.dao.CnXcrecodeDao;
import com.ennuova.dao.PubBoxDao;
import com.ennuova.dao.PubContactsDao;
import com.ennuova.dao.PubDynamicDao;
import com.ennuova.entity.ClickGoodEntity;
import com.ennuova.entity.PubDynamic;
import com.ennuova.tools.weather.WeatherQuery;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;
import com.google.gson.JsonObject;

@Service
public class PubDynamicServiceImpl implements PubDynamicService {
	
	/**
	 * 动态的dao
	 */
	@Resource
	private PubDynamicDao pubDynamicDao;
	
	/**
	 * 车辆实时信息dao
	 */
	@Resource
	private CnSsclxxDao cnSsclxxDao;
	
	/**
	 * 联系人的dao
	 */
	@Resource
	private PubContactsDao pubContactsDao;
	
	/**
	 * 盒子的dao
	 */
	@Resource
	private PubBoxDao pubBoxDao;
	
	/**
	 * 行程dao
	 */
	@Resource
	private CnXcrecodeDao cnXcrecodeDao;
	
	/**
	 * 点赞的dao
	 */
	@Resource
	private ClickGoodDao clickGoodDao;

	/**
	 * 发布动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param pubDynamic
	 * @return
	 */
	@Override
	public PubDynamic doSendDynamic(PubDynamic pubDynamic) {
		return pubDynamicDao.doSendDynamic(pubDynamic);
	}

	/**
	 * 删除动态
	 *@author sududa
	 *@date 2016年10月19日
	 * @param delfLag 
	 * @param id
	 * @param sendId
	 * @return
	 */
	@Override
	public Integer doDelete(String delfLag,String id, String sendId) {
		return pubDynamicDao.doDelete(delfLag,id,sendId);
	}

	/**
	 * 附近的人详情，即查询该人的动态及有车绑定盒子的里程数和油耗
	 *@author sududa
	 *@date 2016年10月25日
	 * @param loginId 登录人的id
	 * @param id 附近人的列表的主键id
	 * @param page 当前页
	 * @param rows 每页显示的条数
	 * @return
	 */
	@Override
	public Map<String, Object> doNearbyDetail(String loginId, String id, Integer page, Integer rows) {
		Map<String, Object> resultMap = cnSsclxxDao.findFirstOneByCusId(id);
		List<Map<String, Object>> timeList = pubDynamicDao.findTimeListById(id,page,rows);
		if(StringUtil.isNotEmpty(timeList) && timeList.size() > 0){
			for (Map<String, Object> map : timeList) {
				String sendDate = map.get("sendDate")+"";
				if(StringUtil.isNotEmpty(sendDate)){
					List<Map<String, Object>> pubDynamicList = pubDynamicDao.findBySendIdAndSendDate(id,sendDate);
					map.put("pubDynamicList", pubDynamicList);
				}
			}
		}
		resultMap.put("resultList", timeList);
		return resultMap;
	}
	


	/**
	 * 根据登录的id查询自己,自己的好友,自己关注的,员工发送的动态列表,带分页
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findByIdWithPage(Integer page, Integer rows, String loginId) {
		List<Map<String, Object>> resultList = pubDynamicDao.findByIdWithPage(page,rows,loginId);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		if(StringUtil.isNotEmpty(resultList) && resultList.size()>0){
			for (Map<String, Object> map : resultList) {
				String imgPath = map.get("imgPath")+"";
				if(StringUtil.isNotEmpty(imgPath)){
					JSONArray jsonArray = JSON.parseArray(imgPath);
					for(int i=0;i<jsonArray.size();i++){
						jsonArray.set(i, imgUrl+jsonArray.getString(i));
					}
					map.put("IMGPATH", jsonArray);
				}
				String dyID = map.get("ID")+"";
				//点赞人数
//				Map<String, Object> goodCountMap = clickGoodDao.doClickGoodCount(dyID,"3");
//				map.put("CLICKGOODCOUNT", goodCountMap.get("clickGoodCount"));
				//点赞人的列表
				List<Map<String, Object>> goodList = clickGoodDao.getClickGoodPhoto(dyID,"3");
				map.put("CLICKGOODLIST", goodList);
				//登陆人是否点赞了这条动态
			    String isClickGood = map.get("isClickGood")+"";
				if(StringUtil.isNotEmpty(isClickGood)){
					map.put("ISCLICKGOOD", "1");
				}else{
					  map.put("ISCLICKGOOD", "");
				}
				
				//是否关注此人
				String isFollow = map.get("ISFOLLOW")+"";
				if(StringUtil.isNotEmpty(isFollow)){
					map.put("ISFOLLOW", "1");
				}else{
					map.put("ISFOLLOW", "");
				}
				
				String sendType = map.get("sendType")+"";
				String cusID =  map.get("sendId")+"";
				if(sendType.equals("1")){
					Integer sendId = Integer.valueOf(cusID);
					map.put("SENDID", sendId);
				}
			}
		}
		return resultList;
	}

	/**
	 * 查询话题列表
	 * @author 陈晓珊
	 * @date 2016年10月27日
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doTopicList(String title,Integer page, Integer rows) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		List<Map<String, Object>> resultList = pubDynamicDao.doTopicList(title,page,rows);
		for(Map<String, Object> map : resultList){
			if(StringUtil.isNotEmpty(map.get("IMGPATH"))){
				String imgPath = map.get("IMGPATH")+"";
				com.alibaba.fastjson.JSONArray imgArr = JSON.parseArray(imgPath);
				for(int i = 0;i < imgArr.size();i++){
					imgArr.set(i,imgUrl + imgArr.get(i));
				}
				map.put("IMGPATH", imgArr);
			}
		}
		return resultList;
	}

	/**
	 * 查询个人的动态列表
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doPersonalList(Integer page, Integer rows, String loginId) {
		List<Map<String, Object>> timeList = pubDynamicDao.findTimeListById(loginId,page,rows);
		if(StringUtil.isNotEmpty(timeList) && timeList.size() > 0){
			String imgUrl = UrlUtil.getInstance().getImgurl();
			for (Map<String, Object> map : timeList) {
				String sendDate = map.get("sendDate")+"";
				if(StringUtil.isNotEmpty(sendDate)){
					List<Map<String, Object>> pubDynamicList = pubDynamicDao.findBySendIdAndSendDate(loginId,sendDate);
					if(StringUtil.isNotEmpty(pubDynamicList) && pubDynamicList.size()>0){
						for (Map<String, Object> dynamicMap : pubDynamicList) {
							String imgPath = dynamicMap.get("imgPath")+"";
							JSONArray jsonArray = JSON.parseArray(imgPath);
							for(int i=0;i<jsonArray.size();i++){
								jsonArray.set(i, imgUrl+jsonArray.get(i));
							}
							dynamicMap.put("IMGPATH", jsonArray);
						}
					}
					map.put("pubDynamicList", pubDynamicList);
				}
			}
		}
		return timeList;
	}

	/**
	 * 查询话题详情列表
	 *@author 陈晓珊
	 *@date 2016年10月28日
	 * @param page
	 * @param rows
	 * @param loginId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doTopicDetilList(String loginId,String id, Integer page,
			Integer rows) {
		List<Map<String, Object>> resultList = pubDynamicDao.doTopicDetilList(loginId,id,page,rows);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		if(StringUtil.isNotEmpty(resultList) && resultList.size()>0){
			for (Map<String, Object> map : resultList) {
				String imgPath = map.get("imgPath")+"";
				if(StringUtil.isNotEmpty(imgPath)){
					JSONArray jsonArray = JSON.parseArray(imgPath);
					for(int i=0;i<jsonArray.size();i++){
						jsonArray.set(i, imgUrl+jsonArray.getString(i));
					}
					map.put("IMGPATH", jsonArray);
				}
				String dyID = map.get("ID")+"";
				//点赞人的列表
				List<Map<String, Object>> goodList = clickGoodDao.getClickGoodPhoto(dyID,"3");
				map.put("CLICKGOODLIST", goodList);
				//登陆人是否点赞了这条动态
				if(StringUtil.isNotEmpty(map.get("isClickGood"))){
					map.put("ISCLICKGOOD", "1");
				}else{
					map.put("ISCLICKGOOD", "");
				}
				//是否关注此人
				if(StringUtil.isNotEmpty(map.get("ISFOLLOW"))){
					map.put("ISFOLLOW", "1");
				}else{
					map.put("ISFOLLOW", "");
				}
				String cusID =  map.get("sendId")+"";
				String sendType = map.get("sendType")+"";
				if(sendType.equals("1")){
					Integer sendId = Integer.valueOf(cusID);
					map.put("SENDID", sendId);
				}
			}
		}
		return resultList;
	}

	/**
	 * 查询个人的盒子激活时间,累计里程，累计油耗
	 * @param loginId
	 * @return
	 */
	@Override
	public Map<String, Object> doPersonalOne(String loginId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> ssclxxMap = cnSsclxxDao.findFirstOneByCusId(loginId);
		String totalMileage = "--";
		String totalOil = "--";
		if(StringUtil.isNotEmpty(ssclxxMap)){
			totalMileage = ssclxxMap.get("totalMileage")+"";
			totalOil = ssclxxMap.get("totalOil")+"";
		}
		resultMap.put("TOTALMILEAGE", totalMileage);
		resultMap.put("TOTALOIL", totalOil);
		Integer page = 1;
		Integer rows = 10;
		String averageOil = "--";
		List<Map<String, Object>> averageTripList = cnXcrecodeDao.findByCusIdWithPage(page,rows,loginId);
		if(StringUtil.isNotEmpty(averageTripList) && averageTripList.size() > 0){
			double allOil = 0.0;
			for (Map<String, Object> map : averageTripList) {
				String bcxslc = map.get("FBCXSLC")+"";//本次行驶里程
				String bcxsyh = map.get("FBCXSYH")+"";//本次行驶油耗
				allOil +=(Double.valueOf(bcxsyh)*100)/Double.valueOf(bcxslc);
			}
			DecimalFormat df = new DecimalFormat(".##");
			averageOil = df.format(allOil/averageTripList.size())+"";
		}
		resultMap.put("AVERAGEOIL", averageOil);
		String jhTime = "--";
		Map<String, Object> boxMap = pubBoxDao.findByCusId(loginId);
		if(StringUtil.isNotEmpty(boxMap)){
			jhTime = boxMap.get("FJHTIME")+"";
		}
		resultMap.put("FJHTIME", jhTime);
		
		return resultMap;
	}

	/**
	 * 个人动态详情
	 *@author 陈晓珊
	 *@date 2016年11月1日
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> doDyDetilList(String loginId,String id) {
		Map<String, Object> map = pubDynamicDao.doDyDetilList(loginId,id);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String imgPath = map.get("imgPath")+"";
		if(StringUtil.isNotEmpty(imgPath)){
			JSONArray jsonArray = JSON.parseArray(imgPath);
			for(int i=0;i<jsonArray.size();i++){
				jsonArray.set(i, imgUrl+jsonArray.getString(i));
			}
			map.put("IMGPATH", jsonArray);
		}
		String dyID = map.get("ID")+"";
		//点赞人的列表
		List<Map<String, Object>> goodList = clickGoodDao.getClickGoodPhoto(dyID,"3");
		map.put("CLICKGOODLIST", goodList);
		//登陆人是否点赞了这条动态
		if(StringUtil.isNotEmpty(map.get("isClickGood"))){
			map.put("ISCLICKGOOD", "1");
		}else{
			map.put("ISCLICKGOOD", "");
		}
		//是否关注此人
		if(StringUtil.isNotEmpty(map.get("ISFOLLOW"))){
			map.put("ISFOLLOW", "1");
		}else{
			map.put("ISFOLLOW", "");
		}
		String cusID =  map.get("sendId")+"";
		String sendType = map.get("sendType")+"";
		if(sendType.equals("1")){
			Integer sendId = Integer.valueOf(cusID);
			map.put("SENDID", sendId);
		}
	
		return map;
	}

	/**
	 * 点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param clickGood
	 * @return
	 */
	@Override
	public ClickGoodEntity doClickGood(ClickGoodEntity clickGood) {
		
		return clickGoodDao.doClickGood(clickGood);
	}

	/**
	 * 取消点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param fromId
	 * @param toId
	 * @param goodType
	 * @return
	 */
	@Override
	public Integer cancelClickGood(String fromId, String toId, String goodType) {
		return clickGoodDao.cancelClickGood(fromId,toId,goodType);
	}
	
	/**
	 * 根据登陆ID，查找是否点赞过某条动态
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doIsClickGood(String toId, String fromId,
			String goodType) {
		return clickGoodDao.isClickGood(toId, fromId, goodType);
	}

}

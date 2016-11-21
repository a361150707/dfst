package com.ennuova.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.OwNewsctService;
import com.ennuova.dao.ClickGoodDao;
import com.ennuova.dao.CommonImagesDao;
import com.ennuova.dao.CommonVideosDao;
import com.ennuova.dao.OwNewsctDao;
import com.ennuova.dao.PubCommentDao;
import com.ennuova.entity.ClickGoodEntity;
import com.ennuova.entity.OwNewsct;
import com.ennuova.util.HtmlUtil;
import com.ennuova.util.StringUtil;

@Service("owNewsctService")
public class OwNewsctServiceImpl implements OwNewsctService{

	@Resource
	private OwNewsctDao owNewsctDao;
	@Resource
	private ClickGoodDao clickGoodDao;
	@Resource
	private PubCommentDao commentDao;
	@Resource
	private CommonImagesDao imagesDao;
	@Resource
	private CommonVideosDao videosDao;
	
	@Override
	public List<OwNewsct> queryPageByState(long fstate, String fcompany,long ftype,int page,int size) {
		List<OwNewsct> newsctList=new ArrayList<OwNewsct>();
		int startIndex = page*size;//0 2 4 6
		int stopIndex = page*size+size;//2 4 6 8
		try {
			newsctList=owNewsctDao.queryPageByState(fstate,fcompany,ftype,startIndex,stopIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (OwNewsct owNewsct : newsctList) {
			owNewsct.setFnrText(HtmlUtil.getTextFromHtml(owNewsct.getFnrText()));
		}
		return newsctList;
	}

	@Override
	public OwNewsct getNewsById(long id) {
		OwNewsct newsct=new OwNewsct();
		try {
			newsct=owNewsctDao.getOwNewsById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsct;
	}

	@Override
	public int growthFviewCount(long id, String tableName) {
		int result = 0;
		try {
			result = owNewsctDao.growthFviewCount(id, tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @author sududa
	 * @date 2016-07-29
	 * @param type
	 * @param keyword
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findListRelation(String type, String keyword) {
		return owNewsctDao.findListRelation(type,keyword);
	}

	/**
	 * @author sududa
	 * @date 2016-07-29
	 * @param newsId
	 * @return
	 */
	@Override
	public Map<String, Object> findDetailAndRelation(String newsId,Long userId) {
		int isClickGood = 0;
		System.out.println("进入新闻详情");
		if(userId>0){
			Long id = clickGoodDao.userIsClickGood(Long.valueOf(newsId), userId, 0);
			if(id>0){
				isClickGood = 1;
			}
		}
		Map<String, Object> resultMap = owNewsctDao.findDetailById(newsId);
		System.out.println(resultMap);
		try {
			
			resultMap.put("isClickGood", isClickGood);
			List<Map<String, Object>> allNewsList = owNewsctDao.findAllNews();
			List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
			if(StringUtil.isNotEmpty(resultMap)){
				String keyword = resultMap.get("keyWord")+"";
				if(StringUtil.isNotEmpty(keyword)){
					String[] strKeyword = keyword.split(",");
					if(StringUtil.isNotEmpty(allNewsList) && allNewsList.size() > 0){
						for(Map<String, Object> map : allNewsList){
							String getKeyWords = map.get("keyWord")+"";//得到关键字
							if(StringUtil.isNotEmpty(getKeyWords)){
								for(int i=0;i<strKeyword.length;i++){
									if(getKeyWords.contains(strKeyword[i])){
										resultList.add(map);
										continue;
									}
								}
							}
						}
					}
				}
				resultMap.put("resultList", resultList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(resultMap);
		return resultMap;
	}

	@Override
	public boolean clickGood(int type, Long fromId, Long userId) {
		ClickGoodEntity clickGoodEntity = new ClickGoodEntity();
		try {
			Long userIsClickGood = clickGoodDao.userIsClickGood(fromId, userId, type);
			if(userIsClickGood==0L){
				clickGoodEntity.setFromId(fromId);
				clickGoodEntity.setGoodType(type+"");
				clickGoodEntity.setToId(userId);
				clickGoodEntity.setCreateDate(new Date());
				clickGoodDao.save(clickGoodEntity);
				if(type==0){
					owNewsctDao.updateCommentOrGoodOrShareCount(1, fromId,1); //更新新闻点赞数
				}else if(type==1){
					commentDao.updateReplyCount(1, fromId,1);	//更新评论点赞数
				}
				else if(type==2){
					videosDao.updateVideoGoodCount(fromId, 1);
				}
			}else{
				clickGoodDao.delete(userIsClickGood);
				if(type==0){
					owNewsctDao.updateCommentOrGoodOrShareCount(1, fromId,-1); //更新新闻点赞数
				}else if(type==1){
					commentDao.updateReplyCount(1, fromId,-1);	//更新评论点赞数
				}
				else if(type==2){
					videosDao.updateVideoGoodCount(fromId, -1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> getCarOtherInfo(int type, Long typeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long newNum = owNewsctDao.getNewCountByKey(type, typeId);
		Long videoNum = videosDao.getVideoCountByLid(type,typeId);
		Long imgNum = imagesDao.getImgNum(type, typeId);
		if(type==1){
			Long newDetailNum = owNewsctDao.getNewCountByLid(typeId);
			map.put("newDetailNum",newDetailNum);
		}
		map.put("newNum",newNum);
		map.put("videoNum",videoNum);
		map.put("imgNum",imgNum);
		return map;
	}

	@Override
	public List<OwNewsct> getNewsByLineIdOrVId(int type, Long typeId, int page,
			int size, int isDetatil) {
		List<OwNewsct> newsctList=new ArrayList<OwNewsct>();
		int startIndex = page*size;//0 2 4 6
		int stopIndex = page*size+size;//2 4 6 8
		try {
			newsctList=owNewsctDao.getNewsByLineIdOrVId(type, typeId, isDetatil, startIndex, stopIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (OwNewsct owNewsct : newsctList) {
			owNewsct.setFnrText(HtmlUtil.getTextFromHtml(owNewsct.getFnrText()));
		}
		return newsctList;
	}

	@Override
	public List<OwNewsct> getTopInfo() {
		return owNewsctDao.getTopInfo();
	}

	
}

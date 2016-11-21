package com.ennuova.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubCommentService;
import com.ennuova.dao.ClickGoodDao;
import com.ennuova.dao.OwNewsctDao;
import com.ennuova.dao.PubCommentDao;
import com.ennuova.dao.PubCustomerDao;
import com.ennuova.dao.PubReplyDao;
import com.ennuova.entity.PubCommentEntity;
import com.ennuova.entity.PubCustomer;
import com.ennuova.entity.PubReplyEntity;
import com.ennuova.util.UrlUtil;

/**评论相关服务实现
 * @author 李智辉
 * @time 2016-7-27
 */
@Service
public class PubCommentServiceImpl implements PubCommentService {
	@Resource
	private PubCommentDao pubCommentDao;
	@Resource
	private OwNewsctDao owNewsctDao;
	@Resource
	private PubReplyDao pubReplyDao;
	@Resource
	private ClickGoodDao clickGoodDao;
	@Resource
	private PubCustomerDao customerDao;
	@Override
	public boolean commentNews(Long fromId, Long userId, String content) {
		String userImg = "";
		String userName = "";
		try {
			PubCustomer customer = customerDao.getById(userId);
			userName = customer.getFusername();
			if(customer.getFphoto()!=null&&customer.getFphoto()!=""){
				String imgUrl =  UrlUtil.getInstance().getImgurl();
				userImg = imgUrl+customer.getFphoto();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PubCommentEntity entity = new PubCommentEntity();
		entity.setFromId(fromId);
		entity.setToId(userId);
		entity.setToName(userName);
		entity.setToImg(userImg);
		entity.setContent(content);
		entity.setCreateDate(new Date());
		entity.setGoodCount(0L);
		entity.setReplyCount(0L);
		try {
			entity = pubCommentDao.save(entity);	//保存评论
			owNewsctDao.updateCommentOrGoodOrShareCount(0, fromId,1);		//更新新闻评论数
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(entity.getId()!=null){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<Map<String, Object>> getCommentListByFromId(Long fromId,
			int pageNum, int size,Long userId) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Long> commentIdList = new ArrayList<Long>();
		List<Map<String, Object>> list = pubCommentDao.getCommentListByNewId(fromId, size, pageNum);
		String dateStr = "";
		Date nowDate = new Date();
		for (Map<String, Object> map : list) {
			map.put("isClickGood",0);
			commentIdList.add(Long.valueOf(map.get("ID").toString()));
			Date date = (Date) map.get("CREATE_DATE");
			if((nowDate.getTime()-date.getTime())<1000*60*5){
				dateStr = "刚刚";
			}else if((nowDate.getTime()-date.getTime())>1000*60*5&&(nowDate.getTime()-date.getTime())<1000*60*60){
				dateStr = "一小时内";
			}else if((nowDate.getTime()-date.getTime())>1000*60*60&&(nowDate.getTime()-date.getTime())<1000*60*60*24){
				dateStr = "今天";
			}else{
				dateStr = dateFormat.format(date);
			}
			map.put("DATE_STR", dateStr);
			map.put("DATE_STR", dateFormat.format(date));
		}
		if(commentIdList.size()>0){
			if(userId!=0){
				List<Long> clickGoodList = clickGoodDao.isClickGood(commentIdList, userId, 1);	//获取本次查询的新闻，该用户是否点赞。
				for (Long clickGoodId : clickGoodList) {
					for (Map<String, Object> map : list) {
						if(clickGoodId.toString().equals(map.get("ID").toString())){
							map.put("isClickGood",1);
							break;
						}
					}
				}
			}
			Map<String, List<PubReplyEntity>> PubReplyEntityCage = pubReplyDao.getReplyMapByIdList(commentIdList);
			for (String key : PubReplyEntityCage.keySet()) {
				for (Map<String, Object> map : list) {
					if(key.equals(map.get("ID").toString())){
						map.put("PUBREPLYS",PubReplyEntityCage.get(key));
						break;
					}
				}
			}
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> getReplyListByCommentId(Long fromId,
			int size, int pageNum) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		
		String dateStr = "";
		try {
			
			maps = pubReplyDao.getReplyListByCommentId(fromId, size, pageNum);
			for (Map<String, Object> map : maps) {
				Date date = (Date) map.get("CREATE_DATE");
				if((nowDate.getTime()-date.getTime())<1000*60*5){
					dateStr = "刚刚";
				}else if((nowDate.getTime()-date.getTime())>1000*60*5&&(nowDate.getTime()-date.getTime())<1000*60*60){
					dateStr = "一小时内";
				}else if((nowDate.getTime()-date.getTime())>1000*60*60&&(nowDate.getTime()-date.getTime())<1000*60*60*24){
					dateStr = "今天";
				}else{
					dateStr = dateFormat.format(date);
				}
				map.put("DATE_STR", dateStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return maps;
		}
		return  maps;
	}
	@Override
	public boolean replyComment(Long fromId, Long userId, String userName,
			String content) {
		PubReplyEntity entity = new PubReplyEntity();
		entity.setFromId(fromId);
		entity.setToId(userId);
		entity.setToName(userName);
		entity.setContent(content);
		entity.setCreateDate(new Date());
		try {
			entity = pubReplyDao.save(entity);	//保存回复
			pubCommentDao.updateReplyCount(0, fromId,1);		//更新评论回复数
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(entity.getId()!=null){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public PubCommentEntity getCommentEntityById(Long cId,Long userId) {
		int isClickGood = 0;
		if(userId>0){
			Long id = clickGoodDao.userIsClickGood(cId, userId, 1);
			if(id>0){
				isClickGood = 1;
			}
		}
		
		PubCommentEntity commentEntity = pubCommentDao.getById(cId);
		commentEntity.setIsClickGood(isClickGood);
		return commentEntity;
	}

}

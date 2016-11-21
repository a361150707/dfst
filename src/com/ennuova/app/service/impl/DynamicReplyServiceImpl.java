package com.ennuova.app.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.DynamicReplyService;
import com.ennuova.dao.ClickGoodDao;
import com.ennuova.dao.DynamicCommentDao;
import com.ennuova.dao.DynamicReplyDao;
import com.ennuova.dao.PubContactsDao;
import com.ennuova.entity.DynamicCommentEntity;
import com.ennuova.entity.DynamicReply;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

@Service("dynamicReplyServiceImpl")
public class DynamicReplyServiceImpl implements DynamicReplyService{
	
	/**
	 * 评论回复的dao
	 */
	@Resource
	private DynamicReplyDao dynamicReplyDao;
	
	/**
	 * 动态评论的dao
	 */
	@Resource
	DynamicCommentDao dynamicCommentDao;
	
	/**
	 * 点赞的dao
	 */
	@Resource
	private ClickGoodDao clickGoodDao;
	
	/**
	 * 联系人的Dao
	 */
	@Resource
	private PubContactsDao pubContactsDao;
	
	/**
	 *回复评论
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param dynamicReply
	 * @return
	 */
	@Override
	public Integer doSendDynamicReply(DynamicReply dynamicReply) {
		DynamicReply resultEntity = dynamicReplyDao.save(dynamicReply);
		if(StringUtil.isNotEmpty(resultEntity)){//表示插入成功
			return 1;
		}
		return 0;
	}

	/**
	 * 删除评论
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param delfLag
	 * @param id
	 * @return
	 */
	@Override
	public Integer doDelete(String delfLag, String id) {
		return dynamicCommentDao.doDelete(delfLag,id);
	}


	/**
	 * 获取动态的评论列表
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param dynamic_id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doCommentList(String dynamic_id,Integer page,
			Integer rows) {
		List<Map<String, Object>> commentList = dynamicCommentDao.doCommentList(dynamic_id,page,rows);
		for(Map<String, Object> map : commentList){
			String commentId = map.get("ID")+"";
			List<Map<String, Object>> replytList = dynamicReplyDao.doReplyList(commentId);
			map.put("REPLYLIST", replytList);
		}
		return commentList;
	}

	/**
	 * 获取评论的数目
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doCommentCount(String id) {
		return dynamicCommentDao.doCommentCount(id);
	}
	
	/**
	 * 获取点赞的人的头像
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getClickGoodPhoto(String id,String type) {
		List<Map<String, Object>> clickGoodList = clickGoodDao.getClickGoodPhoto(id,type);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		return clickGoodList;
	}

	/**
	 * 获取点赞的人数
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doClickGoodCount(String id,String type) {
		return clickGoodDao.doClickGoodCount(id,type);
	}

	/**
	 * 获取点赞的人的列表
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doClickGoodList(String loginId,String id,String type) {
		List<Map<String, Object>> clickGoodList = clickGoodDao.doClickGoodList(id,type);
		List<Map<String, Object>>  followList = pubContactsDao.queryFollowList(loginId);
		//点赞的人中是否有我关注的人
		for(Map<String, Object> map : clickGoodList){
			String cusID =  map.get("ID")+"";
			map.put("ISFOLLOW", "");
			for(int j = 0;j < followList.size();j++){
				String contactsID = followList.get(j).get("CONTACTS_ID")+"";
				if(contactsID.equals(cusID)){
					map.put("ISFOLLOW", "1");
					break;
				}
			}
		}
		return clickGoodList;
	}

	/**
	 * 发布评论
	 * @author 陈晓珊
	 *@date 2016年11月2日
	 * @param dynamIicComment
	 * @return
	 */
	@Override
	public Integer doSendDynamicComment(DynamicCommentEntity dynamIicComment) {
		DynamicCommentEntity resultEntity = dynamicCommentDao.save(dynamIicComment);
		if(StringUtil.isNotEmpty(resultEntity)){//表示插入成功
			return 1;
		}
		return 0;
	}

}

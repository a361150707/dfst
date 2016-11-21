package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.ClickGoodEntity;

/**点赞数据操作层
 * @author 李智辉
 * @time 2016-7-27
 */
public interface ClickGoodDao extends DaoSupport<ClickGoodEntity> {
	/**根据评论ID列表获取该用户是否点赞
	 * @param idLists
	 * @param userId
	 * @param type 点赞的类型(0_新闻,1_评论)
	 * @return Map<String, Integer>
	 */
	List<Long> isClickGood(List<Long> idLists,Long userId,int type);
	/**根据评论ID获取该用户是否点赞
	 * @param id
	 * @param userId
	 * @param type type 点赞的类型(0_新闻,1_评论)
	 * @return Long 点赞主键ID
	 */
	Long userIsClickGood(Long id,Long userId,int type);
	
	/**
	 * 根据动态ID，获取点赞人的头像
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getClickGoodPhoto(String id,String type);
	
	/**
	 * 根据动态ID，获取点赞的人数
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	Map<String, Object> doClickGoodCount(String id,String type);
	
	
	/**
	 * 根据动态ID，获取点赞人的列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> doClickGoodList(String id,String type);
	
	/**
	 * 根据登陆ID，查找是否点赞过某条动态
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	Map<String, Object> isClickGood(String loginId,String id,String type);
	
	/**
	 * 点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param clickGood
	 * @return
	 */
	ClickGoodEntity doClickGood(ClickGoodEntity clickGood);
	
	/**
	 * 取消点赞
	 * @author 陈晓珊
	 * @date 2016年11月1日
	 * @param fromId
	 * @param toId
	 * @param goodType
	 * @return
	 */
	Integer cancelClickGood(String fromId, String toId, String goodType);
}

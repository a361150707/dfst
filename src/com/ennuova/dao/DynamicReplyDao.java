package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.DynamicReply;
/**
 * 评论回复的dao
 * @author 陈晓珊
 * @date 2016年10月21日
 *
 */
@SuppressWarnings("rawtypes")
public interface DynamicReplyDao extends DaoSupport<DynamicReply>{

/*	*//**
	 * 删除动态评论
	 * @param delfLag
	 * @param id
	 * @return
	 *//*
	Integer doDelete(String delfLag, String id);

	*//**
	 * 获取动态评论列表
	 * @param dynamic_id
	 * @return
	 *//*
	List<Map<String, Object>> doCommentList(String dynamic_id,Integer page,
			Integer rows);

	*//**
	 * 根据动态ID，获取动态评论数目
	 *@author 陈晓珊
	 *@date 2016年10月25日
	 * @param id
	 * @return
	 *//*
	Map<String, Object> doCommentCount(String id);*/

	/**
	 * 根据动态ID和评论人ID，获取评论的回复列表
	 * @param dynamic_id
	 * @param cusId
	 * @return
	 */
	List<Map<String, Object>> doReplyList(String commentId);
	
	
}

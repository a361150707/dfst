package com.ennuova.app.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ennuova.entity.DynamicCommentEntity;
import com.ennuova.entity.DynamicReply;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
/**
 * 动态的评论服务接口
 * @author 陈晓珊
 * @date 2016年10月21日
 *
 */
public interface DynamicReplyService {

	/**
	 *回复评论
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param dynamicReply
	 * @return
	 */
	Integer doSendDynamicReply(DynamicReply dynamicReply);

	/**
	 * 删除评论
	 *@author 陈晓珊
	 *@date 2016年10月21日
	 * @param delfLag
	 * @param id
	 * @return
	 */
	Integer doDelete(String delfLag, String id);
	

	/**
	 * 获取动态的评论列表
	 * @author 陈晓珊
	 * @date 2016年10月21日
	 * @param dynamic_id
	 * @return
	 */
	List<Map<String, Object>> doCommentList(String dynamic_id,Integer page,
			Integer rows);

	/**
	 * 获取评论的数目
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	Map<String, Object> doCommentCount(String id);

	/**
	 * 获取点赞的人的头像
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> getClickGoodPhoto(String id,String type);

	/**
	 * 获取点赞的人数
	 * @author 陈晓珊
	 * @date 2016年10月26日
	 * @param id
	 * @return
	 */
	Map<String, Object> doClickGoodCount(String id,String type);

	/**
	 * 获取点赞的人的列表
	 * @author 陈晓珊
	 * @date 2016年10月25日
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> doClickGoodList(String loginId,String id,String type);

	/**
	 * 发布评论
	 * @author 陈晓珊
	 *@date 2016年11月2日
	 * @param dynamIicComment
	 * @return
	 */
	Integer doSendDynamicComment(DynamicCommentEntity dynamIicComment);

}

package com.ennuova.app.service;

import java.util.List;
import java.util.Map;

import com.ennuova.entity.PubCommentEntity;

/**评论相关服务
 * @author 李智辉
 * @time 2016-7-27
 */
public interface PubCommentService {
	/**评论新闻
	 * @param fromId	新闻ID
	 * @param userId	用户ID
	 * @param content	评论内容
	 * @return
	 */
	boolean commentNews(Long fromId,Long userId,String content);
	/**根据新闻ID获取评论
	 * @param fromId
	 * param pageNum 页码
	 * @param size 获取的数量
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> getCommentListByFromId(Long fromId,int pageNum,int size,Long userId);
	/**根据评论ID获取回复列表
	 * @param fromId
	 * @param size
	 * @param pageNum
	 * @return   List<Map<String, Object>>
	 */
	List<Map<String, Object>> getReplyListByCommentId(Long fromId,int size,int pageNum);
	/**回复评论
	 * @param fromId	评论ID
	 * @param userId	用户ID
	 * @param userName	用户昵称
	 * @param content	评论内容
	 * @return boolean true-回复成功 false-回复失败
	 */
	boolean replyComment(Long fromId,Long userId,String userName,String content);
	/**根据评论ID获取评论信息
	 * @param cId
	 * @param userId
	 * @return entity
	 */
	PubCommentEntity getCommentEntityById(Long cId,Long userId);
}

package com.ennuova.dao;

import java.util.List;
import java.util.Map;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubCommentEntity;

/**评论数据库操作Dao层
 * @author 李智辉
 * @time 2016-7-27
 */
public interface PubCommentDao extends DaoSupport<PubCommentEntity> {

	/**根据主键ID更新评论数量或者点赞，每调用一次增加1
	 * @param id
	 * @param type 更新类型 0-更新评论数 1-更新点赞数
	 * @param count 增加的数目
	 * @return Integer
	 */
	Integer updateReplyCount(int type,Long id,int count);
	/**获取评论列表
	 * @param fromId
	 * @param size
	 * @param pageNum
	 * @return List<Map<String, Object>>
	 */
	List<Map<String, Object>> getCommentListByNewId(Long fromId,int size,int pageNum);
}

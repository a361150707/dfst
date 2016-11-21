package com.ennuova.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubReplyEntity;

/**回复dao
 * @author 李智辉
 * @time 2016-7-27
 */
public interface PubReplyDao extends DaoSupport<PubReplyEntity> {
	/**根据评论ID列表获取每个ID两条回复
	 * @param list 评论ID列表
	 * @return  Map<String, Set<PubReplyEntity>>
	 */
	Map<String, List<PubReplyEntity>> getReplyMapByIdList(List<Long> list);
	/**根据评论ID获取列表
	 * @param fromId
	 * @param size
	 * @param pageNum
	 * @return
	 */
	List<Map<String, Object>> getReplyListByCommentId(Long fromId,int size, int pageNum);
}

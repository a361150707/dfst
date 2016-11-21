package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubCommentDao;
import com.ennuova.entity.PubCommentEntity;

/**评论dao实现
 * @author 李智辉
 * @time 2016-7-27
 */
@Repository
public class PubCommentDaoImpl extends DaoSupportImpl<PubCommentEntity> implements
		PubCommentDao {

	@Override
	public Integer updateReplyCount(int type,Long id,int count) {
		String sql = "";
		if(type == 0){
			sql = "update pub_comment set REPLY_COUNT = REPLY_COUNT + "+count+" where id = ?";
		}else{
			sql = "update pub_comment set GOOD_COUNT = GOOD_COUNT + "+count+" where id = ?";
		}
		
		return executeSql(sql, id);
	}

	@Override
	public List<Map<String, Object>> getCommentListByNewId(Long fromId,
			int size, int pageNum) {
		int startNum = (pageNum-1)*size;
		int endNum = (pageNum-1)*size+size;
		String sql = "select * from (select A.*,ROWNUM rn from (select ID,TO_ID,TO_NAME,TO_IMG,CONTENT,CREATE_DATE,GOOD_COUNT,REPLY_COUNT from pub_comment where FROM_ID = "+fromId+" order by CREATE_DATE desc) A) where rn>"+startNum+" and rn<"+endNum+" ";
		return findForJdbc(sql);
	}
}

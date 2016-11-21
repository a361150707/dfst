package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.DynamicReplyDao;
import com.ennuova.entity.DynamicReply;

@Repository("dynamicReplyDaoImpl")
public class DynamicReplyDaoImpl extends DaoSupportImpl<DynamicReply> implements DynamicReplyDao{



	/**
	 * 根据评论ID，获取评论的回复列表
	 * @param dynamic_id
	 * @param cusId
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doReplyList(String commentId) {
		String sql = "SELECT reply.ID as replyId ,reply.REPLY_NAME AS replyName,"
				+ "nvl(reply.REPLY_NICK,'匿名') AS replyNick,reply.REPLY_CONTENT AS replyContent,"
				+ "reply.CUS_ID AS cusId,nvl(cus.FNICK,'匿名') as FNICK,"
				+ "to_char(reply.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS createDate "
				+ "FROM DYNAMIC_REPLY reply LEFT JOIN PUB_CUSTOMER cus "
				+ "ON cus.ID = reply.CUS_ID WHERE reply.DYNAMIC_ID = ? "
				+ "AND nvl(reply.DEL_FLAG,0) = ? ORDER BY createDate DESC";
		return findForJdbc(sql, commentId,0);
	}
	
	
}

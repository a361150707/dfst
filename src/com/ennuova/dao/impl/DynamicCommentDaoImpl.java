package com.ennuova.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.DynamicCommentDao;
import com.ennuova.entity.DynamicCommentEntity;
import com.ennuova.util.UrlUtil;


@Repository("DynamicCommentDaoImpl")
public class DynamicCommentDaoImpl extends DaoSupportImpl<DynamicCommentEntity> implements DynamicCommentDao{

	/**
	 * 删除动态评论
	 * @param delfLag
	 * @param id
	 * @return
	 */
	@Override
	public Integer doDelete(String delfLag, String id) {
		String sql = "update DYNAMIC_COMMENT d set d.DEL_FLAG = ?"
				   + " where d.ID = ?";
		return executeSql(sql,delfLag,id);
	}

	/**
	 * 获取动态评论列表
	 * @param dynamic_id
	 * @return
	 */
	@Override
	public List<Map<String, Object>> doCommentList(String dynamic_id,Integer page,
			Integer rows) {
		String imgUrl = UrlUtil.getInstance().getImgurl();
		String sql = "SELECT com.ID,to_char(com.CREATE_DATE,'yyyy-mm-dd hh24:mi:ss') AS createDate,"
				+ "com.CONTENT,cus.id as cusId,nvl(cus.FNICK,'匿名') as FNICK,cus.FSEX,"
				+ "?||cus.FPHOTO as FPHOTO FROM DYNAMIC_COMMENT com LEFT JOIN PUB_CUSTOMER cus "
				+ "ON com.TO_ID = cus.ID WHERE com.FROM_ID = ? "
				+ "AND nvl(com.DEL_FLAG,0) = ? ORDER BY createDate DESC";
		return findForJdbcPage(sql, page, rows,imgUrl,dynamic_id,0);
	}

	/**
	 * 根据动态ID，获取动态评论数目
	 *@author 陈晓珊
	 *@date 2016年10月25日
	 * @param id
	 * @return
	 */
	@Override
	public Map<String, Object> doCommentCount(String id) {
		String sql = "SELECT COUNT(ID) AS commentCount FROM DYNAMIC_COMMENT "
				+ "WHERE FROM_ID = ? AND nvl(DEL_FLAG,0) = ?";
		return findOneForJdbc(sql, id,0);
	}

	
	
	
}

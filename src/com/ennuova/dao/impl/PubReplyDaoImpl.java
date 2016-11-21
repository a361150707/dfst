package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;
import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubReplyDao;
import com.ennuova.entity.PubReplyEntity;
import com.ennuova.util.DButil;

/**回复dao实现
 * @author 李智辉
 * @time 2016-7-27
 */
@Repository
public class PubReplyDaoImpl extends DaoSupportImpl<PubReplyEntity> implements PubReplyDao {
	@Override
	public Map<String, List<PubReplyEntity>> getReplyMapByIdList(List<Long> list) {
		Map<String, List<PubReplyEntity>>  map = new HashMap<String, List<PubReplyEntity>>();
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		for (Long commentId : list) {
			String sql = "select * from (select id,FROM_ID,TO_ID,TO_NAME,CONTENT from pub_reply where from_id = "+commentId+" order by create_date asc) where rownum<3";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				List<PubReplyEntity> entities = new ArrayList<PubReplyEntity>();
				while(rs.next()){
					PubReplyEntity pubReplyEntity = new PubReplyEntity();
					pubReplyEntity.setFromId(commentId);
					pubReplyEntity.setId(rs.getLong("ID"));
					pubReplyEntity.setToId(rs.getLong("TO_ID"));
					pubReplyEntity.setToName(rs.getString("TO_NAME"));
					pubReplyEntity.setContent(rs.getString("CONTENT"));
					entities.add(pubReplyEntity);	//将对应的信息存储到想要的来源ID里面
				}
				map.put(commentId+"", entities);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DButil.closeDb(conn, ps, rs);
			}
		}
		return map;
	}
	public List<Map<String, Object>> getReplyListByCommentId(Long fromId,
			int size, int pageNum) {
		int startNum = (pageNum-1)*size;
		int endNum = (pageNum-1)*size+size;
		String sql = "select * from (select A.*,ROWNUM rn from (select id,FROM_ID,TO_ID,TO_NAME,CONTENT,CREATE_DATE from pub_reply where FROM_ID = "+fromId+" order by CREATE_DATE desc) A) where rn>"+startNum+" and rn<"+endNum+" ";
		return findForJdbc(sql);
	}
}

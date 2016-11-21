package com.ennuova.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CommonVideosDao;
import com.ennuova.entity.CommonVideos;
import com.ennuova.util.DButil;

/**
 * 
* @Title: CommonImagesDaoImpl.java 
* @Package com.ennuova.dao.impl 
* @Description: 图片附件dao层实现(描述) 
* @author felix
* @date 2016年4月15日
* @version V1.0
 */
@Repository
public class CommonVideosDaoImpl extends DaoSupportImpl<CommonVideos> implements CommonVideosDao{

	@Override
	public List<Map<String, Object>> queryVideos(int tp, Long tpId,int page) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(tp==1){
			list = findForJdbcParam("select * from common_videos where FTYPE = 1 and FTYPEID = ?", page, 6, tpId);
		}else{
			list = findForJdbcParam("select * from common_videos where FTYPE = 2 and FTYPEID = ?", page, 6, tpId);
		}
		return list;
	}

	@Override
	public Long getVideoCountByLid(int type,Long tId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql = "select count(id) vnum from common_videos where ftype="+type+" and FTYPEID = "+tId;
		Long videoNum = 0l;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				videoNum = rs.getLong("vnum");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return videoNum;
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return videoNum;
	}

	@Override
	public int updateVideoGoodCount(Long id,int count) {
		String sql = "update common_videos set GOOD_COUNT = GOOD_COUNT+"+count+" where id = ?";
		return executeSql(sql, id);
	}

}

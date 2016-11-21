package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CommonImagesDao;
import com.ennuova.entity.CommonImages;
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
@Repository("ciDao")
public class CommonImagesDaoImpl extends DaoSupportImpl<CommonImages> implements CommonImagesDao{

	@Override
	public List<CommonImages> queryImages(int tp, Long tpId) {
		return findHql(" from CommonImages where ftype=? and ftypeid=? ", tp, tpId);
	}

	@Override
	public List<Map<String, Object>> getImgNumByLine(int type, Long id) {
		String sql = "select count(id) IMGNUM from COMMON_IMAGES where ftype = ? and ftypeid=?";
		
		return findForJdbc(sql, type,id);
	}

	@Override
	public Long getImgNum(int type, Long tId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql = "select count(id) vnum from common_images where ftype="+type+" and FTYPEID = "+tId;
		Long imgNum = 0l;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				imgNum = rs.getLong("vnum");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return imgNum;
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return imgNum;
	}

}

package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubAppeditonDao;
import com.ennuova.entity.PubAppediton;
import com.ennuova.util.DButil;
/**
 * 版本更新相关服务
 * @author 李智辉
 * @time 2016-2-23
 */
@Repository
public class PubAppeditionDaoImpl extends DaoSupportImpl<PubAppediton> implements PubAppeditonDao {
	@Override
	public PubAppediton getEditonByAppType(String appType) {
		PubAppediton appediton = new PubAppediton();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from PUB_APPEDITON where UPDATETIME = (select max(UPDATETIME) from PUB_APPEDITON where TYPE = '"+appType+"') and TYPE = '"+appType+"'";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				appediton.setId(rs.getLong("id"));
				appediton.setContent(rs.getString("CONTENT"));
				appediton.setEditon(rs.getString("EDITON"));
				appediton.setLink(rs.getString("LINK"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}

		return appediton;
	}

}

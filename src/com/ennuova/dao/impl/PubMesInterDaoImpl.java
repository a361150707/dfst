package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubMesInterDao;
import com.ennuova.entity.PubMesInter;

@Repository("pubMesInterDao")
public class PubMesInterDaoImpl extends DaoSupportImpl<PubMesInter> implements PubMesInterDao{

	@Override
	public PubMesInter queryFirstMesInterface() {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=" SELECT * FROM PUB_MESINTER ";
		PubMesInter mes = new PubMesInter();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				mes.setFcompany(rs.getString("FCOMPANY"));
				mes.setFpassword(rs.getString("FPASSWORD"));
				mes.setFurl(rs.getString("FURL"));
				mes.setFuser(rs.getString("FUSER"));
				mes.setId(rs.getLong("ID"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mes;
	}

}

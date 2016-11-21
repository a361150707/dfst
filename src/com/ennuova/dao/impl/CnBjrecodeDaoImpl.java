package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.DaoSupport;
import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnBjrecodeDao;
import com.ennuova.entity.CnBjrecode;
import com.ennuova.util.Constant;
import com.ennuova.util.DButil;


/**
 * 报警记录DAoimpl
 * @author dofast-pc
 */
@Repository("cnBjrecodeDao")
public class CnBjrecodeDaoImpl extends DaoSupportImpl<CnBjrecode> implements CnBjrecodeDao{

	@Override
	public List<String> getCnBjrecodeList(Long fclid, String startTime,
			String endTime) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql = "select fxxid from CN_BJRECODE where fclid = "+fclid+" and FCREATETIME between to_date('"+startTime+"','yyyy-mm-dd hh24:mi:ss')" +
				" and to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss')";
		List<String> list = new ArrayList<String>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				String fxxid = rs.getString("fxxid");
				if (Constant.TA.equals(fxxid)||Constant.TB.equals(fxxid)) {
					list.add(fxxid);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return list;
	}

}

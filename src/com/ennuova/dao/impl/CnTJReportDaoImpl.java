package com.ennuova.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnTJReportDao;
import com.ennuova.entity.CnTjdetail;
import com.ennuova.entity.CnTjgzdetail;
import com.ennuova.entity.CnTjreport;
import com.ennuova.util.DButil;
@Repository
public class CnTJReportDaoImpl extends DaoSupportImpl<CnTjreport> implements CnTJReportDao {

	@Override
	public List<CnTjreport> queryTjReport(long fclid,long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = "select id,FCPH,FTJTIME,FZGZS from (SELECT ID,FCPH,FTJTIME,FZGZS FROM CN_TJREPORT WHERE FCLID="+fclid+" and id<"+id+" ORDER BY FTJTIME DESC) where rownum<13";
		ResultSet rs = null;
		List<CnTjreport> reportList = new ArrayList<CnTjreport>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				CnTjreport report = new CnTjreport();
				report.setId(rs.getLong("ID"));
				report.setFcph(rs.getString("FCPH"));
				report.setFtjtime(rs.getTimestamp("FTJTIME"));
				report.setFzgzs(rs.getLong("FZGZS"));
				reportList.add(report);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return reportList;
	}

	@Override
	public List<CnTjgzdetail> queryTjDetail(long ftjid) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql =" SELECT * FROM CN_TJGZDETAIL where TJID = "+ftjid;
		List<CnTjgzdetail> tjDetail = new ArrayList<CnTjgzdetail>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				CnTjgzdetail detail = new CnTjgzdetail();
				detail.setId(rs.getLong("ID"));
				detail.setClearstate(rs.getString("CLEARSTATE"));
				CnTjreport cnTjreport = new CnTjreport();
				cnTjreport.setId(rs.getLong("TJID"));
				detail.setCnTjreport(cnTjreport);
				detail.setCode(rs.getString("CODE"));
				detail.setGzid(rs.getString("GZID"));
				detail.setGznr(rs.getString("GZNR"));
				detail.setGzxt(rs.getString("GZXT"));
				detail.setState(rs.getString("STATE"));
				tjDetail.add(detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return tjDetail;
	}

	@Override
	public int getTjNum(long ftjid) {
		Connection conn = null;
		PreparedStatement ps = null;
		int num = 0;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql ="SELECT COUNT(1) FROM CN_TJDETAIL where FTJID = "+ftjid;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				num = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return num;
	}
	
}

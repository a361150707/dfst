package com.ennuova.dao.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnDzwlDao;
import com.ennuova.entity.CnDzwl;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.dzwlview.CnDzwlView;
import com.ennuova.util.DButil;

/**电子围栏daoImpl
 * @author 李智辉 
 * 2015-12-15下午2:54:15
 */
@Repository
public class CnDzwlDaoImpl extends DaoSupportImpl<CnDzwl> implements CnDzwlDao {
	@Override
	public int saveFence(CnDzwl cnDzwl) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql = "update CN_DZWL set FEFFECT = 0 WHERE FCLID = '"+cnDzwl.getPubCarinfo().getId()+"'";
		try {
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			
			this.save(cnDzwl);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
			
		}
		
		return 1;
	}

	@Override
	public int setFence(Long fclid, Long Id,int fsrcs) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql = "update CN_DZWL set FEFFECT = 0 WHERE FCLID = "+fclid+"";
		try {
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update CN_DZWL set FEFFECT = 1,FSRSC = "+fsrcs+" WHERE id = "+Id+"";
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
			
		}
		
		return 1;
	}

	@Override
	public List<CnDzwl> getCnDzwlList(Long fclid) {
		List<CnDzwl> cnDzwls = new ArrayList<CnDzwl>();
		Connection conn = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql = "select * from CN_DZWL where FCLID = "+fclid+"";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				CnDzwl cnDzwl = new CnDzwl();
				PubCarinfo pubCarinfo = new PubCarinfo();
				pubCarinfo.setId(rs.getLong("FCLID"));
				cnDzwl.setPubCarinfo(pubCarinfo);
				cnDzwl.setFcreatetime(rs.getTimestamp("Fcreatetime"));
				cnDzwl.setFeffect(rs.getInt("Feffect"));
				cnDzwl.setFlat(rs.getDouble("Flat"));
				cnDzwl.setFlng(rs.getDouble("Flng"));
				cnDzwl.setFname(rs.getString("Fname"));
				cnDzwl.setFradius(rs.getDouble("Fradius"));
				cnDzwl.setFsrsc(rs.getInt("Fsrsc"));
				cnDzwl.setId(rs.getLong("id"));
				cnDzwls.add(cnDzwl);
			}
		} catch (Exception e) {
			return cnDzwls = new ArrayList<CnDzwl>();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnDzwls;
	}

	@Override
	public CnDzwl getCnDzwl(Long id) {
		CnDzwl cnDzwl = new CnDzwl();
		Connection conn = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql = "select * from CN_DZWL where id = "+id+"";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				PubCarinfo pubCarinfo = new PubCarinfo();
				pubCarinfo.setId(rs.getLong("FCLID"));
				cnDzwl.setPubCarinfo(pubCarinfo);
				cnDzwl.setFcreatetime(rs.getDate("Fcreatetime"));
				cnDzwl.setFeffect(rs.getInt("Feffect"));
				cnDzwl.setFlat(rs.getDouble("Flat"));
				cnDzwl.setFlng(rs.getDouble("Flng"));
				cnDzwl.setFname(rs.getString("Fname"));
				cnDzwl.setFradius(rs.getDouble("Fradius"));
				cnDzwl.setFsrsc(rs.getInt("Fsrsc"));
				cnDzwl.setId(rs.getLong("id"));
			}
		} catch (Exception e) {
			return cnDzwl;
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnDzwl;
	}

	@Override
	public boolean cancelFence(Long id) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		boolean success = false;
		String sql = "update CN_DZWL set FEFFECT = 0 WHERE id = "+id+"";
		try {
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return success;
	}
	@Override
	public List<CnDzwlView> fenceListen() {
		List<CnDzwlView> cnDzwlViews = new ArrayList<CnDzwlView>();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			if (cs == null)
	            cs = conn.prepareCall("{call SP_OW_DZWLTX(?)}");
	        cs.registerOutParameter(1, OracleTypes.CURSOR);
	        cs.execute();
	        rs = (ResultSet) cs.getObject(1);
	        while (rs.next()) {
	        	CnDzwlView cnDzwlView = new CnDzwlView();
	        	cnDzwlView.setFclid(rs.getLong(1));
	        	cnDzwlView.setMessage(rs.getString(2));
	        	cnDzwlView.setAlias(rs.getString(3));
	        	cnDzwlViews.add(cnDzwlView);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, cs, rs);
		}
		
		return cnDzwlViews;
	}
	@Override
	public List<CnDzwlView> falarmListen() {
		List<CnDzwlView> cnDzwlViews = new ArrayList<CnDzwlView>();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			if (cs == null)
	            cs = conn.prepareCall("{call SP_OW_BJXXTX(?)}");
	        cs.registerOutParameter(1, OracleTypes.CURSOR);
	        cs.execute();
	        rs = (ResultSet) cs.getObject(1);
	        while (rs.next()) {
	        	CnDzwlView cnDzwlView = new CnDzwlView();
	        	cnDzwlView.setFclid(rs.getLong(1));
	        	cnDzwlView.setMessage(rs.getString(2));
	        	cnDzwlView.setAlias(rs.getString(3));
	        	cnDzwlViews.add(cnDzwlView);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, cs, rs);
		}
		
		return cnDzwlViews;
	}

}

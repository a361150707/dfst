package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnXcrecodeDao;
import com.ennuova.entity.CnXcrecode;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

/**行程管理impl
 * @author 李智辉 
 * 2015-12-9上午10:40:46
 */
@Repository("cnXcrecodeDao")
public class CnXcrecodeDaoImpl extends DaoSupportImpl<CnXcrecode> implements CnXcrecodeDao {

	@Override
	public List<CnXcrecode> queryCnXcrecodeListByEndTime(Long fclid,
			String startTime, String endTime) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from CN_XCRECODE where FCLID ='"+fclid+"'and FBEGINTIME between to_date('"+startTime+"','yyyy-mm-dd hh24:mi:ss')" +
				" and to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss') order by id desc";
		List<CnXcrecode> xcrecodeList=new ArrayList<CnXcrecode>();
		System.out.println(sql);
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				if (rs.getInt("FENDFLAG")==1) {
					CnXcrecode xcrecode =new CnXcrecode();
					xcrecode.setId(rs.getLong("id"));
					PubCarinfo pubCarinfo = new PubCarinfo();
					pubCarinfo.setId(rs.getLong("FCLID"));
					xcrecode.setPubCarinfo(pubCarinfo);
					xcrecode.setFbegintime(rs.getTimestamp("FBEGINTIME"));
					xcrecode.setFendtime(rs.getTimestamp("FENDTIME"));
					xcrecode.setFbeginjd(rs.getDouble("fbeginjd"));
					xcrecode.setFbeginwd(rs.getDouble("fbeginwd"));
					xcrecode.setFbcxslc(rs.getDouble("FBCXSLC"));
					xcrecode.setFbcxsyh(rs.getDouble("FBCXSYH"));
					xcrecode.setFendjd(rs.getDouble("fendjd"));
					xcrecode.setFendwd(rs.getDouble("fendwd"));
					xcrecodeList.add(xcrecode);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return xcrecodeList;
	}

	/**根据车辆Id,行程序号
	 * @param fclid
	 * @return List<CnXcrecode>
	 */
	public CnXcrecode findByXcxhAndcarId(Long carId, String fxcxh) {
		
		PubCarinfo pubCarinfo=new PubCarinfo();
		pubCarinfo.setId(carId);
		return (CnXcrecode) getSessionFactory().getCurrentSession().createQuery(
				"from CnXcrecode where pubCarinfo=? and fxcxh=?")//
				.setParameter(0, pubCarinfo)//
				.setParameter(1, fxcxh)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	@Override
	public List<CnXcrecode> getOneYearData(Long fclid) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from Cn_xcrecode where fclid="+fclid+" and to_char(FBEGINTIME,'yyyy')=to_char(sysdate,'yyyy') order By id asc";
		List<CnXcrecode> xcrecodeList=new ArrayList<CnXcrecode>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
			if (rs.getInt("FENDFLAG")==1) {
				CnXcrecode xcrecode =new CnXcrecode();
				xcrecode.setId(rs.getLong("id"));
				PubCarinfo pubCarinfo = new PubCarinfo();
				pubCarinfo.setId(rs.getLong("FCLID"));
				xcrecode.setPubCarinfo(pubCarinfo);
				xcrecode.setFbegintime(rs.getTimestamp("FBEGINTIME"));
				xcrecode.setFendtime(rs.getTimestamp("FENDTIME"));
				xcrecode.setFbeginjd(rs.getDouble("fbeginjd"));
				xcrecode.setFbeginwd(rs.getDouble("fbeginwd"));
				xcrecode.setFbcxslc(rs.getDouble("FBCXSLC"));
				xcrecode.setFbcxsyh(rs.getDouble("FBCXSYH"));
				xcrecode.setFendjd(rs.getDouble("fendjd"));
				xcrecode.setFendwd(rs.getDouble("fendwd"));
				xcrecodeList.add(xcrecode);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return xcrecodeList;
	}

	@Override
	public List<CnXcrecode> getOneYearData(Long fclid, Long maxId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select * from Cn_xcrecode where fclid="+fclid+" and id>"+maxId+"order by id asc";
		List<CnXcrecode> xcrecodeList=new ArrayList<CnXcrecode>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
			if (rs.getInt("FENDFLAG")==1) {
				CnXcrecode xcrecode =new CnXcrecode();
				xcrecode.setId(rs.getLong("id"));
				PubCarinfo pubCarinfo = new PubCarinfo();
				pubCarinfo.setId(rs.getLong("FCLID"));
				xcrecode.setPubCarinfo(pubCarinfo);
				xcrecode.setFbegintime(rs.getTimestamp("FBEGINTIME"));
				xcrecode.setFendtime(rs.getTimestamp("FENDTIME"));
				xcrecode.setFbeginjd(rs.getDouble("fbeginjd"));
				xcrecode.setFbeginwd(rs.getDouble("fbeginwd"));
				xcrecode.setFbcxslc(rs.getDouble("FBCXSLC"));
				xcrecode.setFbcxsyh(rs.getDouble("FBCXSYH"));
				xcrecode.setFendjd(rs.getDouble("fendjd"));
				xcrecode.setFendwd(rs.getDouble("fendwd"));
				xcrecodeList.add(xcrecode);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return xcrecodeList;
	}

	@Override
	public List<CnXcrecode> getXcrecodeDataByTime(Long fclid, String time) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String startTime = time+" 00:00:00";
		String endTime = time+" 23:59:59";
		String sql="select * from Cn_xcrecode where fclid="+fclid+" and FBEGINTIME between" +
				" to_date('"+startTime+"','yyyy-mm-dd hh24:mi:ss') and to_date('"+endTime+"','yyyy-mm-dd hh24:mi:ss') order by id asc";
		System.out.println(sql);
		List<CnXcrecode> xcrecodeList=new ArrayList<CnXcrecode>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
			if (rs.getInt("FENDFLAG")==1) {
				CnXcrecode xcrecode =new CnXcrecode();
				xcrecode.setId(rs.getLong("id"));
				PubCarinfo pubCarinfo = new PubCarinfo();
				pubCarinfo.setId(rs.getLong("FCLID"));
				xcrecode.setPubCarinfo(pubCarinfo);
				xcrecode.setFbegintime(rs.getTimestamp("FBEGINTIME"));
				xcrecode.setFendtime(rs.getTimestamp("FENDTIME"));
				xcrecode.setFbeginjd(rs.getDouble("fbeginjd"));
				xcrecode.setFbeginwd(rs.getDouble("fbeginwd"));
				xcrecode.setFbcxslc(rs.getDouble("FBCXSLC"));
				xcrecode.setFbcxsyh(rs.getDouble("FBCXSYH"));
				xcrecode.setFendjd(rs.getDouble("fendjd"));
				xcrecode.setFendwd(rs.getDouble("fendwd"));
				xcrecodeList.add(xcrecode);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return xcrecodeList;
	}

	/**
	 * 根据客户的id获取客户的行程列表取前10条
	 * @author sududa
	 * @date 2016年10月28日
	 */
	@Override
	public List<Map<String, Object>> findByCusIdWithPage(Integer page, Integer rows, String loginId) {
		String sql = "select trip.ID,trip.FBCXSLC,trip.FBCXSYH"
				   + " from CN_XCRECODE trip"
				   + " where trip.FCLID = ("
				   + " select car.ID from PUB_CARINFO car"
				   + " where car.FCUSTOMER = ? and car.FDEFAULT =?)"
				   + " and trip.FBCXSLC is not null"
				   + " and trip.FBCXSYH is not null"
				   + " order by trip.FCREATETIME desc";
		return findForJdbcPage(sql, page, rows, loginId, 1);
	}

	/**
	 * 根据车辆id查询当天的行程记录
	 * @author sududa
	 * @date 2016年11月4日
	 */
	@Override
	public List<Map<String, Object>> findListByCarIdAndDate(String defCarId) {
//		String sql = "select xc.ID,xc.FBCXSLC,xc.FBCXSYH "
//				   + " from CN_XCRECODE xc"
//				   + " where to_char(xc.fcreatetime,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd')"
//				   + " AND xC.FCLID = ?";//方法1
//		String sql = "select xc.ID,xc.FBCXSLC,xc.FBCXSYH "
//				+ " from CN_XCRECODE xc"
//				+ " where trunc(xc.fcreatetime) = to_char(sysdate)"
//				+ " AND xC.FCLID = ?";//方法2
		String sql = "select xc.ID,xc.FBCXSLC,xc.FBCXSYH"
				+ " from CN_XCRECODE xc"
				+ " where xc.fcreatetime>=trunc(sysdate) and xc.fcreatetime<trunc(sysdate)+1"
				+ " AND xC.FCLID = ?";//方法3
		return findForJdbc(sql, defCarId);
	}

	/**
	 * 根据车辆id分页查询车辆的行程记录信息
	 * @author sududa
	 * @date 2016年11月4日
	 */
	@Override
	public List<Map<String, Object>> findByCarIdWithPage(Integer page, Integer rows, String defCarId) {
		String sql = "select trip.ID,trip.FBCXSLC,trip.FBCXSYH"
				   + " from CN_XCRECODE trip"
				   + " where trip.FBCXSLC is not null and"
				   + " and trip.FBCXSYH is not null"
				   + " trip.FCLID = ?";
		return findForJdbcPage(sql, page, rows, defCarId, 1);
	}
	
}

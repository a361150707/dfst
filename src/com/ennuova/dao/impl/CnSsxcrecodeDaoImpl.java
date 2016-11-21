package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnSsxcrecodeDao;
import com.ennuova.entity.CnSsxcrecode;
import com.ennuova.util.DButil;

/**实时行程记录daoImpl
 * @author 李智辉 
 * 2015-12-10下午1:27:22
 */
@Repository
public class CnSsxcrecodeDaoImpl extends DaoSupportImpl<CnSsxcrecode> implements CnSsxcrecodeDao {

	@Override
	public CnSsxcrecode getNewCnSsxcrecode(Long fclId) {
		
		CnSsxcrecode cnSsxcrecode = new CnSsxcrecode();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from Cn_Ssxcrecode where fclid='"+fclId+"' and FGPSTIME= (select max(FGPSTIME) from Cn_Ssxcrecode where fclid='"+fclId+"' and to_char(FGPSTIME,'yyyy')=to_char(sysdate,'yyyy')) ";
		System.out.println(new Date().getTime());
		System.out.println(sql);
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				cnSsxcrecode.setFjd(rs.getDouble("FJD"));
				cnSsxcrecode.setFwd(rs.getDouble("FWD"));
			}
			System.out.println(new Date().getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnSsxcrecode;
	}

	@Override
	public CnSsxcrecode getRealTimeLocation(Long fclid,String initTime) {
		CnSsxcrecode cnSsxcrecode = new CnSsxcrecode();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id,FJD,FWD from Cn_Ssxcrecode where fclid='"+fclid+"' and FGPSTIME= (select max(FGPSTIME) from Cn_Ssxcrecode where fclid='"+fclid+"' and FGPSTIME>to_date('"+initTime+"','yyyy-mm-dd hh24:mi:ss'))";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				cnSsxcrecode.setId(rs.getLong("id"));
				cnSsxcrecode.setFjd(rs.getDouble("FJD"));
				cnSsxcrecode.setFwd(rs.getDouble("FWD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnSsxcrecode;
	}

	@Override
	public CnSsxcrecode getXcrecodeLBS(Long fclid, String initTime,String selectTimeFiveAgo) {
		CnSsxcrecode cnSsxcrecode = new CnSsxcrecode();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id,FJD,FWD from Cn_Ssxcrecode where fclid='"+fclid+"' and FGPSTIME= (select max(FGPSTIME) from Cn_Ssxcrecode where fclid='"+fclid+"' and FGPSTIME<to_date('"+initTime+"','yyyy-mm-dd hh24:mi:ss') and FGPSTIME>to_date('"+selectTimeFiveAgo+"','yyyy-mm-dd hh24:mi:ss'))";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				cnSsxcrecode.setId(rs.getLong("id"));
				cnSsxcrecode.setFjd(rs.getDouble("FJD"));
				cnSsxcrecode.setFwd(rs.getDouble("FWD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnSsxcrecode;
	}

	@Override
	public CnSsxcrecode getXcrecodeLBSByBegin(Long fclid, String selectTime,
			String selectTimeFiveAgo) {
		CnSsxcrecode cnSsxcrecode = new CnSsxcrecode();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id,FJD,FWD from Cn_Ssxcrecode where fclid='"+fclid+"' and FGPSTIME= (select min(FGPSTIME) from Cn_Ssxcrecode where fclid='"+fclid+"' and FGPSTIME<to_date('"+selectTime+"','yyyy-mm-dd hh24:mi:ss') and FGPSTIME>to_date('"+selectTimeFiveAgo+"','yyyy-mm-dd hh24:mi:ss'))";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				cnSsxcrecode.setId(rs.getLong("id"));
				cnSsxcrecode.setFjd(rs.getDouble("FJD"));
				cnSsxcrecode.setFwd(rs.getDouble("FWD"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return cnSsxcrecode;
	}

	@Override
	public List<Map<String, Object>> getCnSsxcrecodeByTime(String beginTime,String endTime, Long carId) {
		String sql = "select id,FJD,FWD from Cn_Ssxcrecode where fclid=? and FGPSTIME between to_date(?,'yyyy-mm-dd hh24:mi:ss') and to_date(?,'yyyy-mm-dd hh24:mi:ss') order by id asc";
		return findForJdbc(sql, carId,beginTime,endTime);
	}

	/**
	 * 根据客户id获取客户车辆的最新的位置信息,即车辆的实时行程记录
	 * @author sududa
	 * @date 2016年10月31日
	 */
	@Override
	public Map<String, Object> findByCusId(String cusId) {
		String sql = "select * from （"
				   + "select s.ID,s.FJD,s.FWD"
				   + " from CN_SSXCRECODE s"
				   + " where s.FCLID = "
				   + " (select car.ID from PUB_CARINFO car"
				   + " where car.FDEFAULT = ? and car.FCUSTOMER = ?)"
				   + " and s.FJD is not null and s.FWD is not null"
				   + " ) where rownum=1";
		return findOneForJdbc(sql, 1, cusId);
	}

	/**
	 * 根据车辆id获取车辆的最新的位置信息,即车辆的实时行程记录
	 * @author sududa
	 * @date 2016年11月2日
	 */
	@Override
	public Map<String, Object> findByCarId(String carId) {
		String sql = "select * from （"
				   + "select s.FJD,s.FWD"
				   + " from CN_SSXCRECODE s"
				   + " where s.FJD is not null and"
				   + " s.FWD is not null and s.FCLID = ?"
				   + " ) where rownum=1";
		return findOneForJdbc(sql, carId);
	}

}

package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnSsclxxDao;
import com.ennuova.entity.CarMonitor;
import com.ennuova.entity.CnSsclxx;
import com.ennuova.util.DButil;

/**
 * 实时车辆信息daoImpl
 * @author dofast-pc
 *
 */
@Repository("cnSsclxxDao")
public class CnSsclxxDaoImpl extends DaoSupportImpl<CnSsclxx> implements CnSsclxxDao{
	
	public CarMonitor getCarMonitor(Long fclid, String startTime) {
		CarMonitor carMonitor = new CarMonitor();
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select id,F43,F54,F65,F40,F46,F53,F64,F51,F63,F32,F52,F41,F13,FCREATETIME from CN_SSCLXX where fclid='"+fclid+"' and FCREATETIME= (select max(FCREATETIME) " +
				"from CN_SSCLXX where fclid='"+fclid+"' and FCREATETIME>to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss')) ";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				carMonitor.setId(rs.getLong("id"));
				carMonitor.setWaterTemp(rs.getString("F43"));
				carMonitor.setOilInstantaneous(rs.getString("F54"));
				carMonitor.setAddMileage(rs.getString("F40"));
				carMonitor.setVoltage(rs.getString("F46"));
				carMonitor.setOilAverage(rs.getString("F53"));
				carMonitor.setoOilpercent(rs.getString("F64"));
				carMonitor.setCarSpeed(rs.getString("F51"));
				carMonitor.setRemainOil(rs.getString("F63"));
				carMonitor.setSeatbelt(rs.getString("F32"));
				carMonitor.setEngineSpeed(rs.getString("F52"));
				carMonitor.setRemaiMileage(rs.getString("F41"));
				carMonitor.setCarLock(rs.getString("F13"));
				carMonitor.setFcreateTime(rs.getTimestamp("FCREATETIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
			
		}
			
		return carMonitor;
	}

	@Override
	public String getKmByCarinfoId(long carId) {
		String realMileage = "";
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select f40 from (select F40 from cn_ssclxx where fclid = "+carId+" and f40 is not null order by f40 desc) t where rownum=1";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				realMileage = rs.getString("F40");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		
		return realMileage;
	}

	/**
	 * 通过客户id查询该客户的默认车的总里程和总油耗的最新一条信息
	 *@author sududa
	 *@date 2016年10月25日
	 * @param cusId
	 * @return
	 */
	@Override
	public Map<String, Object> findFirstOneByCusId(String cusId) {
		String sql = "select * from ("
				   + "select s.ID,s.FCLID,nvl(s.F40,'--') as totalMileage,"
				   + "nvl(s.F42,'--') as totalOil,"
				   + "to_char(s.FCREATETIME,'yyyy-mm-dd hh24:mi:ss') as ssLastTime"
				   + " from CN_SSCLXX s "
				   + " where s.F40 is not null and s.F42 is not null"
				   + " and s.FCLID = (select car.ID from PUB_CARINFO car"
				   + " where car.FCUSTOMER = ? and car.FDEFAULT = ?)"
				   + " order by s.ID desc)"
				   + " where rownum = 1";
		return findOneForJdbc(sql, cusId,1);
	}

	/**
	 * 通过车辆id查询该客户的默认车的总里程和总油耗
	 * @author sududa
	 * @date 2016年11月2日
	 */
	@Override
	public Map<String, Object> findFirstOneByCarId(String carId) {
		/*String sql = "select * from ("
				   + "select s.F40 as totalMileage,s.F42 as totalOil"
				   + " from CN_SSCLXX s "
				   + " where s.F40 is not null and s.F42 is not null"
				   + " and s.FCLID = ?"
				   + " order by s.ID desc)"
				   + " where rownum = 1";*/
		String sql = "select s.F40 as totalMileage,s.FCLID,s.ID"
				   + " from CN_SSCLXX s,"
				   + "(select s.FCLID,max(s.ID) as maxId"
				   + " from CN_SSCLXX s"
				   + " where s.FCLID = ? and s.F40 is not null"
				   + " group by s.FCLID)ms"
				   + " where s.ID=ms.maxId";
		return findOneForJdbc(sql, carId);
	}
}

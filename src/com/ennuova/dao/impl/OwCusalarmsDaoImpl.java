package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwCusalarmsDao;
import com.ennuova.entity.OwCusalarms;
import com.ennuova.entity.PubAlarmsetting;
import com.ennuova.entity.PubCustomer;
import com.ennuova.util.DButil;

@Repository("owCusalarmsDao")
public class OwCusalarmsDaoImpl extends DaoSupportImpl<OwCusalarms> implements OwCusalarmsDao{

	@Override
	public List<OwCusalarms> queryAlarmsByCus(long cusId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<OwCusalarms> alarmList = new ArrayList<OwCusalarms>();
		String sql = "SELECT ALARM.FNAME,ALARM.FDISCRIBE,ALARM.FTYPE,CA.ID,CA.FCUSTOMER CUSID,CA.FSWITCH,CA.FALARM AID FROM OW_CUSALARMS CA LEFT JOIN PUB_ALARMSETTING ALARM ON CA.FALARM=ALARM.ID WHERE CA.FCUSTOMER='"+cusId+"' and ALARM.B_ISUSE=1";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				OwCusalarms alarm = new OwCusalarms();
				alarm.setId(rs.getLong("ID"));
				alarm.setFname(rs.getString("FNAME"));
				alarm.setFdiscribe(rs.getString("FDISCRIBE"));
				alarm.setFtype(rs.getString("FTYPE"));
				alarm.setCusId(rs.getLong("CUSID"));
				alarm.setFswitch(rs.getLong("FSWITCH"));
				alarm.setaId(rs.getLong("AID"));
				if(alarm.getFswitch().equals(Long.valueOf(1))){
					alarm.setSwVal(true);
				}else{
					alarm.setSwVal(false);
				}
				alarmList.add(alarm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return alarmList;
	}

	@Override
	public int updateSwitch(long id, long sw) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		String sql = "UPDATE OW_CUSALARMS SET FSWITCH='"+sw+"' WHERE ID='"+id+"' ";
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	public OwCusalarms queryAlarmsByCusAndPubAlarmsetting(Long rRecuser,
			Long pubAlarmsettingId) {
		return (OwCusalarms) getSessionFactory().getCurrentSession().createQuery(//
				"from OwCusalarms where pubCustomer=? and pubAlarmsetting=?")//
				.setParameter(0, new PubCustomer(rRecuser))//
				.setParameter(1, new PubAlarmsetting(pubAlarmsettingId))//
				.uniqueResult();
	}

}

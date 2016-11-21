package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.app.config.AppResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.ReserveRepairDao;
import com.ennuova.entity.ReserveRepair;
import com.ennuova.util.DButil;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.ReserveRepairGetVO;
@Repository("reserveRepairDao")
public class ReserveRepairDaoImpl extends BaseDAOImpl<ReserveRepair> implements
		ReserveRepairDao {

	/* 
	 * 车辆维修预约
	 */
	@Override
	public ReserveRepair saveReserveRepair(ReserveRepair reserveRepair) {
		//AppResult appResult = new AppResult(SystemInfo.ADD_SUCCESS.getCode(),SystemInfo.ADD_SUCCESS.getMsg());
		reserveRepair.setCreateDate(new Date());
		//状态
		reserveRepair.setReserveState("0");
		this.save(reserveRepair);
		return reserveRepair;
	}

	/* 
	 * 车辆维修记录
	 */
	@Override
	public List<MainTainVO> getReserveRepair(Long carId,Long maxId) {
	List<MainTainVO> list = new ArrayList<MainTainVO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql="select * from (select s.*, r.reserve_repair_id, d.reserve_repair_detail_id,i.repair_item_name "
				+ "from reserve_status s "
				+ "left join reserve_repair r "
				+ "on s.reserve_id=r.reserve_repair_id "
				+ "left join RESERVE_REPAIR_DETAIL "
				+ "d on r.reserve_repair_id = d.reserve_repair_id"
				+ " left join REPAIR_ITEM i "
				+ "on i.repair_item_id=d.repair_item_id "
				+ "where r.carinfo_id = "+carId+" and s.id<"+maxId+" and s.reserve_type=1 order by id desc) where rownum<11";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MainTainVO mainTainVO = new MainTainVO();
				mainTainVO.setId(rs.getLong("id"));
				mainTainVO.setRdserveId(rs.getLong("RESERVE_ID"));
				mainTainVO.setReserveDate(rs.getTimestamp("RESERVE_DATE"));
				mainTainVO.setRepairName(rs.getString("REPAIR_ITEM_NAME"));
				mainTainVO.setReserveStatus(rs.getInt("RESERVE_STATUS"));
				mainTainVO.setReserveStatusName(rs.getString("RESERVE_STATUS_NAME"));
				mainTainVO.setReserveType(rs.getInt("RESERVE_TYPE"));
				list.add(mainTainVO);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return list;
	}

	/* 
	 * 车辆维修明细
	 */
	@Override
	public ReserveRepair getReserveRepairDetail(ReserveRepair reserveRepair) {
		return this.getById(reserveRepair.getReserveRepairId());
	}

	/* 
	 * 通过ID查找维修预约
	 */
	@Override
	public AppResult findReserveRepairById(Long reserveId) {
		ReserveRepair reserveRepair = this.findUnique("from ReserveRepair where reserveRepairId=?", reserveId);
		if (reserveRepair.getReserveState().equals("4")) {
			return new AppResult(SystemInfo.FOLLOWTRIBE_CANCEL_FAIL.getCode(), SystemInfo.FOLLOWTRIBE_CANCEL_FAIL.getMsg());
		}
		reserveRepair.setReserveState("4");
		this.update(reserveRepair);
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql="update RESERVE_STATUS set RESERVE_STATUS = 4,RESERVE_STATUS_NAME = '已取消' where reserve_type = 1 and RESERVE_ID = "+reserveId;
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return new AppResult(SystemInfo.FOLLOWTRIBE_CANCEL_SUCCESS.getCode(), SystemInfo.FOLLOWTRIBE_CANCEL_SUCCESS.getMsg());
	}

}

package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.app.config.AppResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.ReserveRemewalDao;
import com.ennuova.entity.ReserveRemewal;
import com.ennuova.util.DButil;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.ReserveRemewalVO;
import com.ennuova.vo.UserCompanyVO;
@Repository("reserveRemewalDao")
public class ReserveRemewalDaoImpl extends BaseDAOImpl<ReserveRemewal> implements
		ReserveRemewalDao {

	/* 
	 * 车辆续保预约
	 */
	@Override
	public ReserveRemewal saveReserveRemewal(ReserveRemewal reserveRemewal) {
		this.save(reserveRemewal);
		return reserveRemewal;
	}

	/* 
	 * 车辆续保记录
	 */
	@Override
	public List<MainTainVO> getReserveRemewal(Long carId,Long maxId) {
List<MainTainVO> list = new ArrayList<MainTainVO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql="select * from (select s.*,p.s_name,r.reserve_remewal_id "
				+ "from reserve_status s "
				+ "left join reserve_remewal r "
				+ "on s.reserve_id=r.reserve_remewal_id "
				+ "left join pub_inscompany p "
				+ "on p.id=r.insurance_company_id "
				+ "where r.carinfo_id="+carId+" and s.id<"+maxId+" and s.reserve_type=2 order by s.id desc) where rownum<11";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MainTainVO mainTainVO = new MainTainVO();
				mainTainVO.setId(rs.getLong("id"));
				mainTainVO.setRdserveId(rs.getLong("RESERVE_ID"));
				mainTainVO.setReserveDate(rs.getTimestamp("RESERVE_DATE"));
				mainTainVO.setsName(rs.getString("S_NAME"));
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
	 * 车辆续保明细
	 */
	@Override
	public ReserveRemewal getReserveRemewalDetail(ReserveRemewal reserveRemewal) {
		return this.getById(reserveRemewal.getReserveRemewalId());
	}

	/* 
	 * 根据车辆ID获取保险名称和服务顾问信息
	 */
	@Override
	public UserCompanyVO getInscompanyAndUserByCarinfoId(
			ReserveMaintainVO reserveMaintainVO) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserCompanyVO userCompanyVO = new UserCompanyVO();
		String sql="select t.id as id,t.username as username,t.realname as realname,t.userheadimg as userheadimg,i.s_name as sname,i.id as company_id from t_s_base_user t"
				+ " left join pub_carinfo p "
				+ "on p.user_id=t.id "
				+ "left join pub_inscompany i "
				+ "on i.id=p.finscompany where p.id="+reserveMaintainVO.getCarinfoId();
		System.out.println(sql);
		try {
			ps=conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				userCompanyVO.setId(rs.getString("id"));
				userCompanyVO.setRealname(rs.getString("realname"));
				userCompanyVO.setSname(rs.getString("sname"));
				userCompanyVO.setUserheadimg(rs.getString("userheadimg"));
				userCompanyVO.setUsername(rs.getString("username"));
				userCompanyVO.setCompanyId(rs.getLong("company_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		
		return userCompanyVO;
	}

	/* 
	 * 根据ID查询续保预约
	 */
	@Override
	public AppResult findReserveRemewalById(Long reserveId) {
		ReserveRemewal reserveRemewal = this.findUnique("from ReserveRemewal where reserveRemewalId=?", reserveId);
		if (reserveRemewal.getReserveState().equals("4")) {
			return new AppResult(SystemInfo.FOLLOWTRIBE_CANCEL_FAIL.getCode(), SystemInfo.FOLLOWTRIBE_CANCEL_FAIL.getMsg());
		}
		reserveRemewal.setReserveState("4");
		this.update(reserveRemewal);
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql="update RESERVE_STATUS set RESERVE_STATUS = 4,RESERVE_STATUS_NAME = '已取消' where reserve_type = 2 and RESERVE_ID = "+reserveId;
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

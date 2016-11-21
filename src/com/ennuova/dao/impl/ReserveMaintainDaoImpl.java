package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ennuova.app.config.AppResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.ReserveMaintainDao;
import com.ennuova.entity.MaintainPlay;
import com.ennuova.entity.ReserveMaintain;
import com.ennuova.entity.ReserveMaintainItem;
import com.ennuova.entity.TSBaseUser;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.DButil;
import com.ennuova.util.MyBeanUtils;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainItemVO;
import com.ennuova.vo.ReserveMaintainPlanVO;
import com.ennuova.vo.ReserveMaintainVO;
@Repository("reserveMaintainDao")
public class ReserveMaintainDaoImpl extends BaseDAOImpl<ReserveMaintain> implements
		ReserveMaintainDao {

	/**
	 * 根据里程数获取车辆推荐保养信息
	 * @param km里程数 
	 * @return
	 */
	@Override
	public ReserveMaintainPlanVO getReserveMaintainVO(String km, TSBaseUser tsBaseUser) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection(); 
		PreparedStatement ps = null;
		ResultSet rs = null;
		int milage = ConvertTools.stringToInt(km)/10;
		km = ConvertTools.intToString(milage);
		System.out.println("真实里程："+km);
		//获取推荐方案
		String sql="select * from"
				+ "(select t.maintain_play_id as maintainPlayId, t.maintain_play_milage_num as maintainPlayMilageNum, abs(t.maintain_play_milage_num-"+km+") as km from"
				+ " MAINTAIN_PLAY t order by km,t.maintain_play_milage_num) "
				+ "where rownum=1";
		System.out.println(sql);
		MaintainPlay maintainPlay = new MaintainPlay();
		List<ReserveMaintainItemVO> reserveMaintainItemVOs = new ArrayList<ReserveMaintainItemVO>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				maintainPlay.setKm(rs.getInt("km"));
				maintainPlay.setMaintainPlayId(rs.getLong("maintainPlayId"));
				maintainPlay.setMaintainPlayMilageNum(rs.getInt("maintainPlayMilageNum"));
			}
			//获取推荐方案对应的保养项目
			long playId=maintainPlay.getMaintainPlayId();
			String sqlString="select * from "
					+ "maintain_item m "
					+ "where m.maintain_item_id in "
					+ "(select i.maintain_item_id from maintain_play_item i where i.maintain_play_id="+playId+")";
			System.out.println(sqlString);
			ps=conn.prepareStatement(sqlString);
			rs = null;
			rs=ps.executeQuery();
			while(rs.next()){
				ReserveMaintainItemVO itemVO = new ReserveMaintainItemVO();
				itemVO.setMaintainItemId(rs.getLong("maintain_item_id"));
				itemVO.setMaintainItemName(rs.getString("maintain_item_name"));
				reserveMaintainItemVOs.add(itemVO);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		//组装方案和项目信息
		ReserveMaintainPlanVO reserveMaintainVO=new ReserveMaintainPlanVO(tsBaseUser,maintainPlay.getMaintainPlayId(), maintainPlay.getMaintainPlayMilageNum(), km, reserveMaintainItemVOs);
		return reserveMaintainVO;
	}

	/* 
	 * 保存车辆保养预约信息
	 */
	@Override
	public ReserveMaintain saveReserveMaintain(ReserveMaintainVO reserveMaintainVO) {
		//AppResult appResult = new AppResult(SystemInfo.ADD_SUCCESS.getCode(),SystemInfo.ADD_SUCCESS.getMsg());
		ReserveMaintain reserveMaintain=new ReserveMaintain();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(reserveMaintainVO, reserveMaintain);
		} catch (Exception e) {
			e.printStackTrace();
		}
		reserveMaintain.setCreateDate(new Date());
		//待审核
		reserveMaintain.setReserveState("0");
		Set<ReserveMaintainItem> reserveMaintainItems=new HashSet<ReserveMaintainItem>();
		String paramItemId=reserveMaintainVO.getMaintainItemId();
		System.out.println("测试："+paramItemId);
		if (paramItemId.contains(",")) {
			for (String item : paramItemId.split(",")) {
				ReserveMaintainItem reserveMaintainItem=new ReserveMaintainItem();
				reserveMaintainItem.setMaintainItemId(Long.parseLong(item));
				reserveMaintainItem.setReserveMaintain(reserveMaintain);
				reserveMaintainItems.add(reserveMaintainItem);
			}
		}else{ReserveMaintainItem reserveMaintainItem=new ReserveMaintainItem();
		reserveMaintainItem.setMaintainItemId(Long.parseLong(paramItemId));
		reserveMaintainItem.setReserveMaintain(reserveMaintain);
		reserveMaintainItems.add(reserveMaintainItem);
		}
		reserveMaintain.setReserveMaintainItems(reserveMaintainItems);
		/*//保存预约状态信息
		ReserveStatus reserveStatus = new ReserveStatus();
		reserveStatus.setReserveDate(new Date());
		reserveStatus.setReserveId(reserveMaintain.getReserveMaintainId());
		reserveStatus.setReserveStatus(0);
		reserveStatus.setReserveType(0);
		reserveStatus.setReserveStatusName("待审核");*/
		this.save(reserveMaintain);
		return reserveMaintain;
	}

	/* 
	 * 车辆保养记录ReserveMaintainGetVO
	 */
	@Override
	public List<MainTainVO> getReserveMaintain(Long carId,Long maxId){
		List<MainTainVO> list = new ArrayList<MainTainVO>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql=" select * from ( select s.*,r.reserve_maintain_milage from reserve_status s left join reserve_maintain r"+
         " on s.reserve_id=r.reserve_maintain_id where r.carinfo_id="+carId+" and s.id<"+maxId+
        " and s.reserve_type=0 order by id desc) where rownum <11";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MainTainVO mainTainVO = new MainTainVO();
				mainTainVO.setId(rs.getLong("id"));
				mainTainVO.setRdserveId(rs.getLong("RESERVE_ID"));
				mainTainVO.setReserveDate(rs.getTimestamp("RESERVE_DATE"));
				mainTainVO.setReserveMaintainMilage(rs.getInt("RESERVE_MAINTAIN_MILAGE"));
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
	 * 车辆保养明细
	 */
	@Override
	public ReserveMaintain getReserveMaintainDetail(
			ReserveMaintainVO reserveMaintainVO) {
		ReserveMaintain reserveMaintain = new ReserveMaintain();
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql="select * from reserve_maintain where RESERVE_MAINTAIN_ID = "+reserveMaintainVO.getReserveMaintainId();
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				reserveMaintain.setReserveMaintainId(rs.getLong("RESERVE_MAINTAIN_ID"));
				reserveMaintain.setMaintainPlayId(rs.getLong("MAINTAIN_PLAY_ID"));
				reserveMaintain.setReserveName(rs.getString("RESERVE_NAME"));
				reserveMaintain.setReserveTel(rs.getString("RESERVE_TEL"));
				reserveMaintain.setReserveState(rs.getString("RESERVE_STATE"));
				reserveMaintain.setReserveDate(rs.getTimestamp("RESERVE_DATE"));
				reserveMaintain.setReserveDateString(dateFormat.format(reserveMaintain.getReserveDate()));
				reserveMaintain.setReserveMaintainMilage(rs.getInt("RESERVE_MAINTAIN_MILAGE"));
				reserveMaintain.setReserveAddress(rs.getString("RESERVE_ADDRESS"));
				reserveMaintain.setReserveContent(rs.getString("RESERVE_CONTENT"));
				reserveMaintain.setReserveMaintainHomeCar(rs.getString("RESERVE_MAINTAIN_HOME_CAR"));
			}
		} catch (Exception e) {
				e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return reserveMaintain;
	}

	/* 
	 * 通过ID查找保养预约
	 */
	@Override
	public AppResult findReserveMaintainById(Long reserveId) {
		//如果已取消
		ReserveMaintain reserveMaintain = this.findUnique("from ReserveMaintain where reserveMaintainId=?", reserveId);
		if (reserveMaintain.getReserveState().equals("4")) {
			return new AppResult(SystemInfo.FOLLOWTRIBE_CANCEL_FAIL.getCode(), SystemInfo.FOLLOWTRIBE_CANCEL_FAIL.getMsg());
		}
		//更新
		reserveMaintain.setReserveState("4");
		this.update(reserveMaintain);
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		String sql="update RESERVE_STATUS set RESERVE_STATUS = 4,RESERVE_STATUS_NAME = '已取消' where reserve_type = 0 and RESERVE_ID = "+reserveId;
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

package com.ennuova.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.config.AppResult;
import com.ennuova.app.config.SystemInfo;
import com.ennuova.app.service.ReserveMaintainService;
import com.ennuova.dao.CnSsclxxDao;
import com.ennuova.dao.ReserveMaintainDao;
import com.ennuova.dao.ReserveStatusDao;
import com.ennuova.dao.TSBaseUserDao;
import com.ennuova.entity.ReserveMaintain;
import com.ennuova.entity.ReserveRepair;
import com.ennuova.entity.ReserveStatus;
import com.ennuova.entity.TSBaseUser;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainPlanVO;
import com.ennuova.vo.ReserveMaintainVO;
@Service("reserveMaintainService")
public class ReserveMaintainServiceImpl implements ReserveMaintainService {
	/**车辆保养DAO */
	@Resource
	private ReserveMaintainDao reserveMaintainDao;
	/**预约保养项目关联表 */
	/*@Resource
	private reserveMaintainItemDao reserveMaintainItemDao;*/
	/**车辆实时数据DAO */
	@Resource
	private CnSsclxxDao cnSsclxxDao;
	/**用户数据DAO */
	@Resource
	private TSBaseUserDao tsBaseUserDao;
	/**预约状态DAO */
	@Resource 
	private ReserveStatusDao reserveStatusDao;
	/* 
	 * 获取该车辆推荐的保养方案
	 */
	@Override
	public ReserveMaintainPlanVO getReserveMaintainVO(long carId) {
		//获取ID里程数
		String km=cnSsclxxDao.getKmByCarinfoId(carId);
		//根据车辆ID获取服务顾问信息
		TSBaseUser tsBaseUser = tsBaseUserDao.getTsBaseUser(carId);
		return reserveMaintainDao.getReserveMaintainVO(km,tsBaseUser);
	}

	/* 
	 * 车辆保养预约
	 */
	@Override
	public AppResult addReserveMaintain(ReserveMaintainVO reserveMaintainVO) {
		reserveMaintainVO.setReserveDate(new Date(ConvertTools.stringToLong(reserveMaintainVO.getReserveDateString())));
		ReserveMaintain reserveMaintain = reserveMaintainDao.saveReserveMaintain(reserveMaintainVO);
		//保存预约状态信息
		ReserveStatus reserveStatus = new ReserveStatus();
		reserveStatus.setReserveDate(new Date());
		reserveStatus.setReserveId(reserveMaintain.getReserveMaintainId());
		reserveStatus.setReserveStatus(0);
		reserveStatus.setReserveType(0);
		reserveStatus.setReserveStatusName("待审核");
		this.reserveStatusDao.saveReserveStatus(reserveStatus);
		return new AppResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg());
	}

	/* 
	 * 车辆保养记录
	 */
	@Override
	public List<MainTainVO> getReserveMaintain(Long carId,Long maxId) {
		List<MainTainVO> mainTainVOs = new ArrayList<MainTainVO>();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mainTainVOs = reserveMaintainDao.getReserveMaintain(carId,maxId);
			for (MainTainVO mainTainVO : mainTainVOs) {
				mainTainVO.setReserveDateFormat(dateFormat.format(mainTainVO.getReserveDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainTainVOs;
	}

	/* 
	 * 车辆保养明细
	 */
	@Override
	public ReserveMaintain getReserveMaintainDetail(
			ReserveMaintainVO reserveMaintainVO) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ReserveMaintain repair = reserveMaintainDao.getReserveMaintainDetail(reserveMaintainVO);
		repair.setReserveDateString(dateFormat.format(repair.getReserveDate()));
		return repair;
	}

}

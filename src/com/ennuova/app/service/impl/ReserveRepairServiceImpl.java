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
import com.ennuova.app.service.ReserveRepairService;
import com.ennuova.dao.ReserveRepairDao;
import com.ennuova.dao.ReserveStatusDao;
import com.ennuova.entity.ReserveRepair;
import com.ennuova.entity.ReserveStatus;
import com.ennuova.util.ConvertTools;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.ReserveRepairGetVO;
@Service("reserveRepairService")
public class ReserveRepairServiceImpl implements ReserveRepairService {
	@Resource
	private ReserveRepairDao reserveRepairDao;
	@Resource
	private ReserveStatusDao reserveStatusDao;

	/* 
	 * 车辆维修预约
	 */
	@Override
	public AppResult addReserveRepair(ReserveRepair reserveRepair) {
		reserveRepair.setReserveDate(new Date(ConvertTools.stringToLong(reserveRepair.getReserveDateString())));
		ReserveRepair reserveRepair2= reserveRepairDao.saveReserveRepair(reserveRepair);
		//保存预约状态信息
		ReserveStatus reserveStatus = new ReserveStatus();
		reserveStatus.setReserveDate(new Date());
		reserveStatus.setReserveId(reserveRepair2.getReserveRepairId());
		reserveStatus.setReserveStatus(0);
		reserveStatus.setReserveType(1);
		reserveStatus.setReserveStatusName("待审核");
		try {
			this.reserveStatusDao.saveReserveStatus(reserveStatus);
		} catch (Exception e) {
			return new AppResult(SystemInfo.ADD_FAIL.getCode(), SystemInfo.ADD_FAIL.getMsg());
		}
		
		return new AppResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg());
	}

	/* 
	 * 车辆维修记录
	 */
	@Override
	public List<MainTainVO> getReserveRepair(Long carInfoId,Long maxId) {
		List<MainTainVO> mainTainVOs = new ArrayList<MainTainVO>();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mainTainVOs = reserveRepairDao.getReserveRepair(carInfoId, maxId);
			for (MainTainVO mainTainVO : mainTainVOs) {
				mainTainVO.setReserveDateFormat(dateFormat.format(mainTainVO.getReserveDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainTainVOs;
	}

	/* 
	 * 车辆维修明细
	 */
	@Override
	public ReserveRepair getReserveRepairDetail(ReserveRepair reserveRepair) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ReserveRepair repair = reserveRepairDao.getReserveRepairDetail(reserveRepair);
		repair.setReserveDateString(dateFormat.format(repair.getReserveDate()));
		
		return repair;
	}
}

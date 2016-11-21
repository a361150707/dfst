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
import com.ennuova.app.service.ReserveRemewalService;
import com.ennuova.dao.ReserveRemewalDao;
import com.ennuova.dao.ReserveStatusDao;
import com.ennuova.entity.ReserveRemewal;
import com.ennuova.entity.ReserveStatus;
import com.ennuova.util.ConvertTools;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.UserCompanyVO;
@Service("reserveRemewalService")
public class ReserveRemewalServiceImpl implements ReserveRemewalService {
	@Resource
	private ReserveRemewalDao reserveRemewalDao;
	@Resource
	private ReserveStatusDao reserveStatusDao;

	/* 
	 * 车辆续保预约
	 */
	@Override
	public AppResult addReserveRemewal(ReserveRemewal reserveRemewal) {
		try {
			reserveRemewal.setReserveDate(new Date(ConvertTools.stringToLong(reserveRemewal.getReserveDateString())));
			System.out.println(new Date(ConvertTools.stringToLong(reserveRemewal.getReserveDateString())));
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(dateFormat.format(new Date(ConvertTools.stringToLong(reserveRemewal.getReserveDateString()))));
			ReserveRemewal reserveRemewal2=reserveRemewalDao.saveReserveRemewal(reserveRemewal);
			//保存预约状态信息
			ReserveStatus reserveStatus = new ReserveStatus();
			reserveStatus.setReserveDate(new Date());
			reserveStatus.setReserveId(reserveRemewal2.getReserveRemewalId());
			reserveStatus.setReserveStatus(0);
			reserveStatus.setReserveType(2);
			reserveStatus.setReserveStatusName("待审核");
			this.reserveStatusDao.saveReserveStatus(reserveStatus);
		} catch (Exception e) {
			return new AppResult(SystemInfo.ADD_FAIL.getCode(), SystemInfo.ADD_FAIL.getMsg());
		}
		
		return new AppResult(SystemInfo.ADD_SUCCESS.getCode(), SystemInfo.ADD_SUCCESS.getMsg());
	}

	/* 
	 * 车辆续保记录
	 */
	@Override
	public List<MainTainVO> getReserveRemewal(Long carId,Long maxId) {
		List<MainTainVO> mainTainVOs = new ArrayList<MainTainVO>();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mainTainVOs = reserveRemewalDao.getReserveRemewal(carId, maxId);
			for (MainTainVO mainTainVO : mainTainVOs) {
				mainTainVO.setReserveDateFormat(dateFormat.format(mainTainVO.getReserveDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainTainVOs;
	}

	/* 
	 * 根据车辆ID获取保险名称和服务顾问信息
	 */
	@Override
	public UserCompanyVO getInscompanyAndUserByCarinfoId(
			ReserveMaintainVO reserveMaintainVO) {
		return reserveRemewalDao.getInscompanyAndUserByCarinfoId(reserveMaintainVO);
	}

	/* 
	 * 车辆续保明细
	 */
	@Override
	public ReserveRemewal getReserveRemewalDetail(ReserveRemewal reserveRemewal) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ReserveRemewal repair = reserveRemewalDao.getReserveRemewalDetail(reserveRemewal);
		repair.setReserveDateString(dateFormat.format(repair.getReserveDate()));
		return repair;
	}
}

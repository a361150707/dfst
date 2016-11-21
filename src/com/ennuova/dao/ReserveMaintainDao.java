package com.ennuova.dao;

import java.util.List;

import com.ennuova.app.config.AppResult;
import com.ennuova.entity.ReserveMaintain;
import com.ennuova.entity.TSBaseUser;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainPlanVO;
import com.ennuova.vo.ReserveMaintainVO;

public interface ReserveMaintainDao {

	/**
	 * 根据里程数获取车辆推荐保养信息
	 * @param km里程数 
	 * @return
	 */
	ReserveMaintainPlanVO getReserveMaintainVO(String km, TSBaseUser tsBaseUser);

	/**
	 * 保存车辆保养预约信息
	 * @param carId
	 * @param maintainPlayId
	 * @param f40
	 * @param maintainItemId
	 * @return
	 */
	ReserveMaintain saveReserveMaintain(ReserveMaintainVO reserveMaintainVO);

	/**
	 * 车辆保养记录
	 * @param carId
	 * @return
	 */
	List<MainTainVO> getReserveMaintain(Long carId,Long maxId);

	/**
	 * 车辆保养明细
	 * @param reserveMaintainVO
	 * @return
	 */
	ReserveMaintain getReserveMaintainDetail(ReserveMaintainVO reserveMaintainVO);

	/**
	 * 通过ID查找保养预约
	 * @param reserveId
	 * @return
	 */
	AppResult findReserveMaintainById(Long reserveId);


}

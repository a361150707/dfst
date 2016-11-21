package com.ennuova.app.service;

import java.util.List;

import com.ennuova.app.config.AppResult;
import com.ennuova.entity.ReserveMaintain;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainPlanVO;
import com.ennuova.vo.ReserveMaintainVO;

public interface ReserveMaintainService {
	
	/**
	 * 获取该车辆推荐的保养方案
	 * @param carId
	 * @return
	 */
	ReserveMaintainPlanVO getReserveMaintainVO(long carId);

	/**
	 * 车辆保养预约
	 * @param reserveMaintainVO
	 * @return
	 */
	AppResult addReserveMaintain(ReserveMaintainVO reserveMaintainVO);

	/**车辆保养记录
	 * @param carId
	 * @param maxId
	 * @return
	 */
	List<MainTainVO> getReserveMaintain(Long carId, Long maxId);

	/**
	 * 车辆保养明细
	 * @param reserveMaintainVO
	 * @return
	 */
	ReserveMaintain getReserveMaintainDetail(ReserveMaintainVO reserveMaintainVO);


}

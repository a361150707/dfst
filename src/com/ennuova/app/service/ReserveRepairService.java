package com.ennuova.app.service;

import java.util.List;

import com.ennuova.app.config.AppResult;
import com.ennuova.entity.ReserveRepair;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.ReserveRepairGetVO;


public interface ReserveRepairService {

	/**
	 * 车辆维修预约
	 * @param reserveRepair
	 * @return
	 */
	AppResult addReserveRepair(ReserveRepair reserveRepair);

	/**车辆维修记录
	 * @param carId
	 * @param maxId
	 * @return List<ReserveRepair>
	 */
	List<MainTainVO> getReserveRepair(Long carId,Long maxId);

	/**
	 * 车辆维修明细
	 * @param reserveRepair
	 * @return
	 */
	ReserveRepair getReserveRepairDetail(ReserveRepair reserveRepair);


}

package com.ennuova.dao;

import java.util.List;
import com.ennuova.app.config.AppResult;
import com.ennuova.entity.ReserveRepair;
import com.ennuova.vo.MainTainVO;

public interface ReserveRepairDao {

	/**
	 * 车辆维修预约
	 * @param reserveRepair
	 * @return
	 */
	ReserveRepair saveReserveRepair(ReserveRepair reserveRepair);


	/**车辆维修记录
	 * @param carInfoId
	 * @param maxId
	 * @return List<ReserveRepairGetVO>
	 */
	List<MainTainVO> getReserveRepair(Long carInfoId,Long maxId);

	/**
	 * 车辆维修明细
	 * @param reserveRepair
	 * @return
	 */
	ReserveRepair getReserveRepairDetail(ReserveRepair reserveRepair);

	/**通过ID查找维修预约
	 * @param reserveId
	 * @return
	 */
	AppResult findReserveRepairById(Long reserveId);

}

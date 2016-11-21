package com.ennuova.app.service;

import java.util.List;

import com.ennuova.app.config.AppResult;
import com.ennuova.entity.ReserveRemewal;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.UserCompanyVO;

public interface ReserveRemewalService {

	/**
	 * 车辆续保预约
	 * @param reserveRemewal
	 * @return
	 */
	AppResult addReserveRemewal(ReserveRemewal reserveRemewal);
	/**车辆续保记录
	 * @param carId
	 * @param maxId
	 * @return List<MainTainVO>
	 */
	List<MainTainVO> getReserveRemewal(Long carId,Long maxId);

	/**
	 * 根据车辆ID获取保险名称和服务顾问信息
	 * @param reserveMaintainVO
	 * @return
	 */
	UserCompanyVO getInscompanyAndUserByCarinfoId(ReserveMaintainVO reserveMaintainVO);

	/**
	 * 车辆续保明细
	 * @param reserveRemewal
	 * @return
	 */
	ReserveRemewal getReserveRemewalDetail(ReserveRemewal reserveRemewal);

}

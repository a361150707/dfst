package com.ennuova.dao;

import java.util.List;
import com.ennuova.app.config.AppResult;
import com.ennuova.entity.ReserveRemewal;
import com.ennuova.util.PageBean;
import com.ennuova.vo.MainTainVO;
import com.ennuova.vo.ReserveMaintainVO;
import com.ennuova.vo.ReserveRemewalVO;
import com.ennuova.vo.UserCompanyVO;

public interface ReserveRemewalDao {

	/**
	 * 车辆续保预约
	 * @param reserveRemewal
	 * @return
	 */
	ReserveRemewal saveReserveRemewal(ReserveRemewal reserveRemewal);
	/**车辆续保记录
	 * @param carId
	 * @param maxId
	 * @return List<MainTainVO>
	 */
	List<MainTainVO> getReserveRemewal(Long carId,Long maxId);

	/**
	 * 车辆续保明细
	 * @param reserveRemewal
	 * @return
	 */
	ReserveRemewal getReserveRemewalDetail(ReserveRemewal reserveRemewal);

	/**
	 * 根据车辆ID获取保险名称和服务顾问信息
	 * @param reserveMaintainVO
	 * @return
	 */
	UserCompanyVO getInscompanyAndUserByCarinfoId(
			ReserveMaintainVO reserveMaintainVO);

	/**
	 * 根据ID查询续保预约
	 * @param reserveId
	 * @return
	 */
	AppResult findReserveRemewalById(Long reserveId);

}

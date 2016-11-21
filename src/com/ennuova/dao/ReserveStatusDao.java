package com.ennuova.dao;

import com.ennuova.entity.ReserveStatus;

public interface ReserveStatusDao {

	/**
	 * 保存预约状态信息
	 * @param reserveStatus
	 */
	void saveReserveStatus(ReserveStatus reserveStatus);

}

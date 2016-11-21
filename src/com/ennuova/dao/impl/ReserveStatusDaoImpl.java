package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.ReserveStatusDao;
import com.ennuova.entity.ReserveStatus;
@Repository("reserveStatusDao")
public class ReserveStatusDaoImpl extends BaseDAOImpl<ReserveStatus> implements
		ReserveStatusDao {

	@Override
	public void saveReserveStatus(ReserveStatus reserveStatus) {
		this.save(reserveStatus);
	}

}

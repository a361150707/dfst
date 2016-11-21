package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.StaffChangeRecordDao;
import com.ennuova.entity.StaffChangeRecordEntity;

@Repository("staffChangeRecordDao")
public class StaffChangeRecordDaoImpl extends BaseDAOImpl<StaffChangeRecordEntity> implements
		StaffChangeRecordDao {

	/**
	 * 保存员工的变更记录
	 *@author sududa
	 *@date 2016年9月14日
	 * @param staffChangeRecordEntity
	 */
	@Override
	public StaffChangeRecordEntity saveStaffChangeRecoed(StaffChangeRecordEntity staffChangeRecordEntity) {
		return this.save(staffChangeRecordEntity);
	}



}

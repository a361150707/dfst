package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.ReserveStateChangeRecordDao;
import com.ennuova.entity.ReserveStatusChangeRecordEntity;

@Repository("reserveStateChangeRecordDao")
public class ReserveStateChangeRecordDaoImpl extends BaseDAOImpl<ReserveStatusChangeRecordEntity> implements
		ReserveStateChangeRecordDao {

}

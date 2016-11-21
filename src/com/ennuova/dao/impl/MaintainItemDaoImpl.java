package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.BaseDAOImpl;
import com.ennuova.dao.MaintainItemDao;
import com.ennuova.entity.MaintainItem;
@Repository("maintainItemDao")
public class MaintainItemDaoImpl extends BaseDAOImpl<MaintainItem> implements
		MaintainItemDao {

}

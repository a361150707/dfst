package com.ennuova.dao;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubOdbvehicle;

/**
 * ODB车型DAO
 */
public interface PubOdbvehicleDao extends DaoSupport<PubOdbvehicle>{

	void bachSave(String line);

}

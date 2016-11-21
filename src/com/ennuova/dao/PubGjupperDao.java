package com.ennuova.dao;

import java.util.List;

import com.ennuova.base.DaoSupport;
import com.ennuova.entity.PubGjupper;

/**
 * obd固件版本查询DAO
 * @author dofastlwj
 */
public interface PubGjupperDao extends DaoSupport<PubGjupper>{

	List<PubGjupper> getBySbxlh(String sbxlh);

}

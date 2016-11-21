package com.ennuova.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubGjupperDao;
import com.ennuova.entity.CnSbljinfo;
import com.ennuova.entity.PubGjupper;

/**
 * obd固件版本查询DAOIMPL
 * @author dofastlwj
 */
@Repository("pubGjupperDao")
public class PubGjupperDaoImpl extends DaoSupportImpl<PubGjupper> implements PubGjupperDao{

	/**
	 * 根据序列号查询各个模块
	 */
	public List<PubGjupper> getBySbxlh(String sbxlh) {
		return getSessionFactory().getCurrentSession()
				.createQuery(//
						"from  PubGjupper where sbxlh=?")
				.setParameter(0, sbxlh)//
				.list();
	}

}

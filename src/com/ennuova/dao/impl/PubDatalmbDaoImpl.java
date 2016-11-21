package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubDatalmbDao;
import com.ennuova.entity.PubDatalmb;

/**
 * 数据流DAoimpl
 * @author dofast-pc
 */
@Repository("pubDatalmbDao")
public class PubDatalmbDaoImpl extends DaoSupportImpl<PubDatalmb> implements PubDatalmbDao{

	/**
	 * 根据编号查询
	 * @param fcode
	 * @return
	 */
	public PubDatalmb findByFcode(String fcode) {
		return (PubDatalmb) getSessionFactory().getCurrentSession().createQuery(//
				"from PubDatalmb where fcode=?")//
				.setParameter(0, fcode)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

}

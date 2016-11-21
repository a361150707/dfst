package com.ennuova.dao.impl;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CnGzmDao;
import com.ennuova.entity.CnGzm;

/**
 * 故障码表dao
 * @author janson
 */
@Repository("cnGzmDao")
public class CnGzmDaoImpl extends DaoSupportImpl<CnGzm> implements CnGzmDao{

	/**
	 * 根据故障码查询
	 * @param faultcode
	 * @return
	 */
	public CnGzm findByFno(String faultcode) {
		return (CnGzm) getSessionFactory().getCurrentSession().createQuery(//
				"from CnGzm where fno=?")//
				.setParameter(0, faultcode)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

}

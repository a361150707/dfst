package com.ennuova.app.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ennuova.app.service.PubBdstoreService;
import com.ennuova.dao.PubBdstoreDao;
import com.ennuova.entity.PubBdstore;
import com.ennuova.entity.PubCustomer;

@Service("pubBdstoreService")
public class PubBdstoreServiceImpl implements PubBdstoreService{

	@Resource
	private PubBdstoreDao pubBdstoreDao;
	
	@Override
	public int updateDefault(long cusId) {
		int result = -1;
		try {
			result = pubBdstoreDao.updateDefault(cusId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long getIsExistId(long storeId, long cusId) {
		long result = -1;
		try {
			result = pubBdstoreDao.getIsExistId(storeId, cusId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateCusDefault(long id) {
		int result = -1;
		try {
			result = pubBdstoreDao.updateCusDefault(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int save(long storeId,long cusId) {
		BigDecimal def = BigDecimal.valueOf(1);
		int result = -1;
		try {
			PubBdstore bdStore = new PubBdstore();
			bdStore.setFdefault(def);
			bdStore.setFstore(storeId);
			PubCustomer cus=new PubCustomer();
			cus.setId(cusId);
			bdStore.setPubCustomer(cus);
			pubBdstoreDao.save(bdStore);
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delCusStore(long id) {
		boolean bool = false;
		try {
			pubBdstoreDao.delete(id);
			bool = true;
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 根据客户的id查找客户绑定的门店id
	 *@author sududa
	 *@date 2016年10月8日
	 * @param customerId
	 * @return
	 */
	@Override
	public Map<String, Object> findByCusId(long customerId) {
		return pubBdstoreDao.findByCusId(customerId);
	}

}

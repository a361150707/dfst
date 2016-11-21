package com.ennuova.app.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.CustomerVehiclemodelService;
import com.ennuova.dao.CustomerVehiclemodelDao;
import com.ennuova.dao.PubVehicleModelDao;
import com.ennuova.entity.CustomerVehiclemodel;

@Service("cusModelService")
public class CustomerVehiclemodelServiceImpl implements CustomerVehiclemodelService{

	@Resource
	private CustomerVehiclemodelDao cusModelDao;
	
	@Resource
	private PubVehicleModelDao pubModelDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<CustomerVehiclemodel> queryCusModel(long cusId) {
		return cusModelDao.queryCusModel(cusId);
	}

	@Override
	public int saveCusModel(CustomerVehiclemodel cusModel) {
		try {
			List<CustomerVehiclemodel> customerVehiclemodels = cusModelDao.queryUserIsFollow(cusModel.getCustomerId(),cusModel.getModel().getId());
			if(customerVehiclemodels.size()>0){
				cusModelDao.delete(customerVehiclemodels.get(0).getCustomerVehiclemodelId());
				return -1;
			}
			//对关注的车型进行保存
			cusModelDao.save(cusModel);
			//并对车型的关注数做更新
			pubModelDao.updateAttentionNum(cusModel.getModel().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int delCusModel(long cusModelId) {
		try {
			cusModelDao.delete(cusModelId);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	
}

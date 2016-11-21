package com.ennuova.app.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.app.service.PubVehicleModelService;
import com.ennuova.dao.CustomerVehiclemodelDao;
import com.ennuova.dao.PubVehicleModelDao;
import com.ennuova.dao.PubVehiclemodelBorrowDao;
import com.ennuova.entity.CustomerVehiclemodel;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.entity.PubVehiclemodelBorrow;
import com.ennuova.util.UrlUtil;

@Service("pubModelService")
public class PubVehicleModelServiceImpl implements PubVehicleModelService {

	//车型管理Dao
	@Resource
	private PubVehicleModelDao pubModelDao;
	
	@Resource
	private CustomerVehiclemodelDao cusModelDao;
	
	@Resource
	private PubVehiclemodelBorrowDao modelBorrowDao;

	@Transactional(readOnly=true)
	@Override
	public PubVehiclemodel getModel(long id,long customerId){
		//PubVehiclemodel pubVehiclemodel = pubModelDao.getModel(id);
		PubVehiclemodel pubVehiclemodel = pubModelDao.getById(id);
		System.out.println("dddddddddddddd"+pubVehiclemodel.getFareas());
		String imgUrl = UrlUtil.getInstance().getImgurl();
		pubVehiclemodel.setImgStr(imgUrl+pubVehiclemodel.getPubLine().getsPicture());
		pubVehiclemodel.setPubLine(null);
		pubVehiclemodel.setPubBrand(null);
		pubVehiclemodel.setPubCarinfos(null);
		List<CustomerVehiclemodel> customerVehiclemodels = cusModelDao.queryUserIsFollow(customerId,id);
		if(customerVehiclemodels.size()>0){
			pubVehiclemodel.setIsFollow(1);
		}else{
			pubVehiclemodel.setIsFollow(0);
		}
			
		return pubVehiclemodel;
	}


	@Transactional(readOnly=true)
	@Override
	public List<PubVehiclemodel> queryModel(PubVehiclemodel model,long cusId,int type) {
		List<PubVehiclemodel> modelList = pubModelDao.queryModel(model, type);
		//获取是否关注车型
		if(cusId > 0){
			List<CustomerVehiclemodel> cusModelList = cusModelDao.queryCusModel(cusId);
			if(cusModelList.size() > 0){
				for(int i=0;i < cusModelList.size();i++){
					for(int j=0;j<modelList.size();j++){
						if(cusModelList.get(i).getModel().getId().equals(modelList.get(j).getId())){
							modelList.get(j).setAttentId(cusModelList.get(i).getCustomerVehiclemodelId());
							break;
						}
					}
				}				
			}
		}
		return modelList;
	}


	@Override
	public int updateBorrowNum(long id) {
		//更新浏览次数
		int result = pubModelDao.updateBorrowNum(id);
		PubVehiclemodelBorrow mborrow = new PubVehiclemodelBorrow();
		mborrow.setCreateDate(new Date());
		mborrow.setVehiclemodelId(id);
		//记录浏览记录
		modelBorrowDao.save(mborrow);
		return result;
	}


	@Override
	public int updateAttentionNum(long id) {
		return pubModelDao.updateAttentionNum(id);
	}


}

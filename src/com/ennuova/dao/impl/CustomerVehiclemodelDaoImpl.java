package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.CustomerVehiclemodelDao;
import com.ennuova.entity.CustomerVehiclemodel;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

@Repository("cusModelDao")
public class CustomerVehiclemodelDaoImpl extends DaoSupportImpl<CustomerVehiclemodel> implements CustomerVehiclemodelDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerVehiclemodel> queryCusModel(long cusId) {
		String hql = " from CustomerVehiclemodel v where v.customerId = :cusid order by customerVehiclemodelId desc";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setLong("cusid", cusId);
		List<CustomerVehiclemodel> cusModelList = (List<CustomerVehiclemodel>)query.list();
		String imgUrl = UrlUtil.getInstance().getImgurl();
		for (CustomerVehiclemodel customerVehiclemodel : cusModelList) {
			customerVehiclemodel.getModel().setPubBrand(null);
			customerVehiclemodel.getModel().setPubCarinfos(null);
			try {
				customerVehiclemodel.getModel().setImgStr(imgUrl+customerVehiclemodel.getModel().getPubLine().getsPicture());
			} catch (Exception e) {
				
			}
			
			customerVehiclemodel.getModel().setPubLine(null);
		}
		return (List<CustomerVehiclemodel>)query.list();
	}

	@Override
	public List<CustomerVehiclemodel> queryUserIsFollow(Long userId, Long vehileId) {
		String hql = " from CustomerVehiclemodel v where v.customerId = :cusid and v.model = :vmodel";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setLong("cusid", userId);
		query.setLong("vmodel", vehileId);
		List<CustomerVehiclemodel> cusModelList = (List<CustomerVehiclemodel>)query.list();
		System.out.println(cusModelList.size());
		return cusModelList;
	}

}

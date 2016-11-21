package com.ennuova.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubVehicleModelDao;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.util.UrlUtil;
@Repository("pubModelDao")
public class PubVehicleModelDaoImpl extends DaoSupportImpl<PubVehiclemodel> implements
		PubVehicleModelDao {

	/**
	 * 
	* @Title: PubVehicleModelDao.java 
	* @Package com.ennuova.dao 
	* @Description: 获取热度排行车型(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	@Override
	public List<PubVehiclemodel> queryModel(PubVehiclemodel model,int type) {
		StringBuffer hql = new StringBuffer(" select m from PubVehiclemodel m left join m.pubBrand where 1=1 ");
		if(model != null){
			if(model.getPubBrand().getId() != null){
				hql.append(" and m.pubBrand.id = :bid ");
			}
		}
		if(type == 0){
			hql.append(" order by m.attentionNum,m.borrowNum desc ");
		}else{
			hql.append(" order by m.fname desc");
		}
		Query query = getSessionFactory().getCurrentSession().createQuery(hql.toString());
		if(model != null){
			if(model.getPubBrand().getId() != null){
				query.setParameter("bid",  model.getPubBrand().getId());
			}
		}
		if(type == 0){
			query.setFirstResult(0).setMaxResults(5);
		}
		@SuppressWarnings("unchecked")
		List<PubVehiclemodel> modelList = (List<PubVehiclemodel>)query.list();
		String imgUrl = UrlUtil.getInstance().getImgurl();
		for (PubVehiclemodel pubVehiclemodel : modelList) {
			pubVehiclemodel.setPubCarinfos(null);
			try {
				pubVehiclemodel.setImgStr(imgUrl+pubVehiclemodel.getPubLine().getsPicture());
			} catch (Exception e) {
				
			}
			pubVehiclemodel.setPubLine(null);
			pubVehiclemodel.setPubBrand(null);
		}
		return modelList;
	}

	/**
	 * 
	* @Title: PubVehicleModelDaoImpl.java 
	* @Package com.ennuova.dao.impl 
	* @Description: 获取具体车型数据(描述) 
	* @author felix
	* @date 2016年4月13日
	* @version V1.0
	 */
	@Override
	public PubVehiclemodel getModel(long id) {
		//PubVehiclemodel model = (PubVehiclemodel)getSessionFactory().getCurrentSession().get(PubVehiclemodel.class, id);
		PubVehiclemodel model = (PubVehiclemodel)getSessionFactory().getCurrentSession().createQuery("from PubVehiclemodel where id = ?").setParameter(0, id);
		String imgUrl = UrlUtil.getInstance().getImgurl();
		model.setImgStr(imgUrl+model.getPubLine().getsPicture());
		model.setPubLine(null);
		model.setPubBrand(null);
		model.setPubCarinfos(null);
		return model;
	}

	@Override
	public int updateBorrowNum(long id) {
		String hql = " from PubVehiclemodel where id = ?";  
		Query querynum = getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, id).setFirstResult(0).setMaxResults(1);  
		@SuppressWarnings("unchecked")
		List<PubVehiclemodel> res = querynum.list();
		String hqlu = "";
		if(res.size()>0){
			if(res.get(0).getAttentionNum()==null){
				hqlu =" update PubVehiclemodel set borrowNum=1 where id=:id";
			}else{
				hqlu =" update PubVehiclemodel set borrowNum=borrowNum+1 where id=:id";
			}
		}
		//若为null 初值设置为0
		Query query = getSessionFactory().getCurrentSession().createQuery(hqlu);
		query.setLong("id", id);
		return query.executeUpdate();
	}

	@Override
	public int updateAttentionNum(long id) {
		//若为null 初值设置为0
        String hql = " from PubVehiclemodel where id = :id";  
		Query querynum = getSessionFactory().getCurrentSession().createQuery(hql).setLong("id", id).setFirstResult(0).setMaxResults(1);  
		@SuppressWarnings("unchecked")
		List<PubVehiclemodel> res = querynum.list();
		String hqlu = "";
		if(res.size()>0){
			if(res.get(0).getAttentionNum()==null){
				hqlu =" update PubVehiclemodel set attentionNum=1 where id=:id";
			}else{
				hqlu =" update PubVehiclemodel set attentionNum=attentionNum+1 where id=:id";
			}
		}
		Query query = getSessionFactory().getCurrentSession().createQuery(hqlu);
		query.setLong("id", id);
		return query.executeUpdate();
	}
	
	
}

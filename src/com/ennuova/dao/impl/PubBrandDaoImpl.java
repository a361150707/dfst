package com.ennuova.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubBrandDao;
import com.ennuova.entity.PubBrand;
import com.ennuova.entity.PubLine;
import com.ennuova.util.UrlUtil;

@Repository("pubBrandDao")
public class PubBrandDaoImpl extends DaoSupportImpl<PubBrand> implements PubBrandDao {

	/**
	 * 
	* @Title: PubBrandDaoImpl.java 
	* @Package com.ennuova.dao.impl 
	* @Description: 获取车辆品牌(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	@Override
	public PubBrand getBrand(PubBrand brand) {
		//new com.ennuova.entity.PubBrand(b.id,b.fcj,b.fcodeabb,b.fnumber,b.fname,b.fobdsbm)
		StringBuffer hql = new StringBuffer( " select b from PubBrand b left join b.pubLines where 1=1 ");
		if(brand != null){
			if(brand.getFname() != null && !brand.getFname().equals("")){
				hql.append(" and b.fname like :fname ");
			}
			if(brand.getFcodeabb() != null && !brand.getFcodeabb().equals("")){
				hql.append(" and b.fcodeabb=:fcodeabb ");
			}
		}
		hql.append(" order by b.pubLines.fname desc");
		Query query = getSessionFactory().getCurrentSession().createQuery(hql.toString());
		if(brand != null){
			if(brand.getFname() != null && !brand.getFname().equals("")){
				query.setParameter("fname", "%" + brand.getFname() + "%");
			}
			if(brand.getFcodeabb() != null && !brand.getFcodeabb().equals("")){
				query.setParameter("fcodeabb", "" + brand.getFcodeabb() + "");
			}
		}
		@SuppressWarnings("unchecked")
		List<PubBrand> brands =  (List<PubBrand>)query.list();
		if(brands.size() > 0){
			PubBrand data = brands.get(0);
			System.out.println(data.getPubLines().size());
			List<PubLine> lineSet = data.getPubLines();
			data.setPubCarinfos(null);
			data.setPubVehiclemodels(null);
			String imgUrl = UrlUtil.getInstance().getImgurl();
			for (PubLine line : lineSet) {
				line.setPubBrand(null);
				line.setPubCarinfos(null);
				line.setPubVehiclemodels(null);
				line.setsPicture(imgUrl+line.getsPicture());
			}
			return data;
		}
		return null;
	}

	

}

package com.ennuova.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubLineDao;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.util.UrlUtil;

@Repository("pubLineDao")
public class PubLineDaoImpl extends DaoSupportImpl<PubLine> implements PubLineDao {

	/**
	 * 
	* @Title: PubLineDaoImpl.java 
	* @Package com.ennuova.dao.impl 
	* @Description: 获取车系及对应车型(描述) 
	* @author felix
	* @date 2016年4月8日
	* @version V1.0
	 */
	@Override
	public PubLine queryLine(PubLine line) {
		StringBuffer hql = new StringBuffer( " select l from PubLine l left join l.pubVehiclemodels where 1=1 ");
		if(line != null){
			if(line.getId() != null){
				hql.append(" and l.id = :id ");
			}
		}
		Query query = getSessionFactory().getCurrentSession().createQuery(hql.toString());
		if(line != null){
			if(line.getId() != null){
				query.setParameter("id", line.getId() );
			}
		}
		@SuppressWarnings("unchecked")
		List<PubLine> lines =  (List<PubLine>)query.list();
		if(lines.size() > 0){
			PubLine data = lines.get(0);
			System.out.println(data.getPubVehiclemodels().size());
			String imgUrl = UrlUtil.getInstance().getImgurl();
			data.setsPicture(imgUrl+data.getsPicture());
			Set<PubVehiclemodel> modelSet = data.getPubVehiclemodels();
			data.setPubCarinfos(null);
			data.setPubBrand(null);
			for (PubVehiclemodel model : modelSet) {
				model.setPubBrand(null);
				model.setPubCarinfos(null);
				model.setPubLine(null);
			}
			return data;
		}
		return null;
	}

}

package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwDiscountindexDao;
import com.ennuova.entity.OwDiscountindex;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

@Repository("owDiscountindexDao")
public class OwDiscountindexDaoImpl extends DaoSupportImpl<OwDiscountindex> implements OwDiscountindexDao{

	@Override
	public List<OwDiscountindex> queryDiscountIndex(long fcompany) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<OwDiscountindex> discoutList=new ArrayList<OwDiscountindex>();
		String sql=" SELECT NINDEX.ID,NINDEX.FPICTURE,NINDEX.FREL_DISCOUNT,NINDEX.FSORT FROM OW_DISCOUNTINDEX NINDEX LEFT JOIN  OW_DISCOUNTACTION DISCOUNT  ON DISCOUNT.ID=NINDEX.FREL_DISCOUNT WHERE DISCOUNT.FCOMPANY ="+fcompany+" AND NINDEX.FSTATE=1 AND DISCOUNT.FSTATE=2 ORDER BY NINDEX.FSORT";
		System.out.println(sql);
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OwDiscountindex index=new OwDiscountindex();
				index.setFpicture(urlPath+rs.getString("FPICTURE"));
				index.setFsort(rs.getBigDecimal("FSORT"));
				index.setId(rs.getLong("ID"));
				index.setDiscountId(rs.getLong("FREL_DISCOUNT"));
				discoutList.add(index);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return discoutList;
	}
}

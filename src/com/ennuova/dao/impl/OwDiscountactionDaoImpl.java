package com.ennuova.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwDiscountactionDao;
import com.ennuova.entity.OwDiscountaction;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

@Repository("owDiscountactionDao")
public class OwDiscountactionDaoImpl extends DaoSupportImpl<OwDiscountaction> implements OwDiscountactionDao{

	@Override
	public List<OwDiscountaction> queryAllByState(long fstate, long fcompanyId) {
		BigDecimal a=BigDecimal.valueOf(fstate);
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=" SELECT ID,FBTIME,FNR_PIC,FTITLE FROM OW_DISCOUNTACTION WHERE FSTATE='"+a+"' AND FCOMPANY='"+fcompanyId+"'";
		System.out.println(sql);
		List<OwDiscountaction> discountList=new ArrayList<OwDiscountaction>();
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OwDiscountaction disocunt=new OwDiscountaction();
				disocunt.setId(rs.getLong("ID"));
				disocunt.setFbtime(rs.getTimestamp("FBTIME"));
				disocunt.setFnrPic(urlPath+rs.getString("FNR_PIC"));
				disocunt.setFtitle(rs.getString("FTITLE"));
				discountList.add(disocunt);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return discountList;
	}

	@Override
	public OwDiscountaction getOwDiscountById(long id) {
		OwDiscountaction disocunt=new OwDiscountaction();
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=" SELECT DISCOUNT.FTITLE,DISCOUNT.FBTIME,DISCOUNT.FVIEWCOUNT,DISCOUNT.FNR_TEXT,U.NAME FROM OW_DISCOUNTACTION DISCOUNT LEFT JOIN PUB_USER U ON DISCOUNT.FBUSER=U.ID WHERE DISCOUNT.ID='"+id+"'";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				disocunt.setFtitle(rs.getString("FTITLE"));
				disocunt.setFnrText(rs.getString("FNR_TEXT"));
				disocunt.setFviewcount(rs.getBigDecimal("FVIEWCOUNT"));
				disocunt.setUname(rs.getString("NAME"));
				disocunt.setFbtime(rs.getTimestamp("FBTIME"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return disocunt;
	}
}





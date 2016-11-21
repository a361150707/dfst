package com.ennuova.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwNewcarDao;
import com.ennuova.entity.OwNewcar;
import com.ennuova.util.DButil;
import com.ennuova.util.PageBean;
import com.ennuova.util.QueryHelper;
import com.ennuova.util.UrlUtil;

@Repository("owNewcarDao")
public class OwNewcarDaoImpl extends DaoSupportImpl<OwNewcar> implements
		OwNewcarDao {

	
	@Override
	public PageBean queryNewcar(int pageNum,int pageSize,String fstate) {
		QueryHelper queryHelper = new QueryHelper(OwNewcar.class, "");
		queryHelper.addCondition(" fstate = ?", fstate);
		return getPageBean(pageNum, pageSize, queryHelper);
	}

	@Override
	public List<OwNewcar> queryAllByState(long fstate,long fcompanyId) {
		BigDecimal a=BigDecimal.valueOf(fstate);
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=" SELECT ID,FBTIME,FNR_PIC,FTITLE FROM OW_NEWCAR WHERE FSTATE='"+a+"' AND FCOMPANY='"+fcompanyId+"'";
		System.out.println(sql);
		List<OwNewcar> newcarList=new ArrayList<OwNewcar>();
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OwNewcar newcar=new OwNewcar();
				newcar.setId(rs.getLong("ID"));
				newcar.setFbtime(rs.getTimestamp("FBTIME"));
				newcar.setFnrPic(urlPath+rs.getString("FNR_PIC"));
				newcar.setFtitle(rs.getString("FTITLE"));
				newcarList.add(newcar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return newcarList;
		/*getSessionFactory().getCurrentSession().createQuery(
				" from OwNewcar where fstate=? and fcompany=? order by fzhiding desc")
				.setParameter(0, a)
				.setParameter(1, fcompanyId)
				.list();*/
	}

	@Override
	public OwNewcar getOwNewcarById(long id) {
		OwNewcar newcar=new OwNewcar();
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=" SELECT CAR.FTITLE,CAR.FBTIME,CAR.FVIEWCOUNT,CAR.FNR_TEXT,U.NAME FROM OW_NEWCAR CAR LEFT JOIN PUB_USER U ON CAR.FBUSER=U.ID WHERE CAR.ID='"+id+"'";
		try {
			ps=conn.prepareStatement(sql);
		    rs=ps.executeQuery();
		    if(rs.next()){
		    	newcar.setFtitle(rs.getString("FTITLE"));
		    	newcar.setFnrText(rs.getString("FNR_TEXT"));
		    	newcar.setFviewcount(rs.getBigDecimal("FVIEWCOUNT"));
		    	newcar.setUname(rs.getString("NAME"));
		    	newcar.setFbtime(rs.getTimestamp("FBTIME"));
		    }
		    conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return newcar;
	}

	

}

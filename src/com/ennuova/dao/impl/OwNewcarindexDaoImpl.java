package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwNewcarindexDao;
import com.ennuova.entity.OwNewcar;
import com.ennuova.entity.OwNewcarindex;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

@Repository("owNewcarindexDao")
public class OwNewcarindexDaoImpl extends DaoSupportImpl<OwNewcarindex>
		implements OwNewcarindexDao {

	/**
	 * fstate为1是启用
	 */
	@Override
	public List<OwNewcarindex> queryNewcarIndex(long fcompany) {
		Connection conn = null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="  SELECT NINDEX.ID,NINDEX.FPICTURE,NINDEX.FREL_CAR,NINDEX.FSORT FROM OW_NEWCARINDEX NINDEX LEFT JOIN  OW_NEWCAR NEWCAR  ON NEWCAR.ID=NINDEX.FREL_CAR WHERE NEWCAR.FCOMPANY ='"+fcompany+"' AND NINDEX.FSTATE=1 AND NEWCAR.FSTATE=2 ORDER BY NINDEX.FSORT";
		List<OwNewcarindex> newcarindexlist=new ArrayList<OwNewcarindex>();
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OwNewcarindex newcarindex=new OwNewcarindex();
				newcarindex.setFpicture(urlPath+rs.getString("FPICTURE"));
				newcarindex.setFsort(rs.getBigDecimal("FSORT"));
				newcarindex.setId(rs.getLong("ID"));
				OwNewcar owNewcar=new OwNewcar();
				owNewcar.setId(rs.getLong("FREL_CAR"));
				newcarindex.setOwNewcar(owNewcar);
				newcarindexlist.add(newcarindex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return newcarindexlist;/*getSessionFactory().getCurrentSession().createQuery(//
				" from OwNewcarindex where fstate=?")
				.setParameter(0, a)
				.setFirstResult(0)
				.setMaxResults(4)
				.list();*/
	}

	

}

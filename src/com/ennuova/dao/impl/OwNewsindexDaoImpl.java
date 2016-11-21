package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.OwNewsindexDao;
import com.ennuova.entity.OwNewsindex;
import com.ennuova.util.DButil;
import com.ennuova.util.UrlUtil;

@Repository("owNewsindexDao")
public class OwNewsindexDaoImpl extends DaoSupportImpl<OwNewsindex> implements OwNewsindexDao {

	@Override
	public List<OwNewsindex> queryNewsIndex(long fcompany,long ftype) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<OwNewsindex> newsindexList=new ArrayList<OwNewsindex>();
		String sql=" SELECT NINDEX.ID,NINDEX.FPICTURE,NINDEX.FREL_NEWS,NINDEX.FSORT FROM OW_NEWSINDEX NINDEX LEFT JOIN  OW_NEWSCT NEWS  ON NEWS.ID=NINDEX.FREL_NEWS WHERE NEWS.FCOMPANY ='"+fcompany+"' AND NEWS.FTYPE='"+ftype+"' AND NINDEX.FSTATE=1 AND NEWS.FSTATE=2 ORDER BY NINDEX.FSORT";
		System.out.println(sql);
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				OwNewsindex index=new OwNewsindex();
				index.setFpicture(urlPath+rs.getString("FPICTURE"));
				index.setFsort(rs.getBigDecimal("FSORT"));
				index.setId(rs.getLong("ID"));
				index.setNewsId(rs.getLong("FREL_NEWS"));
				newsindexList.add(index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return newsindexList;
	}

}

package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubAreaDao;
import com.ennuova.entity.PubArea;
import com.ennuova.util.DButil;

@Repository("pubAreaDao")
public class PubAreaDaoImpl extends DaoSupportImpl<PubArea> implements PubAreaDao{

	@Override
	public List<PubArea> queryPubArea(long fid, long fgrade) {
		Connection conn=null;
		conn=getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql=" SELECT * FROM PUB_AREA WHERE FID='"+fid+"' AND FGRADE='"+fgrade+"'";
		System.out.println(sql);
		List<PubArea> areaList=new ArrayList<PubArea>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				PubArea area=new PubArea();
				area.setId(rs.getLong("ID"));
				area.setFname(rs.getString("FNAME"));
				area.setFid(rs.getLong("FID"));
				area.setFgrade(rs.getBigDecimal("FGRADE"));
				areaList.add(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return areaList;
	}


	/**
	 * 根据名字获取城市列表
	 * @Description: TODO
	 * @param @param cname
	 * @param @return   
	 * @return List<PubArea>  
	 * @author felix
	 * @date 2015-12-9
	 */
	@Override
	public List<PubArea> queryCityByName(String cname) {
		Connection conn=null;
		PreparedStatement ps=null;
		conn= getSessionFactory().getCurrentSession().connection();
		ResultSet rs=null;
		String sql="  SELECT * FROM PUB_AREA WHERE FNAME LIKE '"+cname+"' AND FGRADE='"+2+"'";
		List<PubArea> areaList=new ArrayList<PubArea>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				PubArea area=new PubArea();
				area.setId(rs.getLong("ID"));
				area.setFname(rs.getString("FNAME"));
				area.setFid(rs.getLong("FID"));
				area.setFgrade(rs.getBigDecimal("FGRADE"));
				areaList.add(area);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return areaList;
	}

}

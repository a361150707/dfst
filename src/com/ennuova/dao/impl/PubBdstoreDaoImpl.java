package com.ennuova.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubBdstoreDao;
import com.ennuova.entity.PubBdstore;
import com.ennuova.util.DButil;

@Repository("pubBdstoreDao")
public class PubBdstoreDaoImpl extends DaoSupportImpl<PubBdstore> implements PubBdstoreDao{

	@Override
	public int updateDefault(long cusId) {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=getSessionFactory().getCurrentSession().connection();
		String sql="UPDATE PUB_BDSTORE SET FDEFAULT=0 WHERE FCUSTOMER='"+cusId+"'";
		int result = 0;
		try {
			ps=conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	@Override
	public long getIsExistId(long storeId, long cusId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " SELECT ID FROM PUB_BDSTORE WHERE FSTORE='"+storeId+"' AND FCUSTOMER='"+cusId+"'";
		System.out.println(sql);
		long id=-1;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getLong("ID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, rs);
		}
		return id;
	}

	@Override
	public int updateCusDefault(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		String sql = " UPDATE PUB_BDSTORE SET FDEFAULT=1 WHERE ID='"+id+"'";
		int result = -1;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return result;
	}

	/**
	 * 根据客户的id查找客户绑定的门店id
	 *@author sududa
	 *@date 2016年10月8日
	 * @param customerId
	 * @return
	 */
	@Override
	public Map<String, Object> findByCusId(long customerId) {
		System.out.println(customerId);
		String sql = "select ID,FSTORE"
				   + " from PUB_BDSTORE"
				   + " where FCUSTOMER = ? and fdefault=1";
		return findOneForJdbc(sql, customerId);
	}

}

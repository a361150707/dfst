package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubStoreDao;
import com.ennuova.entity.PubStore;
import com.ennuova.util.UrlUtil;

@Repository("pubStoreDao")
public class PubStoreDaoImpl extends DaoSupportImpl<PubStore> implements
		PubStoreDao {

	@Override
	public PubStore getPubStorebyId(long id) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql=" SELECT * FROM PUB_STORE WHERE ID='"+id+"'";
		PubStore store=new PubStore();
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				store.setFaddr(rs.getString("faddr"));
				store.setFbackpc(urlPath+rs.getString("fbackpc"));
				store.setFcode(rs.getString("fcode"));
				store.setFname(rs.getString("fname"));
				store.setFtel(rs.getString("ftel"));
				store.setFsypicture(urlPath+rs.getString("fsypicture"));
				store.setFtext(rs.getString("ftext"));
				store.setFweibo(rs.getString("fweibo"));
				store.setFweixin(rs.getString("fweixin"));
				store.setId(rs.getLong("ID"));
				store.setFcompany(rs.getLong("fcompany"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return store;
	}

	@Override
	public List<PubStore> querySomeStore(long cid) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		List<PubStore> storeList=new ArrayList<PubStore>();
		sql = " SELECT ID,FNAME,FLOGO,FADDR,FTEL FROM PUB_STORE WHERE FCITY='"+cid+"'";
		String urlPath=UrlUtil.getInstance().getImgurl();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				PubStore store=new PubStore();
				store.setId(rs.getLong("ID"));
				store.setFname(rs.getString("FNAME"));
				store.setFlogo(urlPath+rs.getString("FLOGO"));
				store.setFaddr(rs.getString("FADDR"));
				store.setFtel(rs.getString("FTEL"));
				storeList.add(store);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeList;
	}

	@Override
	public List<PubStore> queryMyStore(long cusId) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="";
		List<PubStore> storeList=new ArrayList<PubStore>();
		sql = " SELECT S.ID,S.FNAME,S.FADDR,S.FTEL,B.FDEFAULT,B.ID BID FROM PUB_BDSTORE B LEFT JOIN PUB_STORE S ON S.ID=B.FSTORE WHERE B.FCUSTOMER='"+cusId+"' ORDER BY B.FDEFAULT DESC";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				PubStore store=new PubStore();
				store.setId(rs.getLong("ID"));
				store.setFname(rs.getString("FNAME"));
				store.setFdefault(rs.getLong("FDEFAULT"));
				store.setFaddr(rs.getString("FADDR"));
				store.setFtel(rs.getString("FTEL"));
				store.setBid(rs.getLong("BID"));
				storeList.add(store);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return storeList;
	}

	@Override
	public List<PubStore> getPubStoreByAddress(String realTimeAddress) {
		Connection conn = null;
		conn = getSessionFactory().getCurrentSession().connection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PubStore> storeList=new ArrayList<PubStore>();
		String sql = "select FNAME,FTEL,FLOGO,FADDR,FLNG,FLAT from PUB_STORE where FADDR like '%"+realTimeAddress+"%'";
		System.out.println(sql);
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				PubStore pubStore = new PubStore();
				pubStore.setFname(rs.getString("FNAME"));
				pubStore.setFtel(rs.getString("FTEL"));
				pubStore.setFlogo(UrlUtil.getInstance().getImgurl()+rs.getString("FLOGO"));
				pubStore.setFaddr(rs.getString("FADDR"));
				pubStore.setFlat(rs.getDouble("FLAT"));
				pubStore.setFlng(rs.getDouble("FLNG"));
				storeList.add(pubStore);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return storeList;
	}

	@Override
	public List<Map<String, Object>> getPubAllStore() {
		String sql = "select id,fname,faddr,ftel,fsypicture from PUB_STORE where del_flag=1";
		return findForJdbc(sql);
	}

	

}

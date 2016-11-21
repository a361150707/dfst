package com.ennuova.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.ennuova.base.impl.DaoSupportImpl;
import com.ennuova.dao.PubOdbbrandDao;
import com.ennuova.entity.PubOdbbrand;
import com.ennuova.util.DButil;

/**
 * ODB品牌DAoimpl
 * @author dofast-pc
 */
@Repository("pubOdbbrandDao")
public class PubOdbbrandDaoImpl extends DaoSupportImpl<PubOdbbrand> implements PubOdbbrandDao{

	/**
	 * 查询二级
	 */
	public List<PubOdbbrand> findChilrend() {
		return getSessionFactory().getCurrentSession().createQuery(
				"from PubOdbbrand")//
				.list();
	}

	@Override
	public void bachSave(String brand) {
		
		if (brand != null && !brand.equals("")) {
			JSONArray array = JSONArray.fromObject(brand);
			if (array != null) {
				for (int i = 0; i < array.size(); i++) {
					JSONObject object = array.getJSONObject(i);
					// 一级品牌
					PubOdbbrand pubOdbbrand = new PubOdbbrand();
					if (object.has("parentId")) {
						pubOdbbrand.setParentId(Long.parseLong(object
								.getString("parentId")));
					}
					if (object.has("url")) {
						pubOdbbrand.setUrl(object.getString("url"));
					}
					if (object.has("carSeriesName")) {
						pubOdbbrand.setCarSeriesName(object
								.getString("carSeriesName"));
					}
					if (object.has("carSeriesId")) {
						pubOdbbrand.setCarSeriesId(Long.parseLong(object
								.getString("carSeriesId")));
					}

					save(pubOdbbrand);
					if (object.has("subList")
							&& !object.getString("subList").equals("[]")
							&& object.getJSONArray("subList").size() > 0) {
						JSONArray carArray = object.getJSONArray("subList");
						for (int j = 0; j < carArray.size(); j++) {
							JSONObject objects = carArray.getJSONObject(j);
							// 二级品牌
							PubOdbbrand child = new PubOdbbrand();
							if (object.has("parentId")) {
								child.setParentId(Long.parseLong(objects
										.getString("parentId")));
							}
							if (object.has("url")) {
								child.setUrl(objects.getString("url"));
							}
							if (object.has("carSeriesName")) {
								child.setCarSeriesName(objects
										.getString("carSeriesName"));
							}
							if (object.has("carSeriesId")) {
								child.setCarSeriesId(Long.parseLong(objects
										.getString("carSeriesId")));
							}
							
							// 保存数据库
							try {
								save(child);
							} catch (Exception e2) {
								System.out.println("导入失败->"+e2.getMessage());
							}
						}
					}

				}
				// 查询一级品牌

			}
		}
		
	}

	@Override
	public Long getParentIdByDetailId(Long dId) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getSessionFactory().getCurrentSession().connection();
		ResultSet rs = null;
		Long parentId = 0L;
		try {
			String sql = "select PARENTID from PUB_ODBBRAND where CARSERIESID = "+dId;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				parentId = rs.getLong("PARENTID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DButil.closeDb(conn, ps, null);
		}
		return parentId;
	}

}

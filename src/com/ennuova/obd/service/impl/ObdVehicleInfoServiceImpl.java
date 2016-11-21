package com.ennuova.obd.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennuova.dao.CnSbljinfoDao;
import com.ennuova.dao.PubOdbbrandDao;
import com.ennuova.dao.PubOdbvehicleDao;
import com.ennuova.entity.PubOdbbrand;
import com.ennuova.entity.PubOdbvehicle;
import com.ennuova.obd.http.WeixinUtil;
import com.ennuova.obd.model.CarSeries;
import com.ennuova.obd.model.JSONMsg;
import com.ennuova.obd.service.ObdVehicleInfoService;
import com.ennuova.obd.tools.CommonUtils;
import com.ennuova.obd.tools.SignUtils;

/**
 * Odb车辆信息serviceimpl
 * 
 * @author janson
 */
@Transactional
@Service("obdVehicleInfoService")
public class ObdVehicleInfoServiceImpl implements ObdVehicleInfoService {
	
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private PubOdbbrandDao pubOdbbrandDao;// Odb品牌dao
	@Resource
	private PubOdbvehicleDao pubOdbvehicleDao;// Odb车型dao

	/**
	 * 保存品牌
	 * 
	 * @return
	 */
	public boolean saveBrand() {

		boolean res = false;

		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("action", "forjth.getbrandid");
			String param = SignUtils.getSign(params);

			// 请求服务器获取品牌
			String brand = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
					+ param);
			pubOdbbrandDao.bachSave(brand);
			System.out.println("导入成功");

		} catch (Exception e) {
			System.out.println("导入失败->"+e.getMessage());
			res = false;
		}
		return res;

	}

	public boolean saveLine() {
		boolean res = false;
		try {

			List<PubOdbbrand> chilrend = pubOdbbrandDao.findChilrend();
			for (PubOdbbrand pubOdbbrand : chilrend) {

				String url = "http://apps.api.dbscar.com/?action=mine_car_service.query_market_car_type"
						+ "&lan_id_or_name=cn&detailId="
						+ pubOdbbrand.getCarSeriesId();

				// 请求服务器获取车型
				String line = WeixinUtil.httpsend(url);
			
				
			    pubOdbvehicleDao.bachSave(line);

			}
			
			System.out.println("导入成功");

		} catch (Exception e) {
			System.out.println("导入失败->"+e.getMessage());
			res = false;
		}
		return res;
	}

}

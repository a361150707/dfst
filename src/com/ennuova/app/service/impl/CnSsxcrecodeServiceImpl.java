package com.ennuova.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.ennuova.app.service.CnSsxcrecodService;
import com.ennuova.dao.CnSsclxxDao;
import com.ennuova.dao.CnSsxcrecodeDao;
import com.ennuova.dao.CnXcrecodeDao;
import com.ennuova.entity.CarMonitor;
import com.ennuova.entity.CnSsxcrecode;
import com.ennuova.entity.CnXcrecode;
import com.ennuova.util.Constant;
import com.ennuova.util.URLRequest;

/**实时行程记录serviceImpl
 * @author 李智辉 
 * 2015-12-11上午9:34:03
 */
@Service
public class CnSsxcrecodeServiceImpl implements CnSsxcrecodService {
	@Resource
	private CnSsxcrecodeDao cnSsxcrecodeDao;
	@Resource
	private CnSsclxxDao cnSsclxxDao;
	@Resource
	private CnXcrecodeDao xcrecodeDao;
	@Override
	public Map<String, Object> getCarNowLBS(Long fclid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			CnSsxcrecode cnSsxcrecode = new CnSsxcrecode();
			cnSsxcrecode = this.cnSsxcrecodeDao.getNewCnSsxcrecode(fclid);
			if (cnSsxcrecode.getFjd()!=null&&cnSsxcrecode.getFwd()!=null) {
				String content = URLRequest.requestURL((Constant.CONVERT_ADDRESS_URL+cnSsxcrecode.getFwd().toString()+","+cnSsxcrecode.getFjd().toString())).toString();
				JSONObject jsonObject = new JSONObject(content);
				JSONObject result = jsonObject.getJSONObject("result");
				String address = result.getString("formatted_address");
				map.put("address", address);
				map.put("lat", cnSsxcrecode.getFwd());
				map.put("lng", cnSsxcrecode.getFjd());
				map.put("success", true);
			}else {
				map.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static void main(String[] args) {
		String content = URLRequest.requestURL((Constant.CONVERT_ADDRESS_URL+"24.488789"+","+"118.187056")).toString();
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(content);
			JSONObject result = jsonObject.getJSONObject("result");
			JSONObject addressInfo = result.getJSONObject("addressComponent");
			String address = "";
			if(addressInfo.getString("district")!=null){
				address = addressInfo.getString("district");
			}else {
				address = addressInfo.getString("city");
			}
			System.out.println(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public CarMonitor getCarMonitor(Long fclid) {
		CarMonitor carMonitor = new CarMonitor();
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			date = new Date(date.getTime()-35000);
			String startTime = format.format(date);
			carMonitor = cnSsclxxDao.getCarMonitor(fclid, startTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carMonitor;
	}
	@Override
	public Map<String, Object> getRealTimeLocation(Long fclid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Date date = new Date();
			date = new Date(date.getTime()-500000);	//获取当前时间10秒内的数据
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String initTime = dateFormat.format(date);
			CnSsxcrecode cnSsxcrecode = cnSsxcrecodeDao.getRealTimeLocation(fclid, initTime);
			if(cnSsxcrecode.getId()!=null&&cnSsxcrecode.getFwd()!=null){
				map.put("success", true);
				map.put("lat", cnSsxcrecode.getFwd());
				map.put("lng", cnSsxcrecode.getFjd());
			}else {
				map.put("success", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	@Override
	public CarMonitor getCarMonitorMax(Long fclid) {
		CarMonitor carMonitor = new CarMonitor();
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			date = new Date(date.getTime()-2591000000L);
			String startTime = format.format(date);
			carMonitor = cnSsclxxDao.getCarMonitor(fclid, startTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carMonitor;
	}
	@Override
	public List<Map<String, Object>> getLBSList(Long xcoredId, Long carId) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		CnXcrecode cnXcrecode = xcrecodeDao.getById(xcoredId);
		if(cnXcrecode.getId()!=null){
			maps = cnSsxcrecodeDao.getCnSsxcrecodeByTime(format.format(cnXcrecode.getFbegintime()), format.format(cnXcrecode.getFendtime()), carId);
		}
		return maps;
	}
	@Override
	public List<Map<String, Object>> getCnSsxcrecodeByTime(String beginTime,
			String endTime, Long carId) {
		// TODO Auto-generated method stub
		return cnSsxcrecodeDao.getCnSsxcrecodeByTime(beginTime, endTime, carId);
	}
}

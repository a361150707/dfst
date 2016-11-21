package com.ennuova.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.ennuova.app.service.MyBoxService;
import com.ennuova.dao.CarInfoDao;
import com.ennuova.dao.PubBoxDao;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.obd.http.WeixinUtil;
import com.ennuova.obd.tools.SignUtils;
import com.ennuova.obd.util.GoloApplication;

@Service("myBoxSerivce")
public class MyBoxServiceImpl implements MyBoxService{

	@Resource
	private PubBoxDao pubBoxDao;

	@Resource
	private CarInfoDao carInfoDao;
	private Object object;

	@Override
	public List<PubBox> queryMyBox(long cusId) {
		List<PubBox> boxList = new ArrayList<PubBox>();
		try {
			boxList = pubBoxDao.queryMyBox(cusId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boxList;
	}

	@Override
	public long checkBox(String fxlh) {
		return pubBoxDao.checkBox(fxlh);
	}

	@Override
	public List<PubCarinfo> queryNoBindBoxCar(long cusId) {
		return pubBoxDao.queryNoBindBoxCar(cusId);
	}

	/**
	 * 盒子绑定(确认所绑定车辆是否存在车架号，若无车架号就从obd获取，其他情况继续走)
	 */
	@Override
	public boolean addBindBox(String fxlh,long boxid, long fclid,String pwd) {
		boolean flag = false;
		//PubCarinfo car = carInfoDao.queryCarInfo(fclid);
		try {
			/*if(car.getFcarcode()==null||car.getFcarcode().equals("")){
				System.out.println();
				String res = objFindBy(fxlh);
				System.out.println("res:"+res);
				JSONObject json = new JSONObject(res);
				String code = json.get("code").toString();
				if(code.equals("0")){
					JSONArray data=json.getJSONArray("data");
					JSONObject info=data.getJSONObject(0);
					String carcode = info.get("VinCode").toString();
					carInfoDao.updateCarInfo(fclid, "fchassisno", carcode);
				}*/
			pubBoxDao.addBindBox(boxid, fclid, pwd);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public int jiebangBox(long boxId) {
		return pubBoxDao.jiebangBox(boxId);
	}

	@Override
	public int setDefBox(long cusId, long clId) {
		return pubBoxDao.setDefBox(cusId, clId);
	}

	@Override
	public String objFindBy(String seriaNo) {
		Map<String, String> paramss = new HashMap<String, String>();
		paramss.put("action", "data_develop.get_vin_by_devicesn");
		paramss.put("develop_id", GoloApplication.develop_id);
		paramss.put("serial_no", seriaNo);
		paramss.put("time", Long.toString(System.currentTimeMillis() / 1000));
		String sign = SignUtils.getSign(GoloApplication.delevop_key,paramss);
		paramss.put("sign", sign);
		String param = SignUtils.createLinkString(paramss);
		System.out.println(param);
		String res = WeixinUtil.httpsend("http://open.api.dbscar.com/?"
				+ param);
		return res;
	}

}

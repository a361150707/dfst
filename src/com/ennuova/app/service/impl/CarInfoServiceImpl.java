package com.ennuova.app.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ennuova.app.service.CarInfoService;
import com.ennuova.dao.CarInfoDao;
import com.ennuova.dao.CarLocationDao;
import com.ennuova.dao.CnSsclxxDao;
import com.ennuova.dao.CnSsxcrecodeDao;
import com.ennuova.dao.CnXcrecodeDao;
import com.ennuova.dao.PubBoxDao;
import com.ennuova.dao.PubCustomerDao;
import com.ennuova.dao.PubOdbbrandDao;
import com.ennuova.dao.PubVehicleModelDao;
import com.ennuova.dao.TestInsertDao;
import com.ennuova.entity.CarLocation;
import com.ennuova.entity.PubBox;
import com.ennuova.entity.PubCarinfo;
import com.ennuova.entity.PubLine;
import com.ennuova.entity.PubVehiclemodel;
import com.ennuova.entity.TestInsert;
import com.ennuova.obd.http.WeixinUtil;
import com.ennuova.obd.tools.SignUtils;
import com.ennuova.obd.util.GoloApplication;
import com.ennuova.tools.weather.WeatherQuery;
import com.ennuova.util.CommonServicesUtil;
import com.ennuova.util.DateUtil;
import com.ennuova.util.DateUtils;
import com.ennuova.util.MapDistanceUtil;
import com.ennuova.util.StringUtil;
import com.ennuova.util.UrlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("carInfoService")
public class CarInfoServiceImpl implements CarInfoService{
	
	Logger logger=LoggerFactory.getLogger(getClass());

	@Resource
	private CarInfoDao carInfoDao;
	@Resource
	private PubBoxDao boxDao;
	@Resource 
	private PubVehicleModelDao pubVehicleModelDao;
	@Resource
	private PubOdbbrandDao pubOdbbrandDao;
	
	@Resource
	private TestInsertDao testInsertDao;
	
	/**
	 * 客户的dao
	 */
	@Resource
	private PubCustomerDao pubCustomerDao;
	
	/**
	 * 实时行程记录dao,即实时车辆位置信息dao
	 */
	@Resource
	private CnSsxcrecodeDao cnSsxcrecodeDao;
	
	/**
	 * 实时车辆信息dao
	 */
	@Resource
	private CnSsclxxDao cnSsclxxDao;
	
	/**
	 * 车辆的行程dao
	 */
	@Resource
	private CnXcrecodeDao cnXcrecodeDao;
	
	/**
	 * 车辆位置信息的dao
	 */
	@Resource
	private CarLocationDao carLocationDao;
	
	
	@Override
	public PubCarinfo getCarInfo(long carId) {
		System.out.println("carInfoService");
		return carInfoDao.queryCarInfo(carId);
	}

	@Override
	public List getCarBrand() {
		return carInfoDao.queryCarBrand();
	}

	@Override
	public List getCarInfo(String brandId) {
		return carInfoDao.queryBrandModel(brandId);
	}

	@Override
	public List<PubCarinfo> getAllCarInfoByUser(Long userId) {
		return carInfoDao.queryAllCarInfo(userId);
	}

	@Override
	public String addCarInfo(String userId, String brandId, String chexiId,
			String chexingId,String carnum,String carCode) {
		return carInfoDao.addCarInfo(userId, brandId, chexiId, chexingId,carnum,carCode);
	}

	@Override
	public int updateCarInfo(String carId, String label, String content) {
		return this.carInfoDao.updateCarInfo(carId, label, content);

	}


	@Override
	public String modifyCarInfo(String carId, String brandId, String chexiId,
			String chexingId) {
		return carInfoDao.modifyCarInfo(carId, brandId, chexiId, chexingId);
	}

	@Override
	public List getModel(long fline) {
		return carInfoDao.getModel(fline);
	}

	@Override
	public PubCarinfo queryCarinfo(long cusId) {
		return carInfoDao.queryCarinfo(cusId);
	}

	@Override
	public PubCarinfo getDefaultCarId(Long userId) {
		return carInfoDao.getDefaultCarId(userId);
	}

	public Map<String, Object> getCarBoxInfo(Long fclid) {
		System.out.println("进入激活接口");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PubCarinfo pubCarinfo = carInfoDao.getCarBoxInfo(fclid);
			int count = 0;
			PubBox pubBox = boxDao.getPubBoxByFclid(fclid);		
			if(pubBox.getFjhyf()!=null&&pubBox.getFjhyf()==0){
				if(pubCarinfo.getLid()!=null&&pubCarinfo.getFcarcode()!=null&&pubCarinfo.getFcarnum()!=null
						&&pubCarinfo.getFcarcode()!=""&&pubCarinfo.getFcarnum()!=""&&pubCarinfo.getPubVehiclemodel().getId()!=null){
					map.put("car_carcase_num", pubCarinfo.getFcarcode());
					map.put("mine_car_plate_num", pubCarinfo.getFcarnum());
					count = count+2;
					PubVehiclemodel pubVehiclemodel = new PubVehiclemodel();
					pubVehiclemodel = pubVehicleModelDao.getById(pubCarinfo.getPubVehiclemodel().getId());
					if(pubVehiclemodel.getFyearkuan()!=null&&pubVehiclemodel.getFyearkuan()!=""&&pubVehiclemodel.getFgearbox()!=null&&
							pubVehiclemodel.getFgearbox()!=""&&pubVehiclemodel.getFpl()!=null&&pubVehiclemodel.getFpl()!=""){
						map.put("car_producing_year", pubVehiclemodel.getFyearkuan());
						map.put("car_displacement", pubVehiclemodel.getFpl());
						map.put("car_gearbox_type", pubVehiclemodel.getFgearbox());
						count = count+3;
					}
					if(pubBox!=null&&pubBox.getFpassword()!=null&&pubBox.getFxlh()!=null&&pubBox.getFxlh()!=""&&pubBox.getFpassword()!=""){
						
						map.put("serial_no", pubBox.getFxlh());
						map.put("password", pubBox.getFpassword());
						count = count+2;
					}
					Long oId = carInfoDao.getOdbLine(pubCarinfo.getLid());
					if(oId>0){
						Map<String, Object> rs = new HashMap<String, Object>();
						rs = carInfoDao.getOdbCX(oId);
						Long parentId = pubOdbbrandDao.getParentIdByDetailId(Long.parseLong(rs.get("detailId")+""));
						if(rs.get("detailId")!=""&&rs.get("carType")!=""){
							if(parentId==0){
								map.put("code_id", rs.get("detailId"));
							}else{
								map.put("code_id", parentId);
							}
							System.out.println(rs.get("carType"));
							map.put("car_type_id", rs.get("carType"));
							count = count+2;
						}
						
					}
					
				}
				if(count==9){
					
					//注册接口
					Map<String, String> params=new HashMap<String, String>();
					params.put("action", "develop.reg_user");
					String param=SignUtils.getSign(params);
					// 请求服务器
					String res = WeixinUtil
							.httpsend("http://open.api.dbscar.com/?" + param);
					//结果解析
					System.out.println("res1:"+res);
					JSONObject jsonMsg=JSONObject.fromObject(res);
					if(jsonMsg.getInt("code")==0){
						
						JSONObject jsonObject= (JSONObject) jsonMsg.get("data");
						GoloApplication.access_id=jsonObject.getString("access_id");
						GoloApplication.access_token=jsonObject.getString("access_token");
						
						params=new HashMap<String, String>();
						params.put("action", "forjth.save_car_config");
						params.put("car_carcase_num",map.get("car_carcase_num")+"");//vin码
						params.put("mine_car_plate_num", map.get("mine_car_plate_num")+"");//车牌号
						params.put("code_id",map.get("code_id")+"");//车辆品牌ID
						params.put("car_type_id", map.get("car_type_id")+"");//车型名称
						params.put("car_producing_year", map.get("car_producing_year")+"");//年款
						params.put("car_displacement",map.get("car_displacement")+"L");//排量
						params.put("car_gearbox_type",map.get("car_gearbox_type")+"");//档，  0手动 1自动 默认自动
						params.put("serial_no",map.get("serial_no")+"");//序列号
						params.put("password",map.get("password")+"");//
						params.put("display_lan", "cn");
					    param=SignUtils.getSignBySaveCarInfo(params);
						param = param.replaceAll(" ", "%20");
						
//						int lineNum1 = param.indexOf("car_type_id")+12;
//						int lineNum2 = param.indexOf("code_id")-1;
//						String newStr1 = param.substring(lineNum1,lineNum2);
//						int lineNum3 = param.indexOf("plate_num")+10;
//						int lineNum4 = param.indexOf("password")-1;
//						String newStr2 = param.substring(lineNum3,lineNum4);
//						String str1 = param.substring(0,lineNum1);
//						String str2 = URLEncoder.encode(newStr1,"utf-8");
//						String str3 = param.substring(lineNum2,lineNum3);
//						String str4 = URLEncoder.encode(newStr2,"utf-8");
//						String str5 = param.substring(lineNum4);
//						
						// 请求服务器
						String jhurl = "http://open.api.dbscar.com/?"+param;
						
						logger.error("盒子激活url->{} ", jhurl);
						//logger.error("盒子激活url->{} ", "http://open.api.dbscar.com/?"+str1+str2+str3+str4+str5);
						
						// 请求服务器
					    res = WeixinUtil
								.httpsend(jhurl);
					
						logger.error("盒子激活返回 ->{} ", res);
						
					    jsonMsg=JSONObject.fromObject(res);
						if(0==jsonMsg.getInt("code")||(-2==jsonMsg.getInt("code")&&"serialNo have been registered by others".equals(jsonMsg.getString("msg")))){
							map.put("success", true);
							map.put("message", "绑定成功");
							pubBox.setFjhyf(1L);
							PubCarinfo carinfo = new PubCarinfo();
							carinfo.setId(fclid);
							pubBox.setPubCarinfo(carinfo);
							boxDao.update(pubBox);
						}else{
							map.put("success", false);
							if(jsonMsg.getString("msg")!=null&&jsonMsg.getString("msg")!=""){
								map.put("message", jsonMsg.getString("msg"));
							}
						}
						
					}else{
						map.put("success", false);
						map.put("message", "绑定失败");
					}
				}else{
					map.put("success", false);
					map.put("message", "绑定失败");
				}
			}else{
				map.put("message", "绑定成功");
				map.put("success", true);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return map;
	}
	
	@Override
	public Map<String, Object> getCarJHInfo(Long fclid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PubBox box = boxDao.getPubBoxByFclid(fclid);
				if(box.getFjhyf()==0){
				map = carInfoDao.getCarJHInfo(fclid);
				System.out.println(map.toString());
				map.put("success", true);
			}else{
				map.put("success", false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public boolean updateCarCode(Long fclid, String carCode) {
		boolean success = false;
		success = this.carInfoDao.updateCarCode(fclid, carCode);
		return success;
	}

	/**
	 * 处理天气消息
	 */
	@Override
	public Map<String, Object> getWeatherInfo(String cityName) {
		Map<String,Object> mapData = new HashMap<String, Object>();
		String res = WeatherQuery.getWeatherByCityName(cityName);
		try {
			System.out.println("天气信息:"+res);
			JSONObject json = JSONObject.fromObject(res);
			String code = json.get("status").toString();
			if(code.equals("success")){
				JSONArray data=json.getJSONArray("results");
				JSONObject info=data.getJSONObject(0);
				JSONArray index = info.getJSONArray("index");
				JSONArray weaTher = info.getJSONArray("weather_data");
				JSONObject xc = index.getJSONObject(1);
				JSONObject tq = weaTher.getJSONObject(0);
				String mesXc = xc.get("zs").toString();//+xc.get("title");//洗车提示
				String temperature = tq.get("temperature").toString();
				String weather = tq.get("weather").toString();
				String dayPictureUrl = tq.get("dayPictureUrl").toString();
				mapData.put("mesXc", mesXc);
				mapData.put("temperature", temperature);
				mapData.put("dayPictureUrl", dayPictureUrl);
				if(weather.length()>3){
					int lineNum = weather.indexOf("转");
					if(lineNum == -1){
						lineNum = weather.indexOf("到");
					}
					if(lineNum>=0){
						System.out.println(1);
						weather = weather.substring(0,lineNum);
					}
				}
				mapData.put("weatherStr", weather);
				//System.out.println("xc:"+xc.toString()+" --- tq:"+tq.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapData;
	}
	@Override
	public boolean delCusCar(long id) {
		boolean bool = false;
		try {
			carInfoDao.delete(id);
			bool = true;
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;		
	}

	@Override
	public PubLine getPubLine(long lineId) {
		return carInfoDao.getPubLine(lineId);
	}

	
	@Override
	public PubCarinfo afterSaleCar(long cusId) {
		PubCarinfo carInfo = carInfoDao.getDefaultCarId(cusId);
		if(carInfo.getId() == null) {
			return null;
		}else{
			Date date = new Date();
			if(carInfo.getFinsexpire() != null) carInfo.setBxianDay((int)DateUtils.getDistanceOfTwoDate(date,carInfo.getFinsexpire()));
			if(carInfo.getFguaexpire() != null) carInfo.setBxiuDay((int)DateUtils.getDistanceOfTwoDate(date, carInfo.getFguaexpire()));
			if(carInfo.getFyearinsexpire() != null) carInfo.setNjianDay((int)DateUtils.getDistanceOfTwoDate(date, carInfo.getFyearinsexpire()));
			if(carInfo.getMaintianTime() != null) carInfo.setByangDay((int)DateUtils.getDistanceOfTwoDate(date, carInfo.getMaintianTime()));
			return carInfo;
		}
	}

	@Override
	public Map<String, Object> findMyCarByCarId(String carId) {
		return carInfoDao.findMyCarByCarId(carId);
	}

	/**
	 * 根据车辆id获取该车辆的车型名称
	 *@author sududa
	 *@date 2016年10月8日
	 * @param carinfoId
	 * @return
	 */
	@Override
	public Map<String, Object> findModelById(Serializable carinfoId) {
		return carInfoDao.findModelById(carinfoId);
	}

	/**
	 * 查询附近的车列表
	 * @author sududa
	 * @date 2016年10月28日
	 */
	@Override
	//@Cacheable(value="nearbyCarCache",key="#carLocation.cusId")
	public List<Map<String, Object>> doList(Integer page, Integer rows, CarLocation carLocation, String defCarId) {
		Map<String, Object> carLocationMap = carLocationDao.findByCusId(carLocation.getCusId()+"");
		List<Map<String, Object>> resultList = null;
		 if(StringUtil.isNotEmpty(carLocationMap)){//表示已经存入了,不能重复插入
			 resultList = carLocationDao.findListBynotSelfWithParams(page,rows,carLocation);
			//处理距离
			 if(StringUtil.isNotEmpty(resultList) && resultList.size() > 0){
				 Double distance = null;
				 for (Map<String, Object> map : resultList) {
					 map.put("DISTANCE", "");
					 if(StringUtil.isnotObjectsNotEmpty(carLocation.getLng(),carLocation.getLat())){
						 String lng = map.get("LNG")+"";
							String lat = map.get("LAT")+"";
							if(StringUtil.isnotObjectsNotEmpty(lng,lat)){
								distance = MapDistanceUtil.getDistance(carLocation.getLng(), carLocation.getLat(), Double.valueOf(lng), Double.valueOf(lat));
								map.put("DISTANCE", distance);
							} 
					 }
				}
			 }
			 return resultList;
		 }else{//表示没有存入,存入插入的操作
			//第一步保存自己的信息到车辆位置表
				Map<String, Object> ssclxxMap = cnSsclxxDao.findFirstOneByCarId(carLocation.getDefCarId());
				String totalMileage = "";//总里程
				if(StringUtil.isNotEmpty(ssclxxMap)){
					totalMileage = ssclxxMap.get("totalMileage")+"";
				}
			    List<Map<String, Object>> xcRecodeList = cnXcrecodeDao.findListByCarIdAndDate(carLocation.getDefCarId());
			    Float todayMileage = 0F;//当天的行驶总里程
			    Float todayOil = 0F;//当天的行驶总油耗
			    Float averageOil = 0F;//百公里的平均油耗
			    if(StringUtil.isNotEmpty(xcRecodeList) || xcRecodeList.size() > 0){
			    }else{//表示今天没有行程，得重新查询得到平均油耗
			    	xcRecodeList = cnXcrecodeDao.findByCarIdWithPage(page,rows,defCarId);
			    }
			    if(StringUtil.isNotEmpty(xcRecodeList) && xcRecodeList.size() > 0){
			    	for (Map<String, Object> map : xcRecodeList) {
						if(StringUtil.isNotEmpty(map)){
							String bcxslc = map.get("FBCXSLC")+"";
							String bcxsyh = map.get("FBCXSYH")+"";
							if(StringUtil.isNotEmpty(bcxslc) && StringUtil.isNotEmpty(bcxsyh)){
								todayMileage += Float.valueOf(bcxslc);
								todayOil += Float.valueOf(bcxsyh);
								averageOil += (Float.valueOf(bcxsyh)*100)/Float.valueOf(bcxslc);
							}
						}
					}
			    	averageOil = averageOil/Float.valueOf(xcRecodeList.size());
			    }
		    	//第二步查询车辆位置信息的列表
			    carLocation.setTotalMileage(totalMileage);
			    if(todayMileage != 0){
			    	carLocation.setTodayMileage(todayMileage+"");
			    }
			    if(todayOil != 0){
			    	carLocation.setTodayOil(todayOil+"");
			    }
			    if(averageOil != 0){
			    	carLocation.setAverageOil(averageOil+"");
			    }
			    carLocation.setCreatetime(DateUtil.getCurrentDate());
			    CarLocation resultentity = carLocationDao.save(carLocation);
			    if(StringUtil.isNotEmpty(resultentity)){
					resultList = carLocationDao.findListBynotSelf(page,rows,carLocation.getCusId()+"");
				}
		 }
		 //处理距离
		 if(StringUtil.isNotEmpty(resultList) && resultList.size() > 0){
			 Double distance = null;
			 for (Map<String, Object> map : resultList) {
				 map.put("DISTANCE", "");
				 if(StringUtil.isnotObjectsNotEmpty(carLocation.getLng(),carLocation.getLat())){
					 String lng = map.get("LNG")+"";
						String lat = map.get("LAT")+"";
						if(StringUtil.isnotObjectsNotEmpty(lng,lat)){
							distance = MapDistanceUtil.getDistance(carLocation.getLng(), carLocation.getLat(), Double.valueOf(lng), Double.valueOf(lat));
							map.put("DISTANCE", distance);
						} 
				 }
			}
		 }
		return resultList;
	}

	/**
	 * 根据客户的id清除客户默认车辆的位置信息
	 * @author sududa
	 * @date 2016年11月7日
	 */
	@Override
//	@CacheEvict(value="nearbyCarCache",key="#cusId")
	public Integer doCleanCarLocation(String cusId) {
		Map<String, Object> carLocationMap = carLocationDao.findByCusId(cusId);
		if(StringUtil.isNotEmpty(carLocationMap)){//表示还没有清除
			Integer delResult = carLocationDao.doDelByCusId(cusId);
			return delResult;
		}else{//表示已清除了
			return 1;
		}
	}

	/**
	 * 根据客户的默认车辆查询自己当天的车辆信息
	 * @author sududa
	 * @date 2016年11月10日
	 */
	@Override
	public Map<String, Object> QueryMyCarLocation(String cusId, String defCarId) {
		return carLocationDao.findByCusId(cusId);
	}

	/**
	 * 测试批量插入
	 * @author sududa
	 * @date 2016年11月17日
	 */
	@Override
	public void testSaveBatch(List<TestInsert> testInserts,Integer rows) {
		testInsertDao.testSaveBatch(testInserts,rows);
	}

	/**
	 * 测试单条插入
	 * @author sududa
	 * @date 2016年11月18日
	 * @param testInsert
	 * @return void
	 */
	@Override
	public void saveOne(TestInsert testInsert) {
		testInsertDao.save(testInsert);
	}

	
	
}
